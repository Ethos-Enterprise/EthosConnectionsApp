package com.example.ethosconnections.ui.screen.plataforma

import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.navigation.NavController
import com.example.ethosconnections.ui.theme.tituloPagina

@Composable
fun MeuProgresso(navController: NavController) {
    Text(text = "Meu Progresso" , style = tituloPagina)
}