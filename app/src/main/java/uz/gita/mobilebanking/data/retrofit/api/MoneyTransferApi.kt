package uz.gita.mobilebanking.data.retrofit.api

import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.Query
import uz.gita.mobilebanking.data.retrofit.response.MoneyTransferResponse

interface MoneyTransferApi {

    @GET("money-transfer/history")
    suspend fun getMoneyTransferHistory(
        @Query("page-number") pageNumber: Int,
        @Query("page-size") pageSize: Int
    ): Response<MoneyTransferResponse.TransferResponse>
}