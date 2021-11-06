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
import androidx.navigation.fragment.navArgs
import by.kirich1409.viewbindingdelegate.viewBinding
import dagger.hilt.android.AndroidEntryPoint
import uz.gita.mobilebankapplication.presentation.ui.screen.ResetPasswordScreenArgs
import uz.gita.mobilebanking.R
import uz.gita.mobilebanking.data.retrofit.request.NewPasswordRequest
import uz.gita.mobilebanking.databinding.ScreenResetPasswordBinding
import uz.gita.mobilebanking.presentation.viewModel.ResetViewModel
import uz.gita.mobilebanking.presentation.viewModel.impl.ResetViewModelImpl
import uz.gita.mobilebanking.utils.scope

@AndroidEntryPoint
class ResetPasswordScreen:Fragment(R.layout.screen_reset_password) {
    private val binding by viewBinding(ScreenResetPasswordBinding::bind)
    private val viewModel: ResetViewModel by viewModels<ResetViewModelImpl>()
    private val enables = arrayOf(false, false, false)
    private val args: ResetPasswordScreenArgs by navArgs()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) = binding.scope {

        codeInput.addTextChangedListener(getTextWatcher { enables[0] = it.length == 6;check() })
        passwordInput.addTextChangedListener(getTextWatcher { enables[1] = checkPassword(it); check() })
        confirmPasswordInput.addTextChangedListener(getTextWatcher { enables[2] = checkPassword(it); check() })

        check()
        viewModel.useNewPassword(
            NewPasswordRequest(
                args.phone,
                passwordInput.text.toString(),
                codeInput.text.toString()
            )
        )

        viewModel.disableBackButtonLiveData.observe(viewLifecycleOwner, disableBackButtonObserver)
        viewModel.enableBackButtonLiveData.observe(viewLifecycleOwner, enableBackButtonObserver)
        viewModel.hideProgressLiveData.observe(viewLifecycleOwner, hideProgressObserver)
        viewModel.showProgressLiveData.observe(viewLifecycleOwner, showProgressObserver)
        viewModel.errorMessageLiveData.observe(viewLifecycleOwner, errorMessageObserver)
        viewModel.openBackScreenLiveData.observe(viewLifecycleOwner, openBackScreenObserver)

    }

    private val disableBackButtonObserver = Observer<Unit> {
        binding.sendResetPasswordButton.isEnabled = false
    }

    private val enableBackButtonObserver = Observer<Unit> {
        binding.sendResetPasswordButton.isEnabled = true
    }

    private val hideProgressObserver = Observer<Unit> {
        binding.progress.hide()
    }

    private val showProgressObserver = Observer<Unit> {
        binding.progress.show()
    }

    private val openBackScreenObserver = Observer<Unit> {
        findNavController().popBackStack()
    }

    private val errorMessageObserver = Observer<String> {
        Toast.makeText(requireContext(), it, Toast.LENGTH_SHORT).show()
    }

    private fun check() {
        binding.sendResetPasswordButton.isEnabled =
            (enables[0] && enables[1]
                    && enables[2]
                    && binding.passwordInput.text.toString() == binding.confirmPasswordInput.text.toString())
    }

    private fun checkPassword(password: String): Boolean = password.length >= 6
    private fun getTextWatcher(block: (String) -> Unit) =
        object : TextWatcher {
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {

            }

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                block.invoke(s.toString())
            }

            override fun afterTextChanged(s: Editable?) {

            }

        }
}