@file:OptIn(ExperimentalMaterial3Api::class)

package com.example.ethosconnections.ui.screen

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.outlined.Person
import androidx.compose.material3.DrawerValue
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.ModalDrawerSheet
import androidx.compose.material3.ModalNavigationDrawer
import androidx.compose.material3.NavigationDrawerItem
import androidx.compose.material3.NavigationDrawerItemDefaults
import androidx.compose.material3.Text
import androidx.compose.material3.rememberDrawerState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.compose.runtime.getValue
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.compose.AppTheme
import com.example.compose.cor_primaria
import com.example.ethosconnections.R
import com.example.ethosconnections.models.Empresa
import com.example.ethosconnections.repositories.EmpresaRepository
import com.example.ethosconnections.service.EmpresaService
import com.example.ethosconnections.ui.screen.plataforma.AvaliacaoServico
import com.example.ethosconnections.ui.screen.plataforma.CadastroPortfolio
import com.example.ethosconnections.ui.screen.plataforma.MeuProgresso
import com.example.ethosconnections.ui.screen.plataforma.Portfolio
import com.example.ethosconnections.ui.screen.plataforma.SolucoesESG
import com.example.ethosconnections.viewmodel.empresa.EmpresaViewModel
import kotlinx.coroutines.launch

data class NavigationItem(
    val titulo: String,
    val selecionadoIcone: ImageVector,
    val naoSelecionadoIcone: ImageVector,
    val quantidade: Int? = null,
    val rota: String
)

@Composable
fun Plataforma(navController: NavController, empresaData: Empresa?, modifier: Modifier = Modifier) {

    val items = listOf(
        NavigationItem(
            titulo = "Meu Perfil",
            selecionadoIcone = Icons.Outlined.Person,
            naoSelecionadoIcone = Icons.Filled.Person,
            rota = "portfolio"
        ),
        NavigationItem(
            titulo = "Meu Progresso",
            selecionadoIcone = Icons.Outlined.Person,
            naoSelecionadoIcone = Icons.Filled.Person,
            rota = "meuProgresso"
        ),
        NavigationItem(
            titulo = "Cadastrar",
            selecionadoIcone = Icons.Outlined.Person,
            naoSelecionadoIcone = Icons.Filled.Person,
            rota = "cadastroPortfolio"
        )

    )

    val drawerState = rememberDrawerState(initialValue = DrawerValue.Closed)
    val scope = rememberCoroutineScope()
    var itemSelecionado by rememberSaveable {
        mutableStateOf(0)
    }
    ModalNavigationDrawer(
        drawerContent = {
            ModalDrawerSheet {
                Spacer(modifier = Modifier.height(10.dp))
                items.forEachIndexed { index, item ->
                    NavigationDrawerItem(
                        label = {
                            Text(text = item.titulo)
                        },
                        selected = index == itemSelecionado,
                        onClick = {
                            navController.navigate(item.rota)
                            itemSelecionado = index
                            scope.launch {
                                drawerState.close()
                            }
                        },
                        icon = {
                            Icon(
                                imageVector = if (index == itemSelecionado) {
                                    item.selecionadoIcone
                                } else {
                                    item.naoSelecionadoIcone
                                }, contentDescription = item.titulo
                            )
                        },
                        modifier = Modifier
                            .padding(NavigationDrawerItemDefaults.ItemPadding)

                    )

                }


            }
        },
        drawerState = drawerState
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(start = 15.dp, top = 15.dp, end = 15.dp, bottom = 10.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {


            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {

                IconButton(onClick = {
                    scope.launch {
                        drawerState.open()
                    }

                }) {
                    Icon(
                        modifier = Modifier.padding(0.dp),
                        imageVector = Icons.Filled.Menu,
                        tint = cor_primaria,
                        contentDescription = "Menu"
                    )
                }

                Image(
                    modifier = Modifier
                        .width(150.dp),
                    painter = painterResource(id = R.drawable.logo_branco),
                    contentDescription = "Icone da cor branca"
                )

                Image(
                    modifier = Modifier
                        .height(25.dp),
                    painter = painterResource(id = R.mipmap.notificacao_icone),
                    contentDescription = "Ícone do menu"
                )

            }
            Spacer(modifier = Modifier.height(20.dp))

            val componenteNavController = rememberNavController()

            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .verticalScroll(rememberScrollState())
                    .padding(0.dp),
                contentAlignment = Alignment.TopStart
            ) {


                NavHost(navController = componenteNavController, startDestination = "solucoesEsg") {
                    composable("solucoesEsg") {
                        SolucoesESG(componenteNavController)
                    }

                    composable("avaliacaoServico") {
                        AvaliacaoServico(componenteNavController)
                    }

                    composable("meuProgresso") {
                        MeuProgresso(componenteNavController)
                    }

                    composable("portfolio") {
                        Portfolio(componenteNavController)
                    }

                    composable("cadastroPortfolio") {
                        CadastroPortfolio(componenteNavController)
                    }
                }
            }

        }
    }
//    Column(
//        modifier = Modifier
//            .fillMaxSize()
//            .padding(start = 15.dp, top = 15.dp, end = 15.dp, bottom = 10.dp),
//        horizontalAlignment = Alignment.CenterHorizontally
//    ) {


//
//        Row(
//            modifier = Modifier.fillMaxWidth(),
//            horizontalArrangement = Arrangement.SpaceBetween,
//            verticalAlignment = Alignment.CenterVertically
//        ) {
//
//            Image(
//                modifier = Modifier
//                    .height(30.dp),
//                painter = painterResource(id = R.mipmap.menu_lateral),
//                contentDescription = "Ícone do menu"
//            )
//
//            Image(
//                modifier = Modifier
//                    .width(150.dp),
//                painter = painterResource(id = R.drawable.logo_branco),
//                contentDescription = "Icone da cor branca"
//            )
//
//            Image(
//                modifier = Modifier
//                    .height(25.dp),
//                painter = painterResource(id = R.mipmap.notificacao_icone),
//                contentDescription = "Ícone do menu"
//            )
//
//        }
//        Spacer(modifier = Modifier.height(20.dp))

//        val componenteNavController = rememberNavController()
//
//        Box(
//            modifier = Modifier
//                .fillMaxSize()
//                .verticalScroll(rememberScrollState())
//                .padding(0.dp),
//            contentAlignment = Alignment.TopStart
//        ) {
//
//
//            NavHost(navController = componenteNavController, startDestination = "solucoesEsg") {
//                composable("solucoesEsg") {
//                    SolucoesESG(componenteNavController)
//                }
//
//                composable("avaliacaoServico") {
//                    AvaliacaoServico(componenteNavController)
//                }
//
//                composable("meuProgresso") {
//                    MeuProgresso(componenteNavController)
//                }
//
//                composable("portfolio") {
//                    Portfolio(componenteNavController)
//                }
//
//                composable("cadastroPortfolio") {
//                    CadastroPortfolio(componenteNavController)
//                }
//            }
//        }

//        Text(text = "FIZEMOS LOGIN", style = letraPadrao)
//        Spacer(modifier = Modifier.weight(1f))
//        empresaData?.let { empresa ->
//            Text(text = "Razão Social: ${empresa.razaoSocial ?: ""}", style = letraPadrao)
//            Text(text = "CNPJ: ${empresa.cnpj ?: ""}", style = letraPadrao)
//            Text(text = "Telefone: ${empresa.telefone ?: ""}", style = letraPadrao)
//            Text(text = "Email: ${empresa.email ?: ""}", style = letraPadrao)
//            Text(text = "Setor: ${empresa.setor ?: ""}", style = letraPadrao)
//            Text(text = "Quantidade de Funcionários: ${empresa.qtdFuncionarios ?: ""}", style = letraPadrao)
//            Text(text = "Assinante Newsletter: ${empresa.assinanteNewsletter ?: ""}", style = letraPadrao)
//        }
}
//}

@Preview(showBackground = true)
@Composable
fun PlataformaPreview() {
    AppTheme {
        val navController = rememberNavController()

        val empresaService = EmpresaService.create()
        val empresaRepository = EmpresaRepository(empresaService)
        val viewModel = EmpresaViewModel(empresaRepository)

        Plataforma(navController, empresaData = null)
    }
}


//     val componenteNavController = rememberNavController()
//    NavHost(navController, startDestination = "solucoesEsg") {
//
//        NavHost(navController = componenteNavController, startDestination = "solucoesEsg") {
//            composable("solucoesEsg") {
//                SolucoesESG(componenteNavController)
//            }
//
//            composable("avaliacaoServico") {
//                AvaliacaoServico(componenteNavController)
//            }
//
//            composable("meuProgresso") {
//                MeuProgresso(componenteNavController)
//            }
//
//            composable("portfolio") {
//                Portfolio(componenteNavController)
//            }
//
//            composable("cadastroPortfolio") {
//                CadastroPortfolio(componenteNavController)
//            }
//        }
//    }


//        Text(text = "FIZEMOS LOGIN", style = letraPadrao)
//        Spacer(modifier = Modifier.weight(1f))
//        empresaData?.let { empresa ->
//            Text(text = "Razão Social: ${empresa.razaoSocial ?: ""}", style = letraPadrao)
//            Text(text = "CNPJ: ${empresa.cnpj ?: ""}", style = letraPadrao)
//            Text(text = "Telefone: ${empresa.telefone ?: ""}", style = letraPadrao)
//            Text(text = "Email: ${empresa.email ?: ""}", style = letraPadrao)
//            Text(text = "Setor: ${empresa.setor ?: ""}", style = letraPadrao)
//            Text(text = "Quantidade de Funcionários: ${empresa.qtdFuncionarios ?: ""}", style = letraPadrao)
//            Text(text = "Assinante Newsletter: ${empresa.assinanteNewsletter ?: ""}", style = letraPadrao)
//        }