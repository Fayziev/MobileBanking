package uz.gita.mobilebanking.data

import android.content.Context
import android.content.Context.MODE_PRIVATE
import uz.gita.mobilebanking.data.models.StartScreenEnum
import uz.gita.mobilebanking.data.models.getStartScreen

class MyPref private constructor(context: Context) {

    companion object {
        private lateinit var instance: MyPref

        fun init(context: Context) {
            instance = MyPref(context)
        }

        fun getPref(): MyPref = instance

        private const val ACCESS_TOKEN = "accessToken"
        private const val REFRESH_TOKEN = "refreshToken"
        private const val START_SCREEN = "screen"
    }

    private val pref = context.getSharedPreferences("cache", MODE_PRIVATE)

    var token: String
        set(value) {
            pref.edit().putString("token", value).apply()
        }
        get() = pref.getString("token", "")!!

    var startScreen: StartScreenEnum
        set(value) = pref.edit().putString("screen", value.name).apply()
        get() = pref.getString("screen", StartScreenEnum.LOGIN.name)!!.getStartScreen()

    var accessToken: String
        get() = pref.getString(ACCESS_TOKEN, "")!!
        set(token) = pref.edit().putString(ACCESS_TOKEN, token).apply()

    var refreshToken: String
        get() = pref.getString(REFRESH_TOKEN, "")!!
        set(token) = pref.edit().putString(REFRESH_TOKEN, token).apply()


}