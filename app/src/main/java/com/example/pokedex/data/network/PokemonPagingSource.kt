package com.example.pokedex.data.network

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.example.pokedex.data.repositories.PokemonRepository
import com.example.pokedex.domain.models.Pokemon


class PokemonPagingSource (
    private val repository: PokemonRepository,
) : PagingSource<Int, Pokemon>() {

    override fun getRefreshKey(state: PagingState<Int, Pokemon>): Int? {
        return state.anchorPosition?.let { position ->
            val page = state.closestPageToPosition(position)
            page?.prevKey?.minus(1)?: page?.nextKey?.plus(1)
        }
    }

    override suspend fun load(
        params: LoadParams<Int>
    ): LoadResult<Int, Pokemon> {

        return try {
            val page = params.key ?: 0
            val limit = 20
            val result = repository.getPokemonList(limit = limit, offset = page * limit)

            if (result.isSuccess) {
                val data = result.getOrNull() ?: emptyList()

                LoadResult.Page(
                    data = data,
                    prevKey = if (page > 0) page - 1 else null,
                    nextKey = if (data.isNotEmpty()) page + 1 else null
                )
            } else {
                LoadResult.Error(result.exceptionOrNull() ?: Exception("Unknown error"))
            }

        } catch (ex: Exception){
            LoadResult.Error(ex)
        }
    }
}