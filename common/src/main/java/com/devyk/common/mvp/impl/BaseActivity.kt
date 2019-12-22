
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import java.lang.reflect.ParameterizedType
import java.lang.reflect.Type
import kotlin.reflect.KClass
import kotlin.reflect.full.isSubclassOf
import kotlin.reflect.full.primaryConstructor
import kotlin.reflect.jvm.jvmErasure
//class A{
//    open val a: String= ""
//    open val b: String= ""
//    // 没有open关键字无法被其子类重写
//    val c: String= ""
//    open fun function(){
//
//    }
//}


//class B : A{
//    // 注意：如果想要类B内的父类属性不可以被其子类所重写，需要标注上final关键字
//// 没加final可以被其子类重写
//    override val a: String= ""
//    // 有加final不可以被其子类重写
//    final override val b: String= ""
//    final override fun function(){
//
//    }
//}

abstract class BaseActivity<out P : BasePresenter<BaseActivity<P>>> : AppCompatActivity(), IMvpView<P> {

    protected val TAG = javaClass.simpleName;

    final override val p: P

    init {
        p = createPresenterKt()
        p.v = this
    }

    private fun createPresenterKt(): P {
        sequence {
            var thisClass: KClass<*> = this@BaseActivity::class
            while (true) {
                yield(thisClass.supertypes)
                thisClass = thisClass.supertypes.firstOrNull()?.jvmErasure ?: break
            }
        }.flatMap { it ->
            it.flatMap { it.arguments }.asSequence()
        }.first {
                    it.type?.jvmErasure?.isSubclassOf(IPresenter::class) ?: false
                }.let {
                    return it.type!!.jvmErasure.primaryConstructor!!.call() as P
                }
    }

    private fun createPresenter(): P {
        sequence{
            var thisClass: Class<*> = this@BaseActivity.javaClass
            while (true) {
                yield(thisClass.genericSuperclass)
                thisClass = thisClass.superclass ?: break
            }
        }.filter {
            it is ParameterizedType
        }.flatMap {
                    (it as ParameterizedType).actualTypeArguments.asSequence()
                }.first {
                    it is Class<*> && IPresenter::class.java.isAssignableFrom(it)
                }.let {
                    return (it as Class<P>).newInstance()
                }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        p.onCreate(savedInstanceState)
    }

    override fun onViewStateRestored(savedInstanceState: Bundle?) {}

    override fun onStart() {
        super.onStart()
        p.onStart()
    }

    override fun onResume() {
        super.onResume()
        p.onResume()
    }

    override fun onPause() {
        super.onPause()
        p.onPause()
    }

    override fun onStop() {
        super.onStop()
        p.onStop()
    }

    override fun onDestroy() {
        p.onDestroy()
        super.onDestroy()
    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        p.onSaveInstanceState(outState)
    }

    override fun onRestoreInstanceState(savedInstanceState: Bundle?) {
        super.onRestoreInstanceState(savedInstanceState)
        onViewStateRestored(savedInstanceState)
        p.onViewStateRestored(savedInstanceState)
    }


}


