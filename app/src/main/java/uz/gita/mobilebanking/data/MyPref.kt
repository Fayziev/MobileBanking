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

}