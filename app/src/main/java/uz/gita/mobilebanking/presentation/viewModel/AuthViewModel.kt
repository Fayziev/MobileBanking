package uz.gita.mobilebanking.presentation.viewModel

import androidx.lifecycle.LiveData

interface AuthViewModel {

    val openRegisterScreenLiveData:LiveData<Unit>
    val openLoginScreenLiveData:LiveData<Unit>

    fun openRegisterScreen()
    fun openLoginScreen()
}