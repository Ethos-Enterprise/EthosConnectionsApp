package com.example.ethosconnections.ui.screen.plataforma

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Button
import androidx.compose.material3.Divider
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.example.ethosconnections.ui.screen.plataforma.components.BoxEthos
import com.example.ethosconnections.ui.theme.letraButton
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

    BoxEthos {
         Column {
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
                modifier = Modifier.fillMaxWidth().padding(top = 8.dp)
            )
            TextField(
                value = nomeEmpresa.value,
                onValueChange = { nomeEmpresa.value = it },
                modifier = Modifier.fillMaxWidth()
            )

            Text(
                text = "CNPJ",
                style = tituloConteudoBranco,
                modifier = Modifier.fillMaxWidth().padding(top = 8.dp)
            )
            TextField(
                value = cnpj.value,
                onValueChange = { cnpj.value = it },
                modifier = Modifier.fillMaxWidth(),
                shape = RoundedCornerShape(4.dp),
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number)
            )

            Text(
                text = "Email",
                style = tituloConteudoBranco,
                modifier = Modifier.fillMaxWidth().padding(top = 8.dp)
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
                modifier = Modifier.fillMaxWidth().padding(top = 8.dp)
            )
            TextField(
                value = telefone.value,
                onValueChange = { telefone.value = it },
                modifier = Modifier.fillMaxWidth(),
                shape = RoundedCornerShape(4.dp),
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number)
            )

            Text(
                text = "CEP",
                style = tituloConteudoBranco,
                modifier = Modifier.fillMaxWidth().padding(top = 8.dp)
            )
            TextField(
                value = cep.value,
                onValueChange = { cep.value = it },
                modifier = Modifier.fillMaxWidth(),
                shape = RoundedCornerShape(4.dp),
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number)
            )

            Text(
                text = "Área de Atuação",
                style = tituloConteudoBranco,
                modifier = Modifier.fillMaxWidth().padding(top = 8.dp)
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
                modifier = Modifier.fillMaxWidth().padding(top = 8.dp)
            )
            TextField(
                value = quantidadeFuncionarios.value,
                onValueChange = { quantidadeFuncionarios.value = it },
                modifier = Modifier.fillMaxWidth(),
                shape = RoundedCornerShape(4.dp)
            )
        }
        buttons(navController)
    }
}

@Composable
fun buttons(navController: NavController) {
    val color = Color(0xFF1B1F23)
    
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .padding(start = 0.dp, top = 50.dp, end = 0.dp, bottom = 0.dp)
            .background(color)

    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Button(
                onClick = { /*TODO*/ },
                modifier = Modifier
                    .weight(1f)
                    .padding(horizontal = 10.dp)
                    .background(color),
                shape = RoundedCornerShape(5.dp)
            ) {
                Text(
                    text = "Salvar",
                    style = letraButton
                )
            }

            OutlinedButton(
                onClick = { navController.navigate("portfolio") },
                modifier = Modifier
                    .weight(1f)
                    .padding(horizontal = 10.dp),
                shape = RoundedCornerShape(5.dp),
                border = BorderStroke(2.dp, Color(0xFF01A2C3)),
            ) {
                Text(
                    text = "Cancelar",
                    style = letraButton,
                    color = Color(0xFF01A2C3)
                )
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun CadastroPortfolioPreview() {
    val navController = rememberNavController()
    CadastroPortfolio(navController)
}