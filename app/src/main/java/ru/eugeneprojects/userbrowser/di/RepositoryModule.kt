package ru.eugeneprojects.userbrowser.di

import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import ru.eugeneprojects.userbrowser.data.network.connection.ConnectivityRepository
import ru.eugeneprojects.userbrowser.data.network.connection.ConnectivityRepositoryIMPL
import ru.eugeneprojects.userbrowser.data.network.repository.UsersRepository
import ru.eugeneprojects.userbrowser.data.network.repository.UsersRepositoryIMPL
import ru.eugeneprojects.userbrowser.data.room.repository.UsersDBRepository
import ru.eugeneprojects.userbrowser.data.room.repository.UsersDBRepositoryIMPL

@Module
@InstallIn(SingletonComponent::class)
abstract class RepositoryModule {

    @Binds
    abstract fun providesConnectivityRepository(repositoryIMPL: ConnectivityRepositoryIMPL) : ConnectivityRepository

    @Binds
    abstract fun providesUsersRepository(repositoryIMPL: UsersRepositoryIMPL) : UsersRepository

    @Binds
    abstract fun providesUsersDBRepository(repositoryIMPL: UsersDBRepositoryIMPL) : UsersDBRepository
}