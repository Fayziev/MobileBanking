package uz.gita.mobilebanking.ui.fragments

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import by.kirich1409.viewbindingdelegate.viewBinding
import uz.gita.mobilebanking.R
import uz.gita.mobilebanking.databinding.ScreenAuthBinding
import uz.gita.mobilebanking.databinding.ScreenSplashBinding

class SplashScreen: Fragment(R.layout.screen_splash) {

    private val binding by viewBinding(ScreenSplashBinding::bind)
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {


    }
}