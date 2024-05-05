package com.example.ethosconnections.ui.screen.plataforma

import android.os.Handler
import android.os.Looper
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
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
import com.example.ethosconnections.models.Meta
import com.example.ethosconnections.repositories.MetaRepository
import com.example.ethosconnections.service.MetaService
import com.example.ethosconnections.ui.screen.plataforma.components.BoxEthos
import com.example.ethosconnections.ui.theme.letraButton
import com.example.ethosconnections.ui.theme.tituloConteudoAzul
import com.example.ethosconnections.ui.theme.tituloConteudoBranco
import com.example.ethosconnections.ui.theme.tituloPagina
import com.example.ethosconnections.viewmodel.meta.MetaViewModel
import java.util.UUID

@Composable
fun Meta(navController: NavController) {
    val repository = remember { MetaRepository(MetaService.create()) }
    val viewModel = remember { MetaViewModel(repository) }
    Column{
        Text(
            text = "Meta",
            style = tituloPagina,
        )
        CadastroMeta(navController, viewModel)
    }
}

@Composable
fun CadastroMeta(navController: NavController, viewModel: MetaViewModel) {

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

            Divider(modifier = Modifier.padding(bottom = 10.dp))


            Text(
                text = "Em qual dos 3 pilares do ESG sua empresa busca melhorar?",
                style = tituloConteudoAzul,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 8.dp, bottom = 20.dp)
            )

            Column {
                Row {
                    RadioButton(
                        selected = pilarAmbiental.value,
                        onClick = {
                            pilarAmbiental.value = true
                            pilarSocial.value = false
                            pilarGovernamental.value = false
                        }
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
                        selected = pilarSocial.value,
                        onClick = {
                            pilarAmbiental.value = false
                            pilarSocial.value = true
                            pilarGovernamental.value = false
                        }
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
                        selected = pilarGovernamental.value,
                        onClick = {
                            pilarAmbiental.value = false
                            pilarSocial.value = false
                            pilarGovernamental.value = true
                        }
                    )
                    Text(
                        text = "Pilar Governamental",
                        style = tituloConteudoBranco,
                        modifier = Modifier
                            .padding(top = 12.dp)
                    )
                }
            }

            Column {
                Text(
                    text = "Descreva a sua meta ou deixe uma observação",
                    style = tituloConteudoAzul,
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(top = 20.dp)
                )
                TextField(
                    value = descricao.value,
                    onValueChange = { descricao.value = it },
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(150.dp)
                        .background(color = Color(0xFF1B1B1B))
                )
                ValidarData()
            }
        }
        MetaButtons(navController, viewModel)
    }
}

@Composable
fun MetaButtons(navController: NavController, viewModel: MetaViewModel) {
    val errorMessage = remember { mutableStateOf("") }
    val isLoading = remember { mutableStateOf(false) }

    // Dados para testar cadastro de meta
    val meta = Meta(
        id = UUID.fromString("a7ee76fb-2b65-4d36-b8d3-831ce5361454"),
        pilarEsg = "Ambiental",
        descricao = "teste",
        dataInicio = null,
        dataFim = null,
        fkEmpresa = UUID.fromString("a7ee76fb-2b65-4d36-b8d3-831ce5361499"),
    )

    Box(
        modifier = Modifier
            .fillMaxWidth()
            .padding(start = 0.dp, top = 50.dp, end = 0.dp, bottom = 0.dp)
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Button(
                onClick = {
                    viewModel.postMeta(meta) { success ->
                        if (success) {
                            val handler = Handler(Looper.getMainLooper())
                            isLoading.value = true
                            handler.postDelayed({
                                navController.navigate("meuProgresso")
                            },2000)
                        } else {
                            errorMessage.value = "Erro ao cadastrar a meta"
                        }
                    }
                },
                modifier = Modifier.weight(1f),
                shape = RoundedCornerShape(5.dp)
            ) {
                Text(
                    text = "Salvar",
                    style = letraButton
                )
            }

            OutlinedButton(
                onClick = { navController.navigate("meuProgresso") },
                modifier = Modifier
                    .weight(1f)
                    .padding(horizontal = 10.dp),
                shape = RoundedCornerShape(5.dp),
                border = BorderStroke(2.dp, Color(0xFF01A2C3)),
            ) {
                Text(
                    text = "Cancelar",
                    style = tituloConteudoAzul
                )
            }
        }
    }
}

@Composable
fun ValidarData() {
    val dataFim = remember { mutableStateOf("") }
    Column {
        Text(
            text = "Data Limite",
            style = tituloConteudoAzul,
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 30.dp)
        )
        TextField(
            value = dataFim.value,
            onValueChange = { dataFim.value = it },
            label = { Text("dd/mm/aaaa") },
            modifier = Modifier
                .background(color = Color(0xFF1B1B1B))
        )
        if (!validarData(dataFim.value) && dataFim.value.isNotBlank()) {
            Text(
                text = "Insira uma data válida",
                color = Color.Red,
                modifier = Modifier.padding(start = 16.dp)
            )
        }
    }
}

fun validarData(data: String): Boolean {
    val partes = data.split("/")
    if (partes.size != 3) return false
    val dia = partes[0].toIntOrNull() ?: return false
    val mes = partes[1].toIntOrNull() ?: return false
    val ano = partes[2].toIntOrNull() ?: return false

    if (dia !in 1..31) return false
    if (mes !in 1..12) return false
    if (ano <= 2023) return false

    return true
}

@Composable
@Preview(showBackground = true)
fun MetaPreview() {
    val navController = rememberNavController()
    val repository = remember { MetaRepository(MetaService.create()) }
    Meta(navController)
}