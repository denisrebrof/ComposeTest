package com.denisrebrof.utils

import androidx.compose.runtime.Composable
import androidx.paging.PagingData
import androidx.paging.compose.LazyPagingItems
import androidx.paging.compose.collectAsLazyPagingItems
import androidx.paging.map
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.flow.map

fun <T : Any, O : Any> Flow<PagingData<T>>.mapPagingItems(
    mapper: suspend (T) -> O
): Flow<PagingData<O>> = map { pagingData ->
    pagingData.map(mapper)
}

@Composable
fun <T : Any> List<T>.toLazyPagingItems(): LazyPagingItems<T> {
    return PagingData.from(this).let(::flowOf).collectAsLazyPagingItems()
}