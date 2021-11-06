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
import uz.gita.mobilebankapplication.presentation.ui.screen.VerifyScreenArgs
import uz.gita.mobilebanking.R
import uz.gita.mobilebanking.databinding.ScreenVerifyBinding
import uz.gita.mobilebanking.presentation.viewModel.VerifyViewModel
import uz.gita.mobilebanking.presentation.viewModel.impl.VerifyViewModelImpl
import uz.gita.mobilebanking.utils.scope

@AndroidEntryPoint
class SmsVerifyScreen : Fragment(R.layout.screen_verify) {
    private val binding by viewBinding(ScreenVerifyBinding::bind)
    private val viewModel: VerifyViewModel by viewModels<VerifyViewModelImpl>()
    private var enable = false
    private val args: VerifyScreenArgs by navArgs()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) = binding.scope {

        verifyCodeInput.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}
            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                enable = s?.length == 6
                check()
            }

            override fun afterTextChanged(s: Editable?) {}
        })

        viewModel.disableVerifyButtonLiveData.observe(viewLifecycleOwner, disableVerifyObserver)
        viewModel.enableVerifyButtonLiveData.observe(viewLifecycleOwner, enableVerifyObserver)
        viewModel.errorMessageLiveData.observe(viewLifecycleOwner, errorMessageObserver)
        viewModel.hideProgressLiveData.observe(viewLifecycleOwner, hideProgressObserver)
        viewModel.showProgressLiveData.observe(viewLifecycleOwner, showProgressObserver)
        viewModel.openMainScreenLiveData.observe(viewLifecycleOwner, openMainScreenObserver)
    }

    private fun check() {
        binding.sendVerifyButton.isEnabled = enable
    }

    private val disableVerifyObserver = Observer<Unit> {
        binding.sendVerifyButton.isEnabled = false
    }
    private val enableVerifyObserver = Observer<Unit> {
        binding.sendVerifyButton.isEnabled = true
    }

    private val showProgressObserver = Observer<Unit> {
        binding.progress.show()
    }
    private val hideProgressObserver = Observer<Unit> {
        binding.progress.hide()
    }
    private val openMainScreenObserver = Observer<Unit> {
        findNavController().navigate(R.id.action_verifyScreen_to_mainScreen)
    }
    private val errorMessageObserver = Observer<String> {
        Toast.makeText(requireContext(), it, Toast.LENGTH_SHORT).show()
    }


}