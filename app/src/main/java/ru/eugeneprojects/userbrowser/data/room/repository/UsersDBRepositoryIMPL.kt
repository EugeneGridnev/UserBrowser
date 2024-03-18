package ru.eugeneprojects.userbrowser.data.room.repository

import androidx.paging.PagingSource
import ru.eugeneprojects.userbrowser.data.models.Result
import ru.eugeneprojects.userbrowser.data.room.db.UsersDao

class UsersDBRepositoryIMPL (private val dao: UsersDao): UsersDBRepository {
    override suspend fun insert(users: List<Result>) {
        dao.saveUsers(users)
    }

    override suspend fun getUsers(): PagingSource<Int, Result> {
        return dao.getUsers()
    }
}