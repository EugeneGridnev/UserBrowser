package ru.eugeneprojects.userbrowser.api

import retrofit2.Response
import retrofit2.http.GET
import ru.eugeneprojects.userbrowser.data.models.UsersResponse

interface UserAPI {

    @GET("/api/?results=10")
    suspend fun getUsers(): Response<UsersResponse>
}