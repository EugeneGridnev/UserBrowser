package ru.eugeneprojects.userbrowser.data.room.repository

import androidx.paging.PagingSource
import ru.eugeneprojects.userbrowser.data.models.User
import ru.eugeneprojects.userbrowser.data.room.db.UsersDao
import javax.inject.Inject

class UsersDBRepositoryIMPL @Inject constructor(private val dao: UsersDao): UsersDBRepository {
    override suspend fun insert(users: List<User>) {
        dao.saveUsers(users)
    }

    override fun getUsers(): PagingSource<Int, User> {
        return dao.getUsers()
    }

    override suspend fun clear() {
        dao.clear()
    }
}