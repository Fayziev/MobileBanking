package uz.gita.mobilebanking.domain.usecase

import kotlinx.coroutines.flow.Flow
import uz.gita.mobilebanking.data.retrofit.request.AddCardRequest
import uz.gita.mobilebanking.data.retrofit.request.VerifyCardRequest

interface AddCardUseCase {

    fun addCard(request: AddCardRequest): Flow<Result<Unit>>

    fun verifyCard(request: VerifyCardRequest): Flow<Result<Unit>>
}