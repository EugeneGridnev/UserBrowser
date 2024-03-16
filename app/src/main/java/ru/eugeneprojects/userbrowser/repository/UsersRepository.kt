package ru.eugeneprojects.userbrowser.repository

import retrofit2.Response
import ru.eugeneprojects.userbrowser.models.Result
import ru.eugeneprojects.userbrowser.models.User

interface UsersRepository {

    suspend fun getRandomUser(): Response<User>
}