package uz.gita.mobilebanking.di

import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import uz.gita.mobilebanking.domain.AppRepository
import uz.gita.mobilebanking.domain.impl.AppRepositoryImpl
import uz.gita.mobilebanking.domain.impl.TestRepositoryImpl
import javax.inject.Singleton


@Module
@InstallIn(SingletonComponent::class)
abstract class RepositoryModule {

    @Binds
    @Singleton
    abstract fun getAppRepository(repository : AppRepositoryImpl) : AppRepository

}


