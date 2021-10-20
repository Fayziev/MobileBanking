package uz.gita.mobilebanking.data.retrofit.api

import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.Header
import retrofit2.http.POST
import uz.gita.mobilebanking.data.retrofit.request.LoginRequest
import uz.gita.mobilebanking.data.retrofit.request.RegisterRequest
import uz.gita.mobilebanking.data.retrofit.request.VerifyRequest
import uz.gita.mobilebanking.data.retrofit.response.LoginResponse
import uz.gita.mobilebanking.data.retrofit.response.LogoutResponse
import uz.gita.mobilebanking.data.retrofit.response.RegisterResponse
import uz.gita.mobilebanking.data.retrofit.response.VerifyResponse

interface AuthApi {

    @POST("/api/v1/auth/login")
    suspend fun login(@Body data: LoginRequest): Response<LoginResponse>

    @POST("/api/v1/auth/logout")
    suspend fun logout(@Header("token") token: String): Response<LogoutResponse>

    @POST("/api/v1/auth/register")
    suspend fun register(@Body data: RegisterRequest): Response<RegisterResponse>

    @POST("/api/v1/auth/verify")
    suspend fun verify(@Body data: VerifyRequest): Response<VerifyResponse>
}