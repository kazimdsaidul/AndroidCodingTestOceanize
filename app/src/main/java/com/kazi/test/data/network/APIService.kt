package com.kazi.test.data.network

import com.itkacher.okhttpprofiler.OkHttpProfilerInterceptor
import com.kazi.test.BuildConfig
import com.kazi.test.data.db.entities.Employee
import com.kazi.test.data.network.interceptor.NetworkConnectionInterceptor
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET


/**
 * Created by Kazi Md. Saidul Email: Kazimdsaidul@gmail.com  Mobile: +8801675349882 on 7/29/18.
 */
public interface APIService {

    companion object {
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
                .baseUrl("http://dummy.restapiexample.com/")
                .addConverterFactory(GsonConverterFactory.create())
                .build()
                .create(APIService::class.java)
        }
    }


    @GET("api/v1/employees")
    suspend fun getEmployees(
    ): Response<List<Employee>>


}