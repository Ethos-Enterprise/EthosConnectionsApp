package com.example.ethosconnections.ui.screen.plataforma

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Divider
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.example.ethosconnections.ui.screen.plataforma.components.BoxEthos
import com.example.ethosconnections.ui.theme.corLetra
import com.example.ethosconnections.ui.theme.letraPadrao
import com.example.ethosconnections.ui.theme.tituloConteudoAzul
import com.example.ethosconnections.ui.theme.tituloConteudoBranco
import com.example.ethosconnections.ui.theme.tituloConteudoBrancoNegrito
import com.example.ethosconnections.ui.theme.tituloPagina

@Composable
fun MeuPerfil(navController: NavController,
) {
    Column {

        Text(text = "Minha conta", style = tituloPagina)

        BoxEthos {
            Column {
                Text(text = "Meu Perfil", style = tituloConteudoAzul)
                Divider(modifier = Modifier.padding(bottom = 6.dp))
                Text(text = "Minhas informações", style = tituloConteudoBranco)

                Column(
                    modifier = Modifier
                        .padding(2.dp) // Adiciona um preenchimento interno
                        .background(color = Color.Transparent) // Define a cor de fundo como transparente
                        .border(
                            1.dp,
                            Color.White,
                            shape = RoundedCornerShape(5.dp)
                        ) // Adiciona bordas finas e pontas arredondadas
                ) {
                    Row {
                        Column(
                            modifier = Modifier.padding(end = 10.dp)
                        ) {
                            Column {
                                Text(
                                    text = "Nome da empresa: Deloitte",
                                    style = letraPadrao
                                )
                            }
                            Column {
                                Text(
                                    text = "CNPJ: 12.345.678/9101-12:",
                                    style = letraPadrao
                                )
                            }
                            Column {
                                Spacer(modifier = Modifier.height(8.dp))
                                Text(
                                    text = "Área de atuação: Consultoria",
                                    style = letraPadrao
                                )
                                Text(
                                    text = "Tamanho da Empresa: 0 Funcionários",
                                    style = corLetra
                                )
                            }
                        }
                    }
                }
                Text(text = "Endereço e Contatos", style = tituloConteudoBranco)

                Column(
                    modifier = Modifier
                        .padding(2.dp) // Adiciona um preenchimento interno
                        .background(color = Color.Transparent) // Define a cor de fundo como transparente
                        .border(
                            1.dp,
                            Color.White,
                            shape = RoundedCornerShape(5.dp)
                        ) // Adiciona bordas finas e pontas arredondadas
                ) {
                    Row {
                        Column(
                            modifier = Modifier.padding(end = 16.dp)
                        ) {
                            Column {
                                Text(
                                    text = "Endereço: Av. Brig. Faria Lima, 2066 - Pinheiros, São Paulo - SP, 01451-001",
                                    style = letraPadrao
                                )
                            }
                            Column {
                                Spacer(modifier = Modifier.height(8.dp))
                                Text(
                                    text = "Telefone Corporativo: 11 3027-2900",
                                    style = letraPadrao
                                )
                                Text(
                                    text = "Email Corporativo: contato@matrixenergia.com",
                                    style = corLetra
                                )
                            }
                        }
                    }
                }
            }
        }
    }
 }


@Preview(showBackground = true)
@Composable
fun MeuPerfilPreview() {
    val navController = rememberNavController()
    MeuPerfil(navController)
}