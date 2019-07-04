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

    lateinit var bookManager: BookManager
    var isConnection = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        btnAddBook.setOnClickListener {
            if (!isConnection){
                attemptToBindService()
            }
            val book = Book()
            book.price = 101
            book.name = "编码"
            bookManager.addBook(book)
            Log.d("main", "bookmanager:"+bookManager.getBooks().toString())
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
            bookManager = Stub.asInterface(service)
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
