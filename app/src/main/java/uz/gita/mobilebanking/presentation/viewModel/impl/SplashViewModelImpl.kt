package uz.gita.mobilebanking.presentation.viewModel.impl

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import uz.gita.mobilebanking.data.models.StartScreenEnum
import uz.gita.mobilebanking.domain.usecase.SplashUseCase
import uz.gita.mobilebanking.presentation.viewModel.SplashViewModel
import javax.inject.Inject

@HiltViewModel
class SplashViewModelImpl @Inject constructor(private val useCase: SplashUseCase) : ViewModel(), SplashViewModel {
    override val openLoginScreenLiveData = MutableLiveData<Unit>()
    override val openMainScreenLiveData = MutableLiveData<Unit>()

    init {
        viewModelScope.launch {
            delay(2000)
            if (useCase.startScreen() == StartScreenEnum.MAIN) openMainScreenLiveData.value = Unit
            else openLoginScreenLiveData.value = Unit
        }
    }
}