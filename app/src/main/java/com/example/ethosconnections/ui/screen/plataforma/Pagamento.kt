package com.example.ethosconnections.ui.screen.plataforma

import android.content.ClipData
import android.content.ClipboardManager
import android.content.Context
import android.util.Log
import android.widget.Toast
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
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
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
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.example.ethosconnections.R
import com.example.ethosconnections.datastore.EmpresaDataStore
import com.example.ethosconnections.ui.screen.plataforma.components.BoxEthos
import com.example.ethosconnections.ui.screen.plataforma.components.Rodape
import com.example.ethosconnections.ui.theme.letraButton
import com.example.ethosconnections.ui.theme.tituloConteudoBranco
import com.example.ethosconnections.ui.theme.tituloConteudoBrancoNegrito
import com.example.ethosconnections.ui.theme.tituloConteudoPreto
import com.example.ethosconnections.ui.theme.tituloPagina
import kotlinx.coroutines.delay

@Composable
fun Pagamento(navController: NavController, plano: String,preco: Double, empresaDataStore: EmpresaDataStore) {

    Column {

        Text(
            stringResource(R.string.titulo_pagina_pagamento),
            style = tituloPagina
        )
        var codigoPix by remember { mutableStateOf(gerarCodigoPix()) }
        val context = LocalContext.current

        var planoFormatado = plano.replace( context.getString(R.string.txt_plano), "").trim()
        LaunchedEffect(key1 = true) {
            delay(5000)
            empresaDataStore.mudarPlano(planoFormatado)

            if (planoFormatado.contains(context.getString(R.string.txt_plano_analytics))) {
                navController.navigate("meuProgresso")
            }else{
                navController.navigate("meuPortfolio")
            }
        }

        BoxEthos {
            Column {
                Spacer(modifier = Modifier.height(20.dp))

                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.Center,
                    verticalAlignment = Alignment.CenterVertically
                ) {

                    Image(
                        modifier = Modifier.size(28.dp),
                        painter = painterResource(id = R.mipmap.certo),
                        contentDescription = "Icone de símbolo de certo"
                    )

                    Spacer(modifier = Modifier.width(15.dp))

                    Text(
                        stringResource(R.string.subtitulo_pagina_pagamento),
                        style = tituloConteudoBranco
                    )
                }
            }
            Spacer(modifier = Modifier.height(20.dp))


            var exibirBox1 by remember { mutableStateOf(true) }

            Row {
                Text(text = stringResource(R.string.txt_qr_code_pagamento), style = tituloConteudoBranco.copy(
                    background = if (exibirBox1) Color.Gray else Color.Transparent
                ), modifier = Modifier
                    .clickable { exibirBox1 = true }
                    .padding(end = 8.dp))
                Text(text = stringResource(R.string.txt_pix_copia_e_cola_pagamento), style = tituloConteudoBranco.copy(
                    background = if (!exibirBox1) Color.Gray else Color.Transparent
                ), modifier = Modifier.clickable { exibirBox1 = false })
            }

            if (exibirBox1) {

                Box(
                    modifier = Modifier
                        .background(color = Color.Gray)
                        .padding(16.dp)
                ) {

                    Column(
                        modifier = Modifier.fillMaxWidth(),

                        ) {
                        Text(
                            stringResource(R.string.txt_pagamento),
                            style = tituloConteudoBranco,
                            textAlign = TextAlign.Center,
                            modifier = Modifier.align(Alignment.CenterHorizontally)
                        )

                        Spacer(modifier = Modifier.height(20.dp))

                        Image(
                            modifier = Modifier
                                .size(150.dp)
                                .align(Alignment.CenterHorizontally),
                            painter = painterResource(id = R.mipmap.qrcode),
                            contentDescription = stringResource(R.string.txt_qr_code_pagamento)
                        )


                        Spacer(modifier = Modifier.height(20.dp))

                        Column {

                            Row {

                                Text(
                                    text = stringResource(R.string.txt_nome_empresa_pagamento), style = tituloConteudoBrancoNegrito
                                )
                                Spacer(modifier = Modifier.width(5.dp))
                                Text(
                                    text = empresaDataStore.getRazaoSocialEmpresaFlow().collectAsState(initial = null).value ?: stringResource(R.string.txt_empresa_n_a
                                    ), style = tituloConteudoBranco
                                )
                            }

                            Spacer(modifier = Modifier.height(2.dp))
                            Row {
                                Text(
                                    stringResource(R.string.txt_recebedor_pagamento), style = tituloConteudoBrancoNegrito
                                )
                                Spacer(modifier = Modifier.width(5.dp))
                                Text(
                                    text = stringResource(R.string.txt_nome_social_ethos), style = tituloConteudoBranco
                                )
                            }
                            Spacer(modifier = Modifier.height(2.dp))

                            Row {
                                Text(
                                    stringResource(R.string.txt_plano_pagamento), style = tituloConteudoBrancoNegrito
                                )
                                Spacer(modifier = Modifier.width(5.dp))
                                Text(
                                    text ="${planoFormatado}", style = tituloConteudoBranco
                                )
                            }
                            Spacer(modifier = Modifier.height(2.dp))

                            Row {
                                Text(
                                    stringResource(R.string.txt_vencimento_pagamento), style = tituloConteudoBrancoNegrito
                                )
                                Spacer(modifier = Modifier.width(5.dp))
                                Text(
                                    text = "XXXXX", style = tituloConteudoBranco
                                )
                            }
                            Spacer(modifier = Modifier.height(2.dp))

                            Row {
                                Text(
                                    stringResource(R.string.txt_valor_pagamento), style = tituloConteudoBrancoNegrito
                                )
                                Spacer(modifier = Modifier.width(5.dp))
                                Text(
                                    text = "${preco}" , style = tituloConteudoBranco
                                )
                            }
                            Spacer(modifier = Modifier.height(10.dp))

                        }
                        Spacer(modifier = Modifier.height(20.dp))

                    }


                }
            } else {
                Box(
                    modifier = Modifier
                        .background(color = Color.Gray)
                        .padding(16.dp)
                ) {

                    Column(
                        modifier = Modifier.fillMaxWidth(),

                        ) {
                        Text(
                            stringResource(R.string.txt_pagamento),
                            style = tituloConteudoBranco,
                            textAlign = TextAlign.Center,
                            modifier = Modifier.align(Alignment.CenterHorizontally)
                        )

                        Spacer(modifier = Modifier.height(30.dp))

                        //Editar
                        val context = LocalContext.current
                        Column {
                            Text(
                                stringResource(R.string.txt_pix_pagamento),
                                style = tituloConteudoBrancoNegrito
                            )

                            Text(
                                text = codigoPix,
                                style = tituloConteudoPreto,
                                textAlign = TextAlign.Center,
                                modifier = Modifier
                                    .background(
                                        color = Color(0xFFC1C1C1),
                                        shape = RoundedCornerShape(4.dp)
                                    )
                                    .fillMaxWidth()
                            )

                            Spacer(modifier = Modifier.height(15.dp))

                            Button(
                                onClick = {
                                    copyToClipboard(context, codigoPix)
                                    Toast.makeText(
                                        context,
                                        context.getString(R.string.txt_pix_copiado_pagamento),
                                        Toast.LENGTH_SHORT
                                    ).show()
                                }, modifier = Modifier
                                    .padding(8.dp)
                                    .align(Alignment.CenterHorizontally)
                                    .height(40.dp),
                                shape = RoundedCornerShape(5.dp),
                                colors = ButtonDefaults.buttonColors(
                                    Color(0xFF01A2C3)
                                )
                            ) {
                                Text(
                                    text = stringResource(R.string.txt_pix_copiar_pagamento),
                                    style = letraButton
                                )
                            }
                        }

                        Spacer(modifier = Modifier.height(30.dp))
                        Column {

                            Row {

                                Text(
                                    stringResource(R.string.txt_nome_empresa_pagamento), style = tituloConteudoBrancoNegrito
                                )
                                Spacer(modifier = Modifier.width(5.dp))
                                Text(
                                    text = empresaDataStore.getRazaoSocialEmpresaFlow().collectAsState(initial = null).value ?: stringResource(R.string.txt_empresa_n_a), style = tituloConteudoBranco
                                )
                            }
                            Spacer(modifier = Modifier.height(2.dp))

                            Row {
                                Text(
                                    stringResource(R.string.txt_recebedor_pagamento), style = tituloConteudoBrancoNegrito
                                )
                                Spacer(modifier = Modifier.width(5.dp))
                                Text(
                                    text = stringResource(R.string.txt_nome_social_ethos), style = tituloConteudoBranco
                                )
                            }
                            Spacer(modifier = Modifier.height(2.dp))

                            Row {
                                Text(
                                    stringResource(R.string.txt_plano_pagamento), style = tituloConteudoBrancoNegrito
                                )
                                Spacer(modifier = Modifier.width(5.dp))
                                Text(
                                    text = "XXXXX", style = tituloConteudoBranco
                                )
                            }
                            Spacer(modifier = Modifier.height(2.dp))

                            Row {
                                Text(
                                    stringResource(R.string.txt_vencimento_pagamento), style = tituloConteudoBrancoNegrito
                                )
                                Spacer(modifier = Modifier.width(5.dp))
                                Text(
                                    text = "XXXXX", style = tituloConteudoBranco
                                )
                            }
                            Spacer(modifier = Modifier.height(2.dp))

                            Row {
                                Text(
                                    stringResource(R.string.txt_valor_pagamento), style = tituloConteudoBrancoNegrito
                                )
                                Spacer(modifier = Modifier.width(5.dp))
                                Text(
                                    text = "XXXXX", style = tituloConteudoBranco
                                )
                            }

                        }
                        Spacer(modifier = Modifier.height(40.dp))

                    }


                }
            }
            Spacer(modifier = Modifier.height(20.dp))

        }
        Spacer(modifier = Modifier.height(40.dp))
        Rodape(
            acaoBotaoEsquerda = {},
            nomeBotaoEsquerda = stringResource(R.string.txt_button_concluir),
            acaoBotaoDireita = {},
            nomeBotaoDireita = stringResource(R.string.txt_button_cancelar)
        )

    }

}


fun gerarCodigoPix(): String {
    return (10000000..99999999).random().toString()
}

fun copyToClipboard(context: Context, text: String) {
    val clipboard = context.getSystemService(Context.CLIPBOARD_SERVICE) as ClipboardManager
    val clip = ClipData.newPlainText(context.getString(R.string.txt_pix_pagamento), text)
    clipboard.setPrimaryClip(clip)
}

@Preview(showBackground = true)
@Composable
fun PagamentoPreview() {
    val navController = rememberNavController()
    //Pagamento(navController)
}