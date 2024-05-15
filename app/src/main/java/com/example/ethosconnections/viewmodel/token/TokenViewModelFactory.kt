package com.example.ethosconnections.viewmodel.token

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.ethosconnections.repositories.ServicoRepository
import com.example.ethosconnections.repositories.TokenRepository
import com.example.ethosconnections.viewmodel.servico.ServicoViewModel

class TokenViewModelFactory constructor(private val repository: TokenRepository) : ViewModelProvider.Factory{
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return if (modelClass.isAssignableFrom(TokenViewModel::class.java)) {
            TokenViewModel(this.repository) as T
        } else {
            throw IllegalArgumentException("ViewModel Token nao existee")
        }
    }
}
