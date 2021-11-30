package uz.gita.mobilebanking.data.retrofit.api

import okhttp3.MultipartBody
import retrofit2.Response
import retrofit2.http.*
import uz.gita.mobilebanking.data.retrofit.request.ProfileEditRequest
import uz.gita.mobilebanking.data.retrofit.response.ProfileEditResponse
import uz.gita.mobilebanking.data.retrofit.response.ProfileInfoResponse
import uz.gita.mobilebanking.data.retrofit.response.ResponseAvatar

interface ProfileApi {


    @Multipart
    @POST("/api/v1/profile/avatar")
    suspend fun setAvatar(@Part part: MultipartBody.Part): Response<ResponseAvatar>

    @GET("/api/v1/profile/avatar")
    suspend fun getAvatar(): Response<MultipartBody.Part>

    @PUT("/api/v1/profile")
    suspend fun editProfile(@Body data: ProfileEditRequest): Response<ProfileEditResponse>

    @GET("/api/v1/profile/info")
    suspend fun profileInfo(): Response<ProfileInfoResponse>

}