package ru.eugeneprojects.userbrowser.data.network.connection

import kotlinx.coroutines.flow.Flow

interface ConnectivityRepository {

    val isConnected: Flow<Boolean>
}