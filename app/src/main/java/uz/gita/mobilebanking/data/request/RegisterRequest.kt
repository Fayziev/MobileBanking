package uz.gita.mobilebanking.data.request

data class RegisterRequest(
    val firstName: String,
    val lastName: String,
    val phone: String,
    val password: String,
    val status: Int
)