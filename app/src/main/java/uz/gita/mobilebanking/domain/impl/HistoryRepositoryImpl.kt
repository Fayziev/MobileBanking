package uz.gita.mobilebanking.domain.impl

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import androidx.paging.cachedIn
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.flow.Flow
import uz.gita.mobilebanking.data.datasource.HistoryDataSource
import uz.gita.mobilebanking.data.pref.MyPref
import uz.gita.mobilebanking.data.retrofit.api.MoneyTransferApi
import uz.gita.mobilebanking.data.retrofit.response.MoneyTransferResponse
import uz.gita.mobilebanking.domain.HistoryRepository
import javax.inject.Inject

class HistoryRepositoryImpl @Inject constructor(
    private val pref: MyPref,
    val api: MoneyTransferApi
) : HistoryRepository {
    private val config = PagingConfig(10)
    override fun getHistoryPagingData(scope: CoroutineScope): Flow<PagingData<MoneyTransferResponse.HistoryData>> =
        Pager(config) {
            HistoryDataSource(api, pref)
        }.flow.cachedIn(scope)

}