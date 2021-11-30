package uz.gita.mobilebanking.di

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import uz.gita.mobilebanking.data.retrofit.api.AuthApi
import uz.gita.mobilebanking.data.retrofit.api.AuthCardApi
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class ApiModule {

    @Provides
    @Singleton
    fun getAuthApi(retrofit: Retrofit): AuthApi = retrofit.create(AuthApi::class.java)

    @Provides
    @Singleton
    fun getCardApi(retrofit: Retrofit): AuthCardApi = retrofit.create(AuthCardApi::class.java)

}