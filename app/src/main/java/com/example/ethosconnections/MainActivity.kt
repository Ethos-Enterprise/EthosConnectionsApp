package com.example.ethosconnections

import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.get
import androidx.lifecycle.lifecycleScope
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.compose.AppTheme
import com.example.ethosconnections.datastore.EmpresaDataStore
import com.example.ethosconnections.repositories.EmpresaRepository
import com.example.ethosconnections.repositories.InteracaoRepository
import com.example.ethosconnections.repositories.MetaRepository
import com.example.ethosconnections.repositories.PortfolioRepository
import com.example.ethosconnections.repositories.ServicoRepository
import com.example.ethosconnections.repositories.TokenRepository
import com.example.ethosconnections.service.EmpresaService
import com.example.ethosconnections.service.InteracaoService
import com.example.ethosconnections.service.MetaService
import com.example.ethosconnections.service.PortfolioService
import com.example.ethosconnections.service.ServicoService
import com.example.ethosconnections.service.TokenService
import com.example.ethosconnections.ui.screen.Cadastro
import com.example.ethosconnections.ui.screen.Home
import com.example.ethosconnections.ui.screen.Login
import com.example.ethosconnections.ui.screen.Plataforma
import com.example.ethosconnections.viewmodel.empresa.EmpresaViewModelFactory
import com.example.ethosconnections.viewmodel.empresa.EmpresaViewModel
import com.example.ethosconnections.viewmodel.interacao.InteracaoViewModel
import com.example.ethosconnections.viewmodel.interacao.InteracaoViewModelFactory
import com.example.ethosconnections.viewmodel.meta.MetaViewModel
import com.example.ethosconnections.viewmodel.meta.MetaViewModelFactory
import com.example.ethosconnections.viewmodel.portfolio.PortfolioViewModel
import com.example.ethosconnections.viewmodel.portfolio.PortfolioViewModelFactory
import com.example.ethosconnections.viewmodel.servico.ServicoViewModel
import com.example.ethosconnections.viewmodel.servico.ServicoViewModelFactory
import com.example.ethosconnections.viewmodel.token.TokenViewModel
import com.example.ethosconnections.viewmodel.token.TokenViewModelFactory
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.firstOrNull
import kotlinx.coroutines.launch

class MainActivity : ComponentActivity() {

    private lateinit var empresaDataStore: EmpresaDataStore

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        empresaDataStore = EmpresaDataStore(this)

        val empresaViewModel = ViewModelProvider(
            this,
            EmpresaViewModelFactory(this, EmpresaRepository(EmpresaService.create()), empresaDataStore)
        ).get(EmpresaViewModel::class.java)
        val servicoViewModel = ViewModelProvider(
            this,
            ServicoViewModelFactory(this, ServicoRepository(ServicoService.create()))
        ).get(ServicoViewModel::class.java)
        val portfolioViewModel = ViewModelProvider(
            this,
            PortfolioViewModelFactory(this, PortfolioRepository(PortfolioService.create()))
        ).get(PortfolioViewModel::class.java)
        val metaViewModel = ViewModelProvider(
            this,
            MetaViewModelFactory(this, MetaRepository(MetaService.create()))
        ).get(MetaViewModel::class.java)
        val interacaoViewModel = ViewModelProvider(
            this,
            InteracaoViewModelFactory(this, InteracaoRepository(InteracaoService.create()))
        ).get(InteracaoViewModel::class.java)
        val tokenViewModel = ViewModelProvider(
            this,
            TokenViewModelFactory(this, TokenRepository(TokenService.create()), empresaDataStore)
        ).get(TokenViewModel::class.java)

        lifecycleScope.launch {
            val empresa = empresaDataStore.getEmpresaFlow().first()

            tokenViewModel.loginAutenticacao("admin@ethos", "123") { success ->
                val startDestination = if (empresa != null && success) {
                    "plataforma"
                } else {
                    "home"
                }

                setContent {
                    AppTheme {
                        val navController = rememberNavController()

                        NavHost(
                            navController = navController,
                            startDestination = startDestination
                        ) {
                            composable(route = "home") {
                                Home(navController)
                            }
                            composable(route = "login") {
                                Login(navController, viewModel = empresaViewModel)
                            }
                            composable(route = "cadastro") {
                                Cadastro(navController, empresaViewModel)
                            }
                            composable(route = "plataforma") {
                                Plataforma(
                                    navController,
                                    empresaViewModel,
                                    servicoViewModel,
                                    metaViewModel,
                                    interacaoViewModel,
                                    portfolioViewModel,
                                    empresaDataStore
                                )
                            }
                        }
                    }
                }
            }
        }
    }
}