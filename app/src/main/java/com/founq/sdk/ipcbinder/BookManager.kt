package com.founq.sdk.ipcbinder

import android.os.IInterface
import android.os.RemoteException

/**
 * Created by ring on 2019/7/3.
 */
interface BookManager : IInterface{
    @Throws(RemoteException::class)
    fun getBooks() : List<Book>

    @Throws(RemoteException::class)
    fun addBook(book: Book)
}