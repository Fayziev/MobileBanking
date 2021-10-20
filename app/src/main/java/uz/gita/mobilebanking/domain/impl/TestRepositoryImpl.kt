package uz.gita.mobilebanking.domain.impl

import uz.gita.mobilebanking.data.models.StartScreenEnum
import uz.gita.mobilebanking.domain.AppRepository
import javax.inject.Inject

class TestRepositoryImpl @Inject constructor() : AppRepository {
    override fun startScreen()= StartScreenEnum.LOGIN


}