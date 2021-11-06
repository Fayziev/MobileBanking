package uz.gita.mobilebanking.di

import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import uz.gita.mobilebanking.domain.AppRepository
import uz.gita.mobilebanking.domain.AuthRepository
import uz.gita.mobilebanking.domain.CardRepository
import uz.gita.mobilebanking.domain.impl.AppRepositoryImpl
import uz.gita.mobilebanking.domain.impl.AuthRepositoryImpl
import uz.gita.mobilebanking.domain.impl.CardRepositoryImpl
import javax.inject.Singleton


@Module
@InstallIn(SingletonComponent::class)
abstract class RepositoryModule {

    @Binds
    @Singleton
    abstract fun getAppRepository(repository: AppRepositoryImpl): AppRepository

    @Binds
    @Singleton
    abstract fun getAuthRepository(repository: AuthRepositoryImpl): AuthRepository

    @Binds
    @Singleton
    abstract fun getCardRepository(impl: CardRepositoryImpl): CardRepository
}


