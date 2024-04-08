package com.example.ethosconnections.ui.screen.plataforma.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.example.ethosconnections.ui.theme.letraPadrao
import com.example.ethosconnections.ui.theme.tituloConteudoAzul
import com.example.ethosconnections.ui.theme.tituloConteudoBranco


@Composable
fun Servico(
    fotoEmpresa: Int,
    categoria: String,
    nomeServico: String,
    nomeEmpresa: String,
) {
    Card(
        modifier = Modifier
            .width(170.dp)
            .height(210.dp),
        shape = RoundedCornerShape(5.dp)
    ) {
        Column(
            modifier = Modifier.clickable(onClick = { })
        ) {
            Image(
                painter = painterResource(id = fotoEmpresa),
                contentDescription = "Foto de Perfil Empresa",
                modifier = Modifier
                    .fillMaxWidth()
                    .height(100.dp),
                contentScale = ContentScale.Crop
            )

            Box(
                modifier = Modifier
                    .padding(8.dp)
            ) {
                Column {

                    Text(
                        text = categoria,
                        style = tituloConteudoBranco,
                    )

                    Text(
                        text = nomeServico,
                        style = letraPadrao
                    )

                    TextButton(onClick = { /*TODO*/ }) {
                        Text(
                            text = "Saiba Mais",
                            style = tituloConteudoAzul)
                    }
                }

            }


        }
    }
}
