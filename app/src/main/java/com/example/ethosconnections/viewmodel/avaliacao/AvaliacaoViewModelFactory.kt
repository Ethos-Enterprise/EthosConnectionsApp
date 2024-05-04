package com.example.ethosconnections.viewmodel.avaliacao

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.ethosconnections.repositories.AvaliacaoRepository
import com.example.ethosconnections.viewmodel.meta.MetaViewModel

class AvaliacaoViewModelFactory(private val repository: AvaliacaoRepository) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(MetaViewModel::class.java)) {
            @Suppress("UNCHECKED_CAST")
            return AvaliacaoViewModel(repository) as T
        }
        throw IllegalArgumentException("ViewModel Avaliacao não existe")
    }
}