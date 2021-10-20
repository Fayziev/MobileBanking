package uz.gita.mobilebanking.data.api

import retrofit2.Call
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.Header
import retrofit2.http.POST
import uz.gita.mobilebanking.data.request.LoginRequest
import uz.gita.mobilebanking.data.request.RegisterRequest
import uz.gita.mobilebanking.data.request.VerifyRequest
import uz.gita.mobilebanking.data.response.LoginResponse
import uz.gita.mobilebanking.data.response.LogoutResponse
import uz.gita.mobilebanking.data.response.RegisterResponse
import uz.gita.mobilebanking.data.response.VerifyResponse

interface AuthApi {

    @POST("/api/v1/auth/login")
    suspend fun login(@Body data: LoginRequest): Response<LoginResponse>

    @POST("/api/v1/auth/logout")
    fun logout(@Header("token") token: String): Call<LogoutResponse>

    @POST("/api/v1/auth/register")
    fun register(@Body data: RegisterRequest): Call<RegisterResponse>

    @POST("/api/v1/auth/verify")
    fun verify(@Body data: VerifyRequest): Call<VerifyResponse>
}