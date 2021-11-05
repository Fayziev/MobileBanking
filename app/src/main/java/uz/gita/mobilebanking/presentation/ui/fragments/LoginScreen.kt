package uz.gita.mobilebanking.presentation.ui.fragments

import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.core.widget.addTextChangedListener
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import by.kirich1409.viewbindingdelegate.viewBinding
import dagger.hilt.android.AndroidEntryPoint
import uz.gita.mobilebanking.R
import uz.gita.mobilebanking.data.retrofit.request.LoginRequest
import uz.gita.mobilebanking.databinding.ScreenLoginBinding
import uz.gita.mobilebanking.presentation.viewModel.LoginViewModel
import uz.gita.mobilebanking.presentation.viewModel.impl.LoginViewModelImpl

@AndroidEntryPoint
class LoginScreen : Fragment(R.layout.screen_login) {

    private val binding by viewBinding(ScreenLoginBinding::bind)
    private val viewModel: LoginViewModel by viewModels<LoginViewModelImpl>()
    private var boolNumber = false
    private var boolPassword = false


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        binding.send.isEnabled = false
        binding.number.addTextChangedListener {
            it?.let {
                boolNumber = it.length == 13 && it.toString().startsWith("+998")
                check()
            }
        }
        binding.password.addTextChangedListener {
            it?.let {
                boolPassword = it.length > 6
                check()
            }
        }

        binding.send.setOnClickListener {
            viewModel.loginUserData(
                LoginRequest(
                    binding.number.text.toString(),
                    binding.password.text.toString()
                )
            )
        }
        viewModel.disableLoginLiveData.observe(viewLifecycleOwner, disableLoginLiveDataObserver)
        viewModel.enableLoginLiveData.observe(viewLifecycleOwner, enableLoginLiveDataObserver)
        viewModel.errorLivaData.observe(viewLifecycleOwner, errorLivaDataObserver)
        viewModel.progressLiveData.observe(viewLifecycleOwner, progressLiveDataObserver)
        viewModel.successLiveData.observe(viewLifecycleOwner, successLiveDataObserver)
    }

    private fun check() {
        binding.send.isEnabled = boolNumber && boolPassword
    }

    private val disableLoginLiveDataObserver = Observer<Unit> {
        binding.send.isEnabled = false
    }
    private val enableLoginLiveDataObserver = Observer<Unit> {
        binding.send.isEnabled = true
    }
    private val errorLivaDataObserver = Observer<String> {
        Toast.makeText(requireContext(), it, Toast.LENGTH_SHORT).show()
    }
    private val progressLiveDataObserver = Observer<Boolean> {
        if (it) binding.progress.show()
        else binding.progress.hide()
    }
    private val successLiveDataObserver = Observer<String> {
        Toast.makeText(requireContext(), it, Toast.LENGTH_SHORT).show()
        findNavController().navigate(R.id.action_loginScreen_to_smsVerifyScreen)
    }
}