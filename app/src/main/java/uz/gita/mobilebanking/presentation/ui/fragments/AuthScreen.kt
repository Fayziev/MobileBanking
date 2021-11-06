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
import uz.gita.mobilebanking.databinding.ScreenAuthBinding
import uz.gita.mobilebanking.presentation.viewModel.AuthViewModel
import uz.gita.mobilebanking.presentation.viewModel.impl.AuthViewModelImpl
import uz.gita.mobilebanking.utils.scope

@AndroidEntryPoint
class AuthScreen : Fragment(R.layout.screen_auth) {
    private val binding by viewBinding(ScreenAuthBinding::bind)
    private val viewModel: AuthViewModel by viewModels<AuthViewModelImpl>()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) = binding.scope {
        loginButton.setOnClickListener {
            viewModel.openLoginScreen()
        }
        registerButton.setOnClickListener {
            viewModel.openRegisterScreen()
        }

        viewModel.openRegisterScreenLiveData.observe(viewLifecycleOwner, openRegisterScreenObserver)
        viewModel.openLoginScreenLiveData.observe(viewLifecycleOwner, openLoginScreenObserver)
    }

    private val openLoginScreenObserver = Observer<Unit> {
        findNavController().navigate(R.id.action_authScreen_to_loginScreen)
    }
    private val openRegisterScreenObserver = Observer<Unit> {
        findNavController().navigate(R.id.action_authScreen_to_registerScreen)
    }
}