package com.kazi.test.ui.employeesList.employeesViewModelFactory

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider

import com.kazi.test.data.repository.UserRepository
import com.kazi.test.ui.employeesList.EmployeesListViewModel

/**
 * Created by Kazi Md. Saidul Email: Kazimdsaidul@gmail.com  Mobile: +8801675349882 on 2019-09-04.
 */
class EmployeesViewModelFactory(private val repository: UserRepository) :
    ViewModelProvider.NewInstanceFactory() {

    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return EmployeesListViewModel(repository) as T
    }

}