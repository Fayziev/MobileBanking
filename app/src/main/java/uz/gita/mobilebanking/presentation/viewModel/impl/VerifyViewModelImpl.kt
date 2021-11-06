package uz.gita.mobilebanking.presentation.viewModel.impl

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import uz.gita.mobilebanking.data.retrofit.request.VerifyRequest
import uz.gita.mobilebanking.domain.usecase.VerifyUseCase
import uz.gita.mobilebanking.presentation.viewModel.VerifyViewModel
import uz.gita.mobilebanking.utils.isConnected
import javax.inject.Inject

@HiltViewModel
class VerifyViewModelImpl @Inject constructor(
    private val useCase: VerifyUseCase
) : ViewModel(), VerifyViewModel {

    override val openMainScreenLiveData = MutableLiveData<Unit>()
    override val errorMessageLiveData = MutableLiveData<String>()
    override val disableVerifyButtonLiveData = MutableLiveData<Unit>()
    override val enableVerifyButtonLiveData = MutableLiveData<Unit>()
    override val showProgressLiveData = MutableLiveData<Unit>()
    override val hideProgressLiveData = MutableLiveData<Unit>()

    override fun verifyUser(request: VerifyRequest) {
        if (!isConnected()) {
            errorMessageLiveData.value = "Internet mavjud emas"
            return
        }

        disableVerifyButtonLiveData.value = Unit
        showProgressLiveData.value = Unit
        useCase.verifyUser(request).onEach {
            it.onSuccess {
                enableVerifyButtonLiveData.value = Unit
                openMainScreenLiveData.value = Unit
                hideProgressLiveData.value = Unit
                useCase.setScreen()
            }
            it.onFailure { throwable ->
                enableVerifyButtonLiveData.value = Unit
                errorMessageLiveData.value = throwable.message
                hideProgressLiveData.value = Unit
            }
        }.launchIn(viewModelScope)
    }
}