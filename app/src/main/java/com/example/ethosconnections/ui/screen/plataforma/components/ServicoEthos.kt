package com.example.ethosconnections.ui.screen.plataforma.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.compose.AppTheme
import com.example.compose.cor_primaria
import com.example.compose.cor_secundaria
import com.example.compose.preto_azulado
import com.example.ethosconnections.R
import com.example.ethosconnections.ui.theme.letraPadrao
import com.example.ethosconnections.ui.theme.tituloConteudoAzul
import com.example.ethosconnections.ui.theme.tituloServico
import java.util.UUID

@Composable
fun ServicoEthos(
    fotoEmpresa: Int,
    categoria: String,
    nomeServico: String,
    nomeEmpresa: String,
    descricao:String,
    valor:Double,
    id:UUID,
    fkPrestadora:UUID,
    onClick: () -> Unit
) {
    Card(
        modifier = Modifier
            .padding(bottom = 10.dp)
            .width(186.dp)
            .height(220.dp)
            .clickable(onClick = onClick),
        shape = RoundedCornerShape(5.dp),
        colors = CardDefaults.cardColors(containerColor = preto_azulado)

    ) {
        Column {

            Box {
                Image(
                    painter = painterResource(id = fotoEmpresa),
                    contentDescription = "Foto de Perfil Empresa",
                    modifier = Modifier
                        .height(85.dp),
                    contentScale = ContentScale.Crop

                )
                Column(
                    modifier = Modifier
                        .align(Alignment.TopEnd)
                        .padding(top = 4.dp)

                ) {
                    Text(
                        text = "E",
                        style = letraPadrao.copy(color = Color.White),
                        textAlign = TextAlign.Center,
                        modifier = Modifier
                            .background(if (categoria == "ENVIRONMENTAL") cor_primaria else cor_secundaria, RoundedCornerShape(topStart = 8.dp))
                            .padding(start = 5.dp, end = 4.dp, top = 2.dp, bottom = 2.dp)
                            .width(9.dp)
                    )
                    Spacer(modifier = Modifier.height(2.dp))
                    Text(
                        text = "S",
                        style = letraPadrao.copy(color = Color.White),
                        textAlign = TextAlign.Center,
                        modifier = Modifier
                            .background( if (categoria == "SOCIAL") cor_primaria else cor_secundaria, RoundedCornerShape(topStart = 8.dp))
                            .padding(start = 5.dp, end = 4.dp, top = 2.dp, bottom = 2.dp)
                            .width(9.dp)

                    )
                    Spacer(modifier = Modifier.height(2.dp))
                    Text(
                        text = "G",
                        style = letraPadrao.copy(color = Color.White),
                        textAlign = TextAlign.Center,
                        modifier = Modifier
                            .background( if (categoria == "GOVERNANCE") cor_primaria else cor_secundaria, RoundedCornerShape(topStart = 8.dp))
                            .padding(start = 5.dp, end = 4.dp, top = 2.dp, bottom = 2.dp)
                            .width(9.dp)

                    )
                }
            }
            Box {
                Column(
                    modifier = Modifier
                        .padding(top= 8.dp, bottom = 8.dp, start = 10.dp, end = 8.dp)
                ) {

                    Text(
                        text = nomeServico,
                        style = tituloServico,
                        modifier = Modifier.padding(bottom = 5.dp)
                    )
                    Text(
                        text = nomeEmpresa,
                        style = letraPadrao,
                        modifier = Modifier.padding(bottom = 5.dp)
                    )

                    TextButton(
                        onClick = {  onClick },
                        contentPadding = PaddingValues(0.dp),
                    ) {
                        Text(
                            text = "Saiba Mais",
                            style = tituloConteudoAzul,
                            fontSize = 13.sp
                        )
                    }
                }
            }

        }

    }
}


@Preview(showBackground = true)
@Composable
fun SolucoesESGPreview() {

//    AppTheme {
//        Surface {
//            ServicoEthos(fotoEmpresa = R.mipmap.governance,
//                categoria = "Governança",
//                nomeServico = "Treinamento de Responsabilidade Social Corporativa (RSC)",
//                nomeEmpresa = "Deloitte",
//                onClick = { })
//        }
//    }
}
