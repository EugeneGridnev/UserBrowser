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

    override suspend fun load(
        loadType: LoadType,
        state: PagingState<Int, User>
    ): MediatorResult {

        return try {
            val loadKey = when(loadType) {
                LoadType.REFRESH -> 1
                LoadType.PREPEND -> return MediatorResult.Success(
                    endOfPaginationReached = true
                )
                LoadType.APPEND -> {
                    val lastItem = state.lastItemOrNull()
                    if(lastItem == null) {
                        1
                    } else {
                        (lastItem.userId / state.config.pageSize) + 1
                    }
                }
            }

            val users = usersRepository.getUsers()


            usersDBRepository.insert(users.body()!!.results)


            MediatorResult.Success(
                endOfPaginationReached = users.body()?.results!!.isEmpty()
            )
        } catch(e: IOException) {
            MediatorResult.Error(e)
        } catch(e: HttpException) {
            MediatorResult.Error(e)
        }
   }
}