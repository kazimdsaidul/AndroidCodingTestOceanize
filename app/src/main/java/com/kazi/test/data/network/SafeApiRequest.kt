package com.kazi.test.data.network


import com.kazi.test.utils.exception.ApiException
import org.json.JSONException
import org.json.JSONObject
import retrofit2.Response

/**
 * Created by Kazi Md. Saidul Email: Kazimdsaidul@gmail.com  Mobile: +8801675349882 on 2019-09-04.
 */
open class SafeApiRequest {

    suspend fun <T : Any> apiRequest(call: suspend () -> Response<T>): T {
        val response = call.invoke()
        if (response.isSuccessful) {
            return response.body()!!
        } else {
            val error = response.errorBody()?.string()
            val message = StringBuilder()
            error?.let {
                try {
                    message.append(JSONObject(it).getString("message"))
                } catch (e: JSONException) {

                }
                message.append("\n")

            }
            message.append("Error code: ${response.code()}")
            throw  ApiException(message = message.toString())
        }
    }
}