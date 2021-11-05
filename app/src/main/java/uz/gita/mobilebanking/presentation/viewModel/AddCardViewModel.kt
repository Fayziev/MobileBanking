package uz.gita.mobilebanking.presentation.viewModel

import androidx.lifecycle.LiveData
import uz.gita.mobilebanking.data.retrofit.request.AddCardRequest
import uz.gita.mobilebanking.data.retrofit.request.VerifyCardRequest
import uz.gita.mobilebanking.data.retrofit.request.VerifyRequest

interface AddCardViewModel {

    val showProgressLiveData: LiveData<Unit>
    val hideProgressLiveData: LiveData<Unit>

    val enableSendButtonLiveData: LiveData<Unit>
    val disableSendButtonLiveData: LiveData<Unit>

    val enableVerifyButtonLiveData: LiveData<Unit>
    val disableVerifyButtonLiveData: LiveData<Unit>

    val showCodeEditLiveData: LiveData<Unit>
    val errorMessageLiveData: LiveData<String>

    val backToMainScreenLiveData: LiveData<Unit>

    fun addCard(request: AddCardRequest)
    fun verifyCard(request: VerifyCardRequest)

}