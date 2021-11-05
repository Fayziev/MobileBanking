package uz.gita.mobilebanking.presentation.viewModel

import androidx.lifecycle.LiveData
import uz.gita.mobilebanking.data.retrofit.request.NewPasswordRequest

interface ResetViewModel {

    val openBackScreenLiveData: LiveData<Unit>
    val errorMessageLiveData: LiveData<String>
    val enableBackButtonLiveData: LiveData<Unit>
    val disableBackButtonLiveData: LiveData<Unit>
    val showProgressLiveData: LiveData<Unit>
    val hideProgressLiveData: LiveData<Unit>

    fun useNewPassword(request:NewPasswordRequest)
}