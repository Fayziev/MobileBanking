package uz.gita.mobilebanking.domain.impl

import com.google.gson.Gson
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import okhttp3.ResponseBody
import timber.log.Timber
import uz.gita.mobilebanking.data.retrofit.ApiClient
import uz.gita.mobilebanking.data.retrofit.api.AuthApi
import uz.gita.mobilebanking.data.retrofit.request.RegisterRequest
import uz.gita.mobilebanking.data.retrofit.response.RegisterResponse
import uz.gita.mobilebanking.domain.AuthRepository
import javax.inject.Inject

class AuthRepositoryImpl @Inject constructor() : AuthRepository {
    private val api = ApiClient.retrofit.create(AuthApi::class.java)
    private val gson = Gson()

    override fun registerUser(data: RegisterRequest): Flow<Result<String>> = flow {
        try {
            val response = api.register(data)
            if (response.code() == 200) {
                emit(Result.success(response.body()!!.message))
            } else {
                var st = "Serverga ulanishda xatolik bo'ldi"
                ResponseBody
                response.errorBody()?.let {
                    st = gson.fromJson(it.string(),RegisterResponse::class.java).message
                }
                emit(Result.failure<String>(Throwable(st)))
            }
        } catch (e: Exception) {
            Timber.tag("TTT").e(e.message)
            emit(Result.failure<String>(Throwable("Serverga ulanishda xatolik boldi")))
        }
    }.flowOn(Dispatchers.Default)

}