package com.carlos.pokemen.viewmodels

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.PagingData
import androidx.paging.cachedIn
import com.carlos.data.MainRepository
import com.carlos.database.entity.PokemonEntity
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.emptyFlow
import kotlinx.coroutines.flow.flatMapLatest
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(private val mainRepository: MainRepository) : ViewModel() {

    private var _pokemon = mutableStateOf<Flow<PagingData<PokemonEntity>>>(emptyFlow())
    val pokemon: State<Flow<PagingData<PokemonEntity>>> = _pokemon

    private var _colorValue = mutableStateOf(-7839624)
    val color = _colorValue



    init {
        getPokemonList()
    }



    private fun  getPokemonList() = viewModelScope.launch {
        _pokemon.value = mainRepository.fetchPokemonListTwo()
    }





    fun setColor(color: Int){
        _colorValue.value = color
    }




}





