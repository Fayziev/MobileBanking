package uz.gita.mobilebanking.presentation.ui.fragments

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import by.kirich1409.viewbindingdelegate.viewBinding
import uz.gita.mobilebanking.R
import uz.gita.mobilebanking.databinding.ScreenSmsVerifyBinding

class SmsVerifyScreen : Fragment(R.layout.screen_sms_verify) {

    private val binding by viewBinding(ScreenSmsVerifyBinding::bind)
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {

    }
}