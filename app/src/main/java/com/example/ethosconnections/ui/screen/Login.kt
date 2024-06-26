package com.example.ethosconnections.ui.screen

import android.os.Handler
import android.os.Looper
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Divider
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.example.compose.AppTheme
import com.example.ethosconnections.R
import com.example.ethosconnections.models.Empresa
import com.example.ethosconnections.repositories.EmpresaRepository
import com.example.ethosconnections.service.EmpresaService
import com.example.ethosconnections.ui.theme.letraButton
import com.example.ethosconnections.ui.theme.letraClicavel
import com.example.ethosconnections.ui.theme.letraPadrao
import com.example.ethosconnections.ui.theme.textoTop
import com.example.ethosconnections.ui.theme.tituloConteudoBranco
import com.example.ethosconnections.viewmodel.empresa.EmpresaViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

@Composable
fun Login(navController: NavController, viewModel: EmpresaViewModel) {
    Box(modifier = Modifier.fillMaxSize()) {
        val email = remember { mutableStateOf("") }
        val senha = remember { mutableStateOf("") }
        val isLoading = remember { mutableStateOf(false) }
        val errorMessage = remember { mutableStateOf("") }

        val usuarioSenhaIncorretos = stringResource(R.string.login_usuario_senha_incorretos)

        if (isLoading.value) {
            Box(
                modifier = Modifier.fillMaxSize(),
                contentAlignment = Alignment.Center
            ) {
                Column(
                    verticalArrangement = Arrangement.Center,
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    CircularProgressIndicator()
                    Spacer(modifier = Modifier.height(20.dp))
                    Text(text = stringResource(R.string.login_sucesso), style = tituloConteudoBranco)
                }

            }
        } else {
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
                    text = stringResource(R.string.login_faca_login),
                    style = textoTop
                )
                Spacer(modifier = Modifier.height(22.dp))

                if (errorMessage.value.isNotEmpty()) {
                    Column {
                        Text(
                            text = errorMessage.value,
                            style = letraPadrao,
                            color = Color.Red,
                            textAlign = TextAlign.Center,
                            modifier = Modifier.fillMaxWidth()
                        )
                        Spacer(modifier = Modifier.height(14.dp))
                    }
                }
                TextField(
                    value = email.value,
                    onValueChange = { email.value = it },
                    label = { Text(stringResource(R.string.login_email)) },
                    modifier = Modifier.fillMaxWidth(),
                )

                Spacer(modifier = Modifier.height(16.dp))

                TextField(
                    value = senha.value,
                    onValueChange = { senha.value = it },
                    label = { Text(stringResource(R.string.login_senha)) },
                    modifier = Modifier.fillMaxWidth(),
                    visualTransformation = PasswordVisualTransformation()
                )

                Spacer(modifier = Modifier.height(16.dp))

                Button(
                    modifier = Modifier.fillMaxWidth(),
                    shape = RoundedCornerShape(5.dp),
                    onClick = {
                        viewModel.loginEmpresa(email.value, senha.value) { success ->
                            if (success) {
                                val handler = Handler(Looper.getMainLooper())
                                isLoading.value = true
                                handler.postDelayed({
                                navController.navigate("plataforma")
                                },2000)
                            } else {
                                if(viewModel.errorMessage.value.toString().contains("Email ou senha inválido")) {
                                    errorMessage.value = usuarioSenhaIncorretos
                                }else{
                                    errorMessage.value = viewModel.errorMessage.value.toString()
                                }
                            }
                        }
                    }
                ) {
                    Text(
                        text = stringResource(R.string.login_entrar),
                        style = letraButton
                    )
                }
                Spacer(modifier = Modifier.height(32.dp))

                Text(
                    text = stringResource(R.string.login_esqueci_senha),
                    style = letraPadrao, textAlign = TextAlign.Left,
                    modifier = Modifier.clickable { }
                )

                Spacer(modifier = Modifier.height(32.dp))

                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.Center
                ) {
                    Divider(
                        modifier = Modifier
                            .height(0.9.dp)
                            .weight(1f)
                            .background(Color.White),
                        color = Color.White
                    )
                    Spacer(modifier = Modifier.width(10.dp))

                    Text(text = stringResource(R.string.login_ou), style = letraPadrao)

                    Spacer(modifier = Modifier.width(10.dp))

                    Divider(
                        modifier = Modifier
                            .height(0.9.dp)
                            .weight(1f)
                            .background(Color.White),
                        color = Color.White
                    )
                }
                Spacer(modifier = Modifier.height(32.dp))

                Row(
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Text(
                        text = stringResource(R.string.login_nao_cadastrado),
                        style = letraPadrao
                    )

                    TextButton(
                        onClick = { navController.navigate("cadastro") }) {
                        Text(
                            text = stringResource(R.string.login_criar_conta),
                            modifier = Modifier.wrapContentSize(),
                            style = letraClicavel
                        )
                    }
                }
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun LoginPreview() {
    AppTheme {
        val navController = rememberNavController()
        val empresaService = EmpresaService.create()
        val empresaRepository = EmpresaRepository(empresaService)
        //val viewModel = EmpresaViewModel( empresaRepository)

//        Login(navController, viewModel)
    }
}