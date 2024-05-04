package com.example.ethosconnections.datastore

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.stringPreferencesKey
import androidx.datastore.preferences.preferencesDataStore

class ServicoDataStore (private val context: Context) {
    private val appContext = context.applicationContext

    private val Context.usuarioAtual: DataStore<Preferences> by preferencesDataStore("servico")

    companion object {
        private val SERVICO_ID = stringPreferencesKey("servico_id")
        private val SERVICO_NOME = stringPreferencesKey("servico_nome")
        private val SERVICO_DESCRICAO = stringPreferencesKey("servico_descricao")
        private val SERVICO_VALOR = stringPreferencesKey("servico_valor")
        private val SERVICO_AREA_ATUACAO_ESG = stringPreferencesKey("servico_area_atuacao_esg")
        private val SERVICO_FK_PRESTADORA = stringPreferencesKey("servico_fk_prestadora")
    }
}