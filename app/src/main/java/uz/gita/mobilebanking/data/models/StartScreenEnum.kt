package uz.gita.mobilebanking.data.models

enum class StartScreenEnum {
    LOGIN, MAIN
}

fun String.getStartScreen(): StartScreenEnum {
    return if (this == StartScreenEnum.LOGIN.name) StartScreenEnum.LOGIN
    else StartScreenEnum.MAIN
}