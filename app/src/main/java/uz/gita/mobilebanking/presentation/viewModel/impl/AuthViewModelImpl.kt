package uz.gita.mobilebanking.presentation.viewModel.impl

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import uz.gita.mobilebanking.presentation.viewModel.AuthViewModel
import javax.inject.Inject

@HiltViewModel
class AuthViewModelImpl @Inject constructor() : ViewModel(), AuthViewModel {

    override val openRegisterScreenLiveData = MutableLiveData<Unit>()
    override val openLoginScreenLiveData = MutableLiveData<Unit>()
    override fun openRegisterScreen() {
        openRegisterScreenLiveData.value=Unit
    }

    override fun openLoginScreen() {
        openLoginScreenLiveData.value=Unit
    }
}