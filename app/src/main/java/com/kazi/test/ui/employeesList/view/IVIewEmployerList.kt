package com.kazi.test.ui.employeesList.view

import com.kazi.test.base.IView
import com.kazi.test.data.db.entities.Employee

/**
 * Created by Kazi Md. Saidul Email: Kazimdsaidul@gmail.com  Mobile: +8801675349882 on 2019-09-07.
 */
interface IVIewEmployerList : IView {
    fun openEmpDetailsActivity(employee: Employee)
}