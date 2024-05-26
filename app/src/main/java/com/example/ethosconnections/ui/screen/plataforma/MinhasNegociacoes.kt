package com.example.ethosconnections.ui.screen.plataforma

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.compose.cor_primaria
import com.example.ethosconnections.datastore.EmpresaDataStore
import com.example.ethosconnections.ui.screen.plataforma.components.BoxEthos
import com.example.ethosconnections.ui.theme.letraPadrao
import com.example.ethosconnections.ui.theme.tituloConteudoAzul
import com.example.ethosconnections.ui.theme.tituloConteudoBranco

data class Negociacao(
    val id: String,
    val texto: String
)

@Composable
fun MinhasNegociacoes(navController: NavController, empresaDataStore: EmpresaDataStore) {
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
    val titulosColunas = listOf("Nome da Empresa", "Serviço", "Data de Contato", "Status Atual")

    Column(modifier = Modifier.padding(2.dp)) {
        Text(
            text = "Minhas Negociações",
            style = tituloConteudoBranco,
            modifier = Modifier.padding(8.dp)
        )
        BoxEthos {
            Text(
                text = "Status das Negociações",
                style = tituloConteudoAzul,
                modifier = Modifier.padding(2.dp)
            )
            Column(modifier = Modifier.padding(16.dp)) {
                negociacoes.forEach { negociacao ->
                    Row(
                        verticalAlignment = Alignment.CenterVertically,
                        modifier = Modifier
                            .clickable {
                                selectedNegociacao = negociacao.id
                            }
                            .padding(vertical = 1.dp)
                    ) {
                        RadioButton(
                            selected = selectedNegociacao == negociacao.id,
                            onClick = { selectedNegociacao = negociacao.id },
                            colors = RadioButtonDefaults.colors(selectedColor = cor_primaria)
                        )
                        Text(
                            text = negociacao.texto,
                            modifier = Modifier.padding(start = 1.dp),
                            style = letraPadrao
                        )
                    }
                }
            }
        }

        Spacer(modifier = Modifier.height(10.dp))

        Text(
            text = "Controle de Negociações",
            style = tituloConteudoBranco,
            modifier = Modifier.padding(2.dp)
        )

        BoxEthos {
            Column(modifier = Modifier.padding(1.dp)) {
                // Títulos das colunas
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceEvenly,
                    verticalAlignment = Alignment.CenterVertically,
                ) {
                    titulosColunas.forEach { titulo ->
                        Text(
                            text = titulo,
                            style = tituloConteudoBranco,
                            modifier = Modifier.padding(horizontal = 2.dp)
                        )
                    }
                }
                Divider(modifier = Modifier.padding(bottom = 10.dp))

                // Dados da tabela
                dadosTabela.forEach { rowData ->
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.SpaceBetween,
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        rowData.forEach { cellData ->
                            Text(
                                text = cellData,
                                modifier = Modifier.padding(horizontal = 8.dp)
                            )
                        }
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
