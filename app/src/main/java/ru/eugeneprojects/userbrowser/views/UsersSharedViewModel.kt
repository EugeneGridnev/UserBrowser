package ru.eugeneprojects.userbrowser.views

import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import androidx.lifecycle.viewModelScope
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import androidx.paging.cachedIn
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.stateIn
import ru.eugeneprojects.userbrowser.data.models.Result
import ru.eugeneprojects.userbrowser.data.network.connection.ConnectivityRepository
import ru.eugeneprojects.userbrowser.data.network.repository.UsersRepository
import ru.eugeneprojects.userbrowser.data.paging.UserPagingSource
import ru.eugeneprojects.userbrowser.util.Constants
import javax.inject.Inject

@HiltViewModel
class UsersSharedViewModel @Inject constructor(
    private val usersRepository: UsersRepository,
    private val connectivityRepository: ConnectivityRepository
) : ViewModel() {

    val isOnline = connectivityRepository.isConnected.asLiveData()

    val users: StateFlow<PagingData<Result>> = Pager(
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