package ru.eugeneprojects.userbrowser.di

import android.content.Context
import androidx.room.Room
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import ru.eugeneprojects.userbrowser.data.room.db.UsersDatabase

@Module
@InstallIn(SingletonComponent::class)
object DatabaseModule {

    @Provides
    fun createDAO(usersDatabase: UsersDatabase) = usersDatabase.getUsersDao()

    @Provides
    fun createDatabase(@ApplicationContext context: Context) =
        Room.databaseBuilder(
            context.applicationContext,
            UsersDatabase::class.java,
            "password_db.db"
        ).build()
}