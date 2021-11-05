package uz.gita.mobilebanking.data.retrofit.request

data class AddCardRequest(
    val pan: String,
    val exp: String,
    val cardName: String
)