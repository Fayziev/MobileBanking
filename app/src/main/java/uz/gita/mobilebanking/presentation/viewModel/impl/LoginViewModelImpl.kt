package uz.gita.mobilebanking.presentation.viewModel.impl

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import uz.gita.mobilebanking.data.retrofit.request.LoginRequest
import uz.gita.mobilebanking.data.retrofit.request.ResetRequest
import uz.gita.mobilebanking.domain.usecase.LoginUseCase
import uz.gita.mobilebanking.presentation.viewModel.LoginViewModel
import uz.gita.mobilebanking.utils.isConnected
import javax.inject.Inject

@HiltViewModel
class LoginViewModelImpl @Inject constructor(private val useCase: LoginUseCase) : ViewModel(),
    LoginViewModel {

    override val enableLoginLiveData = MutableLiveData<Unit>()
    override val disableLoginLiveData = MutableLiveData<Unit>()
    override val openResetScreenLiveData = MutableLiveData<String>()
    override val openVerifyScreenLiveData = MutableLiveData<Unit>()
    override val enableResetButtonLiveData = MutableLiveData<Unit>()
    override val disableResetButtonLiveData = MutableLiveData<Unit>()
    override val showProgressLiveData = MutableLiveData<Unit>()
    override val hideProgressLiveData = MutableLiveData<Unit>()
    override val errorMessageLiveData = MutableLiveData<String>()
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

    override fun loginUser(request: LoginRequest) {

        if (!isConnected()) {
            errorMessageLiveData.value = "Internet mavjud emas"
            return
        }
        showProgressLiveData.value = Unit
        disableLoginLiveData.value = Unit
        useCase.loginUser(request).onEach {
            it.onFailure { throwable ->
                errorMessageLiveData.value = throwable.message
                enableLoginLiveData.value = Unit
                hideProgressLiveData.value = Unit
            }
            it.onSuccess {

                enableLoginLiveData.value = Unit
                openVerifyScreenLiveData.value = Unit
                hideProgressLiveData.value = Unit
            }
        }.launchIn(viewModelScope)
    }

}