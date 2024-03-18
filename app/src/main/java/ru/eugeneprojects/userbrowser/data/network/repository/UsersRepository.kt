package ru.eugeneprojects.userbrowser.data.network.repository

import retrofit2.Response
import ru.eugeneprojects.userbrowser.data.models.Users

interface UsersRepository {

    suspend fun getRandomUser(): Response<Users>
}