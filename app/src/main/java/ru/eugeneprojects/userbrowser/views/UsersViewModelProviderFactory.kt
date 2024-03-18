package ru.eugeneprojects.userbrowser.views

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import ru.eugeneprojects.userbrowser.data.network.connection.ConnectivityRepository
import ru.eugeneprojects.userbrowser.data.network.repository.UsersRepository

class UsersViewModelProviderFactory(
    private val productsRepository: UsersRepository,
    private val connectivityRepository: ConnectivityRepository
) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return UsersSharedViewModel(productsRepository, connectivityRepository) as T
    }
}