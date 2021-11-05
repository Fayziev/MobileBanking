package uz.gita.mobilebanking.presentation.viewModel.impl

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.onEach
import uz.gita.mobilebanking.data.retrofit.request.DeleteCardRequest
import uz.gita.mobilebanking.data.retrofit.request.ResetRequest
import uz.gita.mobilebanking.data.retrofit.response.CardData
import uz.gita.mobilebanking.domain.usecase.MainUseCase
import uz.gita.mobilebanking.presentation.viewModel.MainViewModel
import uz.gita.mobilebanking.utils.isConnected
import javax.inject.Inject

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
            it.onFailure {

            }

            it.onSuccess {

            }
        }
    }

    override fun logoutUser() {

    }

    override fun cardAdd() {

    }

    override fun cardEdit(data: CardData) {

    }

    override fun cardDelete(request: DeleteCardRequest, pos: Int) {

    }

    override fun cardGet() {

    }

}