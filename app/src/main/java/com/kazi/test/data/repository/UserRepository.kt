package com.kazi.test.data.repository


import com.kazi.test.data.db.AppDatabase
import com.kazi.test.data.db.entities.MovieResultsItem
import com.kazi.test.data.db.entities.PopularMovieListResponse
import com.kazi.test.data.db.entities.ResponseMovieDetails
import com.kazi.test.data.network.APIService
import com.kazi.test.data.network.SafeApiRequest

/**
 * Created by Kazi Md. Saidul Email: Kazimdsaidul@gmail.com  Mobile: +8801675349882 on 2019-09-03.
 */
class UserRepository(
    private val apiService: APIService,
    private val db: AppDatabase



) : SafeApiRequest() {

    suspend fun getPopularMovieAPI(page: Int): PopularMovieListResponse {

        return apiRequest { apiService.getEmployees(APIService.API_KEY, page) }
    }

    suspend fun getMovieDetailsAPI(movieId: Int?): ResponseMovieDetails {

        return apiRequest { apiService.getMovieDetailsAPI(movieId, APIService.API_KEY) }
    }


    suspend fun saveAllMovieList(user: List<MovieResultsItem>) = db.getUserDao().insert(user)


    suspend fun getMoviceFromLocalDB(): List<MovieResultsItem> {
        return db.getUserDao().getAllEmployee()
    }

    suspend fun update(employee: MovieResultsItem): Int {
        return db.getUserDao().update(employee)
    }
}