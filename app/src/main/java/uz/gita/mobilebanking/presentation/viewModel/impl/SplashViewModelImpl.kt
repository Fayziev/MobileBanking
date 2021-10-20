package uz.gita.mobilebanking.presentation.viewModel.impl

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import uz.gita.mobilebanking.data.models.StartScreenEnum
import uz.gita.mobilebanking.domain.AppRepository
import uz.gita.mobilebanking.domain.impl.AppRepositoryImpl
import uz.gita.mobilebanking.presentation.viewModel.SplashViewModel
import javax.inject.Inject

@HiltViewModel
class SplashViewModelImpl @Inject constructor(private val repository: AppRepository) : ViewModel(), SplashViewModel {
    override val openLoginScreenLiveData= MutableLiveData<Unit>()
    override val openMainScreenLiveData= MutableLiveData<Unit>()

    init {
        viewModelScope.launch {
            delay(2000)
            if (repository.startScreen() == StartScreenEnum.LOGIN) openLoginScreenLiveData.value = Unit
            else openMainScreenLiveData.value = Unit
        }
    }
}