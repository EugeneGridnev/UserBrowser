package ru.eugeneprojects.userbrowser.data.room.repository

import androidx.paging.PagingSource
import ru.eugeneprojects.userbrowser.data.models.User

interface UsersDBRepository {

    suspend fun insert(users: List<User>)

    fun getUsers(): PagingSource<Int, User>

    suspend fun clear()
}