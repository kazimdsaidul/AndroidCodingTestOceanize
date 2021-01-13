package com.kazi.test.ui.popularMovieList

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.kazi.test.data.db.entities.PopularMovieListResponse
import com.kazi.test.data.db.entities.MovieResultsItem
import com.kazi.test.data.repository.UserRepository
import com.kazi.test.ui.popularMovieList.view.IVIewEmployerList
import com.kazi.test.utils.Coroutines
import com.kazi.test.utils.exception.ApiException
import com.kazi.test.utils.exception.NoInternetException

class MovieListViewModel(val repository: UserRepository) : ViewModel() {


    private lateinit var employees: PopularMovieListResponse
    var listOfEmployees: MutableLiveData<List<MovieResultsItem>> = MutableLiveData()
    var mEmployee: MutableLiveData<MovieResultsItem> = MutableLiveData()

    private val _text = MutableLiveData<String>().apply {
        value = "This is dashboard Fragment"
    }
    val text: LiveData<String> = _text

    var view: IVIewEmployerList? = null



    fun getEmployeesList() {
        view?.showProgress()
        Coroutines.main {
            try {
                val employeesLocal = repository.getEmployeesLocal()
                if (employeesLocal.size != 0) {
                    listOfEmployees.value = employeesLocal
                } else {
                     employees = repository.getEmployeesAPI()
                    Log.e("", "")
                    repository.saveAllEmployee(employees.results as List<MovieResultsItem>)
                    listOfEmployees.value = employees.results as List<MovieResultsItem>?
                }

            } catch (e: ApiException) {
                view?.onFailure(e.message)
            } catch (e: NoInternetException) {
                view?.noInternetConnectionFound()
            }

        }


    }

    fun onItemClick(movieItem: MovieResultsItem) {
        view?.openEmpDetailsActivity(movieItem)
    }

}