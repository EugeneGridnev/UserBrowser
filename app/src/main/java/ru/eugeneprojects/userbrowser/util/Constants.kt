package ru.eugeneprojects.userbrowser.util

import androidx.paging.PagingConfig

class Constants {
    companion object {
        const val BASE_URL = "https://randomuser.me"
        const val PAGE_SIZE = 10
        private const val PREFETCH_DISTANCE = PAGE_SIZE / 2
        val PAGING_CONFIG = PagingConfig(
            pageSize = PAGE_SIZE,
            prefetchDistance = PREFETCH_DISTANCE,
            enablePlaceholders = true
        )
    }
}