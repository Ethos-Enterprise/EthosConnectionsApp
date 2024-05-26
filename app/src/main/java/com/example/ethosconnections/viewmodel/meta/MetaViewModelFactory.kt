package com.example.ethosconnections.viewmodel.meta

import android.content.Context
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.ethosconnections.R
import com.example.ethosconnections.repositories.MetaRepository

class MetaViewModelFactory(private val context: Context, private val repository: MetaRepository) : ViewModelProvider.Factory {

    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(MetaViewModel::class.java)) {
            @Suppress("UNCHECKED_CAST")
            return MetaViewModel(context, repository) as T
        }
        throw IllegalArgumentException(" Meta ${context.getString(R.string.view_model_nao_encontrado)}")
    }
}
