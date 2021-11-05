package uz.gita.mobilebanking.domain.usecase

import uz.gita.mobilebanking.data.models.StartScreenEnum

interface SplashUseCase {
    fun startScreen(): StartScreenEnum
}