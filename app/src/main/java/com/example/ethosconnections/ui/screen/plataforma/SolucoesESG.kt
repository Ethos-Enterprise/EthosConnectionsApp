package com.example.ethosconnections.ui.screen.plataforma

import androidx.compose.foundation.layout.Column
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.example.compose.AppTheme
import com.example.ethosconnections.ui.screen.Plataforma
import com.example.ethosconnections.ui.screen.plataforma.components.OutlinedButtonEthos
import com.example.ethosconnections.ui.theme.letraPadrao
import com.example.ethosconnections.ui.theme.tituloPagina

@Composable
fun SolucoesESG(navController: NavController) {

    Column {


        Text(text = "Soluções ESG", style = tituloPagina)


        OutlinedButtonEthos({ navController.navigate("avaliacaoServico") }, "ir para avaliacao")

    }

}

@Preview(showBackground = true)
@Composable
fun SolucoesESGPreview() {
    val navController = rememberNavController()

    AppTheme {
        Surface {
            Plataforma(navController = navController, empresaData = null )
        }
    }
}