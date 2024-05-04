package com.example.ethosconnections.ui.screen.plataforma

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
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
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.example.ethosconnections.R
import com.example.ethosconnections.ui.screen.plataforma.components.BoxEthos
import com.example.ethosconnections.ui.screen.plataforma.components.FillButtonEthos
import com.example.ethosconnections.ui.screen.plataforma.components.OutlinedButtonEthos
import com.example.ethosconnections.ui.theme.letraButton
import com.example.ethosconnections.ui.theme.tituloConteudoAzul
import com.example.ethosconnections.ui.theme.tituloConteudoBranco
import com.example.ethosconnections.ui.theme.tituloConteudoCinza
import com.example.ethosconnections.ui.theme.tituloConteudoCinzaNegrito
import com.example.ethosconnections.ui.theme.tituloPagina

@Composable
fun AvaliacaoServico(navController: NavController) {
    Column {
        Text(text = "Avaliação do Serviço", style = tituloPagina)

        // Box 1 - Perfil
        BoxEthos {
            Column {
                Row(verticalAlignment = Alignment.CenterVertically) {
                    Image(
                        modifier = Modifier.size(50.dp),
                        painter = painterResource(id = R.mipmap.avaliacaofoto),
                        contentDescription = "Foto Serviço"
                    )

                    Spacer(modifier = Modifier.width(10.dp))

                    Column(
                        modifier = Modifier
                            .align(Alignment.CenterVertically) // Alinha o texto verticalmente com a imagem
                            .padding(start = 8.dp) // Adiciona espaço à esquerda do texto
                    ) {
                        Text(
                            text = "Deloitte",
                            style = tituloPagina,
                            fontSize = 19.sp,
                        )

                        Text(
                            text = "Certificada desde 2018",
                            style = tituloConteudoCinza,
                            fontSize = 10.sp,
                            )
                    }


                    Spacer(modifier = Modifier.height(10.dp))
                    Spacer(modifier = Modifier.weight(1f))

                    OutlinedButton(
                        onClick = { },
                        shape = RoundedCornerShape(5.dp),
                        border = BorderStroke(2.dp, Color(0xFF01A2C3)),
                        modifier = Modifier.size(width = 140.dp, height = 35.dp)
                    ) {
                        Text(
                            text = "Ver Portfólio",
                            style = letraButton,
                            color = Color(0xFF01A2C3)
                        )
                    }


                }
            }


        }


        //Box 2 - Serviço
        BoxEthos {

            Column {

                Column {
                    Text(
                        text = "Treinamento de Responsabilidade Social Corporativa (RSC)",
                        style = tituloPagina,
                        fontSize = 17.sp
                    )
                    Spacer(modifier = Modifier.height(10.dp))

                    Text(
                        text = "TO treinamento de Responsabilidade Social Corporativa (RSC) é uma parte importante da estratégia de uma empresa para integrar práticas sociais e ambientais responsáveis em suas operações e cultura organizacional. Aqui estão alguns pontos-chave a serem considerados ao desenvolver um programa de treinamento de RSC\n",
                        style = tituloConteudoCinza,
                    )

                    Text(
                        text = "Valor Médio: R\$ 2000\n",
                        style = tituloPagina,
                    )

                    Row() {
                        Button(
                            onClick = {},
                            shape = RoundedCornerShape(5.dp),
                            colors = ButtonDefaults.buttonColors(
                                Color(0xFF01A2C3)
                            ), modifier = Modifier.size(width = 180.dp, height = 35.dp)


                        ) {
                            Text(
                                text = "Solicitar Contato",
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
                                    text = "Favoritar",
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
                        text = "Avaliações de Serviços ", style = tituloConteudoAzul,
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
                Divider(
                    color = Color.Gray,
                    thickness = 1.dp,
                    modifier = Modifier.padding(vertical = 8.dp)
                )

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
                            contentDescription = "estrelVazia"
                        )
                    }


                }

                Text(
                    text = "Feito em 06-09-2023", style = tituloConteudoCinza
                )
                Spacer(modifier = Modifier.height(10.dp))
                Text(
                    text = "Descricao do servico descricao do servico descricao do servico descricao do servico descricao do servico descricao do servico descricao do servico  ",
                    style = tituloConteudoBranco
                )
                Spacer(modifier = Modifier.height(10.dp))


            }


            //Conteúdo 1
            Column {
                Divider(
                    color = Color.Gray,
                    thickness = 1.dp,
                    modifier = Modifier.padding(vertical = 8.dp)
                )

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
                            contentDescription = "estrelVazia"
                        )
                    }


                }

                Text(
                    text = "Feito em 06-09-2023", style = tituloConteudoCinza
                )
                Spacer(modifier = Modifier.height(10.dp))
                Text(
                    text = "Descricao do servico descricao do servico descricao do servico descricao do servico descricao do servico descricao do servico descricao do servico  ",
                    style = tituloConteudoBranco
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
    AvaliacaoServico(navController)
}

