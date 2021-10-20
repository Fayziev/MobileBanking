package uz.gita.mobilebanking.app

import android.app.Application
import timber.log.Timber
import uz.gita.mobilebanking.BuildConfig

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
        instance = this
    }
}