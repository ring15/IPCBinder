package com.founq.sdk.ipcbinder

import java.util.concurrent.CopyOnWriteArrayList

/**
 * Created by ring on 2019/7/4.
 */
class IPersonImpl : IPersonAidlInterface.Stub() {

    private var mStudentList : CopyOnWriteArrayList<Student> = CopyOnWriteArrayList()

    override fun getStudentList(): MutableList<Student> {
        return mStudentList
    }

    override fun addStudent(student: Student?) {
        mStudentList.add(student)
    }

    override fun basicTypes(anInt: Int, aLong: Long, aBoolean: Boolean, aFloat: Float, aDouble: Double, aString: String?) {
    }

}