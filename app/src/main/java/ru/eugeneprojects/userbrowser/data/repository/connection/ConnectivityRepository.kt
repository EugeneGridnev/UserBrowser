package ru.eugeneprojects.userbrowser.data.repository.connection

import kotlinx.coroutines.flow.Flow

interface ConnectivityRepository {

    val isConnected: Flow<Boolean>
}