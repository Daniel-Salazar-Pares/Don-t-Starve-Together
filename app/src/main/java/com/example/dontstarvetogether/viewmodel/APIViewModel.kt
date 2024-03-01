package com.example.dontstarvetogether.viewmodel

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.dontstarvetogether.model.Room.Character
import com.example.dontstarvetogether.api.Repository
import com.example.dontstarvetogether.model.character.Data
import com.example.dontstarvetogether.model.character.DataItem
import com.example.dontstarvetogether.model.crockpot_recipes.DataRecepies
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class APIViewModel: ViewModel() {
    private val repository = Repository()
    private val _loading = MutableLiveData(true)
    val loading = _loading
    fun setLoading(value: Boolean){
        _loading.value = value
    }
    private val _searchText = MutableLiveData<String>()
    val searchText: MutableLiveData<String> = _searchText

    private val _isSearchBarVisible = MutableLiveData<Boolean>(false)
    val isSearchBarVisible: MutableLiveData<Boolean> = _isSearchBarVisible
    fun changeSearchBarVisibility(){
        _isSearchBarVisible.value = !_isSearchBarVisible.value!!
    }
    fun onSearchTextChange(newText: String) {
        _searchText.value = newText
    }
    private val _show = MutableLiveData(false)
    var show = _show

    private val _chosen = MutableLiveData(false)
    val chosen = _chosen
    fun setChosen(value: Boolean){
        _chosen.value = value
    }

    fun setShow(value: Boolean){
        _show.value = value
    }

    private val _characters = MutableLiveData<Data>()
    val characters = _characters

    fun getCharacters(){
        CoroutineScope(Dispatchers.IO).launch {
            val response = repository.getAllCharacters()
            withContext(Dispatchers.Main) {
                if(response.isSuccessful){
                    _characters.value = response.body()
                    _loading.value = false
                }
                else{
                    Log.e("Error :", response.message())
                }
            }
        }
    }

    private val _recepies = MutableLiveData<DataRecepies>()
    val recepies = _recepies
    fun getRecepies(){
        CoroutineScope(Dispatchers.IO).launch {
            val response = repository.getAllRecepies()
            withContext(Dispatchers.Main) {
                if(response.isSuccessful){
                    _recepies.value = response.body()
                    _loading.value = false
                }
                else{
                    Log.e("Error :", response.message())
                }
            }
        }
    }


    private val _isFavorite = MutableLiveData<Boolean>(false)
    val isFavorite = _isFavorite

    private val _favoriteCharacters = MutableLiveData<MutableList<Character>>()
    val favoriteCharacters = _favoriteCharacters

    fun getFavoriteCharacters(){
        CoroutineScope(Dispatchers.IO).launch {
            val response = repository.getFavoriteCharacters()
            withContext(Dispatchers.Main) {
                _favoriteCharacters.value = response
            }
        }
    }

    fun isFavorite(character: Character){
        CoroutineScope(Dispatchers.IO).launch {
            val response = repository.isFavorite(character)
            withContext(Dispatchers.Main) {
                _isFavorite.value = response
            }
        }
    }


    fun saveAsFavorite(character: Character){
        CoroutineScope(Dispatchers.IO).launch {
            repository.saveAsFavorite(character)
        }
    }

    fun deleteFavorite(character: Character){
        CoroutineScope(Dispatchers.IO).launch {
            repository.deleteFavorite(character)
        }
    }





}



