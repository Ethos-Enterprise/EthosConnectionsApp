package com.example.ethosconnections.ui.screen.plataforma

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowForward
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.compose.cor_primaria
import com.example.ethosconnections.R
import com.example.ethosconnections.ui.screen.plataforma.components.BoxEthos
import com.example.ethosconnections.ui.theme.letraPadrao
import com.example.ethosconnections.ui.theme.tituloPagina
import com.example.ethosconnections.viewmodel.portfolio.PortfolioViewModel

@Composable
fun EditarMeuPortfolio(navController: NavController, portfolioViewModel: PortfolioViewModel) {
    Column{

        Text(
            stringResource(R.string.editar_meu_portfolio_titulo),
            style = tituloPagina,
        )

        BoxEthos {

        ItemSelecao(text = "Editar Dados Gerais") {
            navController.navigate("editarEmpresa")
        }
        ItemSelecao(text = "Editar Dados Complementares") {
            navController.navigate("editarPortfolio")
        }
        ItemSelecao(text = "Editar Serviços") {
            navController.navigate("editarServicos")
        }
        }

    }
}

@Composable
fun ItemSelecao(text: String, onClick: () -> Unit) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 12.dp)
            .clickable(onClick = onClick),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Text(
            text = text,
            style = letraPadrao,
            modifier = Modifier.weight(1f)
        )
        Icon(
            imageVector = Icons.Default.ArrowForward,
            contentDescription = null,
            tint = cor_primaria
        )
    }
}