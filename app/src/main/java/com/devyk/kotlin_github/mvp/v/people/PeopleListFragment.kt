package com.devyk.kotlin_github.mvp.v.people

import com.devyk.kotlin_github.mvp.m.entity.User
import com.devyk.common.ext.bindArgument
import com.devyk.kotlin_github.adapter.PeopleListAdapter
import com.devyk.kotlin_github.mvp.base.CommonListFragment
import com.devyk.kotlin_github.mvp.p.PeopleListPresenter

/**
 * <pre>
 *     author  : devyk on 2019-11-04 11:07
 *     blog    : https://juejin.im/user/578259398ac2470061f3a3fb/posts
 *     github  : https://github.com/yangkun19921001
 *     mailbox : yang1001yk@gmail.com
 *     desc    : This is PeopleListFragment
 * </pre>
 */
class PeopleListFragment : CommonListFragment<User, PeopleListPresenter>() {


    //添加适配器
    override val adapter = PeopleListAdapter()

    //拿到登录信息
    val login: String by bindArgument("login")

    //拿到类型
    val type: String by bindArgument("type")


}