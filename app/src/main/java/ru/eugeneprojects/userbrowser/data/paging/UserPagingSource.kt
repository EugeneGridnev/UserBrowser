package ru.eugeneprojects.userbrowser.data.paging

import androidx.paging.PagingSource
import androidx.paging.PagingState
import retrofit2.HttpException
import ru.eugeneprojects.userbrowser.data.models.Result
import ru.eugeneprojects.userbrowser.data.network.repository.UsersRepository

class UserPagingSource (
    val userRepository: UsersRepository
) : PagingSource<Int, Result>() {
    override fun getRefreshKey(state: PagingState<Int, Result>): Int? {
        val anchorPosition = state.anchorPosition ?: return null
        val page = state.closestPageToPosition(anchorPosition) ?: return null
        return page.prevKey?.plus(1) ?: page.nextKey?.minus(1)
    }

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, Result> {

        val pageNumber = params.key ?: INITIAL_PAGE_NUMBER

        try {
            val response = userRepository.getRandomUser()

            if (response.isSuccessful) {
                val users = checkNotNull(response.body()).results.map { user ->
                    Result(
                        0,
                        user.cell,
                        user.dob,
                        user.email,
                        user.gender,
                        user.id,
                        user.location,
                        user.login,
                        user.name,
                        user.nat,
                        user.phone,
                        user.picture,
                        user.registered
                    )

                }

                val nextPageNumber = if (response.body()?.info?.page == 1) null else pageNumber + 1
                val prevPageNumber = if (pageNumber > 0) pageNumber - 1 else null

                return LoadResult.Page(users, prevPageNumber, nextPageNumber)
            } else {
                return LoadResult.Error(HttpException(response))
            }
        } catch (e: Exception) {
            return LoadResult.Error(e)
        }
    }

    companion object {
        const val INITIAL_PAGE_NUMBER = 0
    }
}