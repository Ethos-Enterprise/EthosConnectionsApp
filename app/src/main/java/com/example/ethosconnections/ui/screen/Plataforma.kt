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
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.compose.AppTheme
import com.example.ethosconnections.R
import com.example.ethosconnections.models.Empresa
import com.example.ethosconnections.repositories.EmpresaRepository
import com.example.ethosconnections.service.EmpresaService
import com.example.ethosconnections.ui.screen.plataforma.AvaliacaoServico
import com.example.ethosconnections.ui.screen.plataforma.SolucoesESG
import com.example.ethosconnections.viewmodel.empresa.EmpresaViewModel


@Composable
fun Plataforma(navController: NavController, empresaData: Empresa?, modifier: Modifier = Modifier) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(start = 15.dp, top = 15.dp, end = 15.dp, bottom = 10.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {

        Row (
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ){

            Image(
                modifier = Modifier
                    .height(30.dp),
                painter = painterResource(id = R.mipmap.menu_lateral),
                contentDescription = "Ícone do menu")

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
                contentDescription = "Ícone do menu")

        }
        Spacer(modifier = Modifier.height(20.dp))

        val componenteNavController = rememberNavController()

        Box(modifier = Modifier
            .fillMaxSize()
            .padding(0.dp),
            contentAlignment = Alignment.TopStart ) {


        NavHost(navController = componenteNavController, startDestination = "solucoesEsg") {
            composable("solucoesEsg") {
                SolucoesESG(componenteNavController)
            }

            composable("avaliacaoServico") {
                AvaliacaoServico(componenteNavController)
            }



        }
        }

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
}

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