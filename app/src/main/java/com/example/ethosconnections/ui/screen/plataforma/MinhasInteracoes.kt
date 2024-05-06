package com.example.ethosconnections.ui.screen.plataforma

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.widthIn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.example.ethosconnections.R
import com.example.ethosconnections.ui.screen.plataforma.components.BoxEthos
import com.example.ethosconnections.ui.theme.letraButton
import com.example.ethosconnections.ui.theme.letraClicavel
import com.example.ethosconnections.ui.theme.tituloConteudoAzul
import com.example.ethosconnections.ui.theme.tituloConteudoBranco
import com.example.ethosconnections.ui.theme.tituloConteudoBrancoNegrito
import com.example.ethosconnections.ui.theme.tituloPagina

@Composable
fun MinhasInteracoes(navController: NavController){
    Column {

        Text(text = "Minhas interações", style = tituloPagina)

        BoxEthos{
            Text(text = "Categorias de interações", style = tituloConteudoBranco)

        }

        Text(text = "Empresas Contatadas", style = tituloPagina)

        BoxEthos{

            Row {


                Row(modifier = Modifier.padding(end = 8.dp)) {
                    Text(text = "Em andamento: ", style = tituloConteudoBrancoNegrito)
                    Text(text = "1 empresas", style = tituloConteudoBranco, )
                }

                Row() {
                    Text(text = "Finalizado: ", style = tituloConteudoBrancoNegrito)
                    Text(text = "1 empresas", style = tituloConteudoBranco)
                }
            }
        }

        BoxEthos{
            Column {
                Row {
                    Image(
                        modifier = Modifier
                            .width(100.dp),
                        painter = painterResource(id = R.mipmap.deloitte_logo),
                        contentDescription = "Logo Deloitte"
                    )
                    Column {
                        Text(
                            text = "Deloitte",
                            style = tituloConteudoAzul
                        )
                        Text(
                            text = "Serviço de interesse: Treinamento de Responsabilidade Social Corporativa (RSC)",
                            style = tituloConteudoBranco,
                            fontSize = 12.sp
                        )
                        Text(text = "Status do contato: Aguardando resposta da empresa",
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
                            navController.navigate("")
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

        BoxEthos {
            Row {
                Image(
                    modifier = Modifier
                        .width(100.dp),
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
                    onClick = { navController.navigate("") },
                    modifier = Modifier
                        .padding(horizontal = 5.dp)
                        .size(width = 110.dp, height = 40.dp)
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
                        navController.navigate("")
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


@Preview(showBackground = true)
@Composable
fun MinhasInteracoesPreview() {
    val navController = rememberNavController()
    MinhasInteracoes(navController)
}