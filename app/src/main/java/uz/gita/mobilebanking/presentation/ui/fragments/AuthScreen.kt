package uz.gita.mobilebanking.presentation.ui.fragments

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import by.kirich1409.viewbindingdelegate.viewBinding
import uz.gita.mobilebanking.R
import uz.gita.mobilebanking.databinding.ScreenAuthBinding

class AuthScreen : Fragment(R.layout.screen_auth) {
    private val binding by viewBinding(ScreenAuthBinding::bind)
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {

        binding.loginBtn.setOnClickListener {
            findNavController().navigate(R.id.action_authScreen_to_loginScreen)
        }

        binding.registerBtn.setOnClickListener {
            findNavController().navigate(R.id.action_authScreen_to_registerScreen)
        }
    }
}