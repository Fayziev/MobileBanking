package uz.gita.mobilebanking.presentation.viewModel.impl

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import uz.gita.mobilebanking.data.retrofit.request.LoginRequest
import uz.gita.mobilebanking.domain.AuthRepository
import uz.gita.mobilebanking.presentation.viewModel.LoginViewModel
import uz.gita.mobilebanking.utils.isConnected
import javax.inject.Inject

class LoginViewModelImpl @Inject constructor(private val repository: AuthRepository) : ViewModel(),
    LoginViewModel {

    override val enableLoginLiveData = MutableLiveData<Unit>()
    override val disableLoginLiveData = MutableLiveData<Unit>()
    override val progressLiveData = MutableLiveData<Boolean>()
    override val errorLivaData = MutableLiveData<String>()
    override val successLiveData = MutableLiveData<String>()
    override fun loginUserData(data: LoginRequest) {

        if (!isConnected()) {
            errorLivaData.value = "Connect to the Internet and try again"
            return
        }
        progressLiveData.value = true
        disableLoginLiveData.value = Unit
        repository.loginUser(data).onEach {

            progressLiveData.value = false
            enableLoginLiveData.value = Unit
            it.onFailure { throwable ->
                errorLivaData.value = throwable.message
            }
            it.onSuccess { message ->
                successLiveData.value = message
            }
        }.launchIn(viewModelScope)
    }
}