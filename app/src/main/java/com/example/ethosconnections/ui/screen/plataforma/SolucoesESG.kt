package com.example.ethosconnections.ui.screen.plataforma

import androidx.compose.foundation.layout.Column
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.navigation.NavController
import com.example.ethosconnections.ui.theme.letraPadrao

@Composable
fun SolucoesESG(navController: NavController) {

    Column {


        Text(text = "PAGINA SOLUCOES ESG", style = letraPadrao)

        Button(
            onClick = { navController.navigate("avaliacaoServico") }
        ) {
            Text("Ir para cadastro")
        }
    }

}
