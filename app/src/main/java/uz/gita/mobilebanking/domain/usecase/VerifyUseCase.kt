package uz.gita.mobilebanking.domain.usecase

import kotlinx.coroutines.flow.Flow
import uz.gita.mobilebanking.data.retrofit.request.VerifyRequest

interface VerifyUseCase {
    fun verifyUser(request: VerifyRequest): Flow<Result<Unit>>

    fun setScreen()
}