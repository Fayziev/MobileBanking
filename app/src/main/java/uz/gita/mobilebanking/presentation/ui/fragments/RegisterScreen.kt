package uz.gita.mobilebanking.presentation.ui.fragments

import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.View
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import by.kirich1409.viewbindingdelegate.viewBinding
import dagger.hilt.android.AndroidEntryPoint
import uz.gita.mobilebankapplication.presentation.ui.screen.RegisterScreenDirections
import uz.gita.mobilebanking.R
import uz.gita.mobilebanking.data.retrofit.request.RegisterRequest
import uz.gita.mobilebanking.databinding.ScreenRegisterBinding
import uz.gita.mobilebanking.presentation.viewModel.RegisterViewModel
import uz.gita.mobilebanking.presentation.viewModel.impl.RegisterViewModelImpl
import uz.gita.mobilebanking.utils.scope


@AndroidEntryPoint
class RegisterScreen : Fragment(R.layout.screen_register) {
    private val binding by viewBinding(ScreenRegisterBinding::bind)
    private val viewModel: RegisterViewModel by viewModels<RegisterViewModelImpl>()
    private val enables = arrayOf(false, false, false, false, false)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) = binding.scope {

        firstNameInput.addTextChangedListener(getTextWatcher { enables[0] = it.length > 4;check() })
        lastNameInput.addTextChangedListener(getTextWatcher { enables[1] = it.length > 4;check() })
        phoneInput.addTextChangedListener(getTextWatcher { enables[2] = checkPhoneInput(it);check() })
        passwordInput.addTextChangedListener(getTextWatcher { enables[3] = checkPassword(it);check() })
        confirmPasswordInput.addTextChangedListener(getTextWatcher { enables[4] = checkPassword(it);check() })

        check()
        sendRegisterButton.setOnClickListener {
            viewModel.registerUser(
                RegisterRequest(
                    firstNameInput.text.toString(),
                    lastNameInput.text.toString(),
                    phoneInput.text.toString(),
                    passwordInput.text.toString()
                )
            )
        }

        viewModel.disableRegisterButtonLiveData.observe(viewLifecycleOwner, disableRegisterObserver)
        viewModel.enableRegisterButtonLiveData.observe(viewLifecycleOwner, enableRegisterObserver)
        viewModel.errorMessageLiveData.observe(viewLifecycleOwner, errorMessageObserver)
        viewModel.hideProgressLiveData.observe(viewLifecycleOwner, hideProgressObserver)
        viewModel.showProgressLiveData.observe(viewLifecycleOwner, showProgressObserver)
        viewModel.openVerifyScreenLiveData.observe(viewLifecycleOwner, openVerifyScreenObserver)

    }

    private val disableRegisterObserver = Observer<Unit> {
        binding.sendRegisterButton.isEnabled = false
    }
    private val enableRegisterObserver = Observer<Unit> {
        binding.sendRegisterButton.isEnabled = true
    }
    private val showProgressObserver = Observer<Unit> {
        binding.progress.show()
    }
    private val hideProgressObserver = Observer<Unit> {
        binding.progress.hide()
    }
    private val errorMessageObserver = Observer<String> {
        Toast.makeText(requireContext(), it, Toast.LENGTH_SHORT).show()
    }
    private val openVerifyScreenObserver = Observer<Unit> {
        findNavController().navigate(RegisterScreenDirections.actionRegisterScreenToVerifyScreen(binding.phoneInput.text.toString()))
    }


    private fun checkPassword(password: String): Boolean = password.length >= 6

    private fun checkPhoneInput(string: String): Boolean =
        string.length == 13 && string.startsWith("+998", true)

    private fun check() {
        binding.sendRegisterButton.isEnabled =
            (enables[0]
                    && enables[1]
                    && enables[2]
                    && enables[3]
                    && enables[4]
                    && binding.passwordInput.text.toString() == binding.confirmPasswordInput.text.toString())
    }

    private fun getTextWatcher(block: (String) -> Unit) =
        object : TextWatcher {
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}
            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                block.invoke(s.toString())
            }

            override fun afterTextChanged(s: Editable?) {}

        }

}