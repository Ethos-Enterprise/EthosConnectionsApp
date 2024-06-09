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
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
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
import java.util.UUID

@Composable
fun MeuPortfolio(navController: NavController,servicoViewModel: ServicoViewModel, portfolioViewModel: PortfolioViewModel, empresaDataStore: EmpresaDataStore) {
    val idPrestadoraState = remember { mutableStateOf<UUID?>(null) }

    LaunchedEffect(key1 = true) {
        val idPrestadora = empresaDataStore.getId()
        idPrestadoraState.value = idPrestadora
        servicoViewModel.getServicos(empresaDataStore.getToken())
    }


    val servicosStateMeuPortfolio by servicoViewModel.servicos.observeAsState(SnapshotStateList())
    val servicosMeuPortfolio = remember { SnapshotStateList<Servico>() }

    LaunchedEffect(servicosStateMeuPortfolio) {
        servicosMeuPortfolio.clear()
        servicosMeuPortfolio.addAll(servicosStateMeuPortfolio.filter { it.fkPrestadoraServico == idPrestadoraState.value })
    }
    Column(
        modifier = Modifier
            .fillMaxSize()
    ) {
        Text(
            stringResource(R.string.titulo_pagina_meu_portfolio),
            style = tituloPagina,
        )
        BoxMeusDadosGerais(navController, empresaDataStore, portfolioViewModel)
        Column(
            modifier = Modifier
                .padding(top = 8.dp)
        ) {
            Text(
                stringResource(R.string.titulo_todos_servicos_meu_portfolio),
                style = tituloPagina,
                modifier = Modifier.fillMaxWidth()
            )
            Spacer(modifier = Modifier.height(8.dp))
            BoxTodosMeusServicos(navController, servicosMeuPortfolio)
        }
    }
}


@Composable
fun BoxMeuPortfolio(navController: NavController, empresaDataStore: EmpresaDataStore, viewModel: PortfolioViewModel) {
    val fotoPerfil = remember { mutableStateOf(Foto("", 80, 95)) }
    val fotoCapa = remember { mutableStateOf(Foto("", 110, 500)) }

    val empresa by empresaDataStore.getEmpresaFlow().collectAsState(initial = null)
    val portfolio by viewModel.portfolio.observeAsState()

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
                        text = empresa?.razaoSocial ?: stringResource(R.string.txt_empresa_n_a),
                        style = tituloConteudoBranco
                    )
                    Text(
                        text = portfolio?.linkWebsiteEmpresa ?: stringResource(R.string.txt_url_site),
                        style = letraClicavel,
                        fontSize = 12.sp
                    )
                    }

                    Spacer(modifier = Modifier.weight(1f))

                    Button(
                        onClick = { navController.navigate("editarMeuPortfolio") },
                        modifier = Modifier
                            .wrapContentWidth()
                            .background(Color(0xFF1B1F23))
                            .padding(0.dp)
                            .size(width = 124.dp, height = 40.dp),
                        shape = RoundedCornerShape(5.dp)

                    ) {
                        Text(
                            text = stringResource(R.string.txt_button_editar),
                            style = letraButton,
                            fontSize = 12.sp
                        )
                    }
                    Spacer(modifier = Modifier.width(5.dp))

                }


        }
    }
}


@Composable
fun BoxMeusDadosGerais(navController: NavController, empresaDataStore: EmpresaDataStore, portfolioViewModel: PortfolioViewModel) {
    val empresa by empresaDataStore.getEmpresaFlow().collectAsState(initial = null)
    val portfolio by portfolioViewModel.portfolio.observeAsState()

    LaunchedEffect(Unit) {
        empresaDataStore.getEmpresaFlow().collect { empresa ->
            empresa?.let {
                portfolioViewModel.getPortfolioByFkPrestadora(it.id!!, empresaDataStore.getToken())
            }
        }
    }

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
                BoxMeuPortfolio(navController, empresaDataStore, portfolioViewModel)
                Column(
                    modifier = Modifier.padding(start = 15.dp, top = 7.dp)
                ) {
                    Text(
                        text = portfolio?.descricaoEmpresa ?: stringResource(R.string.txt_descricao_empresa),
                        style = corLetra
                    )
                }
            }
        }

        BoxEthos {
            Text(
                stringResource(R.string.subtitulo_sobre_empresa_meu_portfolio),
                style = tituloConteudoAzul,
                modifier = Modifier.fillMaxWidth()
            )
            Divider(modifier = Modifier.padding(bottom = 10.dp))

            Text(
                text = portfolio?.sobreEmpresa?: stringResource(R.string.txt_sobre_empresa),
                style = letraDescricao,
                modifier = Modifier
                    .fillMaxWidth()
                    .height(20.dp)
            )
        }

        BoxEthos {
            Text(
                stringResource(R.string.subtitulo_dados_gerais_meu_portfolio),
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
                            text = stringResource(R.string.txt_area_atuacao),
                            style = letraPadrao
                        )
                        Text(
                            text =  empresa?.setor ?: stringResource(R.string.txt_empresa_n_a),
                            style = corLetra
                        )
                    }
                    Column {
                        Spacer(modifier = Modifier.height(8.dp))
                        Text(
                            text = stringResource(R.string.txt_telefone),
                            style = letraPadrao
                        )
                        Text(
                            text = empresa?.telefone ?: stringResource(R.string.txt_empresa_n_a),
                            style = corLetra
                        )
                    }
                }

                Column(
                ) {
                    Column {
                        Text(
                            text = stringResource(R.string.txt_email_portfolio),
                            style = letraPadrao
                        )
                        Text(
                            text = empresa?.email ?: stringResource(R.string.txt_empresa_n_a),
                            style = corLetra
                        )
                    }
                    Column {
                        Spacer(modifier = Modifier.height(8.dp))
                        Text(
                            text = stringResource(R.string.txt_qtd_funcionario_portfolio),
                            style = letraPadrao
                        )
                        Text(
                            text = stringResource(R.string.txt_qtd_funcionario),
                            style = corLetra
                        )
                    }
                    Spacer(modifier = Modifier.height(8.dp))
                }
            }
            Column {
                Text(
                    text = stringResource(R.string.txt_endereco_portfolio),
                    style = letraPadrao
                )
                Text(
                    text = stringResource(R.string.txt_endereco_fake),
                    style = corLetra
                )
            }
        }


    }

    BoxEthos {
        Text(
            stringResource(R.string.subtitulo_certificados_meu_portfolio),
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
            if (servicos.isEmpty()) {
                Text(text = stringResource(R.string.txt_sem_servicos), style = tituloConteudoBranco)
            } else {
                GridServicos(servicos, navController)
            }        }
    }
}

@Preview(showBackground = true)
@Composable
fun MeuPortfolioPreview() {
    val navController = rememberNavController()
    val context = LocalContext.current
    val empresaDataStore = EmpresaDataStore(context)
    //MeuPortfolio(navController, empresaDataStore)
}