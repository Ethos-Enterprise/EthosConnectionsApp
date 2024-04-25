package com.example.ethosconnections.ui.screen.plataforma

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.border
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
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.example.ethosconnections.ui.screen.plataforma.components.BoxEthos
import com.example.ethosconnections.ui.theme.letraButton
import com.example.ethosconnections.ui.theme.tituloConteudoBranco
import com.example.ethosconnections.ui.theme.tituloConteudoBrancoNegrito
import com.example.ethosconnections.ui.theme.tituloPagina

@Composable
fun Contrato(navController: NavController) {
    boxContrato("teste", 20.2)
}

@Composable
fun boxContrato(
    nomePlano: String?,
    preco: Double?,
) {

    Column {

        Text(text = "Aquisição de Plano", style = tituloPagina)


        BoxEthos {
            val scrollState = rememberScrollState()



            Text(
                text = "Leia atentamente as informações para a aquisição do plano",
                style = tituloConteudoBranco
            )

            Spacer(modifier = Modifier.height(14.dp))

            Column {
                Row {
                    Text(text = "Nome do Plano:", style = tituloConteudoBranco)
                    Spacer(modifier = Modifier.width(14.dp))

                    Box(
                        modifier = Modifier
                            .size(600.dp, 20.dp) // Ajuste o tamanho conforme necessário
                            .border(
                                width = 1.dp,
                                color = Color.White
                            ), // Adiciona uma borda branca
                        contentAlignment = Alignment.Center
                    ) {
                        // Conteúdo dentro do retângulo
                        Text(text = "Teste", style = tituloConteudoBranco)
                    }
                }
                Row {
                    Text(text = "Preço: ", style = tituloConteudoBranco)
                    Spacer(modifier = Modifier.width(14.dp))

                    Box(
                        modifier = Modifier
                            .size(600.dp, 20.dp) // Ajuste o tamanho conforme necessário
                            .border(
                                width = 1.dp,
                                color = Color.White
                            ), // Adiciona uma borda branca
                        contentAlignment = Alignment.Center
                    ) {
                        // Conteúdo dentro do retângulo
                        Text(text = "Teste", style = tituloConteudoBranco)
                    }
                }
            }

            Column {
                Spacer(modifier = Modifier.height(14.dp))
                Text(text = "Termos e Condições", style = tituloPagina)
                Spacer(modifier = Modifier.height(14.dp))
            }

            Column {
                Text(text = "1. Descrição do Plano", style = tituloConteudoBrancoNegrito)
                Text(
                    text = "   O Plano oferece serviços e benefícios conforme descritos na oferta ou material promocional da Ethos.",
                    style = tituloConteudoBranco
                )
                Spacer(modifier = Modifier.height(14.dp))
                Text(text = "2. Pagamento e Faturamento", style = tituloConteudoBrancoNegrito)
                Text(
                    text = "    O Cliente concorda em pagar as taxas de acordo com as condições de pagamento especificadas pela Ethos.",
                    style = tituloConteudoBranco
                )
                Spacer(modifier = Modifier.height(14.dp))
                Text(text = "3. Duração e Cancelamento", style = tituloConteudoBrancoNegrito)
                Text(
                    text = "    A duração do Plano é estabelecida na aquisição. O Cliente pode cancelar de acordo com as políticas de cancelamento da Ethos,",
                    style = tituloConteudoBranco
                )
                Spacer(modifier = Modifier.height(14.dp))
                Text(
                    text = "4. Responsabilidades do Cliente",
                    style = tituloConteudoBrancoNegrito
                )
                Text(
                    text = "    O Cliente deve usar os serviços de forma ética e legal, respeitando todas as leis aplicáveis.",
                    style = tituloConteudoBranco
                )
                Spacer(modifier = Modifier.height(14.dp))
                Text(
                    text = "5. Responsabilidades da Ethos",
                    style = tituloConteudoBrancoNegrito
                )
                Text(
                    text = "    A Ethos fornecerá os serviços de acordo com os padrões de qualidade e prazos acordados.",
                    style = tituloConteudoBranco
                )
                Spacer(modifier = Modifier.height(14.dp))
                Text(
                    text = "6. Privacidade e Proteção de Dados",
                    style = tituloConteudoBrancoNegrito
                )
                Text(
                    text = "    A Ethos protegerá os dados do Cliente de acordo com as leis de privacidade aplicáveis. Consulte a Política de Privacidade da Ethos para obter mais informações.",
                    style = tituloConteudoBranco
                )
                Spacer(modifier = Modifier.height(14.dp))
                Text(text = "7. Disposições Gerais", style = tituloConteudoBrancoNegrito)
                Text(
                    text = "    Estes Termos e Condições constituem o acordo",
                    style = tituloConteudoBranco
                )
                Spacer(modifier = Modifier.height(14.dp))

            }


        }
        rodape()


    }
}


@Composable
fun rodape() {

//Rodapé

    // Botões fixados na parte inferior
    val color = Color(0xFF1B1F23)
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .padding(start = 0.dp, top = 15.dp, end = 0.dp, bottom = 0.dp)
            .background(color) // Adicionando retângulo por trás dos botões

    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Button(
                onClick = { /*TODO*/ },
                modifier = Modifier
                    .weight(1f)
                    .padding(horizontal = 10.dp),
                shape = RoundedCornerShape(5.dp),
            ) {
                Text(
                    text = "Adquirir Plano",
                    style = letraButton
                )
            }


            OutlinedButton(
                onClick = { /*TODO*/ },
                modifier = Modifier
                    .weight(1f)
                    .padding(horizontal = 10.dp),
                shape = RoundedCornerShape(5.dp),
                border = BorderStroke(2.dp, Color(0xFF01A2C3)),
            ) {
                Text(
                    text = "Cancelar",
                    style = letraButton,
                    color = Color(0xFF01A2C3)
                )
            }
        }
    }

//fim
}


@Preview(showBackground = true)
@Composable
fun contratoESGPreview() {
    val navController = rememberNavController()


    Contrato(navController)


}