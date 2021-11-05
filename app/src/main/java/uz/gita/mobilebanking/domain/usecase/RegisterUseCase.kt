package uz.gita.mobilebanking.domain.usecase

import kotlinx.coroutines.flow.Flow
import uz.gita.mobilebanking.data.retrofit.request.RegisterRequest

interface RegisterUseCase {
    fun registerUser(register: RegisterRequest): Flow<Result<Unit>>
}