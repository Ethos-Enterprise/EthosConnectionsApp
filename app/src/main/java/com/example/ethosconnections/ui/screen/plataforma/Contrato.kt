package com.example.ethosconnections.ui.screen.plataforma

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.example.ethosconnections.R
import com.example.ethosconnections.datastore.EmpresaDataStore
import com.example.ethosconnections.ui.screen.plataforma.components.Rodape
import com.example.ethosconnections.ui.theme.tituloConteudoBranco
import com.example.ethosconnections.ui.theme.tituloConteudoBrancoNegrito
import com.example.ethosconnections.ui.theme.tituloPagina



@Composable
fun Contrato(navController: NavController,
             nomePlano: String?,
             preco: Double?,
             empresaDataStore: EmpresaDataStore
) {

    Column {
        Text(stringResource(R.string.titulo_pagina_contrato), style = tituloPagina)
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 8.dp)
                .background(color = Color(0xFF1B1F23), shape = RoundedCornerShape(5.dp))
                .padding(15.dp)
                .height(580.dp)
                .verticalScroll(rememberScrollState())
        ) {
            Column {

                Text(
                    stringResource(R.string.txt_aquisicao_plano_contrato),
                    style = tituloConteudoBranco
                )

                Spacer(modifier = Modifier.height(14.dp))

                Column {
                    Row {
                        Text(
                            stringResource(R.string.nome_plano),
                            style = tituloConteudoBranco)
                        Spacer(modifier = Modifier.width(14.dp))

                        Box(
                            modifier = Modifier
                                .size(600.dp, 20.dp) // Ajuste o tamanho conforme necessário
                                .border(
                                    width = 1.dp,
                                    color = Color.White,
                                    shape = RoundedCornerShape(4.dp)
                                ), // Adiciona uma borda branca
                            contentAlignment = Alignment.Center
                        )  {
                            // Conteúdo dentro do retângulo
                            Text(text = "${nomePlano ?: ""}", style = tituloConteudoBranco)
                        }
                    }
                    Row {
                        Text(
                            stringResource(R.string.Preco),
                            style = tituloConteudoBranco)
                        Spacer(modifier = Modifier.width(14.dp))

                        Box(
                            modifier = Modifier
                                .size(600.dp, 20.dp) // Ajuste o tamanho conforme necessário
                                .border(
                                    width = 1.dp,
                                    color = Color.White,
                                    shape = RoundedCornerShape(4.dp)
                                ), // Adiciona uma borda branca
                            contentAlignment = Alignment.Center
                        )  {
                            // Conteúdo dentro do retângulo
                            Text(text = "${preco ?: ""}", style = tituloConteudoBranco)
                        }
                    }
                }

                Column {
                    Spacer(modifier = Modifier.height(14.dp))
                    Text(stringResource(R.string.txt_termos_contrato),
                        style = tituloPagina)
                    Spacer(modifier = Modifier.height(14.dp))
                }

                Column {
                    Text(stringResource(R.string.topico_1_contrato), style = tituloConteudoBrancoNegrito)
                    Text(
                        stringResource(R.string.txt_topico_1_contrato),
                        style = tituloConteudoBranco
                    )
                    Spacer(modifier = Modifier.height(14.dp))
                    Text(stringResource(R.string.topico_2_contrato), style = tituloConteudoBrancoNegrito)
                    Text(
                        stringResource(R.string.txt_topico_2_contrato),
                        style = tituloConteudoBranco
                    )
                    Spacer(modifier = Modifier.height(14.dp))
                    Text(stringResource(R.string.topico_3_contrato), style = tituloConteudoBrancoNegrito)
                    Text(
                        stringResource(R.string.txt_topico_3_contrato),
                        style = tituloConteudoBranco
                    )
                    Spacer(modifier = Modifier.height(14.dp))
                    Text(
                        stringResource(R.string.topico_4_contrato),
                        style = tituloConteudoBrancoNegrito
                    )
                    Text(
                        stringResource(R.string.txt_topico_4_contrato),
                        style = tituloConteudoBranco
                    )
                    Spacer(modifier = Modifier.height(14.dp))
                    Text(
                        stringResource(R.string.topico_5_contrato),
                        style = tituloConteudoBrancoNegrito
                    )
                    Text(
                        stringResource(R.string.txt_topico_5_contrato),
                        style = tituloConteudoBranco
                    )
                    Spacer(modifier = Modifier.height(14.dp))
                    Text(
                        stringResource(R.string.topico_6_contrato),
                        style = tituloConteudoBrancoNegrito
                    )
                    Text(
                        stringResource(R.string.txt_topico_6_contrato),
                        style = tituloConteudoBranco
                    )
                    Spacer(modifier = Modifier.height(14.dp))
                    Text(stringResource(R.string.topico_7_contrato), style = tituloConteudoBrancoNegrito)
                    Text(
                        stringResource(R.string.txt_topico_7_contrato),
                        style = tituloConteudoBranco
                    )
                    Spacer(modifier = Modifier.height(14.dp))

                }


            }

        }
        Rodape(
            acaoBotaoEsquerda = { navController.navigate("pagamento/$nomePlano") },
            nomeBotaoEsquerda = "Adquirir plano",
            acaoBotaoDireita = {},
            nomeBotaoDireita = "Cancelar"
        )


    }

}


@Preview(showBackground = true)
@Composable
fun contratoESGPreview() {
    val navController = rememberNavController()
    //Contrato(navController, "Analytics", 29.90)
}