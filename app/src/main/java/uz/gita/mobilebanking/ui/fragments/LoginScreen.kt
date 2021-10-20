package uz.gita.mobilebanking.ui.fragments

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import by.kirich1409.viewbindingdelegate.viewBinding
import uz.gita.mobilebanking.R
import uz.gita.mobilebanking.databinding.ScreenAuthBinding
import uz.gita.mobilebanking.databinding.ScreenLoginBinding

class LoginScreen:Fragment(R.layout.screen_login) {

    private val binding by viewBinding(ScreenLoginBinding::bind)
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {


    }
}