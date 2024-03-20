package ru.eugeneprojects.userbrowser.data.network.repository

import ru.eugeneprojects.userbrowser.api.UserAPI
import javax.inject.Inject

class UsersRepositoryIMPL @Inject constructor(private val api: UserAPI) : UsersRepository {
    override suspend fun getUsers(pageSize: Int) = api.getUsers(pageSize)
}