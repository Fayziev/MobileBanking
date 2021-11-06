package uz.gita.mobilebanking.presentation.viewModel

import androidx.lifecycle.LiveData
import uz.gita.mobilebanking.data.retrofit.request.LoginRequest
import uz.gita.mobilebanking.data.retrofit.request.ResetRequest

interface LoginViewModel {
    val enableLoginLiveData: LiveData<Unit>
    val disableLoginLiveData: LiveData<Unit>

    val openResetScreenLiveData:LiveData<String>
    val openVerifyScreenLiveData:LiveData<Unit>

    val enableResetButtonLiveData:LiveData<Unit>
    val disableResetButtonLiveData:LiveData<Unit>

    val showProgressLiveData:LiveData<Unit>
    val hideProgressLiveData:LiveData<Unit>
    val errorMessageLiveData:LiveData<String>

    fun resetUser(request:ResetRequest)
    fun loginUser(request: LoginRequest)
}