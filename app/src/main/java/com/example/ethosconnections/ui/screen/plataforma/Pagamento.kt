package com.example.ethosconnections.ui.screen.plataforma

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
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
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.example.ethosconnections.R
import com.example.ethosconnections.ui.screen.plataforma.components.BoxEthos
import com.example.ethosconnections.ui.screen.plataforma.components.Rodape

import com.example.ethosconnections.ui.theme.tituloConteudoBranco
import com.example.ethosconnections.ui.theme.tituloConteudoBrancoNegrito
import com.example.ethosconnections.ui.theme.tituloPagina

@Composable
fun Pagamento(navController: NavController) {

    Column {

        Text(text = "Realizar Pagamento Pix", style = tituloPagina)

        BoxEthos {
            Column {
                Spacer(modifier = Modifier.height(20.dp))

                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.Center, // Centraliza horizontalmente
                    verticalAlignment = Alignment.CenterVertically // Centraliza verticalmente
                ) {

                    Image(
                        modifier = Modifier.size(28.dp),
                        painter = painterResource(id = R.mipmap.certo),
                        contentDescription = "Icone de símbolo de certo"
                    )

                    Spacer(modifier = Modifier.width(15.dp))

                    Text(
                        text = "Obrigado por fazer parte da Ethos!", style = tituloConteudoBranco
                    )


                }
            }
            Spacer(modifier = Modifier.height(20.dp))


            var exibirBox1 by remember { mutableStateOf(true) }

            Row {
                Text(text = "QR Code", style = tituloConteudoBranco.copy(
                    background = if (exibirBox1) Color.Gray else Color.Transparent
                ), modifier = Modifier
                    .clickable { exibirBox1 = true }
                    .padding(end = 8.dp))
                Text(text = "Pix copia e cola", style = tituloConteudoBranco.copy(
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
                            text = "Sua plataforma será atualizada imediatamente após o pagamento",
                            style = tituloConteudoBranco,
                            textAlign = TextAlign.Center,
                            modifier = Modifier.align(Alignment.CenterHorizontally)
                        )

                        Spacer(modifier = Modifier.height(30.dp))

                        Image(
                            modifier = Modifier
                                .size(200.dp)
                                .align(Alignment.CenterHorizontally),
                            painter = painterResource(id = R.mipmap.qrcode),
                            contentDescription = "QR CODE"
                        )


                        Spacer(modifier = Modifier.height(30.dp))

                        Column {

                            Row {

                                Text(
                                    text = "Nome da Empresa:", style = tituloConteudoBrancoNegrito
                                )
                                Spacer(modifier = Modifier.width(5.dp))
                                Text(
                                    text = "Matrix", style = tituloConteudoBranco
                                )
                            }
                            Spacer(modifier = Modifier.height(2.dp))

                            Row {
                                Text(
                                    text = "Recebedor:", style = tituloConteudoBrancoNegrito
                                )
                                Spacer(modifier = Modifier.width(5.dp))
                                Text(
                                    text = " Ethos Connections cia.", style = tituloConteudoBranco
                                )
                            }
                            Spacer(modifier = Modifier.height(2.dp))

                            Row {
                                Text(
                                    text = "Plano:", style = tituloConteudoBrancoNegrito
                                )
                                Spacer(modifier = Modifier.width(5.dp))
                                Text(
                                    text = "XXXXX", style = tituloConteudoBranco
                                )
                            }
                            Spacer(modifier = Modifier.height(2.dp))

                            Row {
                                Text(
                                    text = "Vencimento:", style = tituloConteudoBrancoNegrito
                                )
                                Spacer(modifier = Modifier.width(5.dp))
                                Text(
                                    text = "XXXXX", style = tituloConteudoBranco
                                )
                            }
                            Spacer(modifier = Modifier.height(2.dp))

                            Row {
                                Text(
                                    text = "Valor:", style = tituloConteudoBrancoNegrito
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
            } else {
                Box(
                    modifier = Modifier
                        .background(color = Color.Gray)
                        .padding(16.dp)
                ) {
                    Text(
                        text = "Box2", style = tituloConteudoBranco
                    )
                }
            }
            Spacer(modifier = Modifier.height(40.dp))

        }



        Column(
            modifier = Modifier.fillMaxSize(),
            verticalArrangement = Arrangement.spacedBy(0.dp)
        ) {
            // Conteúdo da sua página aqui

            Spacer(modifier = Modifier.weight(1f))

            Rodape(
                acaoBotaoEsquerda = {},
                nomeBotaoEsquerda = "Concluir",
                acaoBotaoDireita = {},
                nomeBotaoDireita = "Cancelar"
            )
        }

    }

}


@Preview(showBackground = true)
@Composable
fun PagamentoPreview() {
    val navController = rememberNavController()
    Pagamento(navController)
}