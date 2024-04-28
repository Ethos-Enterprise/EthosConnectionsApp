package com.example.ethosconnections.ui.screen.plataforma

import androidx.compose.foundation.layout.Column
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.example.ethosconnections.ui.screen.plataforma.components.BoxEthos
import com.example.ethosconnections.ui.theme.tituloPagina

@Composable
fun MeuPerfil(navController: NavController,
) {
    Column {

        Text(text = "Meu Perfil", style = tituloPagina)

        BoxEthos { }
    }
}


@Preview(showBackground = true)
@Composable
fun meuPerfilPreview() {
    val navController = rememberNavController()
    MeuPerfil(navController)
}