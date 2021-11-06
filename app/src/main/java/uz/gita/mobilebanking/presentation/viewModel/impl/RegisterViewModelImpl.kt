package uz.gita.mobilebanking.presentation.viewModel.impl

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import uz.gita.mobilebanking.data.retrofit.request.RegisterRequest
import uz.gita.mobilebanking.domain.usecase.RegisterUseCase
import uz.gita.mobilebanking.presentation.viewModel.RegisterViewModel
import uz.gita.mobilebanking.utils.isConnected
import javax.inject.Inject

@HiltViewModel
class RegisterViewModelImpl @Inject constructor(
    private val useCase: RegisterUseCase
) : ViewModel(), RegisterViewModel {


    override val openVerifyScreenLiveData = MutableLiveData<Unit>()
    override val errorMessageLiveData = MutableLiveData<String>()
    override val disableRegisterButtonLiveData = MutableLiveData<Unit>()
    override val showProgressLiveData = MutableLiveData<Unit>()
    override val hideProgressLiveData = MutableLiveData<Unit>()
    override val enableRegisterButtonLiveData = MutableLiveData<Unit>()

    override fun registerUser(request: RegisterRequest) {

        if (!isConnected()) {
            errorMessageLiveData.value = "Internet mavjud emas"
            return
        }
        showProgressLiveData.value = Unit
        disableRegisterButtonLiveData.value = Unit

        useCase.registerUser(request).onEach {

            it.onSuccess {
                enableRegisterButtonLiveData.value = Unit
                openVerifyScreenLiveData.value = Unit
                hideProgressLiveData.value = Unit
            }
            it.onFailure { throwable ->
                enableRegisterButtonLiveData.value = Unit
                errorMessageLiveData.value = throwable.message
                hideProgressLiveData.value = Unit
            }
        }.launchIn(viewModelScope)

    }


}