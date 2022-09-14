package com.carlos.pokemen.viewmodels

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.PagingData
import com.carlos.network.models.Pokemon
import com.carlos.pokemen.data.repository.MainRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.emptyFlow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.launch

@HiltViewModel
class MainViewModel(private val mainRepository: MainRepository) : ViewModel() {

    private var _pokemon = mutableStateOf<Flow<PagingData<Pokemon>>>(emptyFlow())
    val pokemon: State<Flow<PagingData<Pokemon>>> = _pokemon




    fun  getPokemonList() = viewModelScope.launch {
        _pokemon.value = mainRepository.fetchPokemonList()
    }


}