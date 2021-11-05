package uz.gita.mobilebanking.utils

import android.util.Log
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.viewbinding.ViewBinding
import timber.log.Timber
import uz.gita.mobilebanking.data.models.StartScreenEnum

fun String.startScreen(): StartScreenEnum {
    return if (this == StartScreenEnum.LOGIN.name) StartScreenEnum.LOGIN
    else StartScreenEnum.MAIN
}

fun timber(message: String, tag: String = "TTT") {
    Timber.tag(tag).d(message)
}

fun myLog(message: String, tag: String = "TTT") {
    Log.d(tag, message)
}

fun Fragment.showToast(message: String) {
    Toast.makeText(this.requireContext(), message, Toast.LENGTH_SHORT).show()
}
fun <T : ViewBinding> T.scope(block : T.() ->Unit) {
    block(this)
}