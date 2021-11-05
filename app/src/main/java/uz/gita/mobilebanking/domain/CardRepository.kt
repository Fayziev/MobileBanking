package uz.gita.mobilebanking.domain

import kotlinx.coroutines.flow.Flow
import uz.gita.mobilebanking.data.retrofit.request.AddCardRequest
import uz.gita.mobilebanking.data.retrofit.request.DeleteCardRequest
import uz.gita.mobilebanking.data.retrofit.request.EditCardRequest
import uz.gita.mobilebanking.data.retrofit.request.VerifyCardRequest
import uz.gita.mobilebanking.data.retrofit.response.CardData

interface CardRepository {
    fun cardAdd(request: AddCardRequest): Flow<Result<Unit>>

    fun cardVerify(request: VerifyCardRequest): Flow<Result<Unit>>

    fun cardEdit(request: EditCardRequest): Flow<Result<Unit>>

    fun cardDelete(request: DeleteCardRequest): Flow<Result<Unit>>

    fun cardGet(): Flow<Result<List<CardData>>>
}