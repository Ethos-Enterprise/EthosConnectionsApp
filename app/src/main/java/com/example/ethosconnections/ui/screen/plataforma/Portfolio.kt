package com.example.ethosconnections.ui.screen.plataforma

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.Divider
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.snapshots.SnapshotStateList
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.example.ethosconnections.R
import com.example.ethosconnections.models.Foto
import com.example.ethosconnections.models.Servico
import com.example.ethosconnections.ui.screen.plataforma.components.BoxEthos
import com.example.ethosconnections.ui.theme.corLetra
import com.example.ethosconnections.ui.theme.letraButton
import com.example.ethosconnections.ui.theme.letraClicavel
import com.example.ethosconnections.ui.theme.letraPadrao
import com.example.ethosconnections.ui.theme.tituloConteudoAzul
import com.example.ethosconnections.ui.theme.tituloConteudoBranco
import com.example.ethosconnections.ui.theme.tituloPagina
import com.example.ethosconnections.viewmodel.servico.ServicoViewModel

@Composable
fun Portfolio(navController: NavController, servicoViewModel: ServicoViewModel) {

    LaunchedEffect(key1 = true) {
        servicoViewModel.getServicos()
    }
    val servicos = remember { servicoViewModel.servicos }.observeAsState(SnapshotStateList())


    Column(
        modifier = Modifier
            .fillMaxSize()
    ) {
        Text(
            text = "Meu portfólio",
            style = tituloPagina,
        )
        BoxDadosGerais(navController)
        Column (
            modifier = Modifier
                .padding(top = 30.dp)
        ){
            Text(
                text = "Todos os serviços",
                style = tituloPagina,
                modifier = Modifier.fillMaxWidth()
            )
            Spacer(modifier = Modifier.height(8.dp))
            BoxTodosServicos(navController, servicos.value)
        }
    }
}


@Composable
fun BoxPortfolio(navController: NavController) {
    val fotoPerfil = remember { mutableStateOf(Foto("", 120, 120)) }
    val fotoCapa = remember { mutableStateOf(Foto("", 100, 500)) }

    Box {
        Image(
            modifier = Modifier
                .align(Alignment.TopCenter)
                .fillMaxWidth()
                .aspectRatio(fotoCapa.value.largura.toFloat() / fotoCapa.value.altura.toFloat()),
            painter = painterResource(id = R.mipmap.portfolio_background),
            contentDescription = "Foto de capa"
        )
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = fotoCapa.value.altura.dp - 60.dp)
        ) {
            Box(
                modifier = Modifier
                    .padding(start = 8.dp)
            ) {
                Image(
                    modifier = Modifier
                        .size(fotoPerfil.value.altura.dp),
                    painter = painterResource(id = R.mipmap.portfolio_perfil),
                    contentDescription = "Foto de perfil"
                )
            }
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(
                        top = 30.dp,
                        start = 16.dp
                    )
            ) {
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.End
                ) {
                    Button(
                        onClick = { navController.navigate("cadastroPortfolio") },
                        modifier = Modifier
                            .background(Color(0xFF1B1F23)),
                        shape = RoundedCornerShape(5.dp)
                    ) {
                        Text(
                            text = "Editar dados",
                            style = letraButton
                        )
                    }
                }
            }
        }
    }
}


@Composable
fun BoxDadosGerais(navController: NavController) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
    ) {
        BoxEthos {
            BoxPortfolio(navController)
            Column{
                Text(
                    text = "Deloitte",
                    style = tituloConteudoBranco
                )
                Text(
                    text = "www.deloitte.com",
                    style = letraClicavel
                )
                Text(
                    text = "Serviços e consultoria de TI | Empresa certificada desde 2018 ",
                    style = corLetra
                )
                Spacer(modifier = Modifier.height(16.dp))
            }
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
                modifier = Modifier
                    .fillMaxWidth()
            )
        }

        BoxEthos {
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
                    modifier = Modifier.padding(end = 16.dp)
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
                    Spacer(modifier = Modifier.height(8.dp))
                }
            }
            Column {
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
            Spacer(modifier = Modifier.height(8.dp))
            Image(
                modifier = Modifier.width(50.dp),
                painter = painterResource(id = R.mipmap.imagem_certificado),
                contentDescription = "Icone da cor branca"
            )
        }
    }
}

@Composable
fun BoxTodosServicos(navController: NavController, servicos: SnapshotStateList<Servico>) {
    Box{
        Column(
            modifier = Modifier
                .fillMaxWidth()
        ) {
            GridServicos(servicos, navController)
        }
    }
}

@Preview(showBackground = true)
@Composable
fun PortfolioPreview() {
    val navController = rememberNavController()
    //Portfolio(navController)
}