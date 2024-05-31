package com.example.ethosconnections.viewmodel.interacao

import android.content.Context
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.ethosconnections.R
import com.example.ethosconnections.datastore.EmpresaDataStore
import com.example.ethosconnections.repositories.InteracaoRepository

class InteracaoViewModelFactory  constructor(private val context: Context, private val repository: InteracaoRepository,  private val empresaDataStore: EmpresaDataStore) : ViewModelProvider.Factory{

    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return if (modelClass.isAssignableFrom(InteracaoViewModel::class.java)) {
            InteracaoViewModel(context, this.repository, empresaDataStore ) as T
        } else {
            throw IllegalArgumentException("Interacao ${context.getString(R.string.view_model_nao_encontrado)}")
        }
    }
}