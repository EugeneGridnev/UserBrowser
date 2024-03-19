package ru.eugeneprojects.userbrowser.data.room.db

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import ru.eugeneprojects.userbrowser.data.models.Result


@Database(
    version = 1,
    entities = [ Result::class ]
)
@TypeConverters(Converters::class)
abstract class UsersDatabase : RoomDatabase() {

    abstract fun getUsersDao(): UsersDao
}