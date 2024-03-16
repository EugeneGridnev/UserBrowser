package ru.eugeneprojects.userbrowser.views

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import ru.eugeneprojects.userbrowser.repository.UsersRepository

class UsersViewModelProviderFactory(
    private val productsRepository: UsersRepository
) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return UsersSharedViewModel(productsRepository) as T
    }
}