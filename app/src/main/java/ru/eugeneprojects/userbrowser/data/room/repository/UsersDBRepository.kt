package ru.eugeneprojects.userbrowser.data.room.repository

import androidx.paging.PagingSource
import ru.eugeneprojects.userbrowser.data.models.Result

interface UsersDBRepository {

    suspend fun insert(users: List<Result>)

    //TODO возможно тут просто лист
    suspend fun getUsers(): PagingSource<Int, Result>
}