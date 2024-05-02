package com.example.ethosconnections.viewmodel.prestadora

import android.content.Context
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.ethosconnections.repositories.PrestadoraRepository
import com.example.ethosconnections.viewmodel.empresa.EmpresaViewModel

class PrestadoraViewModelFactory constructor(private val repository: PrestadoraRepository) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return if (modelClass.isAssignableFrom(EmpresaViewModel::class.java)) {
            PrestadoraViewModel(this.repository) as T
        } else {
            throw IllegalArgumentException("ViewModel Prestadora nao existee")
        }
    }
}

