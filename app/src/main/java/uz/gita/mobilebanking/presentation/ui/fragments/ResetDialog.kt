package uz.gita.mobilebanking.presentation.ui.fragments

import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.View
import android.view.ViewGroup
import android.view.WindowManager
import androidx.fragment.app.DialogFragment
import by.kirich1409.viewbindingdelegate.viewBinding
import dagger.hilt.android.AndroidEntryPoint
import uz.gita.mobilebanking.R
import uz.gita.mobilebanking.databinding.DialogResetBinding
import uz.gita.mobilebanking.utils.scope

class ResetDialog : DialogFragment(R.layout.dialog_reset) {
    private val binding by viewBinding(DialogResetBinding::bind)
    private var listener: ((String) -> Unit)? = null

    override fun onResume() {
        super.onResume()
        val params: ViewGroup.LayoutParams = dialog!!.window!!.attributes
        params.width = ViewGroup.LayoutParams.MATCH_PARENT
        params.height = ViewGroup.LayoutParams.WRAP_CONTENT
        dialog!!.window!!.attributes = params as WindowManager.LayoutParams
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) = binding.scope {
        phoneInput.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {}
            override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
                check()
            }

            override fun afterTextChanged(p0: Editable?) {}
        })
        sendLoginButton.setOnClickListener { listener?.invoke(phoneInput.text.toString()); dismiss() }
    }

    private fun check() {
        binding.sendLoginButton.isEnabled =
            (binding.phoneInput.text.toString().length == 13 && binding.phoneInput.text.toString().startsWith("+998", true))
    }

    fun setListener(f: (String) -> Unit) {
        listener = f
    }
}