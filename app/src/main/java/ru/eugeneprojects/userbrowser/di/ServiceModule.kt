package ru.eugeneprojects.userbrowser.di

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import ru.eugeneprojects.userbrowser.api.UserAPI
import ru.eugeneprojects.userbrowser.util.Constants

@Module
@InstallIn(SingletonComponent::class)
internal object ServiceModule {

    @Provides
    fun provideUserApi(): UserAPI = Retrofit.Builder()
        .baseUrl(Constants.BASE_URL)
        .addConverterFactory(GsonConverterFactory.create())
        .build()
        .create(UserAPI::class.java)
}