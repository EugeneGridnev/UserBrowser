package ru.eugeneprojects.userbrowser.views

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import ru.eugeneprojects.userbrowser.data.repository.connection.ConnectivityRepository
import ru.eugeneprojects.userbrowser.data.repository.network.UsersRepository

class UsersViewModelProviderFactory(
    private val productsRepository: UsersRepository,
    private val connectivityRepository: ConnectivityRepository
) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return UsersSharedViewModel(productsRepository, connectivityRepository) as T
    }
}