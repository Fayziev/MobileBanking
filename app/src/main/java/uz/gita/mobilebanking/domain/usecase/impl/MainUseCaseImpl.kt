package uz.gita.mobilebanking.domain.usecase.impl

import kotlinx.coroutines.flow.Flow
import uz.gita.mobilebanking.data.retrofit.request.DeleteCardRequest
import uz.gita.mobilebanking.data.retrofit.request.ResetRequest
import uz.gita.mobilebanking.data.retrofit.response.CardData
import uz.gita.mobilebanking.domain.AppRepository
import uz.gita.mobilebanking.domain.AuthRepository
import uz.gita.mobilebanking.domain.CardRepository
import uz.gita.mobilebanking.domain.usecase.MainUseCase
import javax.inject.Inject

class MainUseCaseImpl @Inject constructor(
    private val repositoryApp: AppRepository,
    private val repositoryAuth: AuthRepository,
    private val repositoryCard: CardRepository
) : MainUseCase {
    override fun getToken(): String = repositoryApp.getToken()

    override fun resetUser(request: ResetRequest): Flow<Result<Unit>> = repositoryAuth.resetUser(request)

    override fun logoutUser(): Flow<Result<Unit>> = repositoryAuth.logoutUser(repositoryApp.getToken())

    override fun cardDelete(request: DeleteCardRequest): Flow<Result<Unit>> = repositoryCard.cardDelete(request)

    override fun cardGet(): Flow<Result<List<CardData>>> = repositoryCard.cardGet()
}