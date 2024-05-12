package com.example.ethosconnections.viewmodel.interacao

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.ethosconnections.repositories.InteracaoRepository

class InteracaoViewModelFactory  constructor( private val repository: InteracaoRepository) : ViewModelProvider.Factory{

    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return if (modelClass.isAssignableFrom(InteracaoViewModel::class.java)) {
            InteracaoViewModel(this.repository) as T
        } else {
            throw IllegalArgumentException("ViewModel Interacao nao existe")
        }
    }
}