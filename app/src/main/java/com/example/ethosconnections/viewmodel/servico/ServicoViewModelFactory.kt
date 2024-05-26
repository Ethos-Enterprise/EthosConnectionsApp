package com.example.ethosconnections.viewmodel.servico

import android.content.Context
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.ethosconnections.R
import com.example.ethosconnections.repositories.ServicoRepository
class ServicoViewModelFactory constructor(private val context: Context, private val repository: ServicoRepository) : ViewModelProvider.Factory{

    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return if (modelClass.isAssignableFrom(ServicoViewModel::class.java)) {
            ServicoViewModel(context, this.repository) as T
        } else {
            throw IllegalArgumentException("Servico ${context.getString(R.string.view_model_nao_encontrado)}")
        }
    }
}