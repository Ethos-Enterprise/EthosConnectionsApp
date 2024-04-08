package com.example.ethosconnections.ui.screen.plataforma

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Divider
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.example.ethosconnections.R
import com.example.ethosconnections.ui.screen.plataforma.components.BoxEthos
import com.example.ethosconnections.ui.theme.corLetra
import com.example.ethosconnections.ui.theme.letraPadrao
import com.example.ethosconnections.ui.theme.tituloConteudoAzul
import com.example.ethosconnections.ui.theme.tituloConteudoBranco
import com.example.ethosconnections.ui.theme.tituloPagina


@Composable
fun Portfolio(navController: NavController) {
    Column {
        Text(text = "Meu portfólio", style = tituloPagina)
    }
}

@Composable
fun BoxDadosEmpresa(
    nomeEmpresa: String?,
    missaoEmpresa: String?,
    visaoEmpresa: String?
) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp)
    ) {

        BoxEthos {
            Row {

                Column {
                    Text(
                        text = "Deloitte",
                        style = tituloConteudoBranco
                    )
                    Text(
                        text = "www.deloitte.com",
                        style = tituloConteudoAzul
                    )
                    Text(
                        text = "Serviços e consultoria de TI | Empresa certificada desde 2018 ",
                        style = corLetra
                    )
                }
            }
            Spacer(modifier = Modifier.height(14.dp))
            Text(
                text = "Sobre a empresa",
                style = tituloConteudoAzul,
                modifier = Modifier.fillMaxWidth()
            )
            Divider(
                color = Color.Gray,
                thickness = 1.dp,
                modifier = Modifier.padding(vertical = 8.dp)
            )
            Text(
                text = "Líder global na prestação de serviços de audit & assurance, consulting, financial advisory, risk advisory, tax e serviços relacionados. A nossa rede de firmas membro compreende mais de 150 países e territórios e presta serviços a quatro em cada cinco entidades listadas na Fortune Global 500®.",
                style = letraPadrao,
                modifier = Modifier.fillMaxWidth()
            )
        }

        BoxEthos{
            Text(
                text = "Dados Gerais",
                style = tituloConteudoAzul,
                modifier = Modifier.fillMaxWidth()
            )
            Divider(
                color = Color.Gray,
                thickness = 1.dp,
                modifier = Modifier.padding(vertical = 8.dp)
            )

            Row {
                Column(
                    modifier = Modifier.padding(horizontal = 8.dp)
                ) {
                    Column {
                        Text(
                            text = "Área de Atuação",
                            style = letraPadrao
                        )
                        Text(
                            text = "Tecnologia da Informação",
                            style = corLetra
                        )
                    }
                    Column {
                        Spacer(modifier = Modifier.height(8.dp))
                        Text(
                            text = "Telefone Corporativo",
                            style = letraPadrao
                        )
                        Text(
                            text = "(11) 2345-6789",
                            style = corLetra
                        )
                    }
                }

                Column(
                ) {
                    Column {
                        Text(
                            text = "Email Corporativo",
                            style = letraPadrao
                        )
                        Text(
                            text = "email@email.com",
                            style = corLetra
                        )
                    }
                    Column {
                        Spacer(modifier = Modifier.height(8.dp))
                        Text(
                            text = "Tamanho da Empresa",
                            style = letraPadrao
                        )
                        Text(
                            text = "60 - 80 funcionários",
                            style = corLetra
                        )
                    }
                }
            }
            Column(
                modifier = Modifier.padding(horizontal = 8.dp)
            ) {
                Text(
                    text = "Endereço",
                    style = letraPadrao
                )
                Text(
                    text = "Rua Haddock Lobo, 595 - São Paulo - SP",
                    style = corLetra
                )
            }
        }

        BoxEthos {
            Text(
                text = "Certificados",
                style = tituloConteudoAzul,
                modifier = Modifier.fillMaxWidth()
            )
            Divider(
                color = Color.Gray,
                thickness = 1.dp,
                modifier = Modifier.padding(vertical = 8.dp)
            )
            Image(
                modifier = Modifier.width(50.dp),
                painter = painterResource(id = R.mipmap.imagem_certificado),
                contentDescription = "Icone da cor branca"
            )
        }
    }
}


@Preview(showBackground = true)
@Composable
fun PortfolioPreview() {
    val navController = rememberNavController()
    Portfolio(navController)
    BoxDadosEmpresa(nomeEmpresa = null, missaoEmpresa = null, visaoEmpresa = null)
}