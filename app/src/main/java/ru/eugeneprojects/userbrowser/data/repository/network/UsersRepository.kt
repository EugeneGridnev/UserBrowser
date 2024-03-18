package ru.eugeneprojects.userbrowser.data.repository.network

import retrofit2.Response
import ru.eugeneprojects.userbrowser.data.models.Result
import ru.eugeneprojects.userbrowser.data.models.User

interface UsersRepository {

    suspend fun getRandomUser(): Response<User>
}