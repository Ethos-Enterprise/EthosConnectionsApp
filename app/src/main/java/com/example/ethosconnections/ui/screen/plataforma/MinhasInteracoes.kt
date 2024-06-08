package com.example.ethosconnections.ui.screen.plataforma

import android.util.Log
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.runtime.snapshots.SnapshotStateList
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.example.ethosconnections.R
import com.example.ethosconnections.datastore.EmpresaDataStore
import com.example.ethosconnections.ui.screen.plataforma.components.BoxEthos
import com.example.ethosconnections.ui.theme.letraButton
import com.example.ethosconnections.ui.theme.letraClicavel
import com.example.ethosconnections.ui.theme.letraPadrao
import com.example.ethosconnections.ui.theme.tituloConteudoAzul
import com.example.ethosconnections.ui.theme.tituloConteudoBranco
import com.example.ethosconnections.ui.theme.tituloConteudoBrancoNegrito
import com.example.ethosconnections.ui.theme.tituloPagina
import com.example.ethosconnections.viewmodel.interacao.InteracaoViewModel
import java.util.UUID

@Composable
fun MinhasInteracoes(navController: NavController,empresaDataStore: EmpresaDataStore, interacaoViewModel: InteracaoViewModel ) {
    val empresa by empresaDataStore.getEmpresaFlow().collectAsState(initial = null)

    LaunchedEffect(key1 = empresa) {
        empresa?.let {
            val token = empresaDataStore.getToken()
            val idEmpresa = it.id
            if (idEmpresa != null) {
                interacaoViewModel.getInteracoesByFkEmpresa(idEmpresa, token)
            }
        }
    }

    val interacoes = remember { interacaoViewModel.interacoes }.observeAsState(SnapshotStateList())

    // Lógica dos Cards
    var exibirBox1 by remember { mutableStateOf(true) }

    var cardSelecionadoInteracao by remember { mutableStateOf(0) }

    Column {
        Text(
            stringResource(R.string.titulo_minhas_interacoes),
            style = tituloPagina)

        BoxEthos {
            Column {
                Text(
                    stringResource(R.string.Categorias_de_interacoes),
                    style = tituloConteudoBranco)
                Spacer(modifier = Modifier.height(17.dp))
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween
                ) {
                    CategoriaCardRetangulo(
                        imagemCard = R.mipmap.contatoint,
                        contentDescription = "Contatos",
                        buttonText = "Contatos",
                        onClick = { exibirBox1 = true },
                        cardSelecionado = cardSelecionadoInteracao == 0,
                        setSelectedCard = {selecionado -> if (selecionado) cardSelecionadoInteracao = 0 }
                    )

                    CategoriaCardRetangulo(
                        imagemCard = R.mipmap.favoritoin,
                        contentDescription = "Favoritos",
                        buttonText = "Favoritos",
                        onClick = { exibirBox1 = false },
                        cardSelecionado = cardSelecionadoInteracao == 1,
                        setSelectedCard = {selecionado -> if (selecionado) cardSelecionadoInteracao = 1}
                    )
                }
            }
        }

        if (exibirBox1) {
            Column{
                Spacer(modifier = Modifier.height(10.dp))

                Text(
                    stringResource(R.string.empresas_contratadas),
                    style = tituloConteudoBranco)
                BoxEthos{

                    Column {
                        Row(modifier = Modifier.padding(end = 8.dp)) {
                            Text(
                                stringResource(R.string.em_andamento),
                                style = tituloConteudoBrancoNegrito)
                            Text(text = interacoes.value.size.toString() , style = tituloConteudoBranco, )
                        }

                        Row() {
                            Text(
                                stringResource(R.string.status_finalizado),
                                style = tituloConteudoBrancoNegrito)
                            Text(
                                stringResource(R.string.quant_empresas),
                                style = tituloConteudoBranco)
                        }
                    }
                }

                for (interacao in interacoes.value) {
                    BoxEthos {
                        Column {
                            Row {
                                Image(
                                    modifier = Modifier
                                        .width(100.dp)
                                        .padding(top = 15.dp),
                                    painter = painterResource(id = R.mipmap.deloitte_logo),
                                    contentDescription = "Logo Deloitte"
                                )
                                Column {
                                    Text(
                                        text = interacao.nomeEmpresa,
                                        style = tituloConteudoAzul
                                    )
                                    Text(
                                        stringResource(R.string.servico_interesse) +" ${interacao.nomeServico}",
                                        style = tituloConteudoBranco,
                                        fontSize = 12.sp
                                    )
                                    Text(
                                        stringResource(R.string.status_contato)  +" ${interacao.nomeServico}",
                                        style = tituloConteudoBrancoNegrito,
                                        fontSize = 12.sp
                                    )
                                }
                            }

                            Row(
                                modifier = Modifier.fillMaxWidth(),
                                horizontalArrangement = Arrangement.End
                            ) {
                                Button(
                                    onClick = {
                                        navController.navigate("minhasInteracoes")
                                    },
                                    modifier = Modifier
                                        .padding(start = 5.dp)
                                        .background(Color(0xFF1B1F23))
                                        .widthIn(max = 200.dp)
                                        .height(35.dp),
                                    shape = RoundedCornerShape(3.dp)
                                ) {
                                    Text(
                                        stringResource(R.string.avaliar_serviço),
                                        style = letraButton
                                    )
                                }
                            }
                        }
                    }
                }
            }

        } else {

            Column {
                Spacer(modifier = Modifier.height(10.dp))

                Text(
                    stringResource(R.string.historico_curtidas),
                    style = tituloConteudoBranco)

                BoxEthos{
                    Row(modifier = Modifier.padding(end = 8.dp)) {
                        Text(
                            stringResource(R.string.total),
                            style = tituloConteudoBrancoNegrito)
                        Text(
                            stringResource(R.string.quant_empresas2),
                            style = tituloConteudoBranco, )
                    }
                }

                BoxEthos {
                    Row {
                        Image(
                            modifier = Modifier
                                .width(100.dp)
                                .padding(top = 15.dp),
                            painter = painterResource(id = R.mipmap.deloitte_logo),
                            contentDescription = "Logo Deloitte"
                        )
                        Column(modifier = Modifier.padding(bottom = 8.dp)) {
                            Text(
                                stringResource(R.string.treinamento_responsabilidade),
                                style = tituloConteudoAzul
                            )
                            Text(
                                stringResource(R.string.deloitte),
                                style = tituloConteudoBranco,
                                fontSize = 12.sp
                            )
                            Text(
                                stringResource(R.string.saiba_mais),
                                style = letraClicavel,
                                fontSize = 12.sp,
                                textDecoration = TextDecoration.Underline
                            )
                        }
                    }
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.End
                    ) {
                        Spacer(modifier = Modifier.weight(1f))

                        OutlinedButton(
                            onClick = { navController.navigate("minhasInteracoes") },
                            modifier = Modifier
                                .padding(horizontal = 5.dp)
                                .size(width = 120.dp, height = 40.dp)
                                .height(35.dp),
                            shape = RoundedCornerShape(5.dp),
                            border = BorderStroke(1.dp, Color(0xFF01A2C3)),
                        ) {
                            Text(
                                stringResource(R.string.Remover),
                                style = letraButton,
                                color = Color(0xFF01A2C3)
                            )
                        }

                        Button(
                            onClick = {
                                navController.navigate("portfolio")
                            },
                            modifier = Modifier
                                .padding(start = 5.dp)
                                .background(Color(0xFF1B1F23))
                                .widthIn(max = 120.dp)
                                .height(40.dp),
                            shape = RoundedCornerShape(3.dp)
                        ) {
                            Text(
                                stringResource(R.string.contato),
                                style = letraButton
                            )
                        }
                    }

                }

                BoxEthos {
                    Row {
                        Image(
                            modifier = Modifier
                                .width(100.dp)
                                .padding(top = 15.dp),
                            painter = painterResource(id = R.mipmap.ey),
                            contentDescription = "Logo EY"
                        )
                        Column(modifier = Modifier.padding(bottom = 8.dp)) {
                            Text(
                                stringResource(R.string.gestao_portfolio),
                                style = tituloConteudoAzul
                            )
                            Text(
                                stringResource(R.string.Ernest_young),
                                style = tituloConteudoBranco,
                                fontSize = 12.sp
                            )
                            Text(
                                stringResource(R.string.saiba_mais),
                                style = letraClicavel,
                                fontSize = 12.sp,
                                textDecoration = TextDecoration.Underline
                            )
                        }
                    }
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.End
                    ) {
                        Spacer(modifier = Modifier.weight(1f))

                        OutlinedButton(
                            onClick = { navController.navigate("minhasInteracoes") },
                            modifier = Modifier
                                .padding(horizontal = 5.dp)
                                .size(width = 120.dp, height = 40.dp)
                                .height(35.dp),
                            shape = RoundedCornerShape(5.dp),
                            border = BorderStroke(1.dp, Color(0xFF01A2C3)),
                        ) {
                            Text(
                                stringResource(R.string.Remover),
                                style = letraButton,
                                color = Color(0xFF01A2C3)
                            )
                        }

                        Button(
                            onClick = {
                                navController.navigate("portfolio")
                            },
                            modifier = Modifier
                                .padding(start = 5.dp)
                                .background(Color(0xFF1B1F23))
                                .widthIn(max = 120.dp)
                                .height(40.dp),
                            shape = RoundedCornerShape(3.dp)
                        ) {
                            Text(
                                stringResource(R.string.contato),
                                style = letraButton
                            )
                        }
                    }

                }
            }

        }
    }
}


@Composable
fun CategoriaCardRetangulo(
    imagemCard: Int,
    contentDescription: String,
    buttonText: String,
    onClick: () -> Unit,
    cardSelecionado: Boolean,
    setSelectedCard: (Boolean) -> Unit
) {
    Card(
        modifier = Modifier
            .height(73.dp)
            .width(158.dp)
            .clickable {
                Log.d("CategoriaCardRetangulo", "onClick chamado")
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
                contentDescription = contentDescription,
                modifier = Modifier
                    .fillMaxSize()
                    .fillMaxHeight()
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
                    modifier = Modifier
                        .padding(3.dp)
                )
            }
        }

    }
}

@Preview(showBackground = true)
@Composable
fun MinhasInteracoesPreview() {
    val navController = rememberNavController()
    //MinhasInteracoes(navController)
}
