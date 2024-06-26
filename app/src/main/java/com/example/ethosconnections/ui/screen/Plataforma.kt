    package com.example.ethosconnections.ui.screen

    import MeuPerfil
    import androidx.compose.foundation.Image
    import androidx.compose.foundation.layout.Arrangement
    import androidx.compose.foundation.layout.Box
    import androidx.compose.foundation.layout.Column
    import androidx.compose.foundation.layout.Row
    import androidx.compose.foundation.layout.Spacer
    import androidx.compose.foundation.layout.fillMaxHeight
    import androidx.compose.foundation.layout.fillMaxSize
    import androidx.compose.foundation.layout.fillMaxWidth
    import androidx.compose.foundation.layout.height
    import androidx.compose.foundation.layout.padding
    import androidx.compose.foundation.layout.width
    import androidx.compose.foundation.rememberScrollState
    import androidx.compose.foundation.verticalScroll
    import androidx.compose.material.icons.Icons
    import androidx.compose.material.icons.filled.Menu
    import androidx.compose.material.icons.outlined.Notifications
    import androidx.compose.material3.DrawerValue
    import androidx.compose.material3.Icon
    import androidx.compose.material3.IconButton
    import androidx.compose.material3.ModalDrawerSheet
    import androidx.compose.material3.ModalNavigationDrawer
    import androidx.compose.material3.NavigationDrawerItem
    import androidx.compose.material3.NavigationDrawerItemDefaults
    import androidx.compose.material3.Text
    import androidx.compose.material3.rememberDrawerState
    import androidx.compose.runtime.Composable
    import androidx.compose.runtime.collectAsState
    import androidx.compose.runtime.mutableStateOf
    import androidx.compose.runtime.setValue
    import androidx.compose.runtime.getValue
    import androidx.compose.runtime.rememberCoroutineScope
    import androidx.compose.runtime.saveable.rememberSaveable
    import androidx.compose.ui.Alignment
    import androidx.compose.ui.Modifier
    import androidx.compose.ui.graphics.Color
    import androidx.compose.ui.res.painterResource
    import androidx.compose.ui.res.stringResource
    import androidx.compose.ui.tooling.preview.Preview
    import androidx.compose.ui.unit.dp
    import androidx.lifecycle.viewmodel.compose.viewModel
    import androidx.navigation.NavController
    import androidx.navigation.compose.NavHost
    import androidx.navigation.compose.composable
    import androidx.navigation.compose.rememberNavController
    import com.example.compose.AppTheme
    import com.example.compose.cor_primaria
    import com.example.compose.preto_azulado
    import com.example.ethosconnections.R
    import com.example.ethosconnections.datastore.EmpresaDataStore
    import com.example.ethosconnections.datastore.PortfolioDataStore
    import com.example.ethosconnections.repositories.EmpresaRepository
    import com.example.ethosconnections.service.EmpresaService
    import com.example.ethosconnections.ui.screen.plataforma.AvaliacaoServico
    import com.example.ethosconnections.ui.screen.plataforma.Contrato
    import com.example.ethosconnections.ui.screen.plataforma.EditarMeuPortfolio
    import com.example.ethosconnections.ui.screen.plataforma.Formulario
    import com.example.ethosconnections.ui.screen.plataforma.Meta
    import com.example.ethosconnections.ui.screen.plataforma.MeuPlano
    import com.example.ethosconnections.ui.screen.plataforma.MeuPortfolio
    import com.example.ethosconnections.ui.screen.plataforma.MeuProgresso
    import com.example.ethosconnections.ui.screen.plataforma.MinhasInteracoes
    import com.example.ethosconnections.ui.screen.plataforma.MinhasNegociacoes
    import com.example.ethosconnections.ui.screen.plataforma.Pagamento
    import com.example.ethosconnections.ui.screen.plataforma.Portfolio
    import com.example.ethosconnections.ui.screen.plataforma.Questionario
    import com.example.ethosconnections.ui.screen.plataforma.SolucoesESG
    import com.example.ethosconnections.ui.screen.plataforma.cadastrar.CadastrarDadosComplementares
    import com.example.ethosconnections.ui.screen.plataforma.cadastrar.CadastrarServico
    import com.example.ethosconnections.ui.screen.plataforma.editar.EditarEmpresa
    import com.example.ethosconnections.ui.screen.plataforma.editar.EditarPortfolio
    import com.example.ethosconnections.ui.screen.plataforma.editar.EditarServico
    import com.example.ethosconnections.ui.theme.letraMenu
    import com.example.ethosconnections.ui.theme.tituloMenu
    import com.example.ethosconnections.viewmodel.empresa.EmpresaViewModel
    import com.example.ethosconnections.viewmodel.interacao.InteracaoViewModel
    import com.example.ethosconnections.viewmodel.meta.MetaViewModel
    import com.example.ethosconnections.viewmodel.portfolio.PortfolioViewModel
    import com.example.ethosconnections.viewmodel.prestadora.PrestadoraViewModel
    import com.example.ethosconnections.viewmodel.progresso.ProgressoViewModel
    import com.example.ethosconnections.viewmodel.servico.ServicoViewModel
    import com.example.ethosconnections.viewmodel.token.TokenViewModel
    import kotlinx.coroutines.launch
    import java.util.UUID

    data class NavigationItem(
        val titulo: String,
        val quantidade: Int? = null,
        val rota: String
    )

    @Composable
    fun Plataforma(
        navController: NavController,
        empresaViewModel: EmpresaViewModel,
        servicoViewModel: ServicoViewModel,
        metaViewModel: MetaViewModel,
        interacaoViewModel: InteracaoViewModel,
        portfolioViewModel: PortfolioViewModel,
        prestadoraViewModel: PrestadoraViewModel,
        tokenViewModel: TokenViewModel,
        empresaDataStore: EmpresaDataStore,
        portfolioDataStore: PortfolioDataStore
    ) {

        val progressoViewModel: ProgressoViewModel = viewModel()

        val items =
            when (empresaDataStore.getPlanoFlow().collectAsState(initial = null).value ?: "Free") {
                "Free" -> listOf(
                    NavigationItem(titulo = stringResource(R.string.plataforma_menu_solucoes), rota = "solucoesEsg"),
                    NavigationItem(titulo = stringResource(R.string.plataforma_menu_meu_perfil), rota = "meuPerfil"),
                    NavigationItem(titulo = stringResource(R.string.plataforma_menu_minhas_interacoes), rota = "minhasInteracoes"),
                    NavigationItem(titulo = stringResource(R.string.plataforma_menu_meu_plano), rota = "meuPlano"),
                )

                "Analytics" -> listOf(
                    NavigationItem(titulo = stringResource(R.string.plataforma_menu_solucoes), rota = "solucoesEsg"),
                    NavigationItem(titulo = stringResource(R.string.plataforma_menu_meu_progresso), rota = "meuProgresso"),
                    NavigationItem(titulo = stringResource(R.string.plataforma_menu_meu_perfil), rota = "meuPerfil"),
                    NavigationItem(titulo = stringResource(R.string.plataforma_menu_minhas_interacoes), rota = "minhasInteracoes"),
                    NavigationItem(titulo = stringResource(R.string.plataforma_menu_meu_plano), rota = "meuPlano"),
                )

                else -> listOf(
                    NavigationItem(titulo = stringResource(R.string.plataforma_menu_solucoes), rota = "solucoesEsg"),
                    NavigationItem(titulo = stringResource(R.string.plataforma_menu_minhas_negociacoes), rota = "minhasNegociacoes"),
                    NavigationItem(titulo = stringResource(R.string.plataforma_menu_meu_portfolio), rota = "meuPortfolio"),
                    NavigationItem(titulo = stringResource(R.string.plataforma_menu_minhas_interacoes), rota = "minhasInteracoes"),
                    NavigationItem(titulo = stringResource(R.string.plataforma_menu_meu_plano), rota = "meuPlano"),
                )
            }
        val componenteNavController = rememberNavController()

        val drawerState = rememberDrawerState(initialValue = DrawerValue.Closed)
        val scope = rememberCoroutineScope()
        var itemSelecionado by rememberSaveable {
            mutableStateOf(0)
        }
        ModalNavigationDrawer(
            drawerContent = {
                ModalDrawerSheet(
                    drawerContainerColor = preto_azulado,
                    modifier = Modifier
                        .fillMaxWidth(0.6f)
                ) {
                    Spacer(modifier = Modifier.height(18.dp))

                    Image(
                        painter = painterResource(id = R.mipmap.deloitte_logo),
                        contentDescription = "Logo Deloitte",
                        modifier = Modifier
                            .fillMaxHeight(0.23f)
                            .fillMaxWidth(1f)
                    )

                    Spacer(modifier = Modifier.height(14.dp))
                    Text(
                        text = "Olá ${
                            empresaDataStore.getRazaoSocialEmpresaFlow().collectAsState(initial = null).value ?: stringResource(R.string.txt_empresa_n_a)
                        }",
                        style = tituloMenu,
                        modifier = Modifier.padding(start = 25.dp, top = 10.dp)
                    )

                    Spacer(modifier = Modifier.height(8.dp))

                    items.forEachIndexed { index, item ->
                        NavigationDrawerItem(
                            label = {
                                Text(
                                    text = item.titulo,
                                    style = letraMenu,
                                    modifier = Modifier.padding(start = 17.dp)
                                )
                            },
                            selected = index == itemSelecionado,
                            onClick = {
                                componenteNavController.navigate(item.rota)
                                itemSelecionado = index
                                scope.launch {
                                    drawerState.close()
                                }
                            },
                            colors = NavigationDrawerItemDefaults.colors(
                                selectedContainerColor = Color(0x6F121212),
                                unselectedContainerColor = preto_azulado,
                                selectedTextColor = cor_primaria,
                                unselectedTextColor = Color.White
                            ),
                            modifier = Modifier
                                .fillMaxWidth(1f)
                                .padding(start = 0.dp, top = 0.dp)
                        )
                    }

                    NavigationDrawerItem(
                        label = {
                            Text(
                                text = stringResource(R.string.plataforma_menu_sair),
                                style = letraMenu,
                                modifier = Modifier.padding(start = 17.dp)
                            )
                        },
                        selected = false,
                        onClick = {
                            scope.launch {
                                empresaDataStore.clearDataStore()
                                drawerState.close()
                                navController.navigate("home")
                            }
                        },
                        colors = NavigationDrawerItemDefaults.colors(
                            selectedContainerColor = Color(0x6F121212),
                            unselectedContainerColor = preto_azulado,
                            selectedTextColor = cor_primaria,
                            unselectedTextColor = Color.White
                        ),
                        modifier = Modifier
                            .fillMaxWidth(1f)
                            .padding(start = 0.dp, top = 0.dp)
                    )
                }
            },
            drawerState = drawerState
        ) {
            Column(
                modifier = Modifier
                    .fillMaxSize(),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {


                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(start = 5.dp, top = 15.dp, end = 5.dp, bottom = 0.dp),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically
                ) {

                    IconButton(
                        onClick = {
                            scope.launch {
                                drawerState.open()
                            }

                        }) {
                        Icon(
                            modifier = Modifier.fillMaxSize(),
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

                    IconButton(
                        onClick = {
                        }) {
                        Icon(
                            modifier = Modifier.fillMaxSize(),
                            imageVector = Icons.Outlined.Notifications,
                            tint = cor_primaria,
                            contentDescription = "Menu"
                        )
                    }

                }

                Box(
                    modifier = Modifier
                        .padding(start = 15.dp, top = 15.dp, end = 15.dp, bottom = 10.dp)
                        .fillMaxSize()
                        .verticalScroll(rememberScrollState())
                        .padding(0.dp),
                    contentAlignment = Alignment.TopStart
                ) {

                    NavHost(
                        navController = componenteNavController,
                        startDestination = "solucoesEsg"
                    ) {
                        composable("solucoesEsg") {
                            SolucoesESG(componenteNavController, servicoViewModel, tokenViewModel, empresaDataStore)
                        }

                        composable("avaliacaoServico/{id}/{nomeServico}/{nomeEmpresa}/{categoria}/{preco}/{descricao}/{fkPrestadoraServico}/{idEmpresa}") { backStackEntry ->
                            val id = UUID.fromString(backStackEntry.arguments?.getString("id") ?: "")
                            val nomeServico = backStackEntry.arguments?.getString("nomeServico") ?: ""
                            val nomeEmpresa = backStackEntry.arguments?.getString("nomeEmpresa") ?: ""
                            val categoria = backStackEntry.arguments?.getString("categoria") ?: ""
                            val preco = backStackEntry.arguments?.getString("preco")?.toDouble() ?: 0.0
                            val descricao = backStackEntry.arguments?.getString("descricao") ?: ""
                            val fkPrestadoraServico = UUID.fromString(backStackEntry.arguments?.getString("fkPrestadoraServico") ?: "")
                            val idEmpresa = UUID.fromString(backStackEntry.arguments?.getString("idEmpresa") ?: "")
                            AvaliacaoServico(
                                componenteNavController,
                                id,
                                nomeServico,
                                nomeEmpresa,
                                categoria,
                                preco,
                                descricao,
                                fkPrestadoraServico,
                                idEmpresa,
                                empresaDataStore,
                                interacaoViewModel
                            )
                        }

                        composable("meuProgresso") {
                            MeuProgresso(componenteNavController, progressoViewModel, metaViewModel,empresaDataStore)
                        }

                        composable("portfolio/{fkPrestadora}/{idEmpresa}") { backStackEntry ->
                            val fkPrestadora = UUID.fromString(backStackEntry.arguments?.getString("fkPrestadora") ?: "")
                            val idEmpresa = UUID.fromString(backStackEntry.arguments?.getString("idEmpresa") ?: "")

                            Portfolio(componenteNavController, servicoViewModel, portfolioViewModel,empresaViewModel, empresaDataStore, fkPrestadora, idEmpresa)
                        }

                        composable("editarEmpresa") {
                            EditarEmpresa(componenteNavController, empresaDataStore)
                        }

                        composable("editarPortfolio") {
                            EditarPortfolio(componenteNavController ,empresaViewModel ,empresaDataStore)
                        }

                        composable("editarServicos") {
                            EditarServico(componenteNavController ,servicoViewModel ,empresaDataStore)
                        }

                        composable("cadastrarServico") {
                            CadastrarServico()
                        }

                        composable("cadastrarDadosComplementares") {
                            CadastrarDadosComplementares()
                        }


                        composable("editarMeuPortfolio") {
                            EditarMeuPortfolio(componenteNavController , portfolioViewModel)
                        }

                        composable("contrato/{nomePlano}/{preco}") { backStackEntry ->
                            val nomePlano = backStackEntry.arguments?.getString("nomePlano") ?: ""
                            val preco = backStackEntry.arguments?.getString("preco")?.toDouble() ?: 0.0
                            Contrato(componenteNavController, nomePlano, preco, empresaDataStore)
                        }
                        composable("formulario/{categoria}") { backStackEntry ->
                            val categoria =
                                backStackEntry.arguments?.getString("categoria") ?: "AMBIENTAL"
                            Formulario(componenteNavController, categoria, empresaDataStore)
                        }
                        composable("pagamento/{plano}/{preco}") { backStackEntry ->
                            Pagamento(
                                componenteNavController,
                                plano = backStackEntry.arguments?.getString("plano") ?: "",
                                preco = backStackEntry.arguments?.getDouble("preco") ?: 0.0,
                                empresaDataStore = empresaDataStore,
                                prestadoraViewModel
                            )
                        }
                        composable("meuPerfil") {
                            MeuPerfil(componenteNavController, empresaDataStore)
                        }
                        composable("meta") {
                            Meta(componenteNavController,metaViewModel, empresaDataStore)
                        }
                        composable("meuPlano") {
                            MeuPlano(componenteNavController, empresaDataStore)
                        }
                        composable("meuPortfolio") {
                            MeuPortfolio(componenteNavController,servicoViewModel, portfolioViewModel, empresaDataStore)
                        }
                        composable("questionario/{categoria}") { backStackEntry ->
                            val categoria =
                                backStackEntry.arguments?.getString("categoria") ?: "Ambiental"
                            Questionario(componenteNavController, progressoViewModel, categoria,empresaDataStore)
                        }
                        composable("minhasInteracoes") {
                            MinhasInteracoes(componenteNavController,empresaDataStore, interacaoViewModel)
                        }
                        composable("minhasNegociacoes") {
                            MinhasNegociacoes(componenteNavController,empresaDataStore, interacaoViewModel)
                        }
                    }
                }

            }
        }
    }

@Composable
@Preview(showBackground = true)
fun PlataformaPreview() {
    AppTheme {
        val navController = rememberNavController()

        val empresaService = EmpresaService.create()
        val empresaRepository = EmpresaRepository(empresaService)

//        Plataforma(navController)
    }
}
