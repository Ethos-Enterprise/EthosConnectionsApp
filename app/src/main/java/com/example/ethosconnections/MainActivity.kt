package com.example.ethosconnections

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.compose.AppTheme
import com.example.ethosconnections.screen.Cadastro
import com.example.ethosconnections.screen.Home
import com.example.ethosconnections.screen.Login

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            AppTheme {

                val navController = rememberNavController()

                NavHost(navController = navController, startDestination = "home") {
                    composable(
                        route = "home"
                    ) {
                        Home(navController)
                    }

                    composable(
                        route = "login"
                    ) {
                        Login(navController)
                    }

                    composable(
                        route = "Cadastro"
                    ) {
                        Cadastro(navController)
                    }

                }
            }
        }

    }
}

