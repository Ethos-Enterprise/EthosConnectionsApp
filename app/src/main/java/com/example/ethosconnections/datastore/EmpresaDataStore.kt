package com.example.ethosconnections.datastore

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.*
import androidx.datastore.preferences.preferencesDataStore
import com.example.ethosconnections.models.Empresa
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import java.util.*

class EmpresaDataStore(private val context: Context) {

    private val Context.usuarioAtual: DataStore<Preferences> by preferencesDataStore("usuarioEmpresa")

    companion object {
        private val EMPRESA_ID = stringPreferencesKey("empresa_id")
        private val EMPRESA_RAZAO_SOCIAL = stringPreferencesKey("empresa_razao_social")
        private val EMPRESA_CNPJ = stringPreferencesKey("empresa_cnpj")
        private val EMPRESA_TELEFONE = stringPreferencesKey("empresa_telefone")
        private val EMPRESA_EMAIL = stringPreferencesKey("empresa_email")
        private val EMPRESA_SETOR = stringPreferencesKey("empresa_setor")
        private val EMPRESA_QTD_FUNCIONARIOS = intPreferencesKey("empresa_qtd_funcionarios")
        private val EMPRESA_ASSINANTE_NEWSLETTER =
            booleanPreferencesKey("empresa_assinante_newsletter")
    }

    suspend fun saveEmpresa(empresa: Empresa) {
        context.usuarioAtual.edit { preferences ->
            preferences[EMPRESA_ID] = empresa.id?.toString() ?: ""
            preferences[EMPRESA_RAZAO_SOCIAL] = empresa.razaoSocial ?: ""
            preferences[EMPRESA_CNPJ] = empresa.cnpj ?: ""
            preferences[EMPRESA_TELEFONE] = empresa.telefone ?: ""
            preferences[EMPRESA_EMAIL] = empresa.email ?: ""
            preferences[EMPRESA_SETOR] = empresa.setor ?: ""
            preferences[EMPRESA_QTD_FUNCIONARIOS] = empresa.qtdFuncionarios ?: 0
            preferences[EMPRESA_ASSINANTE_NEWSLETTER] = empresa.assinanteNewsletter ?: false
        }
    }

    fun getEmpresaFlow(): Flow<Empresa?> {
        return context.usuarioAtual.data.map { preferences ->
            Empresa(
                id = preferences[EMPRESA_ID]?.let { UUID.fromString(it) },
                razaoSocial = preferences[EMPRESA_RAZAO_SOCIAL],
                cnpj = preferences[EMPRESA_CNPJ],
                telefone = preferences[EMPRESA_TELEFONE],
                email = preferences[EMPRESA_EMAIL],
                setor = preferences[EMPRESA_SETOR],
                qtdFuncionarios = preferences[EMPRESA_QTD_FUNCIONARIOS],
                assinanteNewsletter = preferences[EMPRESA_ASSINANTE_NEWSLETTER]
            )
        }
    }
}


