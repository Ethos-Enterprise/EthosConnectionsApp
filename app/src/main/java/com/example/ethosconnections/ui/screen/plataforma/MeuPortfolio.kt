package com.example.ethosconnections.ui.screen.plataforma

import android.util.Log
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
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
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.Divider
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
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
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.example.ethosconnections.R
import com.example.ethosconnections.datastore.EmpresaDataStore
import com.example.ethosconnections.models.Foto
import com.example.ethosconnections.models.Servico
import com.example.ethosconnections.repositories.PortfolioRepository
import com.example.ethosconnections.repositories.ServicoRepository
import com.example.ethosconnections.service.PortfolioService
import com.example.ethosconnections.service.ServicoService
import com.example.ethosconnections.ui.screen.plataforma.components.BoxEthos
import com.example.ethosconnections.ui.theme.corLetra
import com.example.ethosconnections.ui.theme.letraButton
import com.example.ethosconnections.ui.theme.letraClicavel
import com.example.ethosconnections.ui.theme.letraDescricao
import com.example.ethosconnections.ui.theme.letraPadrao
import com.example.ethosconnections.ui.theme.tituloConteudoAzul
import com.example.ethosconnections.ui.theme.tituloConteudoBranco
import com.example.ethosconnections.ui.theme.tituloPagina
import com.example.ethosconnections.viewmodel.portfolio.PortfolioViewModel
import com.example.ethosconnections.viewmodel.servico.ServicoViewModel

@Composable
fun MeuPortfolio(navController: NavController, empresaDataStore: EmpresaDataStore) {
    val portfolioRepository = remember { PortfolioRepository(PortfolioService.create()) }
    val portfolioViewModel = remember { PortfolioViewModel(portfolioRepository) }

    val servicoRepository = remember { ServicoRepository(ServicoService.create()) }
    val servicoViewModel = remember { ServicoViewModel(servicoRepository) }


    LaunchedEffect(key1 = true) {
        servicoViewModel.getServicos()
    }
    val servicos = remember { servicoViewModel.servicos }.observeAsState(SnapshotStateList())

    Log.d("NavControllerLog", "NavController: $navController, Backstack: ${navController.graph}")

    Column(
        modifier = Modifier
            .fillMaxSize()
    ) {
        Text(
            text = "Meu Portfólio",
            style = tituloPagina,
        )
        BoxMeusDadosGerais(navController, empresaDataStore)
        Column(
            modifier = Modifier
                .padding(top = 8.dp)
        ) {
            Text(
                text = "Todos os serviços",
                style = tituloPagina,
                modifier = Modifier.fillMaxWidth()
            )
            Spacer(modifier = Modifier.height(8.dp))
            BoxTodosMeusServicos(navController, servicos.value)
        }
    }
}


@Composable
fun BoxMeuPortfolio(navController: NavController, empresaDataStore: EmpresaDataStore) {
    val fotoPerfil = remember { mutableStateOf(Foto("", 80, 95)) }
    val fotoCapa = remember { mutableStateOf(Foto("", 110, 500)) }

    val empresa by empresaDataStore.getEmpresaFlow().collectAsState(initial = null)

    Box {
        Image(
            modifier = Modifier
                .align(Alignment.TopCenter)
                .fillMaxWidth()
                .aspectRatio(fotoCapa.value.largura.toFloat() / fotoCapa.value.altura.toFloat()),
            painter = painterResource(id = R.mipmap.portfolio_background_branco),
            contentDescription = "Foto de capa"
        )
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = fotoCapa.value.altura.dp - 60.dp)
        ) {
            Box(
                modifier = Modifier
                    .padding(start = 15.dp)
            ) {
                Image(
                    modifier = Modifier
                        .size(fotoPerfil.value.altura.dp)
                        .background(
                            color = Color.Transparent,
                            shape = RoundedCornerShape(9.dp)
                        ),
                    painter = painterResource(id = R.mipmap.portfolio_perfil_branco),
                    contentDescription = "Foto de perfil"
                )
            }

                Row(
                    modifier = Modifier
                        .padding(
                            top = 35.dp,
                            start = 10.dp
                        )
                ) {
                    Column {

                    Text(
                        text = empresa?.razaoSocial ?: "N/A",
                        style = tituloConteudoBranco
                    )
                    Text(
                        text = "www.empresaA.com",
                        style = letraClicavel,
                        fontSize = 12.sp
                    )
                    }

                    Spacer(modifier = Modifier.width(13.dp))

                    Button(
                        onClick = { navController.navigate("cadastroPortfolio") },
                        modifier = Modifier
                            .wrapContentWidth()
                            .background(Color(0xFF1B1F23))
                            .padding(0.dp)
                            .size(width = 124.dp, height = 40.dp),
                        shape = RoundedCornerShape(5.dp)

                    ) {
                        Text(
                            text = "Editar",
                            style = letraButton,
                            fontSize = 12.sp
                        )
                    }
                }


        }
    }
}


@Composable
fun BoxMeusDadosGerais(navController: NavController, empresaDataStore: EmpresaDataStore) {
    val empresa by empresaDataStore.getEmpresaFlow().collectAsState(initial = null)

    Column(
        modifier = Modifier
            .fillMaxWidth()
    ) {
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .background(color = Color(0xFF1B1F23), shape = RoundedCornerShape(5.dp))
                .padding(start = 0.dp, top = 0.dp, bottom = 15.dp)

        ) {
            Column {
                BoxMeuPortfolio(navController, empresaDataStore)
                Column(
                    modifier = Modifier.padding(start = 15.dp, top = 7.dp)
                ) {
                    Text(
                        text = "Serviços e consultoria de TI | Empresa certificada desde 2018 ",
                        style = corLetra
                    )
                }
            }
        }

        BoxEthos {
            Text(
                text = "Sobre a empresa",
                style = tituloConteudoAzul,
                modifier = Modifier.fillMaxWidth()
            )
            Divider(modifier = Modifier.padding(bottom = 10.dp))

            Text(
//                text = "Líder global na prestação de serviços de audit & assurance, consulting, financial advisory, risk advisory, tax e serviços relacionados. A nossa rede de firmas membro compreende mais de 150 países e territórios e presta serviços a quatro em cada cinco entidades listadas na Fortune Global 500®.",
                text = "Descreve melhor a sua empresa",
                style = letraDescricao,
                modifier = Modifier
                    .fillMaxWidth()
                    .height(20.dp)
            )
        }

        BoxEthos {
            Text(
                text = "Dados Gerais",
                style = tituloConteudoAzul,
                modifier = Modifier.fillMaxWidth()
            )
            Divider(modifier = Modifier.padding(bottom = 10.dp))

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
                            text =  empresa?.setor ?: "N/A",
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
                            text = empresa?.telefone ?: "N/A",
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
                            text = empresa?.email ?: "N/A",
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


    }

    BoxEthos {
        Text(
            text = "Certificados",
            style = tituloConteudoAzul,
            modifier = Modifier.fillMaxWidth()
        )
        Divider(modifier = Modifier.padding(bottom = 10.dp))

        Spacer(modifier = Modifier.height(8.dp))
        Image(
            modifier = Modifier.width(50.dp),
            painter = painterResource(id = R.mipmap.imagem_certificado),
            contentDescription = "Icone da cor branca"
        )
    }
}


@Composable
fun BoxTodosMeusServicos(navController: NavController, servicos: SnapshotStateList<Servico>) {
    Box {
        Column(
            modifier = Modifier
                .fillMaxWidth()
        ) {
//            GridServicos(servicos, navController)
            Text(text = "Nenhum serviço cadastrado", style = tituloConteudoBranco)
        }
    }
}

@Preview(showBackground = true)
@Composable
fun MeuPortfolioPreview() {
    val navController = rememberNavController()
    //MeuPortfolio(navController)
}