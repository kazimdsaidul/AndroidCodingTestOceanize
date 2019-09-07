package com.kazi.test.data.repository


import com.kazi.test.data.db.AppDatabase
import com.kazi.test.data.db.entities.Employee
import com.kazi.test.data.network.APIService
import com.kazi.test.data.network.SafeApiRequest

/**
 * Created by Kazi Md. Saidul Email: Kazimdsaidul@gmail.com  Mobile: +8801675349882 on 2019-09-03.
 */
class UserRepository(
    private val apiService: APIService,
    private val db: AppDatabase

) : SafeApiRequest() {

    suspend fun getEmployees(): List<Employee> {
        return apiRequest { apiService.getEmployees() }
    }

    suspend fun saveUser(user: List<Employee>) = db.getUserDao().insert(user)


}