package uz.gita.mobilebanking

import uz.gita.mobilebanking.data.MyPref
import javax.inject.Inject

class AppRepositoryImpl @Inject constructor() : AppRepository {
    private val pref = MyPref.getPref()

    override fun startScreen(): StartScreenEnum = pref.startScreen
}