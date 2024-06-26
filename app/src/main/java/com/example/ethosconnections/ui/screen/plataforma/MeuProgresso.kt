package com.example.ethosconnections.ui.screen.plataforma

import android.util.Log
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
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Divider
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.compose.AppTheme
import com.example.compose.card_formulario
import com.example.compose.cor_primaria
import com.example.compose.cor_secundaria
import com.example.compose.linha_divisoria
import com.example.ethosconnections.R
import com.example.ethosconnections.datastore.EmpresaDataStore
import com.example.ethosconnections.ui.screen.plataforma.components.BoxEthos
import com.example.ethosconnections.ui.screen.plataforma.components.FillButtonEthos
import com.example.ethosconnections.ui.screen.plataforma.components.OutlinedButtonEthos
import com.example.ethosconnections.ui.theme.letraButton
import com.example.ethosconnections.ui.theme.letraDescricao
import com.example.ethosconnections.ui.theme.letraPadrao
import com.example.ethosconnections.ui.theme.letraPadraoExtraLigth
import com.example.ethosconnections.ui.theme.tituloConteudoAzul
import com.example.ethosconnections.ui.theme.tituloConteudoBranco
import com.example.ethosconnections.ui.theme.tituloFormularioCard
import com.example.ethosconnections.ui.theme.tituloPagina
import com.example.ethosconnections.viewmodel.meta.MetaViewModel
import com.example.ethosconnections.viewmodel.progresso.ProgressoViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import java.util.UUID

@Composable
fun MeuProgresso(
    navController: NavController,
    progressoViewModel: ProgressoViewModel,
    metaViewModel: MetaViewModel,
    empresaDataStore: EmpresaDataStore
) {
    val progressoTotal = progressoViewModel.progressoTotal.value
    val progressoAmbiental = progressoViewModel.progressoAmbiental.value
    val progressoSocial = progressoViewModel.progressoSocial.value
    val progressoGovernamental = progressoViewModel.progressoGovernamental.value
    val empresa by empresaDataStore.getEmpresaFlow().collectAsState(initial = null)

    var token: String? = null

    LaunchedEffect(key1 = empresa) {
        empresa?.let {
            val retrievedToken = empresaDataStore.getToken()
            token = retrievedToken
            val idEmpresa = it.id
            if (idEmpresa != null) {
                metaViewModel.getAllMetas(token.toString())
                Log.d("MeuProgresso", "ID da Empresa: $idEmpresa")
            }
        }
    }

    val metas = metaViewModel.allMetas.value
    val idEmpresaAtual = empresa?.id
    Log.d("MeuProgresso", "ID da Empresa Atual: $idEmpresaAtual")
    val filteredMetas = metas?.filter { it.fkEmpresa == idEmpresaAtual }
    Log.d("MeuProgresso", "Metas Filtradas: ${filteredMetas?.size}")

    var mostrarDialogo by remember { mutableStateOf(false) }

    Column {

        Text(stringResource(R.string.titulo_pagina_meu_progresso), style = tituloPagina)
        BoxEthos {
            Text(stringResource(R.string.subtitulo_meu_progresso), style = tituloConteudoAzul)
            Divider(modifier = Modifier.padding(bottom = 10.dp))
            Text(stringResource(R.string.titulo_grafico_meu_progresso), style = tituloConteudoBranco)
            Spacer(modifier = Modifier.height(16.dp))
            ProgressBarWithNumber(progress = progressoTotal.toFloat() / 100f)
            Spacer(modifier = Modifier.height(16.dp))

            Text(stringResource(R.string.subtitulo_grafico_meu_progresso), style = tituloConteudoBranco)
            Spacer(modifier = Modifier.height(16.dp))

            Row(
                horizontalArrangement = Arrangement.SpaceBetween,
                modifier = Modifier.fillMaxWidth()
            ) {
                CircularProgress(
                    nomeGrafico = stringResource(R.string.txt_area_e),
                    progress = progressoAmbiental.toFloat() / 100f
                )
                CircularProgress(
                    nomeGrafico = stringResource(R.string.txt_area_s),
                    progress = progressoSocial.toFloat() / 100f
                )
                CircularProgress(
                    nomeGrafico = stringResource(R.string.txt_area_g),
                    progress = progressoGovernamental.toFloat() / 100f
                )
            }
        }
        BoxEthos {
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
                ) {
                Text(text = stringResource(R.string.minha_meta), style = tituloConteudoAzul)
                Box(
                    modifier = Modifier
                        .width(100.dp)
                        .height(25.dp)
                        .clickable { navController.navigate("meta") }
                        .background(cor_primaria, shape = RoundedCornerShape(5.dp)),
                    contentAlignment = Alignment.Center
                ) {
                    Text(
                        text = stringResource(R.string.criar_meta),
                        style = letraButton
                    )
                }

            }
            Spacer(modifier = Modifier.height(5.dp))
            Divider(modifier = Modifier.padding(bottom = 10.dp))

            Column(
                modifier = Modifier.fillMaxWidth()
            ) {
                if (filteredMetas.isNullOrEmpty()) {
                    Box(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(8.dp)
                    ) {
                        Column(
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(8.dp),
                            horizontalAlignment = Alignment.CenterHorizontally
                        ) {
                            Image(
                                painter = painterResource(id = R.mipmap.meta),
                                contentDescription = "icone meta",
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .fillMaxHeight(0.6f)
                            )
                            Text(
                                stringResource(R.string.mensagem_meta_meu_progresso),
                                style = letraPadraoExtraLigth,
                                textAlign = TextAlign.Center,
                                modifier = Modifier.padding(top = 16.dp)
                            )
                        }
                    }
                } else {
                    filteredMetas.forEach { meta ->
                        Box(
                            modifier = Modifier
                                .fillMaxWidth()
                                .background(
                                    color = linha_divisoria,
                                    shape = RoundedCornerShape(5.dp)
                                )
                                .padding(10.dp)
                        ) {
                            Column {
                                Text(
                                    text = "${stringResource(R.string.txt_pilar)} ${meta.pilarEsg.toString()}",
                                    style = tituloConteudoBranco
                                )
                                
                                Text(text = "${meta.fkEmpresa}"
                                , style = letraPadrao)
                                Text(text = meta.descricao.toString(), style = letraDescricao)
                                Row(
                                    modifier = Modifier.fillMaxWidth(),
                                    verticalAlignment = Alignment.CenterVertically

                                ){
                                    Text(text = stringResource(R.string.data_limite), style = tituloConteudoBranco)
                                    Text(text =  meta.dataFim.toString(), style = letraDescricao)
                                }
                                Spacer(modifier = Modifier.height(5.dp))

                                Row(
                                    horizontalArrangement = Arrangement.SpaceAround,
                                    modifier = Modifier.fillMaxWidth()
                                ) {
                                    OutlinedButtonEthos(
                                        acao = {
                                            val metaIdConvertido: UUID = meta.id ?: UUID.randomUUID()
                                            CoroutineScope(Dispatchers.Main).launch {
                                                val token = empresaDataStore.getToken()
                                                metaViewModel.deleteMeta(metaIdConvertido, token) { sucesso ->
                                                    if (sucesso) {
                                                        mostrarDialogo = true
                                                    }
                                                }
                                            }
                                        },
                                        nomeAcao = stringResource(R.string.excluir_meta)
                                    )
                                    FillButtonEthos(
                                        acao = { /*TODO*/ },
                                        nomeAcao = stringResource(R.string.ver_servicos)
                                    )
                                }

                        }
                        Spacer(modifier = Modifier.height(4.dp))
                    }

                    }
                }
            }
        }

        BoxEthos {
            Text(stringResource(R.string.titulo_formulario_meu_progresso), style = tituloConteudoAzul)
            Divider(modifier = Modifier.padding(bottom = 10.dp))
            CardFormulario(fotoEmpresa = R.mipmap.card_formulario_ambiental,
                categoria = stringResource(R.string.txt_area_e),
                nomeServico = stringResource(R.string.txt_area_e),
                nomeEmpresa = stringResource(R.string.formulario_ambiental_meu_progresso),
                onClick = { navController.navigate("formulario/Ambiental") }
            )

            CardFormulario(fotoEmpresa = R.mipmap.card_formulario_social,
                categoria = stringResource(R.string.txt_area_s),
                nomeServico = stringResource(R.string.txt_area_s),
                nomeEmpresa = stringResource(R.string.formulario_social_meu_progresso),
                onClick = { navController.navigate("formulario/Social") }
            )

            CardFormulario(fotoEmpresa = R.mipmap.card_formulario_governamental,
                categoria = stringResource(R.string.txt_area_g),
                nomeServico = stringResource(R.string.txt_area_g),
                nomeEmpresa = stringResource(R.string.formulario_governamental_meu_progresso),
                onClick = { navController.navigate("formulario/Governamental") }
            )
        }
    }

    if (mostrarDialogo) {
        AlertDialog(
            onDismissRequest = { mostrarDialogo = false },
            confirmButton = {
                Button(onClick = {

                    mostrarDialogo = false
                }) {
                    Text(stringResource(R.string.txt_button_ok))
                }
            },
            title = { Text(stringResource(R.string.meta_excluida))},
            text = { Text(stringResource(R.string.meta_excluida_sucesso))})

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
                        .fillMaxHeight(0.3f)
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
                        text = stringResource(R.string.nao_iniciado),
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
                                    text = stringResource(R.string.area_de_impacto),
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
                                text = stringResource(R.string.pontuacao),
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
fun CircularProgress(
    progress: Float,
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
            contentAlignment = Alignment.Center,
            modifier = Modifier.size(size)
        ) {
            Canvas(
                modifier = Modifier.matchParentSize()
            ) {
                val centerX = size.toPx() / 2
                val centerY = size.toPx() / 2
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

            Text(
                text = progressText,
                style = tituloConteudoAzul,
                textAlign = TextAlign.Center,
                modifier = Modifier
                    .align(Alignment.Center)
                    .padding(start = 10.dp, top = 5.dp)
            )
        }
        Text(
            text = nomeGrafico,
            style = tituloConteudoAzul,
            textAlign = TextAlign.Center,
            modifier = Modifier.padding(top = 12.dp, start = 2.dp)
        )
    }
}

@Composable
fun ProgressBarWithNumber(progress: Float) {
    val paddingValue = if (progress > 0) progress - 0.05f else 0f

    Column {

        Row {
            Spacer(modifier = Modifier.fillMaxWidth(paddingValue))
            Box(
                modifier = Modifier
                    .width(40.dp)
                    .height(25.dp)
                    .background(color = cor_secundaria, shape = RoundedCornerShape(percent = 10)),
                contentAlignment = Alignment.Center
            ) {
                Text(
                    text = "${(progress * 100).toInt()}%",
                    style = TextStyle(color = Color.White),
                    textAlign = TextAlign.Center,
                )
            }

        }
        Spacer(modifier = Modifier.height(10.dp))
        Box(
            contentAlignment = Alignment.Center,
            modifier = Modifier.fillMaxWidth()
        ) {
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(10.dp)
                    .background(color = linha_divisoria, shape = RoundedCornerShape(percent = 50))
            ) {
                Box(
                    modifier = Modifier
                        .fillMaxWidth(progress)
                        .height(16.dp)
                        .background(color = cor_primaria, shape = RoundedCornerShape(percent = 50))
                )
            }
        }
    }
}

@Composable
@Preview(showBackground = true)
fun PreviewCardFormulario() {
    AppTheme {
        CircularProgress(progress = 0.75f, nomeGrafico = "Exemplo")

    }
}
