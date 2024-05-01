package com.example.ethosconnections.viewmodel.meta

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.ethosconnections.repositories.MetaRepository

class MetaViewModelFactory(private val repository: MetaRepository) : ViewModelProvider.Factory {

    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(MetaViewModel::class.java)) {
            @Suppress("UNCHECKED_CAST")
            return MetaViewModel(repository) as T
        }
        throw IllegalArgumentException("ViewModel Meta não existe")
    }
}
