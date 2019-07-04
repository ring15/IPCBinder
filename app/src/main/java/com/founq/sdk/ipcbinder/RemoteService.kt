package com.founq.sdk.ipcbinder

import android.app.Service
import android.content.Intent
import android.os.IBinder
import android.util.Log

class RemoteService : Service() {

    var iPersonImpl = IPersonImpl()

    override fun onBind(intent: Intent): IBinder {
        return iPersonImpl
    }

    override fun onCreate() {
        super.onCreate()
        var student = Student()
        student.name = "loop"
        student.id = 21
        iPersonImpl.addStudent(student)
        Log.i("test", iPersonImpl.studentList.get(0)!!.toString())
    }
}
