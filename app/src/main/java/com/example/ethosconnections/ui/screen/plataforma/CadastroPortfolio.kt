package com.example.ethosconnections.ui.screen.plataforma

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Divider
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.example.ethosconnections.ui.screen.plataforma.components.BoxEthos
import com.example.ethosconnections.ui.theme.tituloConteudoAzul
import com.example.ethosconnections.ui.theme.tituloConteudoBranco

@Composable
fun CadastroPortfolio(navController: NavController) {

    val nomeEmpresa = remember {
        mutableStateOf(TextFieldValue())
    }

    val cnpj = remember {
        mutableStateOf(TextFieldValue())
    }

    val email = remember {
        mutableStateOf(TextFieldValue())
    }

    val telefone = remember {
        mutableStateOf(TextFieldValue())
    }

    var cep = remember {
        mutableStateOf(TextFieldValue())
    }

    val areaAtuacao = remember {
        mutableStateOf(TextFieldValue())
    }

    var quantidadeFuncionarios = remember {
        mutableStateOf(TextFieldValue())
    }

    Column {
        BoxEthos {
            Text(
                text = "Dados Gerais",
                style = tituloConteudoAzul,
                modifier = Modifier.fillMaxWidth()
            )
            Divider(
                color = Color.Gray,
                thickness = 1.dp,
                modifier = Modifier.padding(vertical = 8.dp)
            )

            Text(
                text = "Nome da Empresa",
                style = tituloConteudoBranco,
                modifier = Modifier.fillMaxWidth()
            )
            TextField(
                value = nomeEmpresa.value,
                onValueChange = { nomeEmpresa.value = it },
                modifier = Modifier
                    .fillMaxWidth()
                    .background(color = Color(0xFF1B1B1B)),
                shape = RoundedCornerShape(5.dp)
            )

            Text(
                text = "CNPJ",
                style = tituloConteudoBranco,
                modifier = Modifier.fillMaxWidth()
            )
            TextField(
                value = cnpj.value,
                onValueChange = { cnpj.value = it },
                modifier = Modifier.fillMaxWidth(),
                shape = RoundedCornerShape(4.dp)
            )

            Text(
                text = "Email",
                style = tituloConteudoBranco,
                modifier = Modifier.fillMaxWidth()
            )
            TextField(
                value = email.value,
                onValueChange = { email.value = it },
                modifier = Modifier.fillMaxWidth(),
                shape = RoundedCornerShape(4.dp)
            )

            Text(
                text = "Telefone",
                style = tituloConteudoBranco,
                modifier = Modifier.fillMaxWidth()
            )
            TextField(
                value = telefone.value,
                onValueChange = { telefone.value = it },
                modifier = Modifier.fillMaxWidth(),
                shape = RoundedCornerShape(4.dp)
            )

            Text(
                text = "CEP",
                style = tituloConteudoBranco,
                modifier = Modifier.fillMaxWidth()
            )
            TextField(
                value = cep.value,
                onValueChange = { cep.value = it },
                modifier = Modifier.fillMaxWidth(),
                shape = RoundedCornerShape(4.dp)
            )

            Text(
                text = "Área de Atuação",
                style = tituloConteudoBranco,
                modifier = Modifier.fillMaxWidth()
            )
            TextField(
                value = areaAtuacao.value,
                onValueChange = { areaAtuacao.value = it },
                modifier = Modifier.fillMaxWidth(),
                shape = RoundedCornerShape(4.dp)
            )

            Text(
                text = "Quantidade de Funcionários",
                style = tituloConteudoBranco,
                modifier = Modifier.fillMaxWidth()
            )
            TextField(
                value = quantidadeFuncionarios.value,
                onValueChange = { quantidadeFuncionarios.value = it },
                modifier = Modifier.fillMaxWidth(),
                shape = RoundedCornerShape(4.dp)
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
fun CadastroPortfolioPreview() {
    val navController = rememberNavController()
    CadastroPortfolio(navController)
}