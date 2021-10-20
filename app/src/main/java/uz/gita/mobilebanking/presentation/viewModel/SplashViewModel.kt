package uz.gita.mobilebanking.presentation.viewModel

import androidx.lifecycle.LiveData

interface SplashViewModel {
    val openLoginScreenLiveData : LiveData<Unit>
    val openMainScreenLiveData : LiveData<Unit>
}