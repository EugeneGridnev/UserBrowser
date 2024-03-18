package ru.eugeneprojects.userbrowser.data.room.db

import androidx.room.Database
import androidx.room.TypeConverters
import ru.eugeneprojects.userbrowser.data.models.Result


@Database(
    version = 1,
    entities = [ Result::class ]
)
@TypeConverters(Converters::class)
abstract class UsersDatabase {
}