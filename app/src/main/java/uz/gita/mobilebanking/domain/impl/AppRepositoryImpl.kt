package uz.gita.mobilebanking.domain.impl

import uz.gita.mobilebanking.data.models.StartScreenEnum
import uz.gita.mobilebanking.data.MyPref
import uz.gita.mobilebanking.domain.AppRepository
import javax.inject.Inject

class AppRepositoryImpl @Inject constructor() : AppRepository {
    private val pref = MyPref.getPref()

    override fun startScreen(): StartScreenEnum = pref.startScreen
}