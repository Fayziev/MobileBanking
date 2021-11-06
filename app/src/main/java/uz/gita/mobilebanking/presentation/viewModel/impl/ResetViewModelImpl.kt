package uz.gita.mobilebanking.presentation.viewModel.impl

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import uz.gita.mobilebanking.data.retrofit.request.NewPasswordRequest
import uz.gita.mobilebanking.domain.usecase.ResetUseCase
import uz.gita.mobilebanking.presentation.viewModel.ResetViewModel
import uz.gita.mobilebanking.utils.isConnected
import javax.inject.Inject
@HiltViewModel
class ResetViewModelImpl @Inject constructor(private val useCase: ResetUseCase) : ViewModel(), ResetViewModel {

    override val openBackScreenLiveData = MutableLiveData<Unit>()
    override val errorMessageLiveData = MutableLiveData<String>()
    override val enableBackButtonLiveData = MutableLiveData<Unit>()
    override val disableBackButtonLiveData = MutableLiveData<Unit>()
    override val showProgressLiveData = MutableLiveData<Unit>()
    override val hideProgressLiveData = MutableLiveData<Unit>()
    override fun useNewPassword(request: NewPasswordRequest) {
        if (!isConnected()) {
            errorMessageLiveData.value = "Internet mavjud emas"
            return
        }

        disableBackButtonLiveData.value = Unit
        showProgressLiveData.value = Unit
        useCase.newPasswordUser(request).onEach {
            it.onFailure { throwable ->
                enableBackButtonLiveData.value = Unit
                errorMessageLiveData.value = throwable.message
                hideProgressLiveData.value = Unit
            }
            it.onSuccess {
                enableBackButtonLiveData.value = Unit
                openBackScreenLiveData.value = Unit
                hideProgressLiveData.value = Unit
            }
        }.launchIn(viewModelScope)
    }
}