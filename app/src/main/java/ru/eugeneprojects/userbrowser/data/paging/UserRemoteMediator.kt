package ru.eugeneprojects.userbrowser.data.paging

import androidx.paging.ExperimentalPagingApi
import androidx.paging.LoadType
import androidx.paging.PagingState
import androidx.paging.RemoteMediator
import retrofit2.HttpException
import ru.eugeneprojects.userbrowser.data.models.User
import ru.eugeneprojects.userbrowser.data.network.repository.UsersRepository
import ru.eugeneprojects.userbrowser.data.room.repository.UsersDBRepository
import java.io.IOException

@OptIn(ExperimentalPagingApi::class)
class UserRemoteMediator (
    private val usersRepository: UsersRepository,
    private val usersDBRepository: UsersDBRepository
) : RemoteMediator<Int, User>() {

    override suspend fun initialize(): InitializeAction {
        return InitializeAction.SKIP_INITIAL_REFRESH
    }

    override suspend fun load(
        loadType: LoadType,
        state: PagingState<Int, User>
    ): MediatorResult {

        return try {
            if (loadType == LoadType.PREPEND) {
                return MediatorResult.Success(endOfPaginationReached = true)
            }

            val userResponse = usersRepository.getUsers(state.config.pageSize)

            if (!userResponse.isSuccessful || userResponse.body() == null) {
                return MediatorResult.Error(HttpException(userResponse))
            }

            if (loadType == LoadType.REFRESH) {
                usersDBRepository.clear()
            }

            usersDBRepository.insert(userResponse.body()!!.results)

            MediatorResult.Success(
                endOfPaginationReached = false
            )
        } catch(e: IOException) {
            MediatorResult.Error(e)
        } catch(e: HttpException) {
            MediatorResult.Error(e)
        }
   }
}