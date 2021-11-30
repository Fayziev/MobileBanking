package uz.gita.mobilebanking.domain.usecase.impl

import kotlinx.coroutines.flow.Flow
import uz.gita.mobilebanking.data.retrofit.request.NewPasswordRequest
import uz.gita.mobilebanking.domain.AuthRepository
import uz.gita.mobilebanking.domain.usecase.ResetUseCase
import javax.inject.Inject

class ResetUseCaseImpl @Inject constructor(private val repository: AuthRepository) : ResetUseCase {
    override fun newPasswordUser(request: NewPasswordRequest): Flow<Result<Unit>> = repository.newPasswordUser(request)
}