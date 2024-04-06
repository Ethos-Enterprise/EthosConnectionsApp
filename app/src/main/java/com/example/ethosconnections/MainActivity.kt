package com.example.ethosconnections

import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.example.compose.AppTheme
import com.example.ethosconnections.models.Empresa
import com.example.ethosconnections.repositories.EmpresaRepository
import com.example.ethosconnections.service.EmpresaService
import com.example.ethosconnections.ui.screen.Cadastro
import com.example.ethosconnections.ui.screen.Home
import com.example.ethosconnections.ui.screen.Login
import com.example.ethosconnections.ui.screen.Plataforma
import com.example.ethosconnections.viewmodel.empresa.EmpresaViewModelFactory
import com.example.ethosconnections.viewmodel.empresa.EmpresaViewModel

class MainActivity : ComponentActivity() {
    private lateinit var empresaViewModel: EmpresaViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val empresaService = EmpresaService.create()
        val empresaRepository = EmpresaRepository(empresaService)
        val empresaViewModelFactory = EmpresaViewModelFactory(empresaRepository)
        empresaViewModel =  ViewModelProvider(this, empresaViewModelFactory).get(EmpresaViewModel::class.java)

        setContent {
            AppTheme {

                val navController = rememberNavController()

                //start destination deve estar no home, mudei pra testar rapido dentro
                NavHost(navController = navController, startDestination = "plataforma/{empresaId}") {
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
                        route = "plataforma/{empresaId}",
                        arguments = listOf(navArgument("empresaId") { type = NavType.StringType })
                    ) { navBackStackEntry ->
                        val empresaId = navBackStackEntry.arguments?.getString("empresaId")
                        val empresa: Empresa? = empresaViewModel.empresa.value
                        Plataforma(navController, empresa)
                    }

                }

                empresaViewModel.empresa.observe(this, Observer { empresa ->
                    empresa?.let {
                        navController.navigate("plataforma/${empresa.id}") {
                            launchSingleTop = true
                            popUpTo(navController.graph.startDestinationId) {
                                saveState = true
                            }
                        }
                        Log.i("MainActivity", "Login bem-sucedido: $empresa")
                    }
                })

            }
        }

    }
}


