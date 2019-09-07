package com.kazi.test.data.db.entities

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
) {
    @PrimaryKey(autoGenerate = true)
    var uid: Int = 0
}
