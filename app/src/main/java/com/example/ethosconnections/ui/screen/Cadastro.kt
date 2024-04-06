package com.example.ethosconnections.ui.screen

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Checkbox
import androidx.compose.material3.Divider
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.scale
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
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


    val nomeEmpresa = remember {
        mutableStateOf(TextFieldValue())
    }

    val cnpj = remember {
        mutableStateOf(TextFieldValue())
    }

    val cep = remember {
        mutableStateOf(TextFieldValue())
    }

    val telefone = remember {
        mutableStateOf(TextFieldValue())
    }

    val email = remember {
        mutableStateOf(TextFieldValue())
    }

    val senha = remember {
        mutableStateOf(TextFieldValue())
    }

    val confirmacaoSenha = remember {
        mutableStateOf(TextFieldValue())
    }

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
            1 -> EtapaUm(nomeEmpresa, cnpj)
            2 -> EtapaDois(cep, telefone, email)
            3 -> EtapaTres(senha, confirmacaoSenha)
        }

        Spacer(modifier = Modifier.height(32.dp))

        Row(
            horizontalArrangement = Arrangement.SpaceBetween,
            modifier = Modifier.fillMaxWidth()
        ) {
            OutlinedButton(
                onClick = { etapaAtual.value-- },
                shape = RoundedCornerShape(5.dp),
                enabled = etapaAtual.value > 1,
                border = BorderStroke(
                    1.dp,
                    if (etapaAtual.value > 1) Color(0xFF01A2C3) else Color.Gray
                ),
                modifier = Modifier
                    .weight(1f)
                    .padding(end = 8.dp)
            ) {
                Text(
                    text = "Anterior",
                    style = letraButton,
                    color = if (etapaAtual.value > 1) Color(0xFF01A2C3) else Color.Gray,
                )
            }

            Button(
                onClick = { if (etapaAtual.value < totalEtapa) etapaAtual.value++ },
                shape = RoundedCornerShape(5.dp),
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
fun EtapaUm(nomeEmpresa: MutableState<TextFieldValue>, cnpj: MutableState<TextFieldValue>) {

    val areaAtuacao = remember {
        mutableStateOf(TextFieldValue())
    }

    val nFuncionarios = remember {
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
        keyboardOptions =
        KeyboardOptions(keyboardType = KeyboardType.Number)
    )

    Spacer(modifier = Modifier.height(16.dp))

    Row(
        modifier = Modifier.fillMaxWidth()
    ) {

        var expanded = remember { mutableStateOf(false) }
        val selectedOption = remember { mutableStateOf("") }
        val options = listOf(
            "Tecnologia da Informação",
            "Saúde e Medicina",
            "Educação",
            "Financeira e Bancária",
            "Agricultura",
            "Energia e Sustentabilidade",
            "Varejo",
            "Construção e Imóveis",
            "Alimentos e Bebidas",
            "Automobilística",
            "Entretenimento e Mídia",
            "Turismo e Hospitalidade",
            "Manufatura",
            "Telecomunicações",
            "Serviços de Consultoria",
            "Transporte e Logística",
            "Moda e Vestuário",
            "Outros")

        var expanded2 = remember { mutableStateOf(false) }
        val selectedOption2 = remember { mutableStateOf("") }
        val options2 = listOf(
            "Até 9 funcionários",
            "10 a 20 funcionários",
            "21 a 50 funcionários",
            "51 a 100 funcionários",
            "101 a 200 funcionários",
            "201 a 500 funcionários",
            "501 a 1000 funcionários",
            "1001 ou mais funcionários"
        )

        Box(
            modifier = Modifier
                .weight(1f)
                .fillMaxWidth()
        ) {
            Button(
                onClick = { expanded.value = true },
                shape = RoundedCornerShape(4.dp),
                border = BorderStroke(1.dp, Color(0xFF98A1A2)),
                colors = ButtonDefaults.buttonColors(Color(0xff3F484A)),
                modifier = Modifier
                    .fillMaxWidth(),
                contentPadding = PaddingValues(
                    start = 9.dp,
                    top = 15.dp,
                    end = 16.dp,
                    bottom = 15.dp

                )
            ) {
                Text(
                    modifier = Modifier
                        .fillMaxWidth(),
                    text = if (selectedOption.value.isEmpty()) "Área de Atuação" else selectedOption.value,
                    color = Color.White,
                    style = letraPadrao
                )

            }
            DropdownMenu(
                expanded = expanded.value,
                onDismissRequest = { expanded.value = false },
                modifier = Modifier
                    .fillMaxWidth(0.4f)
                    .height(160.dp)
            ) {
                options.forEach { option ->
                    DropdownMenuItem(

                        text = { Text(text = option) },
                        onClick = {
                            selectedOption.value = option
                            expanded.value = false
                        },
                    )
                    Divider()
                }
            }
        }


        Spacer(modifier = Modifier.width(8.dp))

        Box(
            modifier = Modifier
                .weight(1f)
        ) {
            Button(
                onClick = { expanded2.value = true },
                shape = RoundedCornerShape(4.dp),
                border = BorderStroke(1.dp, Color(0xFF98A1A2)),
                colors = ButtonDefaults.buttonColors(Color(0xff3F484A)),
                modifier = Modifier
                    .fillMaxWidth(),
                contentPadding = PaddingValues(
                    start = 9.dp,
                    top = 15.dp,
                    end = 16.dp,
                    bottom = 15.dp
                )
            ) {
                Text(
                    style = letraPadrao,
                    text = if (selectedOption2.value.isEmpty()) "Nº Funcionários" else selectedOption2.value,
                    modifier = Modifier
                        .fillMaxWidth(),
                    color = Color.White,
                )

            }
            DropdownMenu(
                expanded = expanded2.value,
                onDismissRequest = { expanded2.value = false },
                modifier = Modifier
                    .fillMaxWidth(0.4f)
                    .height(160.dp)
            ) {
                options2.forEach { option ->
                    DropdownMenuItem(
                        text = { Text(text = option) },
                        onClick = {
                            selectedOption2.value = option
                            expanded2.value = false
                        })
                    Divider()
                }
            }
        }


    }


}

@Composable
fun EtapaDois(
    cep:MutableState<TextFieldValue>,
    telefone:MutableState<TextFieldValue>,
    email:MutableState<TextFieldValue>
) {

    TextField(
        value = cep.value,
        onValueChange = { cep.value = it },
        label = { Text("CEP") },
        modifier = Modifier.fillMaxWidth(),
        keyboardOptions =
        KeyboardOptions(keyboardType = KeyboardType.Number),
    )

    Spacer(modifier = Modifier.height(16.dp))

    TextField(
        value = telefone.value,
        onValueChange = { telefone.value = it },
        label = { Text("Telefone da Empresa") },
        modifier = Modifier.fillMaxWidth(),
        keyboardOptions =
        KeyboardOptions(keyboardType = KeyboardType.Number),
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
fun EtapaTres(
    senha:MutableState<TextFieldValue>,
    confirmacaoSenha:MutableState<TextFieldValue>,
) {


    TextField(
        value = senha.value,
        onValueChange = { senha.value = it },
        label = { Text("Senha") },
        modifier = Modifier.fillMaxWidth(),
        visualTransformation = PasswordVisualTransformation()
    )

    Spacer(modifier = Modifier.height(16.dp))

    TextField(
        value = confirmacaoSenha.value,
        onValueChange = { confirmacaoSenha.value = it },
        label = { Text("Confirmar Senha") },
        modifier = Modifier.fillMaxWidth(),
        visualTransformation = PasswordVisualTransformation()
    )
    Spacer(modifier = Modifier.height(16.dp))

    var checkedTermos = remember { mutableStateOf(false) }

    var checkedNotificacao = remember { mutableStateOf(false) }

    Row(
        modifier = Modifier.fillMaxWidth(),
        verticalAlignment = Alignment.CenterVertically
    ) {

        Checkbox(
            checked = checkedTermos.value,
            onCheckedChange = { newValue ->
                checkedTermos.value = newValue
            },
            modifier = Modifier
                .size(20.dp)
                .scale(0.8f)

        )

        Text(
            text = "Concordo com os termos de uso e política de privacidade.",
            modifier = Modifier
                .clickable { checkedTermos.value = !checkedTermos.value }
                .padding(start = 8.dp),
            style = letraPadrao,
            fontSize = 11.sp,

            )
    }
    Spacer(modifier = Modifier.height(16.dp))

    Row(
        modifier = Modifier.fillMaxWidth(),
        verticalAlignment = Alignment.CenterVertically
    ) {

        Checkbox(
            checked = checkedNotificacao.value,
            onCheckedChange = { newValue ->
                checkedNotificacao.value = newValue
            },
            modifier = Modifier
                .size(20.dp)
                .scale(0.8f)
        )

        Text(
            text = "Quero receber notificações via e-mail.",
            modifier = Modifier
                .clickable { checkedNotificacao.value = !checkedNotificacao.value }
                .padding(start = 8.dp),
            style = letraPadrao,
            fontSize = 11.sp,
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
                Column(
                    verticalArrangement = Arrangement.Center,
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {

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
