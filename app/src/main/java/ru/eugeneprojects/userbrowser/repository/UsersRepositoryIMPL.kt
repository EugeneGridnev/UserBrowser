package ru.eugeneprojects.userbrowser.repository

import ru.eugeneprojects.userbrowser.api.RetrofitInstance
import ru.eugeneprojects.userbrowser.api.UserAPI

class UsersRepositoryIMPL() : UsersRepository {
    override suspend fun getRandomUser() = RetrofitInstance.api.getUsers()
}