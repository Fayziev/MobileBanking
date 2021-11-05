package uz.gita.mobilebanking.domain.usecase.impl

import uz.gita.mobilebanking.data.models.StartScreenEnum
import uz.gita.mobilebanking.domain.AppRepository
import uz.gita.mobilebanking.domain.usecase.SplashUseCase

class SplashUseCaseImpl constructor(private val repository: AppRepository) : SplashUseCase {
    override fun startScreen(): StartScreenEnum = repository.startScreen()
}