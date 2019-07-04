package com.founq.sdk.ipcbinder

import android.os.IBinder
import android.os.Parcel
import android.os.RemoteException

/**
 * Created by ring on 2019/7/3.
 */
class Proxy(private val remote : IBinder) : BookManager{

    val DESCRIPTOR = "com.founq.sdk.ipcbinder.BookManager"

    @Throws(RemoteException::class)
    override fun getBooks(): List<Book> {
        val data : Parcel = Parcel.obtain()
        val replay : Parcel = Parcel.obtain()
        val result :List<Book>
        try {
            data.writeInterfaceToken(DESCRIPTOR)
            remote.transact(Stub.TRANSAVTION_getBooks, data, replay, 0)//向远端发送出调用
            replay.readException()
            result = replay.createTypedArrayList(Book.CREATOR)
        } finally {
            replay.recycle()
            data.recycle()
        }
        return result
    }

    override fun addBook(book: Book) {
        val data = Parcel.obtain()
        val replay = Parcel.obtain()

        try {
            data.writeInterfaceToken(DESCRIPTOR)
            data.writeInt(1)
            book.writeToParcel(data, 0)
            remote.transact(Stub.TRANSAVTION_addBook, data, replay, 0)
            replay.readException()
        } finally {
            replay.recycle()
            data.recycle()
        }
    }

    override fun asBinder(): IBinder {
        return remote
    }

}