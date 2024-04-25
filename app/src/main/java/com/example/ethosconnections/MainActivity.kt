package com.example.ethosconnections

import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.example.compose.AppTheme
import com.example.ethosconnections.models.Empresa
import com.example.ethosconnections.repositories.EmpresaRepository
import com.example.ethosconnections.repositories.ServicoRepository
import com.example.ethosconnections.service.EmpresaService
import com.example.ethosconnections.service.ServicoService
import com.example.ethosconnections.ui.screen.Cadastro
import com.example.ethosconnections.ui.screen.Home
import com.example.ethosconnections.ui.screen.Login
import com.example.ethosconnections.ui.screen.Plataforma
import com.example.ethosconnections.viewmodel.empresa.EmpresaViewModelFactory
import com.example.ethosconnections.viewmodel.empresa.EmpresaViewModel
import com.example.ethosconnections.viewmodel.servico.ServicoViewModel
import com.example.ethosconnections.viewmodel.servico.ServicoViewModelFactory

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        //ir colcoando todas as viewModel
        val empresaViewModel = ViewModelProvider(this, EmpresaViewModelFactory(this, EmpresaRepository(EmpresaService.create()))).get(EmpresaViewModel::class.java)
        val servicoViewModel = ViewModelProvider(this, ServicoViewModelFactory(ServicoRepository(ServicoService.create()))).get(ServicoViewModel::class.java)

        setContent {
            AppTheme {

                val navController = rememberNavController()

                //start destination deve estar no home, mudei pra testar rapido dentro
                NavHost(navController = navController, startDestination = "plataforma") {
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
                        Cadastro(navController)
                    }

                    composable(
                        route = "plataforma",
                    ) { navBackStackEntry ->
                        Plataforma(navController, empresaViewModel, servicoViewModel)
                    }

                }


            }
        }

    }
}


