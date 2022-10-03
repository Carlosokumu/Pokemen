package com.carlos.pokemen.viewmodels

import android.util.Log
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.carlos.network.models.PokemonInfo
import com.carlos.network.models.PokemonState
import com.carlos.network.models.Specie
import com.carlos.network.models.Stats
import com.carlos.network.network.ApiCallResult
import com.carlos.pokemen.data.repository.MainRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.emptyFlow
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class DetailsViewModel @Inject constructor(private val mainRepository: MainRepository) : ViewModel() {

    private val mutablePokemonState: MutableStateFlow<PokemonState> =
        MutableStateFlow(PokemonState.Loading)
    val pokemonState = mutablePokemonState.asStateFlow()


    private val _pokemonInfo: MutableStateFlow<PokemonInfo> = MutableStateFlow(PokemonInfo(
        id = 0,
        name = "null",
        HP = 0,
        ATK = 0,
        DEF = 0,
        SPD = 0,
        exp = 0,
        height = 0,
        weight = 0,
        stats = emptyList(),
        types = emptyList(),
        species = Specie(name ="","url")
    ))

    val pokemonInfo = _pokemonInfo.asStateFlow()

    private val _stats: MutableStateFlow<List<Float>> = MutableStateFlow(listOf())

    val stats = _stats.asStateFlow()


    fun fetchPokemonDetails(name: String){
        viewModelScope.launch {
           when(val result = mainRepository.fetchPokemonInfo(name)){
               is ApiCallResult.ServerError -> {
                   mutablePokemonState.value = PokemonState.Error("Server Error")
               }
               is ApiCallResult.ApiCallError -> {
                   mutablePokemonState.value = PokemonState.Error("Connection Error")
               }
               is ApiCallResult.Success -> {
                   mutablePokemonState.value = PokemonState.Result(result.data)
                   Log.d("Sucess",result.data.name)
               }
           }
        }
    }


    fun setDetails(pokemonInfo: PokemonInfo){
       _pokemonInfo.value = pokemonInfo
    }

    fun setStats(stats: List<Stats>){
        val mappedStats = stats.map {
            (it.base_stat/100).toFloat()
        }
        _stats.value = mappedStats
    }

}