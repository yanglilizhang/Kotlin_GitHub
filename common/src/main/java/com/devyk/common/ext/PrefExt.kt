package com.devyk.common.ext

import com.devyk.common.App
import kotlin.reflect.jvm.jvmName


inline fun <reified R, T> R.pref(default: T) =
    Preference(App.getInstance(), "", default, R::class.jvmName)


//类的构造器
//类与成员的可见性
//类属性的延迟初始化
//代理 Delegate / 单例 object
//数据类 data class
//枚举类 enum class
//密封类 sealed class
//内联类 inline class