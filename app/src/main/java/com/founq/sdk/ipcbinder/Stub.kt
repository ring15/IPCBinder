package com.founq.sdk.ipcbinder

import android.os.*

/**
 * Created by ring on 2019/7/3.
 */
abstract class Stub : Binder(), BookManager {

    init {
        this.attachInterface(this, DESCRIPTOR)
    }

    override fun asBinder(): IBinder {
        return this
    }
    @Throws(RemoteException::class)
    override fun onTransact(code: Int, data: Parcel, reply: Parcel?, flags: Int): Boolean {//使自己的远程对象能接收到调用
        when (code) {

            IBinder.INTERFACE_TRANSACTION -> {
                reply!!.writeString(DESCRIPTOR)
                return true
            }

            TRANSAVTION_getBooks -> {
                data.enforceInterface(DESCRIPTOR)
                val result = this.getBooks()
                reply!!.writeNoException()
                reply.writeTypedList(result)
                return true
            }

            TRANSAVTION_addBook -> {
                data.enforceInterface(DESCRIPTOR)
                var arg0: Book? = null
                if (data.readInt() != 0) {
                    arg0 = Book.CREATOR.createFromParcel(data)
                }
                this.addBook(arg0!!)
                reply!!.writeNoException()
                return true
            }
        }
        return super.onTransact(code, data, reply, flags)
    }

    companion object {
        val DESCRIPTOR = "com.founq.sdk.ipcbinder.BookManager"
        val TRANSAVTION_getBooks = IBinder.FIRST_CALL_TRANSACTION
        val TRANSAVTION_addBook = IBinder.FIRST_CALL_TRANSACTION + 1

        fun asInterface(binder: IBinder?) : BookManager{
            val iInterface = binder!!.queryLocalInterface(DESCRIPTOR)//找到了就说明Binder是本地对象
            if (iInterface is BookManager){
                return iInterface
            }
            return Proxy(binder)//找不到说明是远程对象，需要创建代理对象实现
        }
    }

}