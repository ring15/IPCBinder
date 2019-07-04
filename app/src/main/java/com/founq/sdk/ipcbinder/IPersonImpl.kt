package com.founq.sdk.ipcbinder

/**
 * Created by ring on 2019/7/4.
 */
class IPersonImpl : IPersonAidlInterface.Stub() {

    private var name: String? = null
    private var age: Int = 0

    override fun setName(name: String?) {
        this.name = name
    }

    override fun setAge(age: Int) {
        this.age = age
    }

    override fun getInfo(): String {
        return "My name is $name, age is  $age!"
    }

    override fun basicTypes(anInt: Int, aLong: Long, aBoolean: Boolean, aFloat: Float, aDouble: Double, aString: String?) {
    }

}