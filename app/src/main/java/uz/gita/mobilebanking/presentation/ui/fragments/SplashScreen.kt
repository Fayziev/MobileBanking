package uz.gita.mobilebanking.presentation.ui.fragments

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import by.kirich1409.viewbindingdelegate.viewBinding
import dagger.hilt.android.AndroidEntryPoint
import uz.gita.mobilebanking.R
import uz.gita.mobilebanking.databinding.ScreenSplashBinding
import uz.gita.mobilebanking.presentation.viewModel.SplashViewModel
import uz.gita.mobilebanking.presentation.viewModel.impl.SplashViewModelImpl

@AndroidEntryPoint
class SplashScreen: Fragment(R.layout.screen_splash) {

    private val binding by viewBinding(ScreenSplashBinding::bind)
    private val viewModel : SplashViewModel by viewModels<SplashViewModelImpl>()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        viewModel.openLoginScreenLiveData.observe(viewLifecycleOwner,openLoginScreenObserver)
        viewModel.openMainScreenLiveData.observe(viewLifecycleOwner,openMainScreenObserver)
    }

    private val openMainScreenObserver = Observer<Unit> {
        findNavController().navigate(R.id.action_splashScreen_to_mainScreen)
    }

    private val openLoginScreenObserver = Observer<Unit> {
        findNavController().navigate(R.id.action_splashScreen_to_authScreen)
    }
}