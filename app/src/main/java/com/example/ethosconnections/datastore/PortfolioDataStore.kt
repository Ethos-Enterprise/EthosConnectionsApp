package com.example.ethosconnections.datastore

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.stringPreferencesKey
import androidx.datastore.preferences.preferencesDataStore
import com.example.ethosconnections.models.Portfolio
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.map

class PortfolioDataStore(private val context: Context) {
    private val Context.portfolioDataStore: DataStore<Preferences> by preferencesDataStore(name = "portfolioData")

    companion object {
        private val PORTFOLIO_UUID = stringPreferencesKey("portfolio_uuid")
        private val URL_IMAGEM_PERFIL = stringPreferencesKey("url_imagem_perfil")
        private val URL_BACKGROUND_PERFIL = stringPreferencesKey("url_background_perfil")
        private val DESCRICAO_EMPRESA = stringPreferencesKey("descricao_empresa")
        private val SOBRE_EMPRESA = stringPreferencesKey("sobre_empresa")
        private val LINK_WEBSITE_EMPRESA = stringPreferencesKey("link_website_empresa")
        private val DATA_EMPRESA_CERTIFICADA = stringPreferencesKey("data_empresa_certificada")
        private val FK_PRESTADORA_SERVICO = stringPreferencesKey("fk_prestadora_servico")
    }

    suspend fun savePortfolio(portfolio: Portfolio) {
        context.portfolioDataStore.edit { preferences ->
            preferences[PORTFOLIO_UUID] = portfolio.uuid ?: ""
            preferences[URL_IMAGEM_PERFIL] = portfolio.urlImagemPerfil ?: ""
            preferences[URL_BACKGROUND_PERFIL] = portfolio.urlBackgroundPerfil ?: ""
            preferences[DESCRICAO_EMPRESA] = portfolio.descricaoEmpresa ?: ""
            preferences[SOBRE_EMPRESA] = portfolio.sobreEmpresa ?: ""
            preferences[LINK_WEBSITE_EMPRESA] = portfolio.linkWebsiteEmpresa ?: ""
            preferences[DATA_EMPRESA_CERTIFICADA] = portfolio.dataEmpresaCertificada ?: ""
            preferences[FK_PRESTADORA_SERVICO] = portfolio.fkPrestadoraServico ?: ""
        }
    }

    fun getPortfolioFlow(): Flow<Portfolio?> {
        return context.portfolioDataStore.data.map { preferences ->
            val uuid = preferences[PORTFOLIO_UUID]
            val urlImagemPerfil = preferences[URL_IMAGEM_PERFIL]
            val urlBackgroundPerfil = preferences[URL_BACKGROUND_PERFIL]
            val descricaoEmpresa = preferences[DESCRICAO_EMPRESA]
            val sobreEmpresa = preferences[SOBRE_EMPRESA]
            val linkWebsiteEmpresa = preferences[LINK_WEBSITE_EMPRESA]
            val dataEmpresaCertificada = preferences[DATA_EMPRESA_CERTIFICADA]
            val fkPrestadoraServico = preferences[FK_PRESTADORA_SERVICO]

            if (uuid != null && urlImagemPerfil != null && urlBackgroundPerfil != null &&
                descricaoEmpresa != null && sobreEmpresa != null && linkWebsiteEmpresa != null &&
                dataEmpresaCertificada != null && fkPrestadoraServico != null) {
                Portfolio(
                    uuid = uuid,
                    urlImagemPerfil = urlImagemPerfil,
                    urlBackgroundPerfil = urlBackgroundPerfil,
                    descricaoEmpresa = descricaoEmpresa,
                    sobreEmpresa = sobreEmpresa,
                    linkWebsiteEmpresa = linkWebsiteEmpresa,
                    dataEmpresaCertificada = dataEmpresaCertificada,
                    fkPrestadoraServico = fkPrestadoraServico
                )
            } else {
                null
            }
        }
    }

    suspend fun getPortfolio(): Portfolio? {
        return getPortfolioFlow().first()
    }

    suspend fun clearDataStore() {
        context.portfolioDataStore.edit { preferences ->
            preferences.clear()
        }
    }
}
