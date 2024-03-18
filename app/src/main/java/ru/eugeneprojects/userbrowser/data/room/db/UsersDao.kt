package ru.eugeneprojects.userbrowser.data.room.db

import androidx.paging.PagingSource
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy.Companion.REPLACE
import androidx.room.Query
import ru.eugeneprojects.userbrowser.data.models.Result

@Dao
interface UsersDao {
    @Insert(onConflict = REPLACE)
    suspend fun saveUsers(users: List<Result>): Long

    @Query("SELECT * FROM table_user")
    suspend fun getUsers(): PagingSource<Int, Result>
}