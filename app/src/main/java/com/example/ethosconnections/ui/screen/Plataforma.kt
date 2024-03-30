package com.example.ethosconnections.ui.screen

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.ethosconnections.models.Empresa
import com.example.ethosconnections.ui.theme.letraPadrao

@Composable
fun Plataforma(navController: NavController, empresaData: Empresa?, modifier: Modifier = Modifier) {
    Column(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(text = "FIZEMOS LOGIN", style = letraPadrao)
        Spacer(modifier = Modifier.weight(1f))
        empresaData?.let { empresa ->
            Text(text = "Razão Social: ${empresa.razaoSocial ?: ""}", style = letraPadrao)
            Text(text = "CNPJ: ${empresa.cnpj ?: ""}", style = letraPadrao)
            Text(text = "Telefone: ${empresa.telefone ?: ""}", style = letraPadrao)
            Text(text = "Email: ${empresa.email ?: ""}", style = letraPadrao)
            Text(text = "Setor: ${empresa.setor ?: ""}", style = letraPadrao)
            Text(text = "Quantidade de Funcionários: ${empresa.qtdFuncionarios ?: ""}", style = letraPadrao)
            Text(text = "Assinante Newsletter: ${empresa.assinanteNewsletter ?: ""}", style = letraPadrao)
        }
        Button(
            onClick = { /* Do something */ },
            modifier = Modifier.padding(16.dp)
        ) {
            Text(text = "Click me!")
        }
    }
}
