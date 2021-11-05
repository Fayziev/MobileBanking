package uz.gita.mobilebanking.presentation.viewModel

import androidx.lifecycle.LiveData
import uz.gita.mobilebanking.data.retrofit.request.LoginRequest

interface LoginViewModel {
    val enableLoginLiveData: LiveData<Unit>
    val disableLoginLiveData: LiveData<Unit>

    val progressLiveData: LiveData<Boolean>
    val errorLivaData: LiveData<String>
    val successLiveData: LiveData<String>

    fun loginUserData(data: LoginRequest)
}