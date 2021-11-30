package uz.gita.mobilebanking.data.pref

import android.content.Context.MODE_PRIVATE
import uz.gita.mobilebanking.app.App
import uz.gita.mobilebanking.data.models.StartScreenEnum
import uz.gita.mobilebanking.data.models.getStartScreen
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class MyPref @Inject constructor() {

    companion object {
        private const val ACCESS_TOKEN = "accessToken"
        private const val REFRESH_TOKEN = "refreshToken"
        private const val START_SCREEN = "screen"
    }

    private val pref = App.instance.getSharedPreferences("cache", MODE_PRIVATE)

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

    var firstName: String
        set(value) = pref.edit().putString("firstName", value).apply()
        get() = pref.getString("firstName", "")!!

    var lastName: String
        set(value) = pref.edit().putString("lastName", value).apply()
        get() = pref.getString("lastName", "")!!

    var phoneNumber: String
        set(value) = pref.edit().putString("phoneNumber", value).apply()
        get() = pref.getString("phoneNumber", "")!!

    var smsCode: String
        set(value) = pref.edit().putString("smsCode", value).apply()
        get() = pref.getString("smsCode", "")!!

    var controlRegister: Boolean
        get() = pref.getBoolean("controlRegister", false)
        set(value) = pref.edit().putBoolean("controlRegister", value).apply()


}