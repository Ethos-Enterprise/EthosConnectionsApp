package com.example.ethosconnections.ui.screen.plataforma

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.FavoriteBorder
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Divider
import androidx.compose.material3.Icon
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.window.Dialog
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.example.ethosconnections.R
import com.example.ethosconnections.datastore.EmpresaDataStore
import com.example.ethosconnections.ui.screen.plataforma.components.BoxEthos
import com.example.ethosconnections.ui.screen.plataforma.components.FillButtonEthos
import com.example.ethosconnections.ui.screen.plataforma.components.OutlinedButtonEthos
import com.example.ethosconnections.ui.theme.letraButton
import com.example.ethosconnections.ui.theme.letraDescricao
import com.example.ethosconnections.ui.theme.tituloConteudoAzul
import com.example.ethosconnections.ui.theme.tituloConteudoBranco
import com.example.ethosconnections.ui.theme.tituloConteudoCinza
import com.example.ethosconnections.ui.theme.tituloConteudoCinzaNegrito
import com.example.ethosconnections.ui.theme.tituloPagina
import com.example.ethosconnections.viewmodel.interacao.InteracaoViewModel
import java.util.UUID

@Composable
fun AvaliacaoServico(
    navController: NavController,
    id: UUID,
    nomeServico: String,
    nomeEmpresa: String,
    categoria: String,
    valorMedio: Double,
    descricao: String,
    fkPrestadora:UUID,
    empresaDataStore: EmpresaDataStore,
    interacaoViewModel: InteracaoViewModel
) {

    val empresa by empresaDataStore.getEmpresaFlow().collectAsState(initial = null)
    var token by remember { mutableStateOf<String?>(null) }

    LaunchedEffect(key1 = true) {
        token = empresaDataStore.getToken()
    }

    Column {
        Text(stringResource(R.string.titulo_pagina_avaliacao), style = tituloPagina)

        // Box 1 - Perfil
        BoxEthos {
            Column {
                Row(verticalAlignment = Alignment.CenterVertically) {
                    Image(
                        modifier = Modifier.size(65.dp),
                        painter = painterResource(id = R.mipmap.avaliacaofoto),
                        contentDescription = "Foto Serviço"
                    )
                    Column(
                        modifier = Modifier
                            .align(Alignment.CenterVertically)
                            .padding(start = 15.dp)
                    ) {
                        Text(
                            text = nomeEmpresa,
                            style = tituloPagina,
                        )

                        Text(
                            text = stringResource(R.string.txt_anos_certificada), //colocar ano certificação
                            style = tituloConteudoCinza,
                            fontSize = 11.sp,
                        )
                    }


                    Spacer(modifier = Modifier.weight(1f))

                    OutlinedButton(
                        onClick = { navController.navigate("portfolio/${fkPrestadora}") },
                        shape = RoundedCornerShape(5.dp),
                        border = BorderStroke(2.dp, Color(0xFF01A2C3)),
                        modifier = Modifier.size(width = 140.dp, height = 35.dp)
                    ) {
                        Text(
                            text = stringResource(R.string.txt_ver_portfolio),
                            style = letraButton,
                            color = Color(0xFF01A2C3)
                        )
                    }


                }
            }


        }

        BoxEthos {

            Column {

                Column {
                    Text(
                        text = nomeServico,
                        style = tituloPagina,
                    )
                    Spacer(modifier = Modifier.height(10.dp))

                    Text(
                        text = descricao,
                        style = letraDescricao,
                    )
                    Spacer(modifier = Modifier.height(10.dp))

                    Text(
                        text = "Valor Médio: R\$ ${valorMedio}",
                        style = tituloPagina,
                    )
                    Spacer(modifier = Modifier.height(10.dp))

                    // Modal
                    var showDialog by remember { mutableStateOf(false) }
                    if (showDialog) {
                        Dialog(
                            onDismissRequest = { showDialog = false }
                        ) {
                            Surface(

                            ) {
                                Box(
                                    modifier = Modifier
                                        .fillMaxWidth()
                                        .background(color = Color(0xFF1B1F23))
                                        .padding(20.dp)
                                ) {


                                    Column() {
                                        Image(
                                            modifier = Modifier.size(300.dp),
                                            painter = painterResource(id = R.mipmap.fotoperfilempresa),
                                            contentDescription = "Foto Modal"
                                        )

                                        Text(
                                            stringResource(R.string.subtitulo_modal_avaliacao),
                                            style = tituloConteudoAzul,
                                            fontSize = 17.sp
                                        )


                                        Divider(modifier = Modifier.padding(bottom = 10.dp))



                                        Text(
                                            stringResource(R.string.txt_modal_avaliacao),
                                            style = tituloConteudoBranco,
                                        )
                                        Spacer(modifier = Modifier.height(10.dp))

                                        Column() {

                                            Row {
                                                Text(
                                                    stringResource(R.string.txt_empresa_avaliacao),
                                                    style = tituloConteudoAzul,
                                                )
                                                Spacer(modifier = Modifier.width(5.dp))
                                                Text(
                                                    text = nomeEmpresa,
                                                    style = tituloConteudoBranco,
                                                )
                                            }

                                            Row {
                                                Text(
                                                    stringResource(R.string.txt_servico_avaliacao),
                                                    style = tituloConteudoAzul,
                                                )
                                                Spacer(modifier = Modifier.width(5.dp))
                                                Text(
                                                    text = nomeServico,
                                                    style = tituloConteudoBranco,
                                                )
                                            }

                                            Row {
                                                Text(
                                                    stringResource(R.string.txt_preco_avaliacao),
                                                    style = tituloConteudoAzul,
                                                )
                                                Spacer(modifier = Modifier.width(5.dp))
                                                Text(
                                                    text = "R\$${valorMedio}",
                                                    style = tituloConteudoBranco,
                                                )
                                            }


                                        }

                                        Spacer(modifier = Modifier.height(20.dp))

                                        Row(
                                            horizontalArrangement = Arrangement.Center,
                                            modifier = Modifier.fillMaxWidth()
                                        ) {


                                            OutlinedButtonEthos(
                                                acao = { showDialog = false },
                                                nomeAcao = stringResource(R.string.txt_button_cancelar)
                                            )
                                            Spacer(modifier = Modifier.width(5.dp))
                                            FillButtonEthos(
                                                acao = {
                                                    token?.let { token ->
                                                        interacaoViewModel.postInteracao("PENDENTE", id, empresa?.id ?: UUID.randomUUID(), token) { success ->
                                                            showDialog = false
                                                        }
                                                    }
                                                },
                                                nomeAcao = stringResource(R.string.txt_button_confirmar)
                                            )
                                        }

                                    }
                                }
                            }
                        }
                    }
                    //Fim Modal


                    Row() {
                        Button(
                            onClick = {
                                        showDialog = true
                            },
                            shape = RoundedCornerShape(5.dp),
                            colors = ButtonDefaults.buttonColors(
                                Color(0xFF01A2C3)
                            ), modifier = Modifier.size(width = 180.dp, height = 35.dp)


                        ) {
                            Text(
                                text = stringResource(R.string.txt_solicitar_contato),
                                style = letraButton
                            )
                        }

                        Spacer(modifier = Modifier.width(20.dp))

                        Row() {
                            var isFilled by remember { mutableStateOf(false) }

                            Row(
                                verticalAlignment = Alignment.CenterVertically
                            ) {
                                Icon(
                                    imageVector = if (isFilled) Icons.Default.Favorite else Icons.Default.FavoriteBorder,
                                    contentDescription = "Favoritar",
                                    tint = if (isFilled) Color(0xFF01A2C3) else Color(0xFF01A2C3),
                                    modifier = Modifier.clickable { isFilled = !isFilled }
                                )
                                Spacer(modifier = Modifier.width(5.dp))
                                Text(
                                    text = stringResource(R.string.txt_favoritar),
                                    style = tituloConteudoBranco,
                                    fontSize = 17.sp
                                )
                            }
                        }

                    }

                }
            }

        }


//Box 3 - Avaliação
        BoxEthos {
            Column {
                Row {
                    Text(
                        stringResource(R.string.subtitulo_pagina_avaliacao), style = tituloConteudoAzul,
                        fontSize = 17.sp
                    )
                    Text(
                        text = "(3)", style = tituloConteudoCinzaNegrito,
                        fontSize = 17.sp
                    )
                }


            }

            //Conteúdo 1
            Column {
                Divider(modifier = Modifier.padding(bottom = 10.dp))


                Spacer(modifier = Modifier.height(10.dp))


                Row {

                    Image(
                        modifier = Modifier
                            .size(24.dp),
                        painter = painterResource(id = R.mipmap.usuario),
                        contentDescription = "Usuario"
                    )

                    Spacer(modifier = Modifier.width(10.dp))

                    Text(
                        text = "Matrix Energia", style = tituloConteudoAzul,
                        fontSize = 17.sp
                    )

                    Spacer(modifier = Modifier.width(10.dp))

                    //Estrelas
                    Row(
                        horizontalArrangement = Arrangement.spacedBy(8.dp),
                    ) {
                        Spacer(modifier = Modifier.weight(1f))
                        Image(
                            modifier = Modifier
                                .size(23.dp),
                            painter = painterResource(id = R.mipmap.estrelapreenchida),
                            contentDescription = "estrelaPreenchida"
                        )

                        Image(
                            modifier = Modifier
                                .size(23.dp),
                            painter = painterResource(id = R.mipmap.estrelapreenchida),
                            contentDescription = "estrelaPreenchida"
                        )

                        Image(
                            modifier = Modifier
                                .size(23.dp),
                            painter = painterResource(id = R.mipmap.estrelapreenchida),
                            contentDescription = "estrelaPreenchida"
                        )

                        Image(
                            modifier = Modifier
                                .size(23.dp),
                            painter = painterResource(id = R.mipmap.estrelapreenchida),
                            contentDescription = "estrelaPreenchida"
                        )
                        Image(
                            modifier = Modifier
                                .size(23.dp),
                            painter = painterResource(id = R.mipmap.estrelavazia),
                            contentDescription = "estrelaVazia"
                        )
                    }


                }

                Text(
                    text = "Feito em 06-09-2023", style = tituloConteudoCinza
                )
                Spacer(modifier = Modifier.height(10.dp))
                Text(
                    stringResource(R.string.txt_descricao_avaliacao),
                    style = letraDescricao
                )
                Spacer(modifier = Modifier.height(10.dp))


            }


            //Conteúdo 1
            Column {
                Divider(modifier = Modifier.padding(bottom = 10.dp))


                Spacer(modifier = Modifier.height(10.dp))


                Row {

                    Image(
                        modifier = Modifier
                            .size(24.dp),
                        painter = painterResource(id = R.mipmap.usuario),
                        contentDescription = "Usuario"
                    )

                    Spacer(modifier = Modifier.width(10.dp))

                    Text(
                        text = "CXP Brasil", style = tituloConteudoAzul,
                        fontSize = 17.sp
                    )

                    Spacer(modifier = Modifier.width(10.dp))

                    //Estrelas
                    Row(
                        horizontalArrangement = Arrangement.spacedBy(8.dp),
                    ) {
                        Spacer(modifier = Modifier.weight(1f))
                        Image(
                            modifier = Modifier
                                .size(23.dp),
                            painter = painterResource(id = R.mipmap.estrelapreenchida),
                            contentDescription = "estrelaPreenchida"
                        )

                        Image(
                            modifier = Modifier
                                .size(23.dp),
                            painter = painterResource(id = R.mipmap.estrelapreenchida),
                            contentDescription = "estrelaPreenchida"
                        )

                        Image(
                            modifier = Modifier
                                .size(23.dp),
                            painter = painterResource(id = R.mipmap.estrelapreenchida),
                            contentDescription = "estrelaPreenchida"
                        )

                        Image(
                            modifier = Modifier
                                .size(23.dp),
                            painter = painterResource(id = R.mipmap.estrelapreenchida),
                            contentDescription = "estrelaPreenchida"
                        )
                        Image(
                            modifier = Modifier
                                .size(23.dp),
                            painter = painterResource(id = R.mipmap.estrelavazia),
                            contentDescription = "estrelaVazia"
                        )
                    }


                }

                Text(
                    text = "Feito em 06-09-2023", style = tituloConteudoCinza
                )
                Spacer(modifier = Modifier.height(10.dp))
                Text(
                    stringResource(R.string.txt_descricao_avaliacao),
                    style = letraDescricao
                )
                Spacer(modifier = Modifier.height(10.dp))


            }

            Spacer(modifier = Modifier.height(20.dp))

        }
        Spacer(modifier = Modifier.height(30.dp))


    }

}

@Preview(showBackground = true)
@Composable
fun avaliacaoServicoPreview() {
    val navController = rememberNavController()
   // AvaliacaoServico(navController)
}

