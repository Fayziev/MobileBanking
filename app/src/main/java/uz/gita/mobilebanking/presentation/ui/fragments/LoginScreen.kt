package uz.gita.mobilebanking.presentation.ui.fragments

import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.View
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import by.kirich1409.viewbindingdelegate.viewBinding
import dagger.hilt.android.AndroidEntryPoint
import uz.gita.mobilebanking.R
import uz.gita.mobilebanking.data.retrofit.request.LoginRequest
import uz.gita.mobilebanking.data.retrofit.request.ResetRequest
import uz.gita.mobilebanking.databinding.ScreenLoginBinding
import uz.gita.mobilebanking.presentation.viewModel.LoginViewModel
import uz.gita.mobilebanking.presentation.viewModel.impl.LoginViewModelImpl
import uz.gita.mobilebanking.utils.scope

@AndroidEntryPoint
class LoginScreen : Fragment(R.layout.screen_login) {

    private val binding by viewBinding(ScreenLoginBinding::bind)
    private val viewModel: LoginViewModel by viewModels<LoginViewModelImpl>()
    private val enables = arrayOf(false, false, false)


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) = binding.scope {
        phoneInput.addTextChangedListener(getTextWatcher { enables[0] = checkPhoneInput(it);check() })
        passwordInput.addTextChangedListener(getTextWatcher { enables[1] = checkPassword(it);check() })
        confirmPasswordInput.addTextChangedListener(getTextWatcher { enables[2] = checkPassword(it);check() })

        check()
        sendLoginButton.setOnClickListener {
            viewModel.loginUser(
                LoginRequest(
                    phoneInput.text.toString(),
                    passwordInput.text.toString()
                )
            )
        }

        resetPasswordButton.setOnClickListener {
            val dialog = ResetDialog()

            dialog.setListener {
                viewModel.resetUser(
                    ResetRequest(it)
                )
            }
            dialog.show(childFragmentManager, "resetDialog")
        }

        viewModel.disableLoginLiveData.observe(viewLifecycleOwner, disableLoginObserver)
        viewModel.enableLoginLiveData.observe(viewLifecycleOwner, enableLoginObserver)
        viewModel.disableResetButtonLiveData.observe(viewLifecycleOwner, disableResetObserver)
        viewModel.enableResetButtonLiveData.observe(viewLifecycleOwner, enableResetObserver)
        viewModel.showProgressLiveData.observe(viewLifecycleOwner, showProgressObserver)
        viewModel.hideProgressLiveData.observe(viewLifecycleOwner, hideProgressObserver)
        viewModel.openVerifyScreenLiveData.observe(viewLifecycleOwner, openVerifyScreenObserver)
        viewModel.openResetScreenLiveData.observe(viewLifecycleOwner, openResetScreenObserver)
    }


    private val disableLoginObserver = Observer<Unit> {
        binding.sendLoginButton.isEnabled = false
    }
    private val enableLoginObserver = Observer<Unit> {
        binding.sendLoginButton.isEnabled = true
    }
    private val disableResetObserver = Observer<Unit> {
        binding.resetPasswordButton.isEnabled = false
    }
    private val enableResetObserver = Observer<Unit> {
        binding.resetPasswordButton.isEnabled = true
    }
    private val showProgressObserver = Observer<Unit> {
        binding.progress.show()
    }
    private val hideProgressObserver = Observer<Unit> {
        binding.progress.hide()
    }
    private val openResetScreenObserver = Observer<Unit> {
        Toast.makeText(requireContext(), "reset", Toast.LENGTH_SHORT).show()
    }
    private val openVerifyScreenObserver = Observer<Unit> {
        Toast.makeText(requireContext(), "verify", Toast.LENGTH_SHORT).show()
    }

    private fun check() {
        binding.sendLoginButton.isEnabled =
            (
                    enables[0] &&
                            enables[1] &&
                            enables[2] &&
                            binding.passwordInput.text.toString() == binding.confirmPasswordInput.text.toString())
    }

    private fun checkPassword(password: String): Boolean = (password.length >= 6)

    private fun checkPhoneInput(string: String): Boolean =
        (string.length == 13 && string.startsWith("+998", true))

    private fun getTextWatcher(block: (String) -> Unit) =
        object : TextWatcher {
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                block.invoke(s.toString())
            }

            override fun afterTextChanged(s: Editable?) {}

        }
}