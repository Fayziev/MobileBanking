package uz.gita.mobilebanking.presentation.viewModel.impl

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import uz.gita.mobilebanking.data.retrofit.request.AddCardRequest
import uz.gita.mobilebanking.data.retrofit.request.VerifyCardRequest
import uz.gita.mobilebanking.domain.usecase.AddCardUseCase
import uz.gita.mobilebanking.presentation.viewModel.AddCardViewModel
import uz.gita.mobilebanking.utils.isConnected
import javax.inject.Inject

@HiltViewModel
class AddCardViewModelImpl @Inject constructor(
    private val useCase: AddCardUseCase
) : ViewModel(), AddCardViewModel {

    override val showProgressLiveData = MutableLiveData<Unit>()
    override val hideProgressLiveData = MutableLiveData<Unit>()
    override val enableSendButtonLiveData = MutableLiveData<Unit>()
    override val disableSendButtonLiveData = MutableLiveData<Unit>()
    override val enableVerifyButtonLiveData = MutableLiveData<Unit>()
    override val disableVerifyButtonLiveData = MutableLiveData<Unit>()
    override val showCodeEditLiveData = MutableLiveData<Unit>()
    override val errorMessageLiveData = MutableLiveData<String>()
    override val backToMainScreenLiveData = MutableLiveData<Unit>()
    override fun addCard(request: AddCardRequest) {
        if (!isConnected()) {
            errorMessageLiveData.value = "Internet mavjud emas!"
            return
        }

        disableSendButtonLiveData.value = Unit
        showProgressLiveData.value = Unit
        useCase.addCard(request).onEach {
            it.onFailure { throwable ->
                enableSendButtonLiveData.value = Unit
                errorMessageLiveData.value = throwable.message
                hideProgressLiveData.value = Unit
            }
            it.onSuccess {
                enableSendButtonLiveData.value = Unit
                showCodeEditLiveData.value = Unit
                hideProgressLiveData.value = Unit
            }
        }.launchIn(viewModelScope)
    }

    override fun verifyCard(request: VerifyCardRequest) {
        if (!isConnected()) {
            errorMessageLiveData.value = "Internet mavjud emas!"
            return
        }
        disableVerifyButtonLiveData.value = Unit
        showProgressLiveData.value = Unit
        useCase.verifyCard(request).onEach {
            it.onFailure { throwable ->
                enableVerifyButtonLiveData.value = Unit
                errorMessageLiveData.value = throwable.message
                hideProgressLiveData.value = Unit
            }
            it.onSuccess {
                enableVerifyButtonLiveData.value = Unit
                backToMainScreenLiveData.value = Unit
                hideProgressLiveData.value = Unit
            }
        }.launchIn(viewModelScope)
    }
}