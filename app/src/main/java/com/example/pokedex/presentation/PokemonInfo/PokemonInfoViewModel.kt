package com.example.pokedex.presentation.PokemonInfo

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.pokedex.data.repositories.PokemonRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class PokemonInfoViewModel @Inject constructor(
    private val repository: PokemonRepository,
    private val stateHandle: SavedStateHandle,
): ViewModel() {

    private val _pokemonInfoState = MutableStateFlow(PokemonInfoState())
    val pokemonInfoState: StateFlow<PokemonInfoState> by lazy {
        getPokemonInfoByName()
        _pokemonInfoState
    }

    private val name by lazy { requireNotNull(stateHandle.get<String>("name")) }

    private fun getPokemonInfoByName() {
        viewModelScope.launch {
            repository.getPokemonInfoByName(name = name)
                .onSuccess { info ->
                    _pokemonInfoState.update {
                        it.copy(info = info, isLoading = false, hasError = false)
                    }
                }
                .onFailure {
                    _pokemonInfoState.update {
                        it.copy(isLoading = false, hasError = true)
                    }
                }
        }
    }
}