package ru.eugeneprojects.userbrowser.repository

import ru.eugeneprojects.userbrowser.api.UserAPI

class UserRepositoryIMPL(private val api: UserAPI) : UsersRepository {
    override suspend fun getRandomUser() = api.getProducts()
}