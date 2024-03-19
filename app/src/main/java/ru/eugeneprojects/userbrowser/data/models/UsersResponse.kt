package ru.eugeneprojects.userbrowser.data.models

data class UsersResponse(
    val info: Info,
    val results: List<User>
)