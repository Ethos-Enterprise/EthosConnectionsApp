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
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.snapshots.SnapshotStateList
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.example.ethosconnections.R
import com.example.ethosconnections.datastore.EmpresaDataStore
import com.example.ethosconnections.models.Empresa
import com.example.ethosconnections.models.Foto
import com.example.ethosconnections.models.Portfolio
import com.example.ethosconnections.models.Servico
import com.example.ethosconnections.repositories.PortfolioRepository
import com.example.ethosconnections.repositories.ServicoRepository
import com.example.ethosconnections.service.PortfolioService
import com.example.ethosconnections.service.ServicoService
import com.example.ethosconnections.ui.screen.plataforma.components.BoxEthos
import com.example.ethosconnections.ui.theme.corLetra
import com.example.ethosconnections.ui.theme.letraButton
import com.example.ethosconnections.ui.theme.letraClicavel
import com.example.ethosconnections.ui.theme.letraPadrao
import com.example.ethosconnections.ui.theme.tituloConteudoAzul
import com.example.ethosconnections.ui.theme.tituloConteudoBranco
import com.example.ethosconnections.ui.theme.tituloPagina
import com.example.ethosconnections.viewmodel.empresa.EmpresaViewModel
import com.example.ethosconnections.viewmodel.portfolio.PortfolioViewModel
import com.example.ethosconnections.viewmodel.servico.ServicoViewModel
import java.util.UUID

@Composable
fun Portfolio(navController: NavController,servicoViewModel: ServicoViewModel, portfolioViewModel: PortfolioViewModel,empresaViewModel: EmpresaViewModel, empresaDataStore: EmpresaDataStore, fkPrestadora: UUID, idEmpresa: UUID,
) {

    val fkPrestadoraAtual = remember {mutableStateOf(fkPrestadora)}

    LaunchedEffect(key1 = true) {
        val token = empresaDataStore.getToken()
        servicoViewModel.getServicos(token)
        fkPrestadoraAtual.value.let { prestadoraId ->
            portfolioViewModel.getPortfolioByFkPrestadora(prestadoraId, token)
            empresaViewModel.getEmpresaById(idEmpresa, token)
        }
    }

    val servicosState by servicoViewModel.servicos.observeAsState(SnapshotStateList())
    val servicosPortfolioEmpresa = remember { SnapshotStateList<Servico>() }

    LaunchedEffect(servicosState) {
        servicosPortfolioEmpresa.clear()
        servicosPortfolioEmpresa.addAll(servicosState.filter { it.fkPrestadoraServico == fkPrestadoraAtual.value })
    }

    val portfolioAtual = remember { portfolioViewModel.portfolio }.observeAsState()
    val empresaAtual = remember { empresaViewModel.empresa }.observeAsState()

    Column(
        modifier = Modifier.fillMaxSize()
    ) {
        Text(
            stringResource(R.string.titulo_pagina_portfolio),
            style = tituloPagina,
        )
        BoxDadosGerais(navController = navController, portfolioAtual = portfolioAtual.value, empresaAtual = empresaAtual.value, fkPrestadoraAtual = fkPrestadoraAtual.value, portfolioViewModel)
        Column(
            modifier = Modifier.padding(top = 8.dp)
        ) {
            Text(
                stringResource(R.string.titulo_todos_servicos_portfolio),
                style = tituloPagina,
                modifier = Modifier.fillMaxWidth()
            )
            Spacer(modifier = Modifier.height(8.dp))
            BoxTodosServicos(navController, servicosPortfolioEmpresa)
        }
    }
}


@Composable
fun BoxPortfolio(navController: NavController, portfolioAtual: Portfolio?, empresaAtual: Empresa?) {
    val fotoPerfil = remember { mutableStateOf(Foto("", 90, 100)) }
    val fotoCapa = remember { mutableStateOf(Foto("", 110, 500)) }

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
                    .padding(start = 15.dp)
            ) {
                Image(
                    modifier = Modifier
                        .size(fotoPerfil.value.altura.dp)
                        .background(
                            color = Color.Transparent,
                            shape = RoundedCornerShape(7.dp)
                        ),
                    painter = painterResource(id = R.mipmap.portfolio_perfil),
                    contentDescription = "Foto de perfil"
                )
            }
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(
                        top = 35.dp,
                        start = 10.dp
                    )
            ) {
                Text(
                    text = empresaAtual?.razaoSocial ?: stringResource(R.string.txt_empresa_n_a),
                    style = tituloConteudoBranco
                )
                Text(
                    text = portfolioAtual?.linkWebsiteEmpresa ?: stringResource(R.string.txt_empresa_n_a),
                    style = letraClicavel
                )
            }
        }
    }
}


@Composable
fun BoxDadosGerais(navController: NavController, portfolioAtual: Portfolio?, empresaAtual: Empresa?, fkPrestadoraAtual: UUID?, portfolioViewModel: PortfolioViewModel) {
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
                BoxPortfolio(navController, portfolioAtual, empresaAtual)
                Column(
                    modifier = Modifier.padding(start = 15.dp, top = 5.dp)
                ) {
                    Text(
                        text = portfolioAtual?.descricaoEmpresa ?: stringResource(R.string.txt_empresa_n_a),
                        style = corLetra
                    )
                }
            }
        }
        BoxEthos {
            Text(
                stringResource(R.string.subtitulo_sobre_empresa_portfolio),
                style = tituloConteudoAzul,
                modifier = Modifier.fillMaxWidth()
            )
            Divider(modifier = Modifier.padding(bottom = 10.dp))

            Text(
                text = portfolioAtual?.sobreEmpresa ?: stringResource(R.string.txt_sobre_empresa),
                style = corLetra,
                modifier = Modifier
                    .fillMaxWidth()
            )
        }
        BoxEthos {
            Text(
                stringResource(R.string.subtitulo_dados_gerais_portfolio),
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
                            stringResource(R.string.txt_area_atuacao_portfolio),
                            style = letraPadrao
                        )
                        Text(
                            text = empresaAtual?.setor ?: stringResource(R.string.txt_empresa_n_a),
                            style = corLetra
                        )
                    }
                    Column {
                        Spacer(modifier = Modifier.height(8.dp))
                        Text(
                            stringResource(R.string.txt_telefone_portfolio),
                            style = letraPadrao
                        )
                        Text(
                            text = empresaAtual?.telefone ?: stringResource(R.string.txt_empresa_n_a),
                            style = corLetra
                        )
                    }
                }

                Column(
                ) {
                    Column {
                        Text(
                            stringResource(R.string.txt_email_portfolio),
                            style = letraPadrao
                        )
                        Text(
                            text = empresaAtual?.email ?: stringResource(R.string.txt_empresa_n_a),
                            style = corLetra
                        )
                    }
                    Column {
                        Spacer(modifier = Modifier.height(8.dp))
                        Text(
                            stringResource(R.string.txt_qtd_funcionario_portfolio),
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
                    stringResource(R.string.txt_endereco_portfolio),
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
                stringResource(R.string.subtitulo_certificados_portfolio),
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
}

@Composable
fun BoxTodosServicos(navController: NavController, servicos: SnapshotStateList<Servico>) {
    Box {
        Column(
            modifier = Modifier
                .fillMaxWidth()
        ) {
            if (servicos.isEmpty()) {
                Text(text = stringResource(R.string.txt_sem_servicos), style = tituloConteudoBranco)
            } else {
                GridServicos(servicos, navController)
            }        }
    }
}

@Preview(showBackground = true)
@Composable
fun PortfolioPreview() {
    val navController = rememberNavController()
    //Portfolio(navController)
}