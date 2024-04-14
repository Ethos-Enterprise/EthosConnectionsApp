package com.example.ethosconnections.ui.screen.plataforma

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.material3.Card
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.example.compose.AppTheme
import com.example.compose.cor_primaria
import com.example.ethosconnections.R
import com.example.ethosconnections.models.Servico
import com.example.ethosconnections.ui.screen.Plataforma
import com.example.ethosconnections.ui.screen.plataforma.components.BoxEthos
import com.example.ethosconnections.ui.screen.plataforma.components.ServicoEthos
import com.example.ethosconnections.ui.theme.letraPadrao
import com.example.ethosconnections.ui.theme.tituloConteudoBranco
import com.example.ethosconnections.ui.theme.tituloPagina
import java.util.UUID

fun servicosFake(): List<Servico> {
    return listOf(
        Servico(
            UUID.randomUUID(),
            "Treinamento de Responsabilidade Social Corporativa (RSC)",
            "Descrição do serviço ",
            22.0,
            "Governança",
            UUID.randomUUID()
        ),
        Servico(
            UUID.randomUUID(),
            "Treinamento de Responsabilidade Social Corporativa (RSC)",
            "Descrição do serviço ",
            45.0,
            "Environmental",
            UUID.randomUUID()
        ),
        Servico(
            UUID.randomUUID(),
            "Treinamento de Responsabilidade Social Corporativa (RSC)",
            "Descrição do serviço ",
            30.0,
            "Social",
            UUID.randomUUID()
        )
    )
}

@Composable
fun SolucoesESG(navController: NavController) {

    var pesquisa = remember { mutableStateOf("") }
    var servicos = servicosFake()

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

            BasicTextField(
                value = pesquisa.value,
                onValueChange = { pesquisa.value = it },
                singleLine = true,
                textStyle = letraPadrao,
                modifier = Modifier
                    .fillMaxWidth()
                    .height(25.dp)
                    .border(
                        BorderStroke(1.dp, color = cor_primaria),
                        shape = RoundedCornerShape(5.dp)
                    )
            )
        }
        Spacer(modifier = Modifier.height(14.dp))

        GridServicos(servicos, navController)
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
        shape = RoundedCornerShape(5.dp),
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
                shape = RoundedCornerShape(5.dp)

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


@Composable
fun GridServicos(servicos: List<Servico>, navController: NavController) {
    Column {
        servicos.chunked(2).forEach { rowServices ->
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                rowServices.forEach { servico ->
                    ServicoEthos(
                        fotoEmpresa = R.mipmap.governance,
                        categoria = servico.areaAtuacaoEsg,
                        nomeServico = servico.nome,
                        nomeEmpresa = "Deloitte",
                        onClick = { navController.navigate("avaliacaoServico") }
                    )
                }
                if (rowServices.size == 1) {
                    Spacer(modifier = Modifier.weight(1f))
                }
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