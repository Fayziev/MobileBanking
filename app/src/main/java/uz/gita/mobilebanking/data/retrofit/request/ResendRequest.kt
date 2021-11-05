package uz.gita.mobilebanking.data.retrofit.request

data class ResendRequest(
    val phone: String,
    val password: String
)