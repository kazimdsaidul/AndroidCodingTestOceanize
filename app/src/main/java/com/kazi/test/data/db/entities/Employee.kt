package com.kazi.test.data.db.entities

import android.os.Parcel
import android.os.Parcelable
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName


@Entity
data class Employee(

    @PrimaryKey(autoGenerate = true)
    val uid: Long,

    @SerializedName("id")
    var id: String = "",

    @SerializedName("employee_name")
    var employeeName: String = "",
    @SerializedName("employee_salary")
    var employeeSalary: String = "",
    @SerializedName("employee_age")
    var employeeAge: String = "",
    @SerializedName("profile_image")
    var profileImage: String = "",

    var rating: Int = 0

) : Parcelable {
    constructor(parcel: Parcel) : this(
        parcel.readLong(),
        parcel.readString(),
        parcel.readString(),
        parcel.readString(),
        parcel.readString(),
        parcel.readString(),
        parcel.readInt()
    ) {
    }

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeLong(uid)
        parcel.writeString(id)
        parcel.writeString(employeeName)
        parcel.writeString(employeeSalary)
        parcel.writeString(employeeAge)
        parcel.writeString(profileImage)
        parcel.writeInt(rating)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<Employee> {
        override fun createFromParcel(parcel: Parcel): Employee {
            return Employee(parcel)
        }

        override fun newArray(size: Int): Array<Employee?> {
            return arrayOfNulls(size)
        }
    }
}

