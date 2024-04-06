package com.example.ethosconnections.ui.screen.plataforma

import androidx.compose.foundation.layout.Column
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.navigation.NavController
import com.example.ethosconnections.ui.screen.plataforma.components.FillButtonEthos
import com.example.ethosconnections.ui.screen.plataforma.components.OutlinedButtonEthos
import com.example.ethosconnections.ui.theme.letraPadrao
import com.example.ethosconnections.ui.theme.tituloPagina

@Composable
fun AvaliacaoServico(navController: NavController) {

        Column {

        Text(text = "Avaliação do Serviço", style = tituloPagina)


        FillButtonEthos({ navController.navigate("solucoesEsg") }, "ir para solucoes")
        }


}