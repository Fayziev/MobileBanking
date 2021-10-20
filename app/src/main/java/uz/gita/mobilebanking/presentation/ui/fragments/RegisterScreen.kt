package uz.gita.mobilebanking.presentation.ui.fragments

import android.os.Bundle
import android.util.Log
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
import uz.gita.mobilebanking.app.App
import uz.gita.mobilebanking.data.retrofit.request.RegisterRequest
import uz.gita.mobilebanking.databinding.ScreenRegisterBinding
import uz.gita.mobilebanking.presentation.viewModel.RegisterViewModel
import uz.gita.mobilebanking.presentation.viewModel.impl.RegisterViewModelImpl
import uz.gita.mobilebanking.utils.scope


@AndroidEntryPoint
class RegisterScreen : Fragment(R.layout.screen_register) {
    private val binding by viewBinding(ScreenRegisterBinding::bind)
    private val viewModel : RegisterViewModel by viewModels<RegisterViewModelImpl>()
    private var boolFirstName = false
    private var boolLastName = false
    private var boolPassword = false
    private var boolConfirmPassword = false
    private var boolPhoneNumber = false

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) = binding.scope {
        send.isEnabled = false
        firstName.addTextChangedListener {
            it?.let {
                boolFirstName = it.length > 3
                check()
            }
        }
        lastName.addTextChangedListener {
            it?.let {
                boolLastName = it.length > 3
                check()
            }
        }
        password.addTextChangedListener {
            it?.let {
                boolPassword = it.length > 6 && it.toString() == confirmPassword.text.toString()
                check()
            }
        }
        confirmPassword.addTextChangedListener {
            it?.let {
                boolConfirmPassword = it.length > 6 && it.toString() == password.text.toString()
                check()
            }
        }
        telNumber.addTextChangedListener {
            it?.let {
                boolPhoneNumber = it.length == 13 && it.toString().startsWith("+998")
                check()
            }
        }
        send.setOnClickListener {
            viewModel.registerUser(
                RegisterRequest(
                    firstName.text.toString(),
                    lastName.text.toString(),
                    password.text.toString(),
                    telNumber.text.toString()
                )
            )
        }

        viewModel.disableRegisterLiveData.observe(viewLifecycleOwner,disableRegisterObserver)
        viewModel.enableRegisterLiveData.observe(viewLifecycleOwner,enableRegisterObserver)
        viewModel.errorLivaData.observe(viewLifecycleOwner,errorObserver)
        viewModel.progressLiveData.observe(viewLifecycleOwner,progressObserver)
        viewModel.successLiveData.observe(viewLifecycleOwner,successObserver)
    }

    private fun check() {
        Log.d("TTT","boolFirstName = $boolFirstName")
        Log.d("TTT","boolLastName = $boolLastName")
        Log.d("TTT","boolPassword = $boolPassword")
        Log.d("TTT","boolPhoneNumber = $boolPhoneNumber")
        binding.send.isEnabled = boolFirstName && boolLastName && (boolPassword || boolConfirmPassword) && boolPhoneNumber
    }

    private val disableRegisterObserver = Observer<Unit> {
        binding.send.isEnabled = false
    }
    private val enableRegisterObserver = Observer<Unit> {
        binding.send.isEnabled = true
    }
    private val errorObserver = Observer<String> {
        Toast.makeText(requireContext(),it,Toast.LENGTH_SHORT).show()
    }
    private val progressObserver = Observer<Boolean> {
        if (it) binding.progress.show()
        else binding.progress.hide()
    }
    private val successObserver = Observer<String> {
        Toast.makeText(requireContext(),it,Toast.LENGTH_SHORT).show()
        findNavController().navigate(R.id.action_registerScreen_to_smsVerifyScreen)
    }
}