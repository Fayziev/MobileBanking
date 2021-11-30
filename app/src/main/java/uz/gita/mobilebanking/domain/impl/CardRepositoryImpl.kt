package uz.gita.mobilebanking.domain.impl

import com.google.gson.Gson
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import retrofit2.Response
import uz.gita.mobilebanking.data.pref.MyPref
import uz.gita.mobilebanking.data.retrofit.ApiClient
import uz.gita.mobilebanking.data.retrofit.api.AuthCardApi
import uz.gita.mobilebanking.data.retrofit.request.AddCardRequest
import uz.gita.mobilebanking.data.retrofit.request.DeleteCardRequest
import uz.gita.mobilebanking.data.retrofit.request.EditCardRequest
import uz.gita.mobilebanking.data.retrofit.request.VerifyCardRequest
import uz.gita.mobilebanking.data.retrofit.response.AllCardResponse
import uz.gita.mobilebanking.data.retrofit.response.BasicResponse
import uz.gita.mobilebanking.data.retrofit.response.CardData
import uz.gita.mobilebanking.data.retrofit.response.CardList
import uz.gita.mobilebanking.domain.CardRepository
import javax.inject.Inject

class CardRepositoryImpl @Inject constructor() : CardRepository {
    private val api = ApiClient.retrofit.create(AuthCardApi::class.java)
    private val pref = MyPref.getPref()
    private val gson = Gson()

    override fun cardAdd(request: AddCardRequest): Flow<Result<Unit>> = flow {

        emit(getResult(api.add(pref.accessToken, request)))
    }

    override fun cardVerify(request: VerifyCardRequest): Flow<Result<Unit>> = flow {
        emit(getResult(api.verify(pref.accessToken, request)))
    }

    override fun cardEdit(request: EditCardRequest): Flow<Result<Unit>> = flow {
        emit(getResult(api.edit(pref.accessToken, request)))
    }

    override fun cardDelete(request: DeleteCardRequest): Flow<Result<Unit>> = flow {
        emit(getResult(api.delete(pref.accessToken, request)))
    }

    override fun cardGet(): Flow<Result<List<CardData>>> = flow {

        try {
            val response = api.get(pref.accessToken)
            if (response.code() in 200..299) {
                emit(Result.success(response.body()?.data!!.data))
            } else {
                emit(Result.failure(Throwable(response.errorBody().toString())))
            }
        } catch (e: Exception) {
            emit(Result.failure(Throwable("Server bilan muammo bo'ldi")))
        }
    }

    private fun getResult(response: Response<BasicResponse>): Result<Unit> {
        return try {
            if (response.code() in 200..299) {
                Result.success(Unit)
            } else {
                Result.failure(Throwable(response.errorBody().toString()))
            }
        } catch (e: Exception) {
            Result.failure(Throwable("Server bilan muammo bo'ldi"))
        }
    }

    private fun getCardResult(response: Response<AllCardResponse>): Pair<Result<Unit>, CardList> {
        return try {
            if (response.code() in 200..299) {
                Pair(Result.success(Unit), response.body()?.data!!)
            } else {
                Pair(Result.failure(Throwable(response.errorBody().toString())), CardList(ArrayList()))
            }
        } catch (e: Exception) {
            Pair(Result.failure(Throwable("Server bilan muammo bo'ldi")), CardList(ArrayList()))
        }
    }
}