package uz.gita.mobilebanking.domain

import uz.gita.mobilebanking.data.models.StartScreenEnum

interface AppRepository {
    fun startScreen(): StartScreenEnum
}