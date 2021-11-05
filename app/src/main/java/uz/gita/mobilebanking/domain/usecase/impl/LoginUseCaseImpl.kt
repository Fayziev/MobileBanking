package uz.gita.mobilebanking.domain.usecase.impl

import kotlinx.coroutines.flow.Flow
import uz.gita.mobilebanking.data.retrofit.request.LoginRequest
import uz.gita.mobilebanking.data.retrofit.request.ResetRequest
import uz.gita.mobilebanking.domain.AuthRepository
import uz.gita.mobilebanking.domain.usecase.LoginUseCase
import javax.inject.Inject

class LoginUseCaseImpl @Inject constructor(private val repository: AuthRepository) : LoginUseCase {
    override fun resetUser(request: ResetRequest): Flow<Result<Unit>> = repository.resetUser(request)

    override fun loginUser(request: LoginRequest): Flow<Result<Unit>> = repository.loginUser(request)
}