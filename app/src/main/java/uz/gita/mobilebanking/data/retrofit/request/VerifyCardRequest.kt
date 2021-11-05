package uz.gita.mobilebanking.data.retrofit.request

data class VerifyCardRequest(
    val pan:String,
    val code:String
)