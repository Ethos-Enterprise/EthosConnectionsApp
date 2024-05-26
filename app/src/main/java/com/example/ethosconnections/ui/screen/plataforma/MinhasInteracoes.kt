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

    LaunchedEffect(key1 = true) {
        interacaoViewModel.getInteracoesByFkEmpresa(empresa?.id ?: UUID.randomUUID(), empresaDataStore.getToken())
    }
    val interacoes = remember { interacaoViewModel.interacoes }.observeAsState(SnapshotStateList())

    // Lógica dos Cards
    var exibirBox1 by remember { mutableStateOf(true) }

    Column {
        Text(text = "Minhas interações", style = tituloPagina)


        BoxEthos {
            Column {
                Text(text = "Categorias de interações", style = tituloConteudoBranco)
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
                        cardSelecionado = false,
                        setSelectedCard = {}
                    )

                    CategoriaCardRetangulo(
                        imagemCard = R.mipmap.favoritoin,
                        contentDescription = "Favoritos",
                        buttonText = "Favoritos",
                        onClick = { exibirBox1 = false },
                        cardSelecionado = false,
                        setSelectedCard = {}
                    )
                }
            }
        }

        // Empresas Contatadas
        if (exibirBox1) {
            Column{
                Spacer(modifier = Modifier.height(10.dp))

                Text(text = "Empresas Contatadas", style = tituloConteudoBranco)
                BoxEthos{

                    Column {
                        Row(modifier = Modifier.padding(end = 8.dp)) {
                            Text(text = "Em andamento: ", style = tituloConteudoBrancoNegrito)
                            Text(text = "1 Empresa", style = tituloConteudoBranco, )
                        }

                        Row() {
                            Text(text = "Finalizado: ", style = tituloConteudoBrancoNegrito)
                            Text(text = "1 Empresa", style = tituloConteudoBranco)
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
                                        text = "Serviço de interesse: ${interacao.nomeServico}",
                                        style = tituloConteudoBranco,
                                        fontSize = 12.sp
                                    )
                                    Text(
                                        text = "Status do contato: ${interacao.status}",
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
                                        text = "Avaliar Serviço",
                                        style = letraButton
                                    )
                                }
                            }
                        }
                    }
                }


//                BoxEthos{
//                    Column {
//                        Row {
//                            Image(
//                                modifier = Modifier
//                                    .width(100.dp)
//                                    .padding(top = 15.dp),
//                                painter = painterResource(id = R.mipmap.ey),
//                                contentDescription = "Logo EY"
//                            )
//                            Column {
//                                Text(
//                                    text = "Ernest & Young",
//                                    style = tituloConteudoAzul
//                                )
//                                Text(
//                                    text = "Serviço de interesse: Gestão de portfólios de investimentos",
//                                    style = tituloConteudoBranco,
//                                    fontSize = 12.sp
//                                )
//                                Text(text = "Status do contato: Contato realizado",
//                                    style = tituloConteudoBrancoNegrito,
//                                    fontSize = 12.sp
//                                )
//                            }
//                        }
//
//                        Row(
//                            modifier = Modifier.fillMaxWidth(),
//                            horizontalArrangement = Arrangement.End
//                        ) {
//                            Button(
//                                onClick = {
//                                    navController.navigate("minhasInteracoes")
//                                },
//                                modifier = Modifier
//                                    .padding(start = 5.dp)
//                                    .background(Color(0xFF1B1F23))
//                                    .widthIn(max = 200.dp)
//                                    .height(35.dp),
//                                shape = RoundedCornerShape(3.dp)
//                            ) {
//                                Text(
//                                    text = "Avaliar Serviço",
//                                    style = letraButton
//                                )
//                            }
//                        }
//                    }
//                }
            }

        } else {
            // Histórico de Curtidas
            Column {
                Spacer(modifier = Modifier.height(10.dp))

                Text(text = "Histórico de Curtidas", style = tituloConteudoBranco)

                BoxEthos{
                    Row(modifier = Modifier.padding(end = 8.dp)) {
                        Text(text = "Total: ", style = tituloConteudoBrancoNegrito)
                        Text(text = "2 Empresas", style = tituloConteudoBranco, )
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
                                text = "Treinamento de Responsabilidade Social Corporativa (RSC)",
                                style = tituloConteudoAzul
                            )
                            Text(
                                text = "Deloitte",
                                style = tituloConteudoBranco,
                                fontSize = 12.sp
                            )
                            Text(
                                text = "Saiba mais",
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
                                text = "Remover",
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
                                text = "Contato",
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
                                text = "Gestão de portfólios de investimentos",
                                style = tituloConteudoAzul
                            )
                            Text(
                                text = "Ernest & Young",
                                style = tituloConteudoBranco,
                                fontSize = 12.sp
                            )
                            Text(
                                text = "Saiba mais",
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
                                text = "Remover",
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
                                text = "Contato",
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
