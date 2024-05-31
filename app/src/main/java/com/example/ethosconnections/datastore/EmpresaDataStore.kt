package com.example.ethosconnections.datastore

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.*
import androidx.datastore.preferences.preferencesDataStore
import com.example.ethosconnections.R
import com.example.ethosconnections.models.Empresa
import com.example.ethosconnections.models.Token
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.first
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
        private val EMPRESA_ASSINANTE_NEWSLETTER = booleanPreferencesKey("empresa_assinante_newsletter")
        private val EMPRESA_PLANO = stringPreferencesKey("empresa_plano")
        private val EMPRESA_TOKEN = stringPreferencesKey("empresa_token")
        private val EMPRESA_ID_PRESTADORA = stringPreferencesKey("empresa_id_prestadora")
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
            preferences[EMPRESA_PLANO] = empresa.plano ?: empresa.plano ?: "Free"
            preferences[EMPRESA_ID_PRESTADORA] = empresa.idPrestadora?.toString() ?: ""

        }
    }

    fun getEmpresaFlow(): Flow<Empresa?> {
        return context.usuarioAtual.data.map { preferences ->
            val idString = preferences[EMPRESA_ID]
            val id = idString?.let { UUID.fromString(it) }
            val razaoSocial = preferences[EMPRESA_RAZAO_SOCIAL]
            val cnpj = preferences[EMPRESA_CNPJ]
            val telefone = preferences[EMPRESA_TELEFONE]
            val email = preferences[EMPRESA_EMAIL]
            val setor = preferences[EMPRESA_SETOR]
            val qtdFuncionarios = preferences[EMPRESA_QTD_FUNCIONARIOS]
            val assinanteNewsletter = preferences[EMPRESA_ASSINANTE_NEWSLETTER]
            val plano = preferences[EMPRESA_PLANO]
            val idPrestadoraString = preferences[EMPRESA_ID_PRESTADORA]
            val idPrestadora = if (!idPrestadoraString.isNullOrEmpty()) UUID.fromString(idPrestadoraString) else null

            if (idString != null && razaoSocial != null && cnpj != null && telefone != null && email != null &&
                setor != null && qtdFuncionarios != null && assinanteNewsletter != null && plano != null
            ) {
                Empresa(
                    id = id,
                    razaoSocial = razaoSocial,
                    cnpj = cnpj,
                    telefone = telefone,
                    email = email,
                    setor = setor,
                    qtdFuncionarios = qtdFuncionarios,
                    assinanteNewsletter = assinanteNewsletter,
                    plano = plano,
                    idPrestadora = idPrestadora
                )
            } else {
                null
            }
        }
    }


    suspend fun saveToken(token: Token) {
        context.usuarioAtual.edit { preferences ->
            preferences[EMPRESA_TOKEN] = token.token
        }
    }

    fun getRazaoSocialEmpresaFlow(): Flow<String?> {
        return context.usuarioAtual.data.map { preferences ->
            preferences[EMPRESA_RAZAO_SOCIAL]
        }
    }

    fun getPlanoFlow(): Flow<String?> {
        return context.usuarioAtual.data.map { preferences ->
            preferences[EMPRESA_PLANO]
        }
    }

    suspend fun mudarPlano(novoPlano: String) {
        context.usuarioAtual.edit { preferences ->
            preferences[EMPRESA_PLANO] = novoPlano
        }
    }

    suspend fun getToken(): String {
        return context.usuarioAtual.data.map { preferences ->
            preferences[EMPRESA_TOKEN] ?: throw NoSuchElementException(context.getString(R.string.nao_encontrado))
        }.first()

    }
    suspend fun getId(): UUID? {
        val empresa = getEmpresaFlow().first()
        return empresa?.id
    }
    suspend fun clearDataStore() {
        context.usuarioAtual.edit { preferences ->
            preferences.clear()
        }
    }
}


