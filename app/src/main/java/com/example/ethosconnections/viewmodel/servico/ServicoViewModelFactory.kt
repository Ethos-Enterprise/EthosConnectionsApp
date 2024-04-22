package com.example.ethosconnections.viewmodel.servico

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.ethosconnections.repositories.ServicoRepository
class ServicoViewModelFactory constructor(private val repository: ServicoRepository) : ViewModelProvider.Factory{

    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return if (modelClass.isAssignableFrom(ServicoViewModel::class.java)) {
            ServicoViewModel(this.repository) as T
        } else {
            throw IllegalArgumentException("ViewModel Servico nao existee")
        }
    }
}