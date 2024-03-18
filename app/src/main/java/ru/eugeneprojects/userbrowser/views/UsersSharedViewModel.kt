package ru.eugeneprojects.userbrowser.views

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import androidx.paging.cachedIn
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.stateIn
import ru.eugeneprojects.userbrowser.data.models.Result
import ru.eugeneprojects.userbrowser.data.repository.network.UsersRepository
import ru.eugeneprojects.userbrowser.data.repository.paging.UserPagingSource
import ru.eugeneprojects.userbrowser.util.Constants

class UsersSharedViewModel(private val usersRepository: UsersRepository) : ViewModel() {

    val products: StateFlow<PagingData<Result>> = Pager(
        config = PagingConfig(
            Constants.PAGE_SIZE,
            enablePlaceholders = false,
            initialLoadSize = Constants.PAGE_SIZE
        ),
        pagingSourceFactory = { UserPagingSource(usersRepository) }
    ).flow
        .cachedIn(viewModelScope)
        .stateIn(viewModelScope, SharingStarted.Lazily, PagingData.empty())
}