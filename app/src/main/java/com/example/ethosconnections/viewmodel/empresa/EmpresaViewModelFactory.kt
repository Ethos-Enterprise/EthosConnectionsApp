package com.example.ethosconnections.viewmodel.empresa

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.ethosconnections.repositories.EmpresaRepository

class EmpresaViewModelFactory constructor(private val repository: EmpresaRepository) : ViewModelProvider.Factory{

    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return if (modelClass.isAssignableFrom(EmpresaViewModel::class.java)) {
            EmpresaViewModel(this.repository) as T
        } else {
            throw IllegalArgumentException("ViewModel nao existee")
        }
    }
}