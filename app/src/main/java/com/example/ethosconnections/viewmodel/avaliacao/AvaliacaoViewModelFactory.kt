package com.example.ethosconnections.viewmodel.avaliacao

import android.content.Context
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.ethosconnections.R
import com.example.ethosconnections.repositories.AvaliacaoRepository
import com.example.ethosconnections.viewmodel.meta.MetaViewModel

class AvaliacaoViewModelFactory(private val context: Context, private val repository: AvaliacaoRepository) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(MetaViewModel::class.java)) {
            @Suppress("UNCHECKED_CAST")
            return AvaliacaoViewModel(context, repository) as T
        }
        throw IllegalArgumentException(" Avaliacao ${context.getString(R.string.view_model_nao_encontrado)}")
    }
}