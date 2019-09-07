package com.kazi.test.data.db.entities

import android.os.Parcel
import android.os.Parcelable
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName


@Entity
data class Employee(

    @SerializedName("id")
    var id: String = "",

    @SerializedName("employee_name")
    var employeeName: String = "",
    @SerializedName("employee_salary")
    var employeeSalary: String = "",
    @SerializedName("employee_age")
    var employeeAge: String = "",
    @SerializedName("profile_image")
    var profileImage: String = ""
) : Parcelable {
    @PrimaryKey(autoGenerate = true)
    var uid: Int = 0

    constructor(source: Parcel) : this(
        source.readString(),
        source.readString(),
        source.readString(),
        source.readString(),
        source.readString()
    )

    override fun describeContents() = 0

    override fun writeToParcel(dest: Parcel, flags: Int) = with(dest) {
        writeString(id)
        writeString(employeeName)
        writeString(employeeSalary)
        writeString(employeeAge)
        writeString(profileImage)
    }

    companion object {
        @JvmField
        val CREATOR: Parcelable.Creator<Employee> = object : Parcelable.Creator<Employee> {
            override fun createFromParcel(source: Parcel): Employee = Employee(source)
            override fun newArray(size: Int): Array<Employee?> = arrayOfNulls(size)
        }
    }
}
