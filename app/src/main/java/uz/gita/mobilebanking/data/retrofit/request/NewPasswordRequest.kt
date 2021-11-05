package uz.gita.mobilebanking.data.retrofit.request

data class NewPasswordRequest(
    val phone:String,
    val newPassword:String,
    val code:String
)