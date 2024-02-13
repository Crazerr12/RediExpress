package com.example.deliveryapp.di

import com.example.deliveryapp.data.repositories.DataStoreRepository
import com.example.deliveryapp.domain.repositories.NetworkRepository
import com.example.deliveryapp.domain.usecases.LogInUserUseCase
import com.example.deliveryapp.domain.usecases.RegisterUserUseCase
import com.example.deliveryapp.domain.usecases.SavePasswordUseCase
import com.example.deliveryapp.domain.usecases.SignWithGoogleUseCase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent

@Module
@InstallIn(ViewModelComponent::class)
object DomainModule {

    @Provides
    fun provideRegisterUserUseCase(networkRepository: NetworkRepository): RegisterUserUseCase {
        return RegisterUserUseCase(networkRepository = networkRepository)
    }

    @Provides
    fun provideLogInUserUseCase(networkRepository: NetworkRepository): LogInUserUseCase {
        return LogInUserUseCase(networkRepository = networkRepository)
    }

    @Provides
    fun provideSignWithGoogleUseCase(networkRepository: NetworkRepository): SignWithGoogleUseCase {
        return SignWithGoogleUseCase(networkRepository = networkRepository)
    }

    @Provides
    fun provideSavePasswordUseCase(dataStoreRepository: DataStoreRepository): SavePasswordUseCase {
        return SavePasswordUseCase(dataStoreRepository = dataStoreRepository)
    }
}