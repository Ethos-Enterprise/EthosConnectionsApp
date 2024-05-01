package com.example.ethosconnections.ui.screen.plataforma

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
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
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.Card
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.snapshots.SnapshotStateList
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.example.compose.AppTheme
import com.example.compose.cor_primaria
import com.example.ethosconnections.R
import com.example.ethosconnections.models.Servico
import com.example.ethosconnections.ui.screen.plataforma.components.BoxEthos
import com.example.ethosconnections.ui.screen.plataforma.components.ServicoEthos
import com.example.ethosconnections.ui.theme.letraPadrao
import com.example.ethosconnections.ui.theme.tituloConteudoBranco
import com.example.ethosconnections.ui.theme.tituloPagina
import com.example.ethosconnections.viewmodel.servico.ServicoViewModel
import java.util.UUID

@Composable
fun SolucoesESG(navController: NavController, servicoViewModel: ServicoViewModel) {

    LaunchedEffect(key1 = true) {
        servicoViewModel.getServicos()
    }
    val servicos = remember { servicoViewModel.servicos }.observeAsState(SnapshotStateList())

    var pesquisa = remember { mutableStateOf("") }

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
            OutlinedTextField(
                value = pesquisa.value,
                onValueChange = { pesquisa.value = it },
                modifier = Modifier
                    .fillMaxWidth()
                    .background(Color.Transparent),
                textStyle = TextStyle(color = Color.White),
                colors = TextFieldDefaults.colors(
                    focusedContainerColor = Color.Transparent,
                    unfocusedContainerColor = Color.Transparent,
                    focusedIndicatorColor = Color.White,
                    unfocusedIndicatorColor = Color(0xFF01A2C3)
                ),

                placeholder = {
                    Text(
                        "Buscar soluções",
                        color = Color.White,
                        fontSize = 14.sp,
                        modifier = Modifier.padding(bottom = 0.dp)
                    )
                },
                trailingIcon = {
                    IconButton(
                        onClick = { },
                        modifier = Modifier
                            .background(cor_primaria)
                            .padding(PaddingValues(0.dp))
                    ) {
                        Icon(
                            imageVector = Icons.Default.Search,
                            contentDescription = "Buscar",
                            tint = Color.White,

                            )
                    }
                }
            )

        }
        Spacer(modifier = Modifier.height(10.dp))

        GridServicos(servicos.value, navController)
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
fun GridServicos(servicos: SnapshotStateList<Servico>, navController: NavController) {
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
                        nomeServico = servico.nomeServico,
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
//            Plataforma(navController = navController)
        }
    }
}