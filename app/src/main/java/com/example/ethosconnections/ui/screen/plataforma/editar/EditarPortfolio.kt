package com.example.ethosconnections.ui.screen.plataforma.editar

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
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.ethosconnections.R
import com.example.ethosconnections.datastore.EmpresaDataStore
import com.example.ethosconnections.ui.screen.plataforma.components.BoxEthos
import com.example.ethosconnections.ui.theme.letraButton
import com.example.ethosconnections.ui.theme.tituloConteudoAzul
import com.example.ethosconnections.ui.theme.tituloConteudoBranco
import com.example.ethosconnections.viewmodel.empresa.EmpresaViewModel

@Composable
fun EditarPortfolio(
    navController: NavController,
    empresaViewModel: EmpresaViewModel,
    empresaDataStore: EmpresaDataStore
) {
//    val empresaPortfolio by empresaDataStore.getEmpresaFlow().collectAsState(initial = null)

    var descricaoBreve = remember { mutableStateOf(TextFieldValue("")) }
    var sobreEmpresa = remember { mutableStateOf(TextFieldValue("")) }
    val linkWebsite = remember { mutableStateOf(TextFieldValue("")) }
    val empresaAnoCertificada = remember { mutableStateOf(TextFieldValue("")) }


    BoxEthos {
        Column {
            Text(
                stringResource(R.string.editar_portfolio_titulo),
                style = tituloConteudoAzul,
                modifier = Modifier.fillMaxWidth()
            )

            Divider(modifier = Modifier.padding(bottom = 10.dp))


            Text(
                stringResource(R.string.editar_portfolio_label_descricao_empresa),
                style = tituloConteudoBranco,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 8.dp)
            )
            TextField(
                value = descricaoBreve.value,
                onValueChange = { descricaoBreve.value = it },
                modifier = Modifier.fillMaxWidth()
            )

            Text(
                stringResource(R.string.editar_portfolio_label_sobre_empresa),
                style = tituloConteudoBranco,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 8.dp)
            )
            TextField(
                value = sobreEmpresa.value,
                onValueChange = { sobreEmpresa.value = it },
                modifier = Modifier.fillMaxWidth(),
                shape = RoundedCornerShape(4.dp),
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number)
            )

            Text(
                stringResource(R.string.editar_portfolio_label_url_empresa),
                style = tituloConteudoBranco,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 8.dp)
            )
            TextField(
                value = linkWebsite.value,
                onValueChange = { linkWebsite.value = it },
                modifier = Modifier.fillMaxWidth(),
                shape = RoundedCornerShape(4.dp)
            )

            Text(
                stringResource(R.string.editar_portfolio_label_data_certificada_empresa),
                style = tituloConteudoBranco,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 8.dp)
            )
            TextField(
                value = empresaAnoCertificada.value,
                onValueChange = { empresaAnoCertificada.value = it },
                modifier = Modifier.fillMaxWidth(),
                shape = RoundedCornerShape(4.dp),
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number)
            )

            buttonsPortfolio(navController)
        }
    }
}

@Composable
fun buttonsPortfolio(navController: NavController) {

    Box(
        modifier = Modifier
            .fillMaxWidth()
            .padding(start = 0.dp, top = 50.dp, end = 0.dp, bottom = 0.dp)
            .background(Color(0xFF1B1F23))

    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Button(
                onClick = {
                    navController.navigate("portfolio")
//                    viewModel.putPortfolio(
//                        id = UUID.fromString(id.value),
//                        Portfolio()
//                    )
                },
                modifier = Modifier
                    .weight(1f)
                    .padding(horizontal = 10.dp)
                    .background(Color(0xFF1B1F23)),
                shape = RoundedCornerShape(5.dp)
            ) {
                Text(
                    text = stringResource(R.string.txt_button_salvar),
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
                    text = stringResource(R.string.txt_button_cancelar),
                    style = letraButton,
                    color = Color(0xFF01A2C3)
                )
            }
        }
    }
}
