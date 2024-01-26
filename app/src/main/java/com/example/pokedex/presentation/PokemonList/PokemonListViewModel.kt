package com.example.pokedex.presentation.PokemonList

import android.graphics.Bitmap
import android.graphics.drawable.BitmapDrawable
import android.graphics.drawable.Drawable
import androidx.compose.ui.graphics.Color
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.ExperimentalPagingApi
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.cachedIn
import androidx.paging.map
import androidx.palette.graphics.Palette
import com.example.pokedex.data.network.PokemonRemoteMediator
import com.example.pokedex.data.repositories.PokemonRepository
import com.example.pokedex.domain.mappers.PokemonMapper.toPokemon
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.flow.map
import javax.inject.Inject


@HiltViewModel
class PokemonListViewModel @Inject constructor(
    private val repository: PokemonRepository,
    private val remoteMediator: PokemonRemoteMediator
): ViewModel() {

    private val _pokemonListState = MutableStateFlow(PokemonListState())
    val pokemonListState: StateFlow<PokemonListState> by lazy {
        //loadData()
        _pokemonListState
    }

    @OptIn(ExperimentalPagingApi::class)
    val pokemonPager = Pager(
        config = PagingConfig(
            pageSize = 20,
            enablePlaceholders = false
        ),
        remoteMediator = remoteMediator,
        pagingSourceFactory = {
            repository.getPagingSource()
        }
    ).flow
        .map {pagingData ->
            pagingData.map { it.toPokemon() }
        }
        .cachedIn(viewModelScope)
        .flowOn(Dispatchers.IO)

    fun calcDominantColor(drawable: Drawable, onFinish: (Color) -> Unit) {
        val bmp = (drawable as BitmapDrawable).bitmap.copy(Bitmap.Config.ARGB_8888, true)

        Palette.from(bmp).generate { palette ->
            palette?.dominantSwatch?.rgb?.let { colorValue ->
                onFinish(Color(colorValue))
            }
        }
    }

    /*val pokemonPager = Pager(
        PagingConfig(pageSize = 20)
    ){
        PokemonPagingSource(repository)
    }.flow.cachedIn(viewModelScope)*/


    /*private fun loadData() {
        viewModelScope.launch {
            repository.getPokemonList(limit = 20, offset = 0)
                .onSuccess { results ->
                    _pokemonListState.update {
                        it.copy(results = results, isLoading = false, hasError = false)
                    }
                }
                .onFailure {
                     _pokemonListState.update {
                         it.copy(isLoading = false, hasError = true)
                     }
                }
        }
    }*/
}

