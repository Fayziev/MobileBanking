package uz.gita.mobilebanking.presentation.viewModel.impl

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import uz.gita.mobilebanking.data.retrofit.request.RegisterRequest
import uz.gita.mobilebanking.domain.AuthRepository
import uz.gita.mobilebanking.presentation.viewModel.RegisterViewModel
import uz.gita.mobilebanking.utils.isConnected
import javax.inject.Inject

@HiltViewModel
class RegisterViewModelImpl @Inject constructor(private val repository: AuthRepository) : ViewModel(), RegisterViewModel {

    override val enableRegisterLiveData= MutableLiveData<Unit>()
    override val disableRegisterLiveData= MutableLiveData<Unit>()
    override val progressLiveData= MutableLiveData<Boolean>()
    override val errorLivaData= MutableLiveData<String>()
    override val successLiveData= MutableLiveData<String>()

    override fun registerUser(data: RegisterRequest) {
        if (!isConnected()) {
            errorLivaData.value ="Internetga ulanib qayta urining"
            return
        }
        progressLiveData.value = true
        disableRegisterLiveData.value = Unit
        repository.registerUser(data).onEach {
            progressLiveData.value = false
            enableRegisterLiveData.value = Unit
            it.onFailure { throwable ->
                errorLivaData.value = throwable.message
            }
            it.onSuccess { message ->
                successLiveData.value = message
            }
        }.launchIn(viewModelScope)
    }
}