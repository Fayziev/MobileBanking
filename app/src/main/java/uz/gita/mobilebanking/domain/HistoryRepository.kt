package uz.gita.mobilebanking.domain

import androidx.paging.PagingData
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.flow.Flow
import uz.gita.mobilebanking.data.retrofit.response.MoneyTransferResponse

interface HistoryRepository {

    fun getHistoryPagingData(scope: CoroutineScope): Flow<PagingData<MoneyTransferResponse.HistoryData>>
}