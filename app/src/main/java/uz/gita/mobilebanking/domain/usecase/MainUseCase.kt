package uz.gita.mobilebanking.domain.usecase

import kotlinx.coroutines.flow.Flow
import uz.gita.mobilebanking.data.retrofit.request.DeleteCardRequest
import uz.gita.mobilebanking.data.retrofit.request.ResetRequest
import uz.gita.mobilebanking.data.retrofit.response.CardData

interface MainUseCase {
    fun getToken(): String

    fun resetUser(request: ResetRequest): Flow<Result<Unit>>
    fun logoutUser(): Flow<Result<Unit>>

    fun cardDelete(request: DeleteCardRequest): Flow<Result<Unit>>
    fun cardGet(): Flow<Result<List<CardData>>>
}