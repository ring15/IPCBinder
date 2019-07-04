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
        iPersonImpl.setAge(21)
        iPersonImpl.setName("loop")
        Log.i("test1", iPersonImpl.info)
    }
}
