package uz.gita.mobilebanking.presentation.ui.fragments

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import by.kirich1409.viewbindingdelegate.viewBinding
import dagger.hilt.android.AndroidEntryPoint
import uz.gita.mobilebanking.R
import uz.gita.mobilebanking.databinding.ScreenLoginBinding
import uz.gita.mobilebanking.presentation.viewModel.SplashViewModel
import uz.gita.mobilebanking.presentation.viewModel.impl.SplashViewModelImpl

@AndroidEntryPoint
class LoginScreen:Fragment(R.layout.screen_login) {

    private val binding by viewBinding(ScreenLoginBinding::bind)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {

    }
}