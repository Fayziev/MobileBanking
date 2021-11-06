package uz.gita.mobilebanking.domain.impl

import uz.gita.mobilebanking.data.pref.MyPref
import uz.gita.mobilebanking.data.models.StartScreenEnum
import uz.gita.mobilebanking.domain.AppRepository
import javax.inject.Inject

class AppRepositoryImpl @Inject constructor() : AppRepository {
    private val pref = MyPref.getPref()

    override fun startScreen(): StartScreenEnum = pref.startScreen

    override fun setStartScreen(screenEnum: StartScreenEnum) {
        pref.startScreen = screenEnum
    }

    override fun getToken(): String = pref.accessToken
}