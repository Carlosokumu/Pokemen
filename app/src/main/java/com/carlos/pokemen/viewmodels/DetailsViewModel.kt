package com.carlos.pokemen.viewmodels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.airbnb.lottie.compose.rememberLottieComposition
import com.carlos.data.MainRepository
import com.carlos.model.PokemonInfo
import com.carlos.model.PokemonState
import com.carlos.model.Specie
import com.carlos.model.Stats
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
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


    fun fetchPokemonDetails(name: String){
        viewModelScope.launch {
           val pokemonInfo = mainRepository.fetchPokemonInfo(name)
            if (pokemonInfo != null){
                setDetails(pokemonInfo)
                setStats(pokemonInfo.stats)
                mutablePokemonState.value = PokemonState.Result
            }
            else{
               mutablePokemonState.value = PokemonState.Error("Couldn't fetch pokemon Info")
            }
        }
    }


    private fun setDetails(pokemonInfo: PokemonInfo){
       _pokemonInfo.value = pokemonInfo
    }

    private fun setStats(stats: List<Stats>){
        val mappedStats = stats.map {
            (it.base_stat/100).toFloat()
        }
        _stats.value = mappedStats
    }



}