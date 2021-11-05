package uz.gita.mobilebanking.data.retrofit.api

import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.Header
import retrofit2.http.POST
import uz.gita.mobilebanking.data.retrofit.request.*
import uz.gita.mobilebanking.data.retrofit.response.BasicResponse
import uz.gita.mobilebanking.data.retrofit.response.VerifyResponse

interface AuthApi {

    @POST("/api/v1/auth/login")
    suspend fun login(@Body data: LoginRequest): Response<BasicResponse>

    @POST("/api/v1/auth/logout")
    suspend fun logout(@Header("token") token: String): Response<BasicResponse>

    @POST("/api/v1/auth/register")
    suspend fun register(@Body data: RegisterRequest): Response<BasicResponse>

    @POST("/api/v1/auth/verify")
    suspend fun verify(@Body data: VerifyRequest): Response<VerifyResponse>

    @POST("/api/v1/auth/resend")
    suspend fun resend(@Body request: ResendRequest): Response<BasicResponse>

    @POST("/api/v1/auth/reset")
    suspend fun reset(@Body request: ResetRequest): Response<BasicResponse>

    @POST("/api/v1/auth/newpassword")
    suspend fun newpassword(@Body request: NewPasswordRequest): Response<BasicResponse>
}