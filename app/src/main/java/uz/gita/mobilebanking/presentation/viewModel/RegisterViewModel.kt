package uz.gita.mobilebanking.presentation.viewModel

import androidx.lifecycle.LiveData
import uz.gita.mobilebanking.data.retrofit.request.RegisterRequest

interface RegisterViewModel {
    val openVerifyScreenLiveData: LiveData<Unit>
    val errorMessageLiveData: LiveData<String>
    val disableRegisterButtonLiveData: LiveData<Unit>
    val showProgressLiveData: LiveData<Unit>
    val hideProgressLiveData: LiveData<Unit>
    val enableRegisterButtonLiveData: LiveData<Unit>

    fun registerUser(data: RegisterRequest)
}