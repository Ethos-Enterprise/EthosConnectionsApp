package com.example.ethosconnections.ui.screen.plataforma

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.example.ethosconnections.R
import com.example.ethosconnections.ui.screen.plataforma.components.BoxEthos

import com.example.ethosconnections.ui.theme.tituloConteudoBranco
import com.example.ethosconnections.ui.theme.tituloPagina

@Composable
fun Pagamento(navController: NavController) {

    Column {

        Text(text = "Realizar Pagamento Pix", style = tituloPagina)

        BoxEthos {
            Column {
                Spacer(modifier = Modifier.width(14.dp))

                Image(
                    modifier = Modifier.width(100.dp),
                    painter = painterResource(id = R.mipmap.certo),
                    contentDescription = "Icone de simbolo de certo",
                )
                Text(
                    text = "Obrigado por fazer parte da Ethos!", style = tituloConteudoBranco
                )

                Spacer(modifier = Modifier.width(14.dp))
            }
        }
    }

}

@Preview(showBackground = true)
@Composable
fun PagamentoPreview() {
    val navController = rememberNavController()
    Pagamento(navController)
}