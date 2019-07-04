package com.founq.sdk.ipcbinder

import android.content.ComponentName
import android.content.Context
import android.content.Intent
import android.content.ServiceConnection
import androidx.appcompat.app.AppCompatActivity

import android.os.Bundle
import android.os.IBinder
import android.util.Log
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    var isConnection = false
    private lateinit var iPersonAidlInterface : IPersonAidlInterface

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        btnAddBook.setOnClickListener {
            if (!isConnection){
                attemptToBindService()
            }
            Log.i("test", iPersonAidlInterface.studentList.get(0)!!.toString())
            var student = Student()
            student.name = "ring"
            student.id = 23
            iPersonAidlInterface.addStudent(student)
            Log.i("test", iPersonAidlInterface.studentList.get(1)!!.toString())
        }
    }

    private fun attemptToBindService() {
        var intent = Intent(this, RemoteService::class.java)
        intent.action = "com.founq.sdk.ipcbinder"
        bindService(intent, serviceConnection, Context.BIND_AUTO_CREATE)
    }

    private val serviceConnection = object : ServiceConnection{
        override fun onServiceDisconnected(name: ComponentName?) {
            isConnection = false
        }

        override fun onServiceConnected(name: ComponentName?, service: IBinder?) {
            isConnection = true
            iPersonAidlInterface = IPersonAidlInterface.Stub.asInterface(service)
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        if (isConnection){
            unbindService(serviceConnection)
        }
    }

    override fun onStart() {
        super.onStart()
        if (!isConnection) {
            attemptToBindService()
        }
    }
}
