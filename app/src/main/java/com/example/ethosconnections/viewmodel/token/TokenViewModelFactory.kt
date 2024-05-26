package com.example.ethosconnections.viewmodel.token

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.ethosconnections.datastore.EmpresaDataStore
import com.example.ethosconnections.repositories.ServicoRepository
import com.example.ethosconnections.repositories.TokenRepository
import com.example.ethosconnections.viewmodel.servico.ServicoViewModel

class TokenViewModelFactory constructor(private val repository: TokenRepository,   private val empresaDataStore: EmpresaDataStore) : ViewModelProvider.Factory{
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return if (modelClass.isAssignableFrom(TokenViewModel::class.java)) {
            TokenViewModel(repository, empresaDataStore) as T
        } else {
            throw IllegalArgumentException("ViewModel Token nao existee")
        }
    }
}
