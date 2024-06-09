package com.example.ethosconnections.viewmodel.prestadora

import android.content.Context
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.ethosconnections.R
import com.example.ethosconnections.repositories.PrestadoraRepository
import com.example.ethosconnections.viewmodel.empresa.EmpresaViewModel

class PrestadoraViewModelFactory constructor(private val context: Context, private val repository: PrestadoraRepository) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return if (modelClass.isAssignableFrom(PrestadoraViewModel::class.java)) {
            PrestadoraViewModel(context, this.repository) as T
        } else {
            throw IllegalArgumentException(" Prestadora ${context.getString(R.string.view_model_nao_encontrado)}")
        }
    }
}

