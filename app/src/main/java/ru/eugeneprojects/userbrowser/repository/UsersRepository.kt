package ru.eugeneprojects.userbrowser.repository

import retrofit2.Response
import ru.eugeneprojects.userbrowser.models.Result

interface UsersRepository {

    suspend fun getRandomUser(): Response<Result>
}