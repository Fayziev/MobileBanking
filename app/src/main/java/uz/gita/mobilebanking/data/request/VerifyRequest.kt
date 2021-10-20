package uz.gita.mobilebanking.data.request

data class VerifyRequest(
    val phone: String,
    val code: String
)