package com.example.ethosconnections.ui.screen.plataforma

import android.os.Handler
import android.os.Looper
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Button
import androidx.compose.material3.Divider
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.RadioButton
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.example.ethosconnections.R
import com.example.ethosconnections.datastore.EmpresaDataStore
import com.example.ethosconnections.models.Meta
import com.example.ethosconnections.models.Token
import com.example.ethosconnections.repositories.MetaRepository
import com.example.ethosconnections.service.MetaService
import com.example.ethosconnections.ui.screen.plataforma.components.BoxEthos
import com.example.ethosconnections.ui.theme.letraButton
import com.example.ethosconnections.ui.theme.tituloConteudoAzul
import com.example.ethosconnections.ui.theme.tituloConteudoBranco
import com.example.ethosconnections.ui.theme.tituloPagina
import com.example.ethosconnections.viewmodel.meta.MetaViewModel
import java.time.LocalDate
import java.util.Calendar
import java.util.UUID

@Composable
fun Meta(navController: NavController,metaViewModel: MetaViewModel, empresaDataStore: EmpresaDataStore) {

    var token = ""
    LaunchedEffect(key1 = null) {
        token = empresaDataStore.getToken()
    }

    Column {
        Text(
            stringResource(R.string.titulo_pagina_meta),
            style = tituloPagina,
        )
        CadastroMeta(navController, metaViewModel, empresaDataStore, token)
    }
}

@Composable
fun CadastroMeta(
    navController: NavController,
    viewModel: MetaViewModel,
    empresaDataStore: EmpresaDataStore,
    token:String
) {

    var pilarEsg = remember {
        mutableStateOf("")
    }

    var descricao = remember {
        mutableStateOf("")
    }

    var dataInicio = remember {
        mutableStateOf("")
    }

    var dataFimState = remember { mutableStateOf("") }


    BoxEthos {
        Column {
            Text(
                stringResource(R.string.subtitulo_pagina_meta),
                style = tituloPagina,
                modifier = Modifier.fillMaxWidth()
            )

            Divider(modifier = Modifier.padding(bottom = 10.dp))


            Text(
                stringResource(R.string.opcoes_pilares_esg_meta),
                style = tituloConteudoAzul,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 8.dp, bottom = 20.dp)
            )

            Column(modifier = Modifier.fillMaxWidth()) {
                Row(
                    modifier = Modifier.fillMaxWidth()
                    .clickable { pilarEsg.value = "Ambiental" }
                ) {
                    RadioButton(
                        selected = pilarEsg.value == "Ambiental",
                        onClick = { }
                    )
                    Text(
                        text = "Pilar Ambiental",
                        style = tituloConteudoBranco,
                        modifier = Modifier
                            .padding(top = 12.dp)
                    )
                }

                Row(
                    modifier = Modifier.clickable { pilarEsg.value = "Social" }
                        .fillMaxWidth()
                ) {
                    RadioButton(
                        selected = pilarEsg.value == "Social",
                        onClick = { }
                    )
                    Text(
                        text = "Pilar Social",
                        style = tituloConteudoBranco,
                        modifier = Modifier
                            .padding(top = 12.dp)
                    )
                }

                Row(
                    modifier = Modifier.clickable { pilarEsg.value = "Governamental" }
                        .fillMaxWidth()
                ) {
                    RadioButton(
                        selected = pilarEsg.value == "Governamental",
                        onClick = { }
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
                    stringResource(R.string.descricao_meta),
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

                ValidarData(
                    dataFim = dataFimState.value,
                    onDateChange = { dataFimState.value = it }
                )
            }
        }
    }
    MetaButtons(
        navController, viewModel, empresaDataStore,
        pilarEsg.value,
        descricao.value,
        dataFimState.value,
        token
    )
}


@Composable
fun MetaButtons(
    navController: NavController,
    viewModel: MetaViewModel,
    empresaDataStore: EmpresaDataStore,
    pilarEsg: String,
    descricao: String,
    dataFim: String,
    token: String
) {
    val errorMessage = remember { mutableStateOf("") }
    val isLoading = remember { mutableStateOf(false) }
    val empresa by empresaDataStore.getEmpresaFlow().collectAsState(initial = null)

    val hoje = Calendar.getInstance()
    val dataAtual = String.format(
        "%04d-%02d-%02d",
        hoje.get(Calendar.YEAR),
        hoje.get(Calendar.MONTH) + 1,
        hoje.get(Calendar.DAY_OF_MONTH)
    )
    val dataConvertida = converterData(dataFim)


    val meta = Meta(
        pilarEsg = pilarEsg,
        descricao = descricao,
        dataInicio = dataAtual,
        dataFim = dataConvertida,
        fkEmpresa = empresa?.id
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
                    viewModel.postMeta(meta, token ) { success ->
                        if (success) {
                            val handler = Handler(Looper.getMainLooper())
                            isLoading.value = true
                            handler.postDelayed({
                                navController.navigate("meuProgresso")
                            }, 2000)
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
fun ValidarData(dataFim: String, onDateChange: (String) -> Unit) {
    Column {
        Text(
            stringResource(R.string.data_limite_meta),
            style = tituloConteudoAzul,
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 30.dp)
        )
        TextField(
            value = dataFim,
            onValueChange = { newValue ->
                onDateChange(newValue.take(10))
            },
            label = { Text("dd-mm-aaaa") },
            modifier = Modifier
                .background(color = Color(0xFF1B1B1B))
        )
        if (!validarData(dataFim) && dataFim.isNotBlank()) {
            Text(
                text = "Insira uma data válida",
                color = Color.Red,
                modifier = Modifier.padding(start = 16.dp)
            )
        }
    }
}


fun validarData(data: String): Boolean {
    val partes = data.split("-")
    if (partes.size != 3) return false
    val dia = partes[0].toIntOrNull() ?: return false
    val mes = partes[1].toIntOrNull() ?: return false
    val ano = partes[2].toIntOrNull() ?: return false

    if (dia !in 1..31) return false
    if (mes !in 1..12) return false
    if (ano <= 2023) return false

    return true
}

fun converterData(data: String): String {
    val partes = data.split("-")
    if (partes.size != 3) {
        return "Formato de data inválido"
    }

    val dia = partes[0]
    val mes = partes[1]
    val ano = partes[2]

    return "$ano-$mes-$dia"
}
@Composable
@Preview(showBackground = true)
fun MetaPreview() {
    val navController = rememberNavController()
    val repository = remember { MetaRepository(MetaService.create()) }
    //Meta(navController)
}