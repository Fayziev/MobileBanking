package uz.gita.mobilebanking.domain.impl

import com.google.gson.Gson
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import retrofit2.Response
import uz.gita.mobilebanking.data.pref.MyPref
import uz.gita.mobilebanking.data.retrofit.ApiClient
import uz.gita.mobilebanking.data.retrofit.api.AuthApi
import uz.gita.mobilebanking.data.retrofit.request.*
import uz.gita.mobilebanking.data.retrofit.response.BasicResponse
import uz.gita.mobilebanking.domain.AuthRepository
import uz.gita.mobilebanking.utils.timber
import javax.inject.Inject

class AuthRepositoryImpl @Inject constructor() : AuthRepository {
    private val api = ApiClient.retrofit.create(AuthApi::class.java)
    private val gson = Gson()
    private val pref = MyPref.getPref()

    override fun registerUser(request: RegisterRequest): Flow<Result<Unit>> = flow {
        emit(getResult(api.register(request)))
    }

    override fun loginUser(request: LoginRequest): Flow<Result<Unit>> = flow {
        emit(getResult(api.login(request)))
    }

    override fun logoutUser(token: String): Flow<Result<Unit>> = flow {
        emit(getResult(api.logout(token)))
    }

    override fun verifyUser(request: VerifyRequest): Flow<Result<Unit>> = flow {

        try {
            val response = api.verify(request)
            if (response.code() in 200..299) {
                response.body()?.let {
                    pref.refreshToken = it.data.refreshToken
                    pref.accessToken = it.data.accessToken
                }
                emit(Result.success(Unit))
            } else {
                emit(Result.failure(Throwable(response.errorBody().toString())))
            }
        } catch (e: Exception) {
            timber(e.message.toString())
            emit(Result.failure(Throwable("Server bilan muammo bo'ldi")))
        }
    }

    override fun resendUser(request: ResendRequest): Flow<Result<Unit>> = flow {
        emit(getResult(api.resend(request)))
    }


    override fun resetUser(request: ResetRequest): Flow<Result<Unit>> = flow {
        emit(getResult(api.reset(request)))
    }

    override fun newPasswordUser(request: NewPasswordRequest): Flow<Result<Unit>> = flow {
        emit(getResult(api.newpassword(request)))
    }

    private fun getResult(response: Response<BasicResponse>): Result<Unit> {
        return try {
            if (response.code() in 200..299) {
                Result.success(Unit)
            } else {
                Result.failure(Throwable(response.errorBody().toString()))
            }
        } catch (e: Exception) {
            timber(e.message.toString())
            Result.failure(Throwable(response.errorBody().toString()))
        }

    }

    private fun getResultWithToken(response: Response<BasicResponse>): Result<Unit> {
        return try {
            if (response.code() in 200..299) {
                pref.accessToken = response.body()!!.message.split(" ")[1]
                Result.success(Unit)
            } else {
                Result.failure(Throwable(response.errorBody().toString()))
            }
        } catch (e: Exception) {
            Result.failure<Unit>(Throwable("Server bilan muammo bo'ldi"))
        }
    }

}