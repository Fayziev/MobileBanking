package uz.gita.mobilebanking.di

import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import uz.gita.mobilebanking.domain.usecase.*
import uz.gita.mobilebanking.domain.usecase.impl.*
import uz.gita.mobilebanking.presentation.viewModel.impl.LoginViewModelImpl
import javax.inject.Singleton


@Module
@InstallIn(SingletonComponent::class)
abstract class UseCaseModule {

    @Binds
    @Singleton
    abstract fun getSplashUseCase(impl: SplashUseCaseImpl): SplashUseCase

    @Binds
    @Singleton
    abstract fun getRegisterUseCase(impl: RegisterUseCaseImpl): RegisterUseCase

    @Binds
    @Singleton
    abstract fun getMainUseCase(impl: MainUseCaseImpl): MainUseCase

    @Binds
    @Singleton
    abstract fun getVerifyUseCase(impl: VerifyUseCaseImpl): VerifyUseCase

    @Binds
    @Singleton
    abstract fun getLoginUseCase(impl: LoginViewModelImpl): LoginUseCase

    @Binds
    @Singleton
    abstract fun getResetUseCase(impl: ResetUseCaseImpl): ResetUseCase

    @Binds
    @Singleton
    abstract fun getAddCardUseCase(impl: AddCardUseCaseImpl): AddCardUseCase

}