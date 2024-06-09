package com.example.ethosconnections.viewmodel.portfolio

import android.content.Context
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.ethosconnections.R
import com.example.ethosconnections.datastore.PortfolioDataStore
import com.example.ethosconnections.repositories.PortfolioRepository

class PortfolioViewModelFactory(private val context: Context, private val repository: PortfolioRepository, private val portfolioDataStore: PortfolioDataStore) : ViewModelProvider.Factory {

    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(PortfolioViewModel::class.java)) {
            @Suppress("UNCHECKED_CAST")
            return PortfolioViewModel( context, repository, portfolioDataStore) as T
        }
        throw IllegalArgumentException("Portfolio ${context.getString(R.string.view_model_nao_encontrado)}")
    }
}
