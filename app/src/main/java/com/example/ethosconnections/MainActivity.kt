package com.example.ethosconnections

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.get
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.compose.AppTheme
import com.example.ethosconnections.repositories.EmpresaRepository
import com.example.ethosconnections.repositories.InteracaoRepository
import com.example.ethosconnections.repositories.MetaRepository
import com.example.ethosconnections.repositories.PortfolioRepository
import com.example.ethosconnections.repositories.ServicoRepository
import com.example.ethosconnections.service.EmpresaService
import com.example.ethosconnections.service.InteracaoService
import com.example.ethosconnections.service.MetaService
import com.example.ethosconnections.service.PortfolioService
import com.example.ethosconnections.service.ServicoService
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

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        //ir colocando todas as viewModel
        val empresaViewModel = ViewModelProvider(this, EmpresaViewModelFactory(this, EmpresaRepository(EmpresaService.create()))).get(EmpresaViewModel::class.java)
        val servicoViewModel = ViewModelProvider(this, ServicoViewModelFactory(ServicoRepository(ServicoService.create()))).get(ServicoViewModel::class.java)
        val portfolioViewModel = ViewModelProvider(this, PortfolioViewModelFactory(PortfolioRepository(PortfolioService.create()))).get(PortfolioViewModel::class.java)
        val metaViewModel = ViewModelProvider(this, MetaViewModelFactory(MetaRepository(MetaService.create()))).get(MetaViewModel::class.java)
        val avaliacaoViewModel = ViewModelProvider(this, InteracaoViewModelFactory(InteracaoRepository(InteracaoService.create()))).get(InteracaoViewModel::class.java)
        setContent {
            AppTheme {

                val navController = rememberNavController()

                //start destination deve estar no home, mudei pra testar rapido dentro
                NavHost(navController = navController, startDestination = "home") {
                    composable(
                        route = "home"
                    ) {
                        Home(navController)
                    }

                    composable(
                        route = "login"
                    ) {
                        Login(navController, viewModel = empresaViewModel)
                    }

                    composable(
                        route = "cadastro"
                    ) {
                        Cadastro(navController,empresaViewModel)
                    }

                    composable(
                        route = "plataforma",
                    ) {
                        Plataforma(navController, empresaViewModel, servicoViewModel, metaViewModel)
                    }

                }


            }
        }

    }
}


