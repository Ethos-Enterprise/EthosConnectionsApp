package com.example.ethosconnections.viewmodel.empresa

import android.content.Context
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.ethosconnections.repositories.EmpresaRepository

class EmpresaViewModelFactory constructor(private val context: Context, private val repository: EmpresaRepository) : ViewModelProvider.Factory{

    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return if (modelClass.isAssignableFrom(EmpresaViewModel::class.java)) {
            EmpresaViewModel(context, this.repository) as T
        } else {
            throw IllegalArgumentException("ViewModel Empresa nao existee")
        }
    }
}