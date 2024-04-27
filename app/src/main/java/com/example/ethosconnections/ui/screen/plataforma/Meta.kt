package com.example.ethosconnections.ui.screen.plataforma

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
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
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.Divider
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.RadioButton
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
import com.example.ethosconnections.ui.theme.letraButton
import com.example.ethosconnections.ui.theme.tituloConteudoAzul
import com.example.ethosconnections.ui.theme.tituloConteudoBranco
import com.example.ethosconnections.ui.theme.tituloPagina

@Composable
fun Meta(navController: NavController) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(10.dp)
    ) {
        Text(
            text = "Meta",
            style = tituloPagina,
        )
        CadastroMeta(navController)
    }
}

@Composable
fun CadastroMeta(navController: NavController) {

    var pilarEsg = remember {
        mutableStateOf("")
    }

    var pilarAmbiental = remember {
        mutableStateOf(false)
    }

    var pilarSocial = remember {
        mutableStateOf(false)
    }

    var pilarGovernamental = remember {
        mutableStateOf(false)
    }

    var descricao = remember {
        mutableStateOf(TextFieldValue())
    }

    var dataInicio = remember {
        mutableStateOf(TextFieldValue())
    }

    var dataFim = remember {
        mutableStateOf(TextFieldValue())
    }

    BoxEthos {
        Column {
            Text(
                text = "Defina sua meta",
                style = tituloPagina,
                modifier = Modifier.fillMaxWidth()
            )

            Divider(
                color = Color.Gray,
                thickness = 1.dp,
                modifier = Modifier.padding(vertical = 8.dp)
            )

            Text(
                text = "Em qual dos 3 pilares do ESG sua empresa busca melhorar?",
                style = tituloConteudoAzul,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 8.dp)
            )

            Column {
                Row {
                    RadioButton(
                        selected = false,
                        onClick = { /*TODO*/ }
                    )
                    Text(
                        text = "Pilar Ambiental",
                        style = tituloConteudoBranco,
                        modifier = Modifier
                            .padding(top = 12.dp)
                    )
                }

                Row {
                    RadioButton(
                        selected = false,
                        onClick = { /*TODO*/ }
                    )
                    Text(
                        text = "Pilar Social",
                        style = tituloConteudoBranco,
                        modifier = Modifier
                            .padding(top = 12.dp)
                    )
                }

                Row {
                    RadioButton(
                        selected = false,
                        onClick = { /*TODO*/ }
                    )
                    Text(
                        text = "Pilar Governamental",
                        style = tituloConteudoBranco,
                        modifier = Modifier
                            .padding(top = 12.dp)
                    )
                }
            }

            Text(
                text = "Descreva a sua meta ou deixe uma observação",
                style = tituloConteudoAzul,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 8.dp)
            )
            TextField(
                value = descricao.value,
                onValueChange = { descricao.value = it },
                modifier = Modifier
                    .fillMaxWidth()
                    .height(150.dp)
                    .background(color = Color(0xFF1B1B1B))
            )

            Column {
                Text(
                    text = "Data Limite",
                    style = tituloConteudoAzul,
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(top = 20.dp)
                )
                TextField(
                    value = dataFim.value,
                    onValueChange = { dataFim.value = it },
                    label = { Text("dd/mm/aaaa") },
                    modifier = Modifier
                        .height(30.dp)
                        .width(180.dp)
                        .background(color = Color(0xFF1B1B1B))
                )
            }
        }
        MetaButtons(navController)
    }
}

@Composable
fun MetaButtons(navController: NavController) {
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
fun MetaPreview() {
    val navController = rememberNavController()
    Meta(navController)
}