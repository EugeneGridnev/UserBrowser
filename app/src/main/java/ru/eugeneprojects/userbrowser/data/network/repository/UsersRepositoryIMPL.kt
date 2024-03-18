package ru.eugeneprojects.userbrowser.data.network.repository

import ru.eugeneprojects.userbrowser.api.RetrofitInstance

class UsersRepositoryIMPL() : UsersRepository {
    override suspend fun getRandomUser() = RetrofitInstance.api.getUsers()
}