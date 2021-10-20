package uz.gita.mobilebanking.presentation.viewModel

import androidx.lifecycle.LiveData
import uz.gita.mobilebanking.data.retrofit.request.RegisterRequest

interface RegisterViewModel {
    val enableRegisterLiveData : LiveData<Unit>
    val disableRegisterLiveData : LiveData<Unit>

    val progressLiveData : LiveData<Boolean>
    val errorLivaData : LiveData<String>
    val successLiveData : LiveData<String>

    fun registerUser(data : RegisterRequest)
}