package com.kazi.test.data.network.interceptor

import android.content.Context
import android.net.ConnectivityManager
import com.kazi.test.utils.exception.NoInternetException
import okhttp3.Interceptor
import okhttp3.Response

/**
 * Created by Kazi Md. Saidul Email: Kazimdsaidul@gmail.com  Mobile: +8801675349882 on 2019-09-04.
 */
class NetworkConnectionInterceptor(
    context: Context
) : Interceptor {

    private val applicationCon = context.applicationContext

    override fun intercept(chain: Interceptor.Chain): Response {
        if (!isInternetAvailable())
            throw NoInternetException("No Internet connection found")
        return chain.proceed(chain.request())
    }

    public fun isInternetAvailable(): Boolean {
        val connectivityManager =
            applicationCon.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager

        connectivityManager.activeNetworkInfo.also {
            return it != null && it.isConnected
        }

    }
}
