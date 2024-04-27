package com.example.ethosconnections.ui.screen.plataforma

import androidx.compose.foundation.layout.Column
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.example.ethosconnections.ui.theme.tituloPagina

@Composable
fun Pagamento(navController: NavController) {

    Column {
        Text(text = "Realizar Pagamento Pix", style = tituloPagina)
    }


}

@Preview(showBackground = true)
@Composable
fun pagamentoPreview() {
    val navController = rememberNavController()
    Pagamento(navController)
}