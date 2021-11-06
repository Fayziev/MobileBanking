package uz.gita.mobilebanking.presentation.viewModel.impl

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import uz.gita.mobilebanking.data.retrofit.request.DeleteCardRequest
import uz.gita.mobilebanking.data.retrofit.request.ResetRequest
import uz.gita.mobilebanking.data.retrofit.response.CardData
import uz.gita.mobilebanking.domain.usecase.MainUseCase
import uz.gita.mobilebanking.presentation.viewModel.MainViewModel
import uz.gita.mobilebanking.utils.isConnected
import javax.inject.Inject

@HiltViewModel
class MainViewModelImpl @Inject constructor(private val useCase: MainUseCase) : ViewModel(), MainViewModel {

    override val showProgressLiveData = MutableLiveData<Unit>()
    override val hideProgressLiveData = MutableLiveData<Unit>()
    override val enableLogoutButtonLiveData = MutableLiveData<Unit>()
    override val disableLogoutButtonLiveData = MutableLiveData<Unit>()
    override val enableResetButtonLiveData = MutableLiveData<Unit>()
    override val disableResetButtonLiveData = MutableLiveData<Unit>()
    override val openResetScreenLiveData = MutableLiveData<String>()
    override val openLoginScreenLiveData = MutableLiveData<Unit>()
    override val openAddCardScreenLiveData = MutableLiveData<Unit>()
    override val openEditCardScreenLiveData = MutableLiveData<CardData>()
    override val deleteCardRecyclerView = MutableLiveData<Int>()
    override val errorMessageLiveData = MutableLiveData<String>()
    override val recyclerViewLiveData = MutableLiveData<List<CardData>>()
    override val enableAddCardLiveData = MutableLiveData<Unit>()
    override val disableAddCardLiveData = MutableLiveData<Unit>()
    override val enableRecyclerView = MutableLiveData<Unit>()
    override val disableRecyclerView = MutableLiveData<Unit>()

    override fun getToken(): String = useCase.getToken()

    override fun resetUser(request: ResetRequest) {

        if (!isConnected()) {
            errorMessageLiveData.value = "Internet mavjud emas"
            return
        }

        disableResetButtonLiveData.value = Unit
        showProgressLiveData.value = Unit
        useCase.resetUser(request).onEach {
            it.onFailure { throwable ->
                enableResetButtonLiveData.value = Unit
                errorMessageLiveData.value = throwable.message
                hideProgressLiveData.value = Unit
            }
            it.onSuccess {

                enableResetButtonLiveData.value = Unit
                openResetScreenLiveData.value = request.phone
                hideProgressLiveData.value = Unit
            }
        }.launchIn(viewModelScope)
    }

    override fun logoutUser() {

        if (!isConnected()) {
            errorMessageLiveData.value = "Internet mavjud emas"
            return
        }

        disableLogoutButtonLiveData.value = Unit
        showProgressLiveData.value = Unit
        useCase.logoutUser().onEach {
            it.onFailure { throwable ->
                enableLogoutButtonLiveData.value = Unit
                errorMessageLiveData.value = throwable.message
                hideProgressLiveData.value = Unit
            }
            it.onSuccess {
                enableLogoutButtonLiveData.value = Unit
                openLoginScreenLiveData.value = Unit
                hideProgressLiveData.value = Unit
            }
        }.launchIn(viewModelScope)
    }

    override fun cardAdd() {
        openAddCardScreenLiveData.value = Unit
    }

    override fun cardEdit(data: CardData) {
        openEditCardScreenLiveData.value = data
    }

    override fun cardDelete(request: DeleteCardRequest, pos: Int) {

        if (!isConnected()) {
            errorMessageLiveData.value = "Internet mavjud emas"
            return
        }

        showProgressLiveData.value = Unit
        disableRecyclerView.value = Unit
        useCase.cardDelete(request).onEach {
            it.onFailure { throwable ->
                disableRecyclerView.value = Unit
                errorMessageLiveData.value = throwable.message
                hideProgressLiveData.value = Unit
            }
            it.onSuccess {
                enableRecyclerView.value = Unit
                deleteCardRecyclerView.value = pos
                hideProgressLiveData.value = Unit
            }
        }
    }

    override fun cardGet() {
        if (!isConnected()) {
            errorMessageLiveData.value = "Internet mavjud emas"
            return
        }
        showProgressLiveData.value = Unit
        useCase.cardGet().onEach {
            it.onFailure { throwable ->
                disableRecyclerView.value = Unit
                errorMessageLiveData.value = throwable.message
                hideProgressLiveData.value = Unit
            }
            it.onSuccess { list ->
                enableRecyclerView.value = Unit
                recyclerViewLiveData.value = list
                hideProgressLiveData.value = Unit
            }
        }.launchIn(viewModelScope)
    }

}