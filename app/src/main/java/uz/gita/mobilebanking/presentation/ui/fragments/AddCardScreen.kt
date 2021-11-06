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
import uz.gita.mobilebanking.R
import uz.gita.mobilebanking.data.retrofit.request.AddCardRequest
import uz.gita.mobilebanking.data.retrofit.request.VerifyCardRequest
import uz.gita.mobilebanking.databinding.ScreenAddCardBinding
import uz.gita.mobilebanking.presentation.viewModel.AddCardViewModel
import uz.gita.mobilebanking.presentation.viewModel.impl.AddCardViewModelImpl
import uz.gita.mobilebanking.utils.scope

@AndroidEntryPoint
class AddCardScreen : Fragment(R.layout.screen_add_card) {
    private val binding by viewBinding(ScreenAddCardBinding::bind)
    private val viewModel: AddCardViewModel by viewModels<AddCardViewModelImpl>()
    private val enables = arrayOf(false, false, false)
    private var codeEnable = false

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) = binding.scope {
        inputCardName.addTextChangedListener(getTextWatcher { enables[0] = checkName(); check() })
        inputPan.addTextChangedListener(getTextWatcher { enables[1] = checkNumber(); check() })
        inputExp.addTextChangedListener(getTextWatcher { enables[2] = checkExp(); check() })
        inputCode.addTextChangedListener(getTextWatcher { codeEnable = checkCode(); check() })

        addCardButton.setOnClickListener {
            viewModel.addCard(
                AddCardRequest(
                    inputPan.text.toString(),
                    inputExp.text.toString(),
                    inputCardName.text.toString()
                )
            )
        }

        verifyButton.setOnClickListener {
            viewModel.verifyCard(
                VerifyCardRequest(
                    inputPan.text.toString(),
                    inputCode.text.toString()
                )
            )
        }

        viewModel.backToMainScreenLiveData.observe(viewLifecycleOwner, backToMainScreenObserver)
        viewModel.enableSendButtonLiveData.observe(viewLifecycleOwner, enableSendButtonObserver)
        viewModel.disableSendButtonLiveData.observe(viewLifecycleOwner, disableSendButtonObserver)
        viewModel.enableVerifyButtonLiveData.observe(viewLifecycleOwner, enableVerifyButtonObserver)
        viewModel.disableVerifyButtonLiveData.observe(viewLifecycleOwner, disableVerifyButtonObserver)
        viewModel.showProgressLiveData.observe(viewLifecycleOwner, showProgressObserver)
        viewModel.hideProgressLiveData.observe(viewLifecycleOwner, hideProgressObserver)
        viewModel.errorMessageLiveData.observe(viewLifecycleOwner, errorMessageObserver)

    }

    private fun check() {
        binding.addCardButton.isEnabled =
            enables[0] && enables[1] && enables[2]
    }

    private val backToMainScreenObserver = Observer<Unit> {
        findNavController().popBackStack()
    }

    private val enableSendButtonObserver = Observer<Unit> {
        binding.addCardButton.isEnabled=true
    }

    private val disableSendButtonObserver = Observer<Unit> {
        binding.addCardButton.isEnabled=false
    }

    private val enableVerifyButtonObserver = Observer<Unit> {
        binding.verifyButton.visibility=View.VISIBLE
    }

    private val disableVerifyButtonObserver = Observer<Unit> {
        binding.verifyButton.visibility=View.GONE
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

    private fun checkCode() = binding.inputCode.text.length == 6
    private fun checkNumber() = binding.inputPan.text.length == 16
    private fun checkExp() = (binding.inputExp.text.length == 5) && (binding.inputExp.text.toString()[2] == '/')
    private fun checkName() = binding.inputCardName.text.isNotEmpty()

    private fun getTextWatcher(block: (String) -> Unit) =
        object : TextWatcher {
            override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {}

            override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
                block.invoke(p0?.toString()!!)
            }

            override fun afterTextChanged(p0: Editable?) {}
        }


}