package com.example.ethosconnections.ui.screen.plataforma

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.snapshots.SnapshotStateList
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.compose.cor_primaria
import com.example.compose.preto_azulado
import com.example.ethosconnections.R
import com.example.ethosconnections.datastore.EmpresaDataStore
import com.example.ethosconnections.ui.screen.plataforma.components.BoxEthos
import com.example.ethosconnections.ui.theme.letraPadrao
import com.example.ethosconnections.ui.theme.tituloConteudoAzul
import com.example.ethosconnections.ui.theme.tituloConteudoBranco
import com.example.ethosconnections.viewmodel.interacao.InteracaoViewModel
import kotlinx.coroutines.flow.first

data class Negociacao(
    val id: String,
    val texto: String
)

@Composable
fun MinhasNegociacoes(
    navController: NavController,
    empresaDataStore: EmpresaDataStore,
    interacaoViewModel: InteracaoViewModel
) {
    LaunchedEffect(key1 = true) {
        val empresaAtual = empresaDataStore.getEmpresaFlow().first()

        empresaAtual?.idPrestadora?.let { idPrestadora ->
            interacaoViewModel.getInteracoesServicos(idPrestadora, empresaDataStore.getToken())
        } ?: run {
            println("ID Prestadora é nulo")
        }
    }

    var interacoes = remember { interacaoViewModel.interacoes }.observeAsState(SnapshotStateList())

    val negociacoes = listOf(
        Negociacao(id = "1", texto = "Em andamento"),
        Negociacao(id = "2", texto = "Pendente"),
        Negociacao(id = "3", texto = "Finalizada")
    )
    val dadosTabela = listOf(
        listOf("Empresa A", "Serviço A", "01/05/2024", "Em andamento"),
        listOf("Empresa B", "Serviço B", "02/05/2024", "Pendente"),
        listOf("Empresa C", "Serviço C", "03/05/2024", "Finalizado")
    )
    var selectedNegociacao by remember { mutableStateOf<String?>(null) }
    val titulosColunas = listOf("Empresa", "Serviço", "Data de Contato", "Status Atual")

    // Filtra os dados conforme o status selecionado
    val dadosFiltrados = remember(selectedNegociacao) {
        if (selectedNegociacao == null) {
            dadosTabela
        } else {
            dadosTabela.filter { it[3] == negociacoes.first { it.id == selectedNegociacao }.texto }
        }
    }

    Column(
        modifier = Modifier
            .padding(2.dp)
            .fillMaxWidth()
    ) {
        Text(
            stringResource(R.string.titulo_Minhas_Nego),
            style = tituloConteudoBranco,
        )
        BoxEthos {
            Text(
                stringResource(R.string.status_negociacoes),
                style = tituloConteudoAzul,
                modifier = Modifier.padding(2.dp)
            )
            Column {
                negociacoes.forEach { negociacao ->
                    Row(
                        verticalAlignment = Alignment.CenterVertically,
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(vertical = 4.dp)
                            .clickable {
                                selectedNegociacao = negociacao.id
                            }
                    ) {
                        RadioButton(
                            selected = selectedNegociacao == negociacao.id,
                            onClick = { selectedNegociacao = negociacao.id },
                            colors = RadioButtonDefaults.colors(selectedColor = cor_primaria)
                        )
                        Text(
                            text = negociacao.texto,
                            style = letraPadrao,
                            modifier = Modifier.padding(start = 8.dp)
                        )
                    }
                }
            }
        }

        Spacer(modifier = Modifier.height(10.dp))
        Text(
            stringResource(R.string.controle_Negociacoes),
            style = tituloConteudoBranco,
            modifier = Modifier.padding(2.dp)
        )

        Column(
            modifier = Modifier
                .padding(1.dp)
                .fillMaxWidth()
        ) {

            dadosFiltrados.forEach { rowData ->
                Card(
                    modifier = Modifier
                        .padding(vertical = 8.dp)
                        .fillMaxWidth(),
                    shape = RoundedCornerShape(10.dp),
                    colors = CardDefaults.cardColors(
                        containerColor = preto_azulado,
                        contentColor = Color.White
                    ),
                    elevation = CardDefaults.cardElevation(8.dp)
                ) {
                    Column(modifier = Modifier.padding(16.dp)) {
                        Text(
                            text = rowData[0],
                            style = tituloConteudoAzul,
                            modifier = Modifier.padding(bottom = 8.dp)
                        )
                        Divider(color = Color.LightGray, thickness = 1.dp)
                        Spacer(modifier = Modifier.height(8.dp))
                        Text(
                            text = "${titulosColunas[1]}: ${rowData[1]}",
                            style = letraPadrao,
                            modifier = Modifier.padding(bottom = 8.dp)
                        )
                        Text(
                            text = "${titulosColunas[2]}: ${rowData[2]}",
                            style = letraPadrao,
                            modifier = Modifier.padding(bottom = 8.dp)
                        )
                        Text(
                            text = "${titulosColunas[3]}: ${rowData[3]}",
                            style = letraPadrao,
                        )
                    }
                }
            }
        }
    }
}
@Preview(showBackground = true)
@Composable
fun MinhasNegociacoesPreview() {
    // MinhasNegociacoes()
}
