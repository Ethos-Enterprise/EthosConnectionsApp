package com.example.ethosconnections.viewmodel.progresso

import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel

class ProgressoViewModel : ViewModel() {
    val progressoAmbiental = mutableStateOf(0)
    val progressoSocial = mutableStateOf(0)
    val progressoGovernamental = mutableStateOf(0)

    val progressoTotal = derivedStateOf {
        (progressoAmbiental.value + progressoSocial.value + progressoGovernamental.value) / 3
    }

    fun atualizarProgresso(categoria: String, pontuacao: Int) {
        when (categoria) {
            "Ambiental" -> atualizarProgressoAmbiental(pontuacao)
            "Social" -> atualizarProgressoSocial(pontuacao)
            "Governamental" -> atualizarProgressoGovernamental(pontuacao)
        }
    }

    private fun atualizarProgressoAmbiental(valor: Int) {
        progressoAmbiental.value = valor
    }

    private fun atualizarProgressoSocial(valor: Int) {
        progressoSocial.value = valor
    }

    private fun atualizarProgressoGovernamental(valor: Int) {
        progressoGovernamental.value = valor
    }
}
