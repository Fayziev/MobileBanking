package uz.gita.mobilebanking.data.retrofit.response

import com.google.gson.annotations.SerializedName

data class VerifyResponse(
    val data: TokenData
)

data class TokenData(
    @SerializedName("access_token")
    val accessToken: String,
    @SerializedName("refresh_token")
    val refreshToken: String
)