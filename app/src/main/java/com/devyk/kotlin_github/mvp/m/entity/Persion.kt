package com.devyk.kotlin_github.mvp.m.entity


class Persion constructor(name: String) {
    var name = name;

    init {
        print("---->$name")
    }

    constructor() : this("sss") {

    }
}

class Persion2 constructor(var name: String, age: Int) {
    //name自动创建属性并赋值
    var age = age

    init {
        print("------>")
    }

}
