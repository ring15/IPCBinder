package com.founq.sdk.ipcbinder

import android.os.Parcel
import android.os.Parcelable

/**
 * Created by ring on 2019/7/3.
 */
class Book() : Parcelable{

    override fun writeToParcel(dest: Parcel, flags: Int) {
        dest.writeInt(this.price)
        dest.writeString(this.name)
    }

    override fun describeContents(): Int {
        return 0
    }

    var price : Int = 0
    var name : String? = null

    constructor(parcel: Parcel) : this() {
        price = parcel.readInt()
        name = parcel.readString()
    }

    companion object CREATOR : Parcelable.Creator<Book> {
        override fun createFromParcel(parcel: Parcel): Book {
            return Book(parcel)
        }

        override fun newArray(size: Int): Array<Book?> {
            return arrayOfNulls(size)
        }
    }

    override fun toString(): String {
        return "Book{" +
                "price=" + price +
                ", name='" + name + '\''.toString() +
                '}'
    }
}
