package com.example.ethosconnections.ui.screen.plataforma.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardColors
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.example.compose.cinza_caixas_claras
import com.example.compose.cor_primaria
import com.example.compose.cor_secundaria
import com.example.compose.preto_azulado
import com.example.ethosconnections.ui.theme.letraPadrao
import com.example.ethosconnections.ui.theme.tituloConteudoAzul
import com.example.ethosconnections.ui.theme.tituloConteudoBranco

@Composable
fun Servico(
    fotoEmpresa: Int,
    categoria: String,
    nomeServico: String,
    nomeEmpresa: String,
    onClick: () -> Unit

) {
    Card(
        modifier = Modifier
            .width(170.dp)
            .height(225.dp),
        shape = RoundedCornerShape(5.dp),
        colors = CardDefaults.cardColors(containerColor = preto_azulado)

    ) {
        Column {

            Box { // Este Box contém tanto a imagem quanto as letras "E", "S" e "G"
                Image(
                    painter = painterResource(id = fotoEmpresa),
                    contentDescription = "Foto de Perfil Empresa",
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(100.dp),
                    contentScale = ContentScale.Crop
                )
                Column(
                    modifier = Modifier
                        .align(Alignment.TopEnd)
                        .padding(top = 8.dp, end = 0.dp)
                ) {
                    Text(
                        text = "E",
                        style = letraPadrao.copy(color = Color.White),
                        modifier = Modifier
                            .background(cor_secundaria)
                            .padding(4.dp)
                            .width(5.dp)
                    )
                    Text(
                        text = "S",
                        style = letraPadrao.copy(color = Color.White),
                        modifier = Modifier
                            .background(cor_secundaria)
                            .padding(4.dp)
                            .width(5.dp)

                    )
                    Text(
                        text = "G",
                        style = letraPadrao.copy(color = Color.White),
                        modifier = Modifier
                            .background(cor_primaria)
                            .padding(4.dp)
                            .width(5.dp)

                    )
                }
            }
            Box {
                Column(
                    modifier = Modifier
                        .padding(8.dp)
                ) {

                    Text(
                        text = nomeServico,
                        style = letraPadrao
                    )

                    TextButton(
                        onClick = onClick,
                        modifier = Modifier.padding(0.dp)
                    ) {
                        Text(
                            text = "Saiba Mais",
                            style = tituloConteudoAzul,
                        )
                    }
                }
            }

        }

    }
}
