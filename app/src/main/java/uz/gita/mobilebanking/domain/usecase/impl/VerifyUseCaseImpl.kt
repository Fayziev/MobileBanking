package uz.gita.mobilebanking.domain.usecase.impl

import kotlinx.coroutines.flow.Flow
import uz.gita.mobilebanking.data.models.StartScreenEnum
import uz.gita.mobilebanking.data.retrofit.request.VerifyRequest
import uz.gita.mobilebanking.domain.AppRepository
import uz.gita.mobilebanking.domain.AuthRepository
import uz.gita.mobilebanking.domain.usecase.VerifyUseCase
import javax.inject.Inject

class VerifyUseCaseImpl @Inject constructor(
    private val repositoryAuth: AuthRepository,
    private val repositoryApp: AppRepository
) : VerifyUseCase {
    override fun verifyUser(request: VerifyRequest): Flow<Result<Unit>> = repositoryAuth.verifyUser(request)

    override fun setScreen() = repositoryApp.setStartScreen(StartScreenEnum.MAIN)

}