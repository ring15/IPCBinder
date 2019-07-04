package com.founq.sdk.ipcbinder

import android.os.Parcel
import android.os.Parcelable

/**
 * Created by ring on 2019/7/4.
 */
class Student() : Parcelable {
    var id = 0
    var name: String? = null

    constructor(parcel: Parcel) : this() {
        id = parcel.readInt()
        name = parcel.readString()
    }

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeInt(id)
        parcel.writeString(name)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<Student> {
        override fun createFromParcel(parcel: Parcel): Student {
            return Student(parcel)
        }

        override fun newArray(size: Int): Array<Student?> {
            return arrayOfNulls(size)
        }
    }

    override fun toString(): String {
        return "My name is $name, age is $id!"
    }
}