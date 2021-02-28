package com.example.ftsroom

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch

class MainViewModel(application: Application) : AndroidViewModel(application) {

    private val _data = MutableLiveData<List<User>>()
    val data: LiveData<List<User>> = _data

    private val _filteredData = MutableLiveData<List<UserCallback>>()
    val filteredData: LiveData<List<UserCallback>> = _filteredData


    private val repository = MainRepository(application)

    fun getData(){
        viewModelScope.launch {
            _data.value = repository.getData()
        }
    }

    fun getFilteredData(term: String){
        viewModelScope.launch {
            _filteredData.value = repository.getFilteredData(term)
        }
    }

    fun insertUser(username: String, lastName: String){
        viewModelScope.launch {
            repository.insertUsers(User(null, username, lastName))
        }
    }
}