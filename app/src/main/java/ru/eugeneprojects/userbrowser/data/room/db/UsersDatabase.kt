package ru.eugeneprojects.userbrowser.data.room.db

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import ru.eugeneprojects.userbrowser.data.models.User


@Database(
    version = 1,
    entities = [ User::class ]
)
@TypeConverters(Converters::class)
abstract class UsersDatabase : RoomDatabase() {

    abstract fun getUsersDao(): UsersDao
}