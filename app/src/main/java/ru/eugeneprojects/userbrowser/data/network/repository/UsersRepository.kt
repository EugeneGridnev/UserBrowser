package ru.eugeneprojects.userbrowser.data.network.repository

import retrofit2.Response
import ru.eugeneprojects.userbrowser.data.models.UsersResponse

interface UsersRepository {

    suspend fun getUsers(): Response<UsersResponse>
}