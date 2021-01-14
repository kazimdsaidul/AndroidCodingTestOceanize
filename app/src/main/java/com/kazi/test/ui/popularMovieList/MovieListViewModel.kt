package com.kazi.test.ui.popularMovieList

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.kazi.test.data.db.entities.MovieResultsItem
import com.kazi.test.data.db.entities.PopularMovieListResponse
import com.kazi.test.data.db.entities.ResponseMovieDetails
import com.kazi.test.data.repository.UserRepository
import com.kazi.test.ui.popularMovieList.view.IVIewEmployerList
import com.kazi.test.utils.Coroutines
import com.kazi.test.utils.exception.ApiException
import com.kazi.test.utils.exception.NoInternetException

class MovieListViewModel(val repository: UserRepository) : ViewModel() {


    var popularMovieListResponse: PopularMovieListResponse? = null
    var listOfEmployees: MutableLiveData<List<MovieResultsItem>> = MutableLiveData()
    var movieDetails: MutableLiveData<ResponseMovieDetails> = MutableLiveData()

    private val _text = MutableLiveData<String>().apply {
        value = "This is dashboard Fragment"
    }
    val text: LiveData<String> = _text

    var view: IVIewEmployerList? = null


    fun getMovieList(page: Int) {
        view?.showProgress()
        Coroutines.main {
            try {
                val employeesLocal = repository.getMoviceFromLocalDB()
                if (employeesLocal.size != 0) {
                    listOfEmployees.value = employeesLocal
                } else {

                    var page1 = popularMovieListResponse?.page ?: 0
                    page1 = page1.plus(1)

                    popularMovieListResponse = page1?.let { repository.getPopularMovieAPI(it) }
                    Log.e("", "")
                    repository.saveAllMovieList(popularMovieListResponse?.results as List<MovieResultsItem>)
                    listOfEmployees.value =
                        popularMovieListResponse?.results as List<MovieResultsItem>?
                    view?.hiddenProgress()
                }

            } catch (e: ApiException) {
                view?.hiddenProgress()
                view?.onFailure(e.message)
            } catch (e: NoInternetException) {
                view?.hiddenProgress()
                view?.noInternetConnectionFound()
            }

        }


    }

    fun onItemClick(movieItem: MovieResultsItem) {
        view?.openEmpDetailsActivity(movieItem)
    }

    fun getMovieDetails(m: MovieResultsItem) {
        view?.showProgress()
        Coroutines.main {
            try {

                movieDetails.value = repository.getMovieDetailsAPI(m.id)

                view?.hiddenProgress()

            } catch (e: ApiException) {
                view?.hiddenProgress()
                view?.onFailure(e.message)
            } catch (e: NoInternetException) {
                view?.hiddenProgress()
                view?.noInternetConnectionFound()
            }

        }

    }

    fun loadNextDataFromApi(page: Int) {

    }

}