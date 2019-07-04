package com.founq.sdk.ipcbinder

import android.app.Service
import android.content.Intent
import android.os.IBinder
import android.util.Log

class RemoteService : Service() {

    var books = ArrayList<Book>()

    override fun onBind(intent: Intent): IBinder {
        return bookManager
    }

    override fun onCreate() {
        super.onCreate()
        val book = Book()
        book.name = "三体"
        book.price = 88
        books.add(book)
    }

    val bookManager : Stub = object : Stub(){
        override fun getBooks(): List<Book> {
            synchronized(this){
                return books
            }
        }

        override fun addBook(book: Book) {
            synchronized(this){
                books.add(book)
                Log.e("Server", "books: $book")
            }
        }

    }
}
