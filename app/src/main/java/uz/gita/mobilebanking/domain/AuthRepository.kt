package uz.gita.mobilebanking.domain

import kotlinx.coroutines.flow.Flow
import uz.gita.mobilebanking.data.retrofit.request.*

interface AuthRepository {

    fun registerUser(data: RegisterRequest): Flow<Result<Unit>>

    fun loginUser(data: LoginRequest): Flow<Result<Unit>>

    fun verifyUser(data: VerifyRequest): Flow<Result<Unit>>

    fun logoutUser(token: String): Flow<Result<Unit>>

    fun resendUser(request: ResendRequest): Flow<Result<Unit>>

    fun resetUser(request: ResetRequest): Flow<Result<Unit>>

    fun newPasswordUser(request: NewPasswordRequest): Flow<Result<Unit>>
}