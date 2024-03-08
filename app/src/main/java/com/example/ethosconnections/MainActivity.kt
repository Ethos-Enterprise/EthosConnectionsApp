package com.example.ethosconnections

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.compose.AppTheme
import com.example.ethosconnections.ui.theme.semiBold

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            AppTheme {
                LoginScreen()
            }
        }
    }
}

@Composable
fun LoginScreen() {
    Box(modifier = Modifier.fillMaxSize()) {

        val email = remember { mutableStateOf("") }
        val senha = remember { mutableStateOf("") }

        Image(
            painter = painterResource(id = R.drawable.icone_logo),
            contentDescription = "Icone da empresa",
            modifier = Modifier.fillMaxWidth(0.8f)
        )

        Image(
            painter = painterResource(id = R.drawable.background_login),
            contentDescription = null,
            modifier = Modifier.fillMaxSize(),
            contentScale = ContentScale.FillBounds
        )


        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(1.dp),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Image(
                painter = painterResource(id = R.mipmap.icone_logo_branco),
                contentDescription = null,
                modifier = Modifier.fillMaxWidth(0.14f)
            )

            Spacer(modifier = Modifier.height(22.dp))

            Text(
                text = "Faça Login na Ethos",
                style = semiBold
            )
            Spacer(modifier = Modifier.height(22.dp))

            TextField(
                value = email.value,
                onValueChange = { email.value = it },
                label = { Text("Email") }
            )

            Spacer(modifier = Modifier.height(16.dp))

            TextField(
                value = senha.value,
                onValueChange = { senha.value = it },
                label = { Text("Senha") }
            )

            Spacer(modifier = Modifier.height(16.dp))

            Button(onClick = { /*colcoar func*/ }


            ) {
                Text(text = "Entrar")
            }


        }
    }
}

@Preview(showBackground = true)
@Composable
fun LoginScreenPreview() {
    AppTheme {
        LoginScreen()
    }
}
