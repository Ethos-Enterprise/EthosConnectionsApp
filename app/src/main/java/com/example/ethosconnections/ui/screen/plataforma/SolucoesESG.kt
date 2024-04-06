package com.example.ethosconnections.ui.screen.plataforma

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.example.compose.AppTheme
import com.example.ethosconnections.R
import com.example.ethosconnections.ui.screen.Plataforma
import com.example.ethosconnections.ui.screen.plataforma.components.BoxEthos
import com.example.ethosconnections.ui.screen.plataforma.components.OutlinedButtonEthos
import com.example.ethosconnections.ui.theme.letraPadrao
import com.example.ethosconnections.ui.theme.tituloConteudoBranco
import com.example.ethosconnections.ui.theme.tituloPagina

@Composable
fun SolucoesESG(navController: NavController) {

    Column {

        Text(text = "Soluções ESG", style = tituloPagina)

        BoxEthos {

            Text(text = "Categorias ESG ", style = tituloConteudoBranco)

            Spacer(modifier = Modifier.height(14.dp))

            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                CategoriaCard(
                    imagemCard = R.mipmap.environmental,
                    contentDescription = "Categoria Ambiental",
                    buttonText = "Environmental"
                )
                CategoriaCard(
                    imagemCard = R.mipmap.social,
                    contentDescription = "Categoria Social",
                    buttonText = "Social"
                )
                CategoriaCard(
                    imagemCard = R.mipmap.governance,
                    contentDescription = "Categoria Governance",
                    buttonText = "Governance"
                )
            }

        }

        BoxEthos {
            TextField(value = "oi", onValueChange = { "it" })
        }

        OutlinedButtonEthos({ navController.navigate("avaliacaoServico") }, "ir para avaliacao")

    }


}

@Composable
fun CategoriaCard(
    imagemCard: Int,
    contentDescription: String,
    buttonText: String
) {
    Card(
        modifier = Modifier
            .height(110.dp)
            .width(110.dp)
            .clickable { /* FUNCAOOOO*/ },
        shape = RoundedCornerShape(7.dp),
    ) {
        Box(
            contentAlignment = Alignment.Center,
        ) {
            Image(
                painter = painterResource(id = imagemCard),
                contentDescription = contentDescription
            )
            Surface(
                color = Color(0xFF014D5C),
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(2.dp),
                shape = RoundedCornerShape(6.dp)

            ) {
                Text(
                    textAlign = TextAlign.Center,
                    text = buttonText,
                    style = letraPadrao,
                    color = Color.White,
                    modifier = Modifier.padding(3.dp)
                )
            }
        }

    }
}

@Preview(showBackground = true)
@Composable
fun SolucoesESGPreview() {
    val navController = rememberNavController()

    AppTheme {
        Surface {
            Plataforma(navController = navController, empresaData = null)
        }
    }
}