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
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(private val mainRepository: MainRepository) : ViewModel() {

    private var _pokemon = mutableStateOf<Flow<PagingData<Pokemon>>>(emptyFlow())
    val pokemon: State<Flow<PagingData<Pokemon>>> = _pokemon

    private var _colorValue = mutableStateOf(-7839624)
    val color = _colorValue


    fun  getPokemonList() = viewModelScope.launch {
        _pokemon.value = mainRepository.fetchPokemonList()
    }


    fun setColor(color: Int){
        _colorValue.value = color
    }





}





