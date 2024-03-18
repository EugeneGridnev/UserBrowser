package ru.eugeneprojects.userbrowser.repository.network

import ru.eugeneprojects.userbrowser.api.RetrofitInstance
import ru.eugeneprojects.userbrowser.repository.network.UsersRepository

class UsersRepositoryIMPL() : UsersRepository {
    override suspend fun getRandomUser() = RetrofitInstance.api.getUsers()
}