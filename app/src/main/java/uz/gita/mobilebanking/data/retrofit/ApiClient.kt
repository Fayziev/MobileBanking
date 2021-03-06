package uz.gita.mobilebanking.data.retrofit

import com.google.gson.Gson
import com.jakewharton.retrofit2.adapter.kotlin.coroutines.CoroutineCallAdapterFactory
import com.readystatesoftware.chuck.ChuckInterceptor
import okhttp3.Interceptor
import okhttp3.MediaType.Companion.toMediaTypeOrNull
import okhttp3.OkHttpClient
import okhttp3.RequestBody.Companion.toRequestBody
import okhttp3.logging.HttpLoggingInterceptor
import org.json.JSONObject
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import uz.gita.mobilebanking.BuildConfig.BASE_URL
import uz.gita.mobilebanking.BuildConfig.LOGGING
import uz.gita.mobilebanking.app.App
import uz.gita.mobilebanking.data.pref.MyPref
import uz.gita.mobilebanking.data.retrofit.response.VerifyResponse
import uz.gita.mobilebanking.utils.myLog
import uz.gita.mobilebanking.utils.timber

object ApiClient {

    val retrofit: Retrofit = Retrofit.Builder()
        .baseUrl(BASE_URL)
        .addConverterFactory(GsonConverterFactory.create())
        .addCallAdapterFactory(CoroutineCallAdapterFactory())
        .client(getHttpClient())
        .build()

    private fun getHttpClient(): OkHttpClient {
        return OkHttpClient.Builder()
            .addLogging()
            .addInterceptor(refreshInterceptor())
            .addInterceptor(tokenInterceptor())
            .build()
    }

    private fun OkHttpClient.Builder.addLogging(): OkHttpClient.Builder {
        if (LOGGING) {
            val logging = HttpLoggingInterceptor(object : HttpLoggingInterceptor.Logger {
                override fun log(message: String) {
                    timber(message, "HTTP")
                }
            })
            logging.level = HttpLoggingInterceptor.Level.BODY
            addNetworkInterceptor(logging)
            addNetworkInterceptor(ChuckInterceptor(App.instance))
        }
        return this
    }

    fun tokenInterceptor() = Interceptor {
        val request = it.request()
        val pref = MyPref()
        val newRequest = request.newBuilder().removeHeader("token").addHeader("token", pref.accessToken).build()
        val response = it.proceed(newRequest)
        response
    }

    private fun refreshInterceptor() = Interceptor { chain ->
        val request = chain.request()
        val response = chain.proceed(request)
        if (response.code == 401) {
            response.close()
            val pref = MyPref()
            val data = JSONObject()
            data.put("phone", "+998900212303")
            val body =
                data.toString().toRequestBody("application/json; charset=utf-8".toMediaTypeOrNull())

            val requestRefresh = request.newBuilder()
                .addHeader("refresh_token", pref.refreshToken)
                .method("POST", body)
                .url("${BASE_URL}/api/v1/auth/refresh")
                .build()

            val responseRefresh = chain.proceed(requestRefresh)
            myLog(responseRefresh.message)
            if (responseRefresh.code == 401) {
                myLog("login navigate")
                return@Interceptor responseRefresh
            }

            if (responseRefresh.code == 200) {
                responseRefresh.body.let {
                    val data = Gson().fromJson(it?.string(), VerifyResponse::class.java)
                    pref.accessToken = data.data.accessToken
                    pref.refreshToken = data.data.refreshToken
                }
                responseRefresh.close()
                val requestTwo = request.newBuilder()
                    .removeHeader("token")
                    .addHeader("token", pref.accessToken)
                    .build()
                chain.proceed(requestTwo)
            }
        }
        response
    }
}