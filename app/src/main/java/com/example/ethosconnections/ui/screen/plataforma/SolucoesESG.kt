package com.example.ethosconnections.ui.screen.plataforma

import android.util.Log
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
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
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Clear
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.CardElevation
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.snapshots.SnapshotStateList
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
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

    var servicos = remember { servicoViewModel.servicos }.observeAsState(SnapshotStateList())

    var filtroAplicado = remember { mutableStateOf("Exibindo todas as soluções") }
    var pesquisa = remember { mutableStateOf("") }
    var servicosFiltrados = remember { mutableStateOf<SnapshotStateList<Servico>?>(null) }
    var cardSelecionado = remember { mutableStateOf(-1) }


    val filtrarServicos: (List<Servico>, (Servico) -> Boolean, String, String) -> Unit = { servicosList, filtro, mensagemVazia, mensagemResultado ->
        val listaFiltrados = servicosList.filter(filtro)
        if (listaFiltrados.isEmpty()) {
            filtroAplicado.value = mensagemVazia
        } else {
            filtroAplicado.value = mensagemResultado
        }
        servicosFiltrados.value = SnapshotStateList<Servico>().apply { addAll(listaFiltrados) }
    }

    val filtrarServicosPorAreaEsg: (String) -> Unit = { areaEsg ->
        servicos.value?.let { servicosList ->
            filtrarServicos(
                servicosList,
                { it.areaAtuacaoEsg == areaEsg },
                "Nenhum resultado encontrado",
                "Exibindo resultados"
            )
        }
    }

    val filtrarServicosPorPesquisa: () -> Unit = {
        val textoPesquisa = pesquisa.value.trim()
        if (textoPesquisa.isNotEmpty()) {
            servicos.value?.let { servicosList ->
                filtrarServicos(
                    servicosList,
                    { it.nomeServico.contains(textoPesquisa, ignoreCase = true) },
                    "Nenhum resultado encontrado",
                    "Exibindo resultados"
                )
            }
        } else {
            servicosFiltrados.value = null
            filtroAplicado.value = "Exibindo todas as soluções"
        }
    }

    val limparFiltros: () -> Unit = {
        servicosFiltrados.value = null
        cardSelecionado.value = -1
        filtroAplicado.value = "Exibindo todas as soluções"
    }

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
                    buttonText = "Environmental",
                    onClick = { filtrarServicosPorAreaEsg("ENVIRONMENTAL") },
                    cardSelecionado = cardSelecionado.value == 0,
                    setSelectedCard = { selected ->
                        if (selected) {
                            cardSelecionado.value = 0
                        }
                    }
                )
                CategoriaCard(
                    imagemCard = R.mipmap.social,
                    contentDescription = "Categoria Social",
                    buttonText = "Social",
                    onClick = { filtrarServicosPorAreaEsg("SOCIAL") },
                    cardSelecionado = cardSelecionado.value == 1,
                    setSelectedCard = { selected ->
                        if (selected) {
                            cardSelecionado.value = 1
                        }
                    }
                )
                CategoriaCard(
                    imagemCard = R.mipmap.governance,
                    contentDescription = "Categoria Governance",
                    buttonText = "Governance",
                    onClick = { filtrarServicosPorAreaEsg("GOVERNANCE") },
                    cardSelecionado = cardSelecionado.value == 2,
                    setSelectedCard = { selected ->
                        if (selected) {
                            cardSelecionado.value = 2
                        }
                    }
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
                        onClick = { filtrarServicosPorPesquisa() } ,
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

        Row (
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween
        ){
            Text(text = filtroAplicado.value, style = letraPadrao)
            Box( modifier = Modifier
                .clickable { limparFiltros() }
            ){
                Row {

                Text(text = "Limpar Filtro", style = letraPadrao)
                    Spacer(modifier = Modifier.width(13.dp))
                Icon(
                    imageVector = Icons.Default.Clear,
                    contentDescription = "Limpar Filtros",
                    tint = Color.White,
                )
            }
            }

        }
        Spacer(modifier = Modifier.height(10.dp))

        GridServicos(servicosFiltrados.value ?: servicos.value, navController)
    }

}

@Composable
fun CategoriaCard(
    imagemCard: Int,
    contentDescription: String,
    buttonText: String,
    onClick: () -> Unit,
    cardSelecionado: Boolean,
    setSelectedCard: (Boolean) -> Unit
) {

    val shadowColor = Color(0xFFDDDDDD)

    Card(
        modifier = Modifier
            .height(110.dp)
            .width(110.dp)
            .clickable {
                Log.d("CategoriaCard", "onClick chamado")
                setSelectedCard(!cardSelecionado)
                onClick()
            }
//            .shadow(elevation = if (cardSelecionado) 4.dp else 0.dp, shape = RoundedCornerShape(5.dp))
            .border(
                width = 2.dp,
                color = if (cardSelecionado) Color.White else Color.Transparent,
                shape = RoundedCornerShape(5.dp)
            ),
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
                        nomeEmpresa = servico.nomeEmpresa ?: "Não especificado",
                        descricao = servico.descricao,
                        valor = servico.valor,
                        id = servico.id,
                        fkPrestadora = servico.fkPrestadoraServico,
                        onClick = {
                            navController.navigate(
                                "avaliacaoServico/${servico.nomeServico}/${servico.nomeEmpresa ?: ""}/${servico.areaAtuacaoEsg}/${servico.valor}/${servico.descricao}/${servico.fkPrestadoraServico}"
                            )
                        }
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