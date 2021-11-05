package uz.gita.mobilebanking.domain.usecase

import kotlinx.coroutines.flow.Flow
import uz.gita.mobilebanking.data.retrofit.request.NewPasswordRequest

interface ResetUseCase {
    fun newPasswordUser(request: NewPasswordRequest): Flow<Result<Unit>>
}