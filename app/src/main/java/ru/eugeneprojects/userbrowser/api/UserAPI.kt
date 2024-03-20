package ru.eugeneprojects.userbrowser.api

import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query
import ru.eugeneprojects.userbrowser.data.models.UsersResponse
import ru.eugeneprojects.userbrowser.util.Constants

interface UserAPI {

    @GET("/api")
    suspend fun getUsers(
        @Query("results")
        results: Int = Constants.PAGE_SIZE
    ): Response<UsersResponse>
}