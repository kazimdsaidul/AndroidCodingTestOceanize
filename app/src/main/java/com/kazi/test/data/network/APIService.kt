package com.kazi.test.data.network

import androidx.databinding.library.BuildConfig
import com.itkacher.okhttpprofiler.OkHttpProfilerInterceptor
import com.kazi.test.data.db.entities.PopularMovieListResponse
import com.kazi.test.data.db.entities.ResponseMovieDetails
import com.kazi.test.data.network.interceptor.NetworkConnectionInterceptor
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query


/**
 * Created by Kazi Md. Saidul Email: Kazimdsaidul@gmail.com  Mobile: +8801675349882 on 7/29/18.
 */
public interface APIService {

    companion object {
        val API_KEY:String = "b5551849719b4d25eefef7ea2e1564a8"

        operator fun invoke(
            networkConnectionInterceptor: NetworkConnectionInterceptor
        ): APIService {

            val okkHttpclient = OkHttpClient.Builder()
                .addInterceptor(networkConnectionInterceptor)

            if (BuildConfig.DEBUG) {
                val logging = HttpLoggingInterceptor()
                logging.setLevel(HttpLoggingInterceptor.Level.BODY)
                okkHttpclient.addInterceptor(logging)
                okkHttpclient.addInterceptor(OkHttpProfilerInterceptor())
            }


            return Retrofit.Builder()
                .client(okkHttpclient.build())
                .baseUrl("https://api.themoviedb.org/")
                .addConverterFactory(GsonConverterFactory.create())
                .build()
                .create(APIService::class.java)
        }
    }


    @GET("3/movie/popular")
    suspend fun getEmployees(@Query("api_key") apiKey: String): Response<PopularMovieListResponse>


    @GET("3/movie/{movieId}")
    suspend fun getMovieDetailsAPI(
        @Path("movieId") movieId: Int?,
        @Query("api_key") apiKey: String?
    ): Response<ResponseMovieDetails>

}