package com.example.tokopancingapp.ui

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.asLiveData
import androidx.lifecycle.viewModelScope
import com.example.tokopancingapp.model.pancing
import com.example.tokopancingapp.repository.pancingrepository
import kotlinx.coroutines.launch

class pancingviewmodel(private val repository: pancingrepository): ViewModel() {
    val allpancing: LiveData<List<pancing>> =repository.allpancing.asLiveData()

    fun insert(pancing: pancing) = viewModelScope.launch{
        repository.insertpancing(pancing)
    }
    fun delete(pancing: pancing) =viewModelScope.launch {
        repository.deletepancing(pancing)
    }
    fun update(pancing: pancing) =viewModelScope.launch {
        repository.updatepancing(pancing)
    }
}

class PancingViewmodelFactory(private val repository: pancingrepository): ViewModelProvider.Factory {
    override fun <T :ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom((pancingviewmodel::class.java))) {
          return pancingviewmodel(repository) as T
        }
        throw IllegalArgumentException("unknown ViewModel class")
    }
}