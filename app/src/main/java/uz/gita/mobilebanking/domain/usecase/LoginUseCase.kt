package uz.gita.mobilebanking.domain.usecase

import kotlinx.coroutines.flow.Flow
import uz.gita.mobilebanking.data.retrofit.request.LoginRequest
import uz.gita.mobilebanking.data.retrofit.request.ResetRequest

interface LoginUseCase {

    fun resetUser(request: ResetRequest): Flow<Result<Unit>>
    fun loginUser(request: LoginRequest): Flow<Result<Unit>>
}