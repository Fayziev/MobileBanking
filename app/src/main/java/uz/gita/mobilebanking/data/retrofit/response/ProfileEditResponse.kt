package uz.gita.mobilebanking.data.retrofit.response

data class ProfileEditResponse(
    val data: Data
)

data class Data(

    val firstName: String,
    val lastName: String,
    val password: String,
    val phone: String
)
