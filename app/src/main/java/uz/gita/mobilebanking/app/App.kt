package uz.gita.mobilebanking.app

import android.app.Application
import dagger.hilt.android.HiltAndroidApp
import timber.log.Timber
import uz.gita.mobilebanking.BuildConfig
import uz.gita.mobilebanking.data.pref.MyPref

@HiltAndroidApp
class App : Application() {

    companion object {
        lateinit var instance: App
            private set
    }

    override fun onCreate() {
        super.onCreate()
        if (BuildConfig.DEBUG) {
            Timber.plant(Timber.DebugTree())
        }
        MyPref.init(this)
        instance = this
    }
}