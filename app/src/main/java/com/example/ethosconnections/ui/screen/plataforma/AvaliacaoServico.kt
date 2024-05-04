package com.example.ethosconnections.ui.screen.plataforma

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Button
import androidx.compose.material3.Divider
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
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
import com.example.ethosconnections.ui.theme.letraPadrao
import com.example.ethosconnections.ui.theme.tituloConteudoAzul
import com.example.ethosconnections.ui.theme.tituloConteudoBranco
import com.example.ethosconnections.ui.theme.tituloPagina

@Composable
fun AvaliacaoServico(navController: NavController) {
    Column {
        Text(text = "Avaliação do Serviço", style = tituloPagina)



        BoxEthos {

            Row {
                Column {
                    Text(
                        text = "Deloitte", style = tituloPagina,
                        fontSize = 17.sp
                    )
                }

                Spacer(modifier = Modifier.width(20.dp))

                Divider(
                    color = Color.Gray,
                    thickness = 0.1.dp, // Definindo o thickness para 0.5dp para criar um traço fino
                    modifier = Modifier
                        .height(300.dp)
                        .width(1.dp)

                )

                Spacer(modifier = Modifier.width(20.dp))

                Column {
                    Text(
                        text = "(teste)", style = tituloPagina,
                        fontSize = 17.sp
                    )

                }
            }

        }

        Spacer(modifier = Modifier.height(10.dp))


        BoxEthos {
            Column {
                Row {
                    Text(
                        text = "Avaliações de Serviços ", style = tituloConteudoAzul,
                        fontSize = 17.sp
                    )
                    Text(
                        text = "(3)", style = tituloPagina,
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
                    text = "Feito em 06-09-2023", style = tituloConteudoBranco
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
                    text = "Feito em 06-09-2023", style = tituloConteudoBranco
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

