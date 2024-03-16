package ru.eugeneprojects.userbrowser.api

import retrofit2.Response
import retrofit2.http.GET
import ru.eugeneprojects.userbrowser.models.User

interface UserAPI {

    @GET("/api")
    suspend fun getProducts(): Response<User>
}