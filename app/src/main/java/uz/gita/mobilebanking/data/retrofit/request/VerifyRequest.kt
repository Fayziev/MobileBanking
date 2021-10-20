package uz.gita.mobilebanking.data.retrofit.request

data class VerifyRequest(
    val phone: String,
    val code: String
)