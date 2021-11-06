package uz.gita.mobilebanking.presentation.viewModel

import androidx.lifecycle.LiveData
import uz.gita.mobilebanking.data.retrofit.request.VerifyRequest

interface VerifyViewModel {

    val openMainScreenLiveData: LiveData<Unit>
    val errorMessageLiveData: LiveData<String>
    val disableVerifyButtonLiveData: LiveData<Unit>
    val enableVerifyButtonLiveData: LiveData<Unit>
    val showProgressLiveData: LiveData<Unit>
    val hideProgressLiveData: LiveData<Unit>

    fun verifyUser(request: VerifyRequest)
}