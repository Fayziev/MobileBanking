package uz.gita.mobilebanking.domain

import kotlinx.coroutines.flow.Flow
import uz.gita.mobilebanking.data.retrofit.request.RegisterRequest

interface AuthRepository {
    fun registerUser(data : RegisterRequest) : Flow<Result<String>>
}