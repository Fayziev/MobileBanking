package uz.gita.mobilebanking.presentation.viewModel

import androidx.lifecycle.LiveData
import uz.gita.mobilebanking.data.retrofit.request.DeleteCardRequest
import uz.gita.mobilebanking.data.retrofit.request.ResetRequest
import uz.gita.mobilebanking.data.retrofit.response.CardData

interface MainViewModel {

    val showProgressLiveData: LiveData<Unit>
    val hideProgressLiveData: LiveData<Unit>

    val enableLogoutButtonLiveData: LiveData<Unit>
    val disableLogoutButtonLiveData: LiveData<Unit>

    val enableResetButtonLiveData: LiveData<Unit>
    val disableResetButtonLiveData: LiveData<Unit>

    val openResetScreenLiveData: LiveData<String>
    val openLoginScreenLiveData: LiveData<Unit>
    val openAddCardScreenLiveData: LiveData<Unit>
    val openEditCardScreenLiveData: LiveData<CardData>
    val deleteCardRecyclerView: LiveData<Int>
    val errorMessageLiveData: LiveData<String>

    val recyclerViewLiveData: LiveData<List<CardData>>
    val enableAddCardLiveData: LiveData<Unit>
    val disableAddCardLiveData: LiveData<Unit>

    val enableRecyclerView: LiveData<Unit>
    val disableRecyclerView: LiveData<Unit>

    fun getToken(): String
    fun resetUser(request: ResetRequest)
    fun logoutUser()

    fun cardAdd()
    fun cardEdit(data: CardData)
    fun cardDelete(request: DeleteCardRequest, pos: Int)
    fun cardGet()
}