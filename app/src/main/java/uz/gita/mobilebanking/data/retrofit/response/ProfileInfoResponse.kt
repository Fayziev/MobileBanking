package uz.gita.mobilebanking.data.retrofit.response

data class ProfileInfoResponse(
    val data: DataUser
)

data class DataUser(

    val firstName: String,
    val lastName: String,
    val password: String,
    val phone: String
)
