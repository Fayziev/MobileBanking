package uz.gita.mobilebanking.domain.usecase.impl

import kotlinx.coroutines.flow.Flow
import uz.gita.mobilebanking.data.retrofit.request.AddCardRequest
import uz.gita.mobilebanking.data.retrofit.request.VerifyCardRequest
import uz.gita.mobilebanking.domain.CardRepository
import uz.gita.mobilebanking.domain.usecase.AddCardUseCase
import javax.inject.Inject

class AddCardUseCaseImpl @Inject constructor(private val repository: CardRepository) : AddCardUseCase {
    override fun addCard(request: AddCardRequest): Flow<Result<Unit>> = repository.cardAdd(request)
    override fun verifyCard(request: VerifyCardRequest): Flow<Result<Unit>> = repository.cardVerify(request)

}