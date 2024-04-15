package com.example.ethosconnections.ui.screen

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.example.compose.AppTheme
import com.example.ethosconnections.R
import com.example.ethosconnections.ui.theme.corLetra
import com.example.ethosconnections.ui.theme.letraButton

@Composable

fun Home(navController: NavController) {

    Image(
        painter = painterResource(id = R.drawable.home),
        contentDescription = null,
        modifier = Modifier
            .fillMaxSize(),
    )

    Box(modifier = Modifier.fillMaxSize()) {

        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(40.dp),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally

        ) {

            Image(
                modifier = Modifier
                    .width(220.dp)
                    .padding(bottom = 4.dp),
                painter = painterResource(id = R.drawable.logo_branco),
                contentDescription = "Icone da cor branca",

                )
            Spacer(modifier = Modifier.height(150.dp))

            Text(
                text = "Conectando negócios, moldando o futuro",
                style = corLetra,
                modifier = Modifier.width(300.dp),
                fontSize = 23.sp,
                textAlign = TextAlign.Center
            )

            Spacer(modifier = Modifier.height(50.dp))

            Button(
                modifier = Modifier.fillMaxWidth(),
                shape = RoundedCornerShape(10.dp),
                onClick = { navController.navigate("login") }) {
                Text(text = "Fazer Login", style = letraButton)
            }
            Spacer(modifier = Modifier.height(10.dp))

            Row (
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.Center
            ){
                Text(
                    text = "Não tem uma conta?",
                    style = corLetra,
                    fontSize = 13.5.sp,
                    )

                TextButton(
                    onClick = { navController.navigate("cadastro") }) {
                    Text(
                        text = "Cadastrar Empresa")
                }
            }

        }
    }

}

@Preview(showBackground = true)
@Composable
fun HomePreview() {
    AppTheme {
        val navController = rememberNavController()
        Home(navController)
    }
}
