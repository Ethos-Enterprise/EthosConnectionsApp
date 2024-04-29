package com.example.ethosconnections.ui.screen.plataforma

import androidx.compose.foundation.Canvas
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
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
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Divider
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.CornerRadius
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.compose.card_formulario
import com.example.compose.cor_primaria
import com.example.compose.linha_divisoria
import com.example.ethosconnections.R
import com.example.ethosconnections.ui.screen.plataforma.components.BoxEthos
import com.example.ethosconnections.ui.screen.plataforma.components.FillButtonEthos
import com.example.ethosconnections.ui.theme.letraPadrao
import com.example.ethosconnections.ui.theme.letraPadraoExtraLigth
import com.example.ethosconnections.ui.theme.tituloConteudoAzul
import com.example.ethosconnections.ui.theme.tituloConteudoBranco
import com.example.ethosconnections.ui.theme.tituloFormularioCard
import com.example.ethosconnections.ui.theme.tituloPagina

@Composable
fun MeuProgresso(navController: NavController) {

    Column {

        Text(text = "Meu Progresso", style = tituloPagina)

        BoxEthos {
            Text(text = "Meu Nível de ESG", style = tituloConteudoAzul)
            Divider(modifier = Modifier.padding(bottom = 10.dp))
            Text(text = "Total de Aderência ESG - em %", style = tituloConteudoBranco)

            ProgressBar(progress = 0.5f)


            Text(text = "Aderência ESG por Área de Impacto - em %", style = tituloConteudoBranco)

            Spacer(modifier = Modifier.height(16.dp))

            Row(
                horizontalArrangement = Arrangement.SpaceBetween,
                modifier = Modifier.fillMaxWidth()
            ) {
                CircularProgress(nomeGrafico = "Ambiental")
                CircularProgress(nomeGrafico = "Social")
                CircularProgress(nomeGrafico = "Governamental")

            }
        }

        BoxEthos {
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,

                ) {
                Text(text = "Minha Meta", style = tituloConteudoAzul)

                FillButtonEthos(
                    acao = { /*TODO*/ },
                    nomeAcao = "Criar Meta"
                )
            }
            Divider()
            Spacer(modifier = Modifier.height(28.dp))
            Image(
                painter = painterResource(id = R.mipmap.meta),
                contentDescription = "icone meta",
                modifier = Modifier
                    .fillMaxWidth()
                    .fillMaxHeight(0.6f)
            )
            Text(
                text = "Nenhuma meta definida",
                style = letraPadraoExtraLigth,
                textAlign = TextAlign.Center,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 16.dp)

            )
        }

        BoxEthos {
            Text(text = "Formulários Avaliativos", style = tituloConteudoAzul)
            Divider(modifier = Modifier.padding(bottom = 10.dp))
            CardFormulario(fotoEmpresa = R.mipmap.card_formulario_ambiental,
                categoria = "Governança",
                nomeServico = "Ambiental",
                nomeEmpresa = "Explicação do formulário breve, talvez sobre o pilar não sei mas deve ser sucinto please amigos e amigas.",
                onClick = { navController.navigate("formulario") })

            CardFormulario(fotoEmpresa = R.mipmap.card_formulario_social,
                categoria = "Governança",
                nomeServico = "Ambiental",
                nomeEmpresa = "Explicação do formulário breve, talvez sobre o pilar não sei mas deve ser sucinto please amigos e amigas.",
                onClick = { navController.navigate("formulario") })

            CardFormulario(fotoEmpresa = R.mipmap.card_formulario_governamental,
                categoria = "Governança",
                nomeServico = "Ambiental",
                nomeEmpresa = "Explicação do formulário breve, talvez sobre o pilar não sei mas deve ser sucinto please amigos e amigas.",
                onClick = { navController.navigate("formulario") })
        }
    }
}


@Composable
fun CardFormulario(
    fotoEmpresa: Int,
    categoria: String,
    nomeServico: String,
    nomeEmpresa: String,
    onClick: () -> Unit

) {
    Card(
        modifier = Modifier
            .padding(bottom = 15.dp)
            .fillMaxWidth()
            .clickable(onClick = onClick),
        shape = RoundedCornerShape(5.dp),
        colors = CardDefaults.cardColors(containerColor = card_formulario)

    ) {
        Column {

            Box {
                Image(
                    painter = painterResource(id = fotoEmpresa),
                    contentDescription = "Foto de Perfil Empresa",
                    modifier = Modifier
                        .fillMaxHeight(0.4f)
                        .fillMaxWidth(),
                    contentScale = ContentScale.Crop

                )
                Column(
                    modifier = Modifier
                        .align(Alignment.TopEnd)
                        .padding(top = 4.dp, end = 4.dp)

                ) {
                    Spacer(modifier = Modifier.height(2.dp))
                    Text(
                        text = "Não Iniciado",
                        style = letraPadrao.copy(color = Color.Black),
                        textAlign = TextAlign.Center,
                        modifier = Modifier
                            .background(cor_primaria, RoundedCornerShape(4.dp))
                            .padding(start = 8.dp, end = 8.dp, top = 2.dp, bottom = 2.dp)
                    )
                }
            }
            Box {
                Column(
                    modifier = Modifier
                        .padding(top = 15.dp, bottom = 15.dp, start = 15.dp, end = 15.dp)
                ) {

                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.SpaceBetween,
                    ) {

                        Box {

                            Column(

                            ) {

                                Text(
                                    text = "Área de Impacto",
                                    style = letraPadrao,
                                    modifier = Modifier.padding(bottom = 5.dp)
                                )

                                Text(
                                    text = nomeServico,
                                    style = tituloFormularioCard,
                                    modifier = Modifier.padding(bottom = 5.dp)
                                )
                            }

                        }

                        Column {
                            Text(
                                text = "Pontuação",
                                style = letraPadrao,
                                modifier = Modifier.padding(bottom = 5.dp)
                            )

                            Text(
                                text = nomeServico,
                                style = tituloFormularioCard,
                                modifier = Modifier.padding(bottom = 5.dp)
                            )
                        }
                    }

                    Text(
                        text = nomeEmpresa,
                        style = letraPadrao,
                        modifier = Modifier.padding(bottom = 5.dp)
                    )

                }
            }

        }

    }
}


@Composable

fun Teste() {
    Column {

        Text(text = "Meu Progresso", style = tituloPagina)

        BoxEthos {
            Text(text = "Meu Nível de ESG", style = tituloConteudoAzul)
            Divider(modifier = Modifier.padding(bottom = 10.dp))
            Text(text = "Total de Aderência ESG - em %", style = tituloConteudoBranco)



            Text(text = "Aderência ESG por Área de Impacto - em %", style = tituloConteudoBranco)

            Row(
                horizontalArrangement = Arrangement.SpaceBetween,
                modifier = Modifier.fillMaxWidth()
            ) {
                CircularProgress(nomeGrafico = "Ambiental")
                CircularProgress(nomeGrafico = "Social")
                CircularProgress(nomeGrafico = "Governamental")

            }
        }
    }
}

@Composable
fun CircularProgress(
    progress: Float = 0.5f,
    strokeWidth: Dp = 5.dp,
    size: Dp = 80.dp,
    color: Color = cor_primaria,
    nomeGrafico: String
) {
    val progressPercent = (progress * 100).toInt()
    val progressText = "$progressPercent%"
    Column(
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Box(
            modifier = Modifier
                .size(size)
        ) {
            Canvas(
                modifier = Modifier
                    .fillMaxSize()
                    .wrapContentSize()
            ) {
                val centerX = size.toPx() / 50
                val centerY = size.toPx() / 50
                val radius = size.toPx() / 2 - strokeWidth.toPx() / 2

                drawArc(
                    color = linha_divisoria,
                    startAngle = -90f,
                    sweepAngle = 360f,
                    useCenter = false,
                    topLeft = Offset(centerX - radius, centerY - radius),
                    size = androidx.compose.ui.geometry.Size(size.toPx(), size.toPx()),
                    style = Stroke(strokeWidth.toPx())
                )

                val sweepAngle = progress * 360f

                drawArc(
                    color = color,
                    startAngle = -90f,
                    sweepAngle = sweepAngle,
                    useCenter = false,
                    topLeft = Offset(centerX - radius, centerY - radius),
                    size = androidx.compose.ui.geometry.Size(size.toPx(), size.toPx()),
                    style = Stroke(strokeWidth.toPx())
                )

            }
        }
        Spacer(modifier = Modifier.height(20.dp))
        Text(text = nomeGrafico, style = tituloConteudoAzul, textAlign = TextAlign.Center, modifier = Modifier.padding(start = 9.dp))
    }
}

@Composable
fun ProgressBar(progress: Float) {
    Canvas(
        modifier = Modifier
            .fillMaxWidth()
            .padding(top = 10.dp, bottom = 10.dp)
            .height(7.dp)
    ) {
        val barHeight = size.height
        val barWidth = size.width * progress
        val cornerRadius = CornerRadius(x = barHeight / 2, y = barHeight / 2)

        drawRoundRect(
            color = linha_divisoria,
            size = size,
            cornerRadius = cornerRadius
        )

        drawRoundRect(
            color = cor_primaria,
            size = size.copy(width = barWidth), // A largura é ajustada ao progresso
            cornerRadius = cornerRadius
        )
    }
}


@Preview(showBackground = true)
@Composable
fun PreviewCircularProgress() {
    Teste()
}

//@Preview(showBackground = true)
//@Composable
//fun MeuProgressoPreview() {
//    AppTheme {
//        Surface {
//            CardFormulario(fotoEmpresa = R.mipmap.card_formulario_social,
//                categoria = "Governança",
//                nomeServico = "Ambiental",
//                nomeEmpresa = "Explicação do formulário breve, talvez sobre o pilar não sei mas deve ser sucinto please amigos e amigas.",
//                onClick = { })
//        }
//    }
//}
