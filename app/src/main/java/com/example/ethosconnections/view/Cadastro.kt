package com.example.ethosconnections.view

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.Image
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.Checkbox
import androidx.compose.material3.Divider
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.example.compose.AppTheme
import com.example.ethosconnections.R
import com.example.ethosconnections.ui.theme.letraButton
import com.example.ethosconnections.ui.theme.letraPadrao
import com.example.ethosconnections.ui.theme.letraProgresso
import com.example.ethosconnections.ui.theme.textoTop

@Composable
fun Cadastro(navController: NavController) {
    val etapaAtual = remember { mutableStateOf(1) }
    val totalEtapa = 3

    Image(
        painter = painterResource(id = R.drawable.background_login),
        contentDescription = null,
        modifier = Modifier.fillMaxSize(),
        contentScale = ContentScale.FillBounds
    )

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(35.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Image(
            painter = painterResource(id = R.mipmap.icone_logo_branco),
            contentDescription = null,
            modifier = Modifier.size(70.dp)
        )

        Spacer(modifier = Modifier.height(22.dp))

        Text(
            text = "Cadastro de Empresa",
            style = textoTop
        )

        Spacer(modifier = Modifier.height(16.dp))

        ProgressBar(etapaAtual.value, totalEtapa)


        Spacer(modifier = Modifier.height(16.dp))

        when (etapaAtual.value) {
            1 -> EtapaUm(etapaAtual)
            2 -> EtapaDois(etapaAtual)
            3 -> EtapaTres(etapaAtual)
        }

        Spacer(modifier = Modifier.height(32.dp))

        Row(
            horizontalArrangement = Arrangement.SpaceBetween,
            modifier = Modifier.fillMaxWidth()
        ) {
            OutlinedButton(
                onClick = { etapaAtual.value-- },
                shape = RoundedCornerShape(2.dp),
                enabled = etapaAtual.value > 1,
                border = BorderStroke(1.dp, if (etapaAtual.value > 1) Color(0xFF01A2C3) else Color.Gray),
                modifier = Modifier
                    .weight(1f)
                    .padding(end = 8.dp)
            ) {
                Text(
                    text = "Anterior",
                    style = letraPadrao,
                    color = if (etapaAtual.value > 1) Color(0xFF01A2C3) else Color.Gray,
                )
            }

            Spacer(modifier = Modifier.width(8.dp))

            Button(
                onClick = { if (etapaAtual.value < totalEtapa) etapaAtual.value++ },
                shape = RoundedCornerShape(2.dp),
                modifier = Modifier.weight(1f)
            ) {
                Text(
                    text = "Próximo",
                    style = letraButton
                )
            }
        }
    }
}

@Composable
fun EtapaUm(etapaAtual: MutableState<Int>) {
    val nomeEmpresa = remember {
        mutableStateOf(TextFieldValue())
    }

    val cnpj = remember {
        mutableStateOf(TextFieldValue())
    }

    TextField(
        value = nomeEmpresa.value,
        onValueChange = { nomeEmpresa.value = it },
        label = { Text("Nome da Empresa") },
        modifier = Modifier.fillMaxWidth(),
    )

    Spacer(modifier = Modifier.height(16.dp))

    TextField(
        value = cnpj.value,
        onValueChange = { cnpj.value = it },
        label = { Text("CNPJ") },
        modifier = Modifier.fillMaxWidth(),
    )

    Spacer(modifier = Modifier.height(16.dp))

    Row(modifier = Modifier.fillMaxWidth()) {
        TextField(
            value = cnpj.value,
            onValueChange = { cnpj.value = it },
            label = { Text("Área de Atuação") },
            modifier = Modifier
                .weight(1f)
                .padding(end = 8.dp)
        )

        TextField(
            value = cnpj.value,
            onValueChange = { cnpj.value = it },
            label = { Text("Nª Funcionários") },
            modifier = Modifier
                .weight(1f)
        )
    }


}

@Composable
fun EtapaDois(etapaAtual: MutableState<Int>) {
    val cep = remember {
        mutableStateOf("")
    }

    val telefone = remember {
        mutableStateOf("")
    }

    val email = remember {
        mutableStateOf("")
    }

    TextField(
        value = cep.value,
        onValueChange = { cep.value = it },
        label = { Text("CEP") },
        modifier = Modifier.fillMaxWidth(),
    )

    Spacer(modifier = Modifier.height(16.dp))

    TextField(
        value = telefone.value,
        onValueChange = { telefone.value = it },
        label = { Text("Telefone da Empresa") },
        modifier = Modifier.fillMaxWidth(),
    )

    Spacer(modifier = Modifier.height(16.dp))

    TextField(
        value = email.value,
        onValueChange = { email.value = it },
        label = { Text("Email Corporativo") },
        modifier = Modifier.fillMaxWidth(),
    )

}

@Composable
fun EtapaTres(etapaAtual: MutableState<Int>) {
    val senha = remember {
        mutableStateOf("")
    }

    val confirmacaoSenha = remember {
        mutableStateOf("")
    }

    TextField(
        value = senha.value,
        onValueChange = { senha.value = it },
        label = { Text("Senha") },
        modifier = Modifier.fillMaxWidth(),
    )

    Spacer(modifier = Modifier.height(16.dp))

    TextField(
        value = confirmacaoSenha.value,
        onValueChange = { confirmacaoSenha.value = it },
        label = { Text("Comfirmar Senha") },
        modifier = Modifier.fillMaxWidth(),
    )
    Spacer(modifier = Modifier.height(16.dp))

    var isChecked = remember { mutableStateOf(false) }

    Row(
        verticalAlignment = Alignment.CenterVertically,
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 16.dp)
    ) {
        // Composable Checkbox
        Checkbox(
            checked = isChecked.value,
            onCheckedChange = { newValue ->
                isChecked.value = newValue // Atualiza o estado
            },
            modifier = Modifier
                .border(BorderStroke(1.dp, if (isChecked.value) Color(0xFF01A2C3) else Color.Gray), RoundedCornerShape(4.dp)) // Cor da borda com base no estado
        )

        Spacer(modifier = Modifier.width(8.dp))

        // Texto clicável
        Text(
            text = "Concordo com os termos de uso e política de privacidade.",
            modifier = Modifier
                .clickable { isChecked.value = !isChecked.value }
                .padding(8.dp)
        )
    }

}

@Composable
fun ProgressBar(etapaAtual: Int, totalEtapa: Int) {
    val corAtiva = Color(0xFF01A2C3)
    val corInativa = Color.Transparent
    val corTextoAtivo = Color.White
    val corTextoInativo = Color(0xFF014D5C)

    Box(
        modifier = Modifier
            .fillMaxWidth()
            .padding(top = 16.dp, start = 16.dp, end = 16.dp),
        contentAlignment = Alignment.Center
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            for (i in 1..totalEtapa) {
                Column (
                    verticalArrangement = Arrangement.Center,
                    horizontalAlignment = Alignment.CenterHorizontally
                ){

                    CircleNumber(
                        number = i,
                        isSelected = i <= etapaAtual,
                        activeColor = corAtiva,
                        inactiveColor = corInativa,
                        activeTextColor = corTextoAtivo,
                        inactiveTextColor = corTextoInativo
                    )

                    Spacer(modifier = Modifier.height(16.dp))

                    Text(
                        text = when (i) {
                            1 -> "Dados Gerais"
                            2 -> "Endereço e Contato"
                            3 -> "Criar Senha"
                            else -> ""
                        },
                        fontFamily = FontFamily(Font(R.font.poppins_light)),
                        fontSize = 11.sp,
                        color = if (i <= etapaAtual) Color.White else Color.Gray
                    )
                    Spacer(modifier = Modifier.height(8.dp))
                }

            }
        }

    }
}

@Composable
fun CircleNumber(
    number: Int,
    isSelected: Boolean,
    activeColor: Color,
    inactiveColor: Color,
    activeTextColor: Color,
    inactiveTextColor: Color
) {
    Box(
        contentAlignment = Alignment.Center,
        modifier = Modifier.size(40.dp)
    ) {
        Canvas(modifier = Modifier.size(40.dp)) {
            if (isSelected) {
                drawCircle(
                    color = activeColor,
                    radius = size.minDimension / 2,
                )
            } else {
                drawCircle(
                    color = inactiveTextColor,
                    radius = size.minDimension / 2,
                    style = Stroke(width = 1.dp.toPx())
                )
            }
        }
        Text(
            text = number.toString(),
            color = if (isSelected) activeTextColor else inactiveTextColor,
            style = letraProgresso,
            modifier = Modifier.align(Alignment.Center)
        )
    }
}



@Preview(showBackground = true)
@Composable
fun CadastroPreview() {
    AppTheme {
        val navController = rememberNavController()
        Cadastro(navController)
    }
}
