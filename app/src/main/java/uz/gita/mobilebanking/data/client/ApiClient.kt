package uz.gita.mobilebanking.data.client

import com.jakewharton.retrofit2.adapter.kotlin.coroutines.CoroutineCallAdapterFactory
import com.readystatesoftware.chuck.ChuckInterceptor
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import timber.log.Timber
import uz.gita.mobilebanking.BuildConfig.BASE_URL
import uz.gita.mobilebanking.BuildConfig.LOGGING
import uz.gita.mobilebanking.app.App

object ApiClient {

    val retrofit: Retrofit = Retrofit.Builder()
        .baseUrl(BASE_URL)
        .addConverterFactory(GsonConverterFactory.create())
        .addCallAdapterFactory(CoroutineCallAdapterFactory())
        .client(OkHttpClient.Builder().addLogging().build())
        .build()

    private fun OkHttpClient.Builder.addLogging(): OkHttpClient.Builder {
        if (LOGGING) {
            val logging = HttpLoggingInterceptor(object : HttpLoggingInterceptor.Logger {
                override fun log(message: String) {
                    Timber.tag("TTT").d(message, "HTTP")
                }
            })
            logging.level = HttpLoggingInterceptor.Level.BODY
            addNetworkInterceptor(logging)
            addNetworkInterceptor(ChuckInterceptor(App.instance))
        }
        return this
    }
}