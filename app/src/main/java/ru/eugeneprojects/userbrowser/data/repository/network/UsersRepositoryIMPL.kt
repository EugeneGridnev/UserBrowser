package ru.eugeneprojects.userbrowser.data.repository.network

import ru.eugeneprojects.userbrowser.api.RetrofitInstance
import ru.eugeneprojects.userbrowser.data.repository.network.UsersRepository

class UsersRepositoryIMPL() : UsersRepository {
    override suspend fun getRandomUser() = RetrofitInstance.api.getUsers()
}