package ru.eugeneprojects.userbrowser.api

import retrofit2.Response
import retrofit2.http.GET
import ru.eugeneprojects.userbrowser.data.models.Users

interface UserAPI {

    @GET("/api")
    suspend fun getUsers(): Response<Users>
}