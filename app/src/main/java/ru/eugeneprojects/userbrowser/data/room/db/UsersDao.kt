package ru.eugeneprojects.userbrowser.data.room.db

import androidx.paging.PagingSource
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy.Companion.REPLACE
import androidx.room.Query
import ru.eugeneprojects.userbrowser.data.models.User

@Dao
interface UsersDao {
    @Insert(onConflict = REPLACE)
    suspend fun saveUsers(users: List<User>)

    @Query("SELECT * FROM table_user")
    fun getUsers(): PagingSource<Int, User>
}