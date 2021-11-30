package uz.gita.mobilebanking.domain.usecase.impl

import kotlinx.coroutines.flow.Flow
import uz.gita.mobilebanking.data.retrofit.request.RegisterRequest
import uz.gita.mobilebanking.domain.AuthRepository
import uz.gita.mobilebanking.domain.usecase.RegisterUseCase
import javax.inject.Inject

class RegisterUseCaseImpl @Inject constructor(private val repository: AuthRepository) : RegisterUseCase {
    override fun registerUser(register: RegisterRequest): Flow<Result<Unit>> = repository.registerUser(register)
}