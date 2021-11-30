package uz.gita.mobilebanking.data.retrofit.api

import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.POST
import uz.gita.mobilebanking.data.retrofit.request.AddCardRequest
import uz.gita.mobilebanking.data.retrofit.request.DeleteCardRequest
import uz.gita.mobilebanking.data.retrofit.request.EditCardRequest
import uz.gita.mobilebanking.data.retrofit.request.VerifyCardRequest
import uz.gita.mobilebanking.data.retrofit.response.AllCardResponse
import uz.gita.mobilebanking.data.retrofit.response.BasicResponse

interface AuthCardApi {
    @POST("/api/v1/card/add-card")
    suspend fun add(@Body request: AddCardRequest): Response<BasicResponse>

    @POST("/api/v1/card/verify")
    suspend fun verify(@Body request: VerifyCardRequest): Response<BasicResponse>

    @POST("/api/v1/card/edit-card")
    suspend fun edit(@Body request: EditCardRequest): Response<BasicResponse>

    @POST("/api/v1/card/delete-card")
    suspend fun delete(@Body request: DeleteCardRequest): Response<BasicResponse>

    @GET("/api/v1/card/all")
    suspend fun get(): Response<AllCardResponse>
}