package com.carlos.pokemen.viewmodels

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.carlos.network.network.ApiCallResult
import com.carlos.pokemen.data.repository.MainRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class DetailsViewModel @Inject constructor(private val mainRepository: MainRepository) : ViewModel() {




    fun fetchPokemonDetails(name: String){
        viewModelScope.launch {
           when(val result = mainRepository.fetchPokemonInfo(name)){
               is ApiCallResult.ServerError -> {

               }
               is ApiCallResult.ApiCallError -> {

               }
               is ApiCallResult.Success -> {
                   Log.d("Sucess",result.data.name)
               }
           }
        }
    }

}