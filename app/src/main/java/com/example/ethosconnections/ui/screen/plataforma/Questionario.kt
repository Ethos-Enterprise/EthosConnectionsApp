package com.example.ethosconnections.ui.screen.plataforma

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.compose.cor_primaria
import com.example.ethosconnections.R
import com.example.ethosconnections.datastore.EmpresaDataStore
import com.example.ethosconnections.ui.screen.plataforma.components.BoxEthos
import com.example.ethosconnections.ui.screen.plataforma.components.FillButtonEthos
import com.example.ethosconnections.ui.screen.plataforma.components.OutlinedButtonEthos
import com.example.ethosconnections.ui.theme.letraButton
import com.example.ethosconnections.ui.theme.letraPadrao
import com.example.ethosconnections.ui.theme.tituloConteudoBranco
import com.example.ethosconnections.viewmodel.progresso.ProgressoViewModel

data class Opcao(
    val id: String,
    val texto: String,
    val valor: Int
)

data class Pergunta(
    val id: Int,
    val pergunta: String,
    val opcoes: List<Opcao>,
    val categoria: String,
    val tema: String
)

@Composable
fun Questionario(
    navController: NavController,
    progressoViewModel: ProgressoViewModel,
    categoria: String,
    empresaDataStore: EmpresaDataStore
) {
    var selectedOption by remember { mutableStateOf<Int?>(null) }
    var perguntaAtual by remember { mutableStateOf(0) }
    var mostrarDialogo by remember { mutableStateOf(false) }
    var respostas by remember { mutableStateOf(mutableListOf<Int?>()) }
    var errorMsg by remember { mutableStateOf<String?>(null) }

    val perguntas = listOf(
        Pergunta(
            id = 1,
            pergunta = "Em relação à gestão de resíduos sólidos:",
            opcoes = listOf(
                Opcao(
                    "a",
                    "A empresa não possui políticas ou práticas documentadas para lidar com resíduos",
                    2
                ),
                Opcao("b", "A empresa tem políticas, mas raramente as implementa", 4),
                Opcao("c", "A empresa implementa políticas de maneira consistente", 6),
                Opcao("d", "A empresa tem um bom controle e recicla resíduos regularmente", 8),
                Opcao("e", "A empresa possui um sistema eficiente de gestão de resíduos", 10)
            ),
            categoria = "Ambiental",
            tema = "Gestão de Resíduos Sólidos"
        ),
        Pergunta(
            id = 2,
            pergunta = "Quanto ao uso de recursos naturais:",
            opcoes = listOf(
                Opcao("a", "A empresa não monitora o uso de recursos naturais", 2),
                Opcao(
                    "b",
                    "A empresa faz monitoramento, mas não adota medidas para reduzir o consumo",
                    4
                ),
                Opcao("c", "A empresa adota medidas esporádicas para reduzir o consumo", 6),
                Opcao(
                    "d",
                    "A empresa possui políticas efetivas de redução de consumo de recursos naturais",
                    8
                ),
                Opcao("e", "A empresa é líder na conservação de recursos naturais", 10)
            ),
            categoria = "ambiental",
            tema = "Uso de Recursos Naturais"
        ),
        Pergunta(
            id = 3,
            pergunta = "Sobre emissões de gases de efeito estufa:",
            opcoes = listOf(
                Opcao("a", "A empresa não rastreia emissões de gases de efeito estufa", 2),
                Opcao("b", "A empresa rastreia, mas não estabelece metas de redução", 4),
                Opcao(
                    "c",
                    "A empresa estabelece metas de redução, mas não as alcança consistentemente",
                    6
                ),
                Opcao("d", "A empresa atinge suas metas de redução de emissões", 8),
                Opcao(
                    "e",
                    "A empresa é carbono neutro e tem estratégias para combater as emissões",
                    10
                )
            ),
            categoria = "ambiental",
            tema = "Emissões de Gases de Efeito Estufa"
        ),
        Pergunta(
            id = 4,
            pergunta = "Quanto ao uso de energias renováveis:",
            opcoes = listOf(
                Opcao("a", "A empresa não utiliza energias renováveis", 2),
                Opcao("b", "A empresa utiliza energias renováveis de forma limitada", 4),
                Opcao(
                    "c",
                    "A empresa investe em energias renováveis, mas não é uma parte significativa de sua matriz energética",
                    6
                ),
                Opcao(
                    "d",
                    "A empresa usa energias renováveis como parte substancial de sua matriz energética",
                    8
                ),
                Opcao("e", "A empresa é 100% alimentada por energias renováveis", 10)
            ),
            categoria = "ambiental",
            tema = "Uso de Energias Renováveis"
        ),
        Pergunta(
            id = 5,
            pergunta = "Sobre a conservação da biodiversidade:",
            opcoes = listOf(
                Opcao("a", "A empresa não tem iniciativas para proteger a biodiversidade", 2),
                Opcao(
                    "b",
                    "A empresa apoia iniciativas esporádicas para a conservação da biodiversidade",
                    4
                ),
                Opcao(
                    "c",
                    "A empresa apoia ativamente projetos de conservação da biodiversidade",
                    6
                ),
                Opcao(
                    "d",
                    "A empresa tem parcerias significativas com organizações de conservação da biodiversidade",
                    8
                ),
                Opcao(
                    "e",
                    "A empresa lidera esforços significativos na preservação da biodiversidade",
                    10
                )
            ),
            categoria = "ambiental",
            tema = "Conservação da Biodiversidade"
        ),
        Pergunta(
            id = 6,
            pergunta = "Quanto à educação e conscientização ambiental dos funcionários:",
            opcoes = listOf(
                Opcao("a", "A empresa não oferece treinamento ou conscientização ambiental", 2),
                Opcao(
                    "b",
                    "A empresa oferece treinamento, mas raramente promove conscientização",
                    4
                ),
                Opcao("c", "A empresa promove conscientização, mas de forma esporádica", 6),
                Opcao(
                    "d",
                    "A empresa promove conscientização regularmente entre os funcionários",
                    8
                ),
                Opcao(
                    "e",
                    "A empresa tem programas de educação e conscientização ambiental robustos",
                    10
                )
            ),
            categoria = "ambiental",
            tema = "Educação e Conscientização Ambiental dos Funcionários"
        ),
        Pergunta(
            id = 7,
            pergunta = "Em relação à transparência e divulgação de informações ambientais:",
            opcoes = listOf(
                Opcao("a", "A empresa não divulga informações sobre seu desempenho ambiental", 2),
                Opcao("b", "A empresa divulga informações limitadas de forma irregular", 4),
                Opcao(
                    "c",
                    "A empresa divulga informações ambientais regularmente, mas de maneira superficial",
                    6
                ),
                Opcao(
                    "d",
                    "A empresa divulga informações detalhadas sobre seu desempenho ambiental",
                    8
                ),
                Opcao(
                    "e",
                    "A empresa é líder em transparência e divulgação de informações ambientais",
                    10
                )
            ),
            categoria = "ambiental",
            tema = "Transparência e Divulgação de Informações Ambientais"
        ),
        Pergunta(
            id = 8,
            pergunta = "Sobre a gestão de riscos ambientais:",
            opcoes = listOf(
                Opcao("a", "A empresa não avalia ou gerencia riscos ambientais", 2),
                Opcao(
                    "b",
                    "A empresa realiza avaliações de risco, mas não implementa ações preventivas",
                    4
                ),
                Opcao("c", "A empresa implementa ações preventivas de forma esporádica", 6),
                Opcao(
                    "d",
                    "A empresa implementa ações preventivas e possui um plano de contingência sólido",
                    8
                ),
                Opcao("e", "A empresa é altamente resiliente a riscos ambientais", 10)
            ),
            categoria = "ambiental",
            tema = "Gestão de Riscos Ambientais"
        ),
        Pergunta(
            id = 9,
            pergunta = "Quanto ao envolvimento com a comunidade local em questões ambientais:",
            opcoes = listOf(
                Opcao("a", "A empresa não se envolve em questões ambientais da comunidade", 2),
                Opcao(
                    "b",
                    "A empresa fornece assistência esporádica em projetos ambientais locais",
                    4
                ),
                Opcao("c", "A empresa é um parceiro ativo em projetos ambientais da comunidade", 6),
                Opcao("d", "A empresa lidera esforços em projetos ambientais locais", 8),
                Opcao(
                    "e",
                    "A empresa é um pilar na promoção de questões ambientais na comunidade",
                    10
                )
            ),
            categoria = "ambiental",
            tema = "Envolvimento com a Comunidade Local em Questões Ambientais"
        ),
        Pergunta(
            id = 10,
            pergunta = "Sobre a inovação e pesquisa em tecnologias sustentáveis:",
            opcoes = listOf(
                Opcao(
                    "a",
                    "A empresa não investe em inovação sustentável ou pesquisa ambiental",
                    2
                ),
                Opcao("b", "A empresa investe esporadicamente em inovação sustentável", 4),
                Opcao("c", "A empresa investe consistentemente em inovação sustentável", 6),
                Opcao(
                    "d",
                    "A empresa é um pioneiro em pesquisa e desenvolvimento de tecnologias sustentáveis",
                    8
                ),
                Opcao("e", "A empresa lidera a indústria em inovação e pesquisa sustentável", 10)
            ),
            categoria = "ambiental",
            tema = "Inovação e Pesquisa em Tecnologias Sustentáveis"
        )
    )

    val mensagemErro = stringResource(R.string.mensagem_erro_selecionar_opcao)

    Column {
        FillButtonEthos(
            acao = { navController.navigate("meuProgresso") },
            nomeAcao = stringResource(R.string.botao_continuar_depois)
        )

        BoxEthos {
            Image(
                painter = painterResource(id = R.mipmap.capa_formulario_ambiental),
                contentDescription = "Logo Deloitte",
                modifier = Modifier
                    .fillMaxWidth()
                    .fillMaxHeight(0.6f)
                    .clip(RoundedCornerShape(5.dp)),
            )

            Spacer(modifier = Modifier.height(14.dp))

            Text("${stringResource(R.string.titulo_pagina_questionario)} $categoria" , style = tituloConteudoBranco)
            Spacer(modifier = Modifier.height(14.dp))

            if (perguntaAtual < perguntas.size) {
                val pergunta = perguntas[perguntaAtual]
                Text("${perguntaAtual+1} - ${pergunta.pergunta}", style = tituloConteudoBranco, )

                pergunta.opcoes.forEachIndexed { index, opcao ->
                    Row(
                        verticalAlignment = Alignment.CenterVertically,
                        modifier = Modifier
                            .fillMaxWidth()
                            .clickable {
                                selectedOption = index
                                errorMsg = null
                            }
                            .padding(vertical = 8.dp)
                    ) {
                        RadioButton(
                            selected = selectedOption == index,
                            onClick = { selectedOption = index },
                            colors = RadioButtonDefaults.colors(selectedColor = cor_primaria)
                        )
                        Text(text = opcao.texto, modifier = Modifier.padding(start = 8.dp), style = letraPadrao)
                    }
                }


                errorMsg?.let {
                    Text(it, color = Color.Red, style = MaterialTheme.typography.bodySmall)
                }

                Row(
                    horizontalArrangement = Arrangement.SpaceAround ,
                    modifier = Modifier.fillMaxWidth()
                ) {

                    Button(
                        onClick = {
                            if (perguntaAtual > 0) {
                                perguntaAtual--
                                selectedOption = null
                                errorMsg = null
                            }
                        },
                        enabled = perguntaAtual > 0,
                        shape = RoundedCornerShape(5.dp),
                        colors = ButtonDefaults.buttonColors(
                            Color(0xFF01A2C3)
                        )
                    ) {
                        Text(
                            text = stringResource(R.string.anterior),
                            style = letraButton
                        )
                    }

                    OutlinedButton(
                        onClick = {
                            if (selectedOption != null) {
                                respostas.add(perguntas[perguntaAtual].opcoes[selectedOption!!].valor)
                                if (perguntaAtual < perguntas.size - 1) {
                                    perguntaAtual++
                                    selectedOption = null
                                    errorMsg = null
                                } else {
                                    mostrarDialogo = true
                                }
                            } else {
                                errorMsg = mensagemErro
                            }
                        },
                        enabled = selectedOption != null || errorMsg != null,
                        shape = RoundedCornerShape(5.dp),
                        border = BorderStroke(2.dp, Color(0xFF01A2C3)),
                        modifier = Modifier.padding(end = 8.dp)
                    ) {
                        Text(
                            text = stringResource(R.string.proximo),
                            style = letraButton,
                            color = Color(0xFF01A2C3)
                        )
                    }

                }

            }
        }


        if (mostrarDialogo) {
            AlertDialog(
                onDismissRequest = { mostrarDialogo = false },
                confirmButton = {
                    Button(onClick = {
                        progressoViewModel.atualizarProgresso(
                            categoria,
                            respostas.filterNotNull().sum()
                        )
                        mostrarDialogo = false
                        navController.navigate("MeuProgresso")
                    }) {
                        Text("OK")
                    }
                },
                title = { Text("${stringResource(R.string.titulo_dialogo_questionario_concluido)}") },
                text = { Text("${stringResource(R.string.mensagem_dialogo_questionario_concluido)}") })

        }
    }
}

