package com.kazi.test.ui.employeesList

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.kazi.test.data.db.entities.Employee
import com.kazi.test.data.repository.UserRepository
import com.kazi.test.ui.employeesList.view.IVIewEmployerList
import com.kazi.test.utils.Coroutines
import com.kazi.test.utils.exception.ApiException
import com.kazi.test.utils.exception.NoInternetException

class EmployeesListViewModel(val repository: UserRepository) : ViewModel() {


    var listOfEmployees: MutableLiveData<List<Employee>> = MutableLiveData()
    private val _text = MutableLiveData<String>().apply {
        value = "This is dashboard Fragment"
    }
    val text: LiveData<String> = _text

    var iAuthListener: IVIewEmployerList? = null


    fun getEmployeesList() {
        iAuthListener?.showProgress()
        Coroutines.main {
            try {
                val employees = repository.getEmployees()
                listOfEmployees.value = employees
                Log.e("", "")

            } catch (e: ApiException) {
                iAuthListener?.onFailure(e.message)
            } catch (e: NoInternetException) {
                iAuthListener?.noInternetConnectionFound()
            }

        }


    }
}