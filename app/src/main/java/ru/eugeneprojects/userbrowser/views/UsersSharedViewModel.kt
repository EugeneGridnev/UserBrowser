package ru.eugeneprojects.userbrowser.views

import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import androidx.lifecycle.viewModelScope
import androidx.paging.ExperimentalPagingApi
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import androidx.paging.cachedIn
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.stateIn
import ru.eugeneprojects.userbrowser.data.models.User
import ru.eugeneprojects.userbrowser.data.network.connection.ConnectivityRepository
import ru.eugeneprojects.userbrowser.data.network.repository.UsersRepository
import ru.eugeneprojects.userbrowser.data.paging.UserRemoteMediator
import ru.eugeneprojects.userbrowser.data.room.repository.UsersDBRepository
import ru.eugeneprojects.userbrowser.util.Constants
import javax.inject.Inject

@HiltViewModel
class UsersSharedViewModel @Inject constructor(
    private val usersRepository: UsersRepository,
    private val usersDBRepository: UsersDBRepository,
    private val connectivityRepository: ConnectivityRepository
) : ViewModel() {

    val isOnline = connectivityRepository.isConnected.asLiveData()

    @OptIn(ExperimentalPagingApi::class)
    val users: StateFlow<PagingData<User>> = Pager(
        config = Constants.PAGING_CONFIG,
        remoteMediator = UserRemoteMediator(usersRepository, usersDBRepository),
        pagingSourceFactory = { usersDBRepository.getUsers() }
    ).flow
        .cachedIn(viewModelScope)
        .stateIn(viewModelScope, SharingStarted.Lazily, PagingData.empty())
}