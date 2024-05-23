package com.example.ethosconnections.ui.screen.plataforma

import androidx.compose.foundation.Canvas
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.border
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.drawscope.Fill
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.layout.VerticalAlignmentLine
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.example.ethosconnections.ui.screen.plataforma.components.BoxEthos
import com.example.ethosconnections.ui.theme.tituloConteudoAzul
import com.example.ethosconnections.ui.theme.tituloPagina

@Composable
fun MinhasNegociacoes(componenteNavController: NavHostController) {
    var selectedStatus by remember { mutableStateOf<String?>(null) }

    Column {
        Text(
            text = "Minhas Negociações",
            style = tituloPagina,
            modifier = Modifier.padding(8.dp)
        )

        BoxEthos {
            Column(Modifier.padding(8.dp)) {
                Text(
                    text = "Status das Negociações",
                    style = tituloConteudoAzul,
                    modifier = Modifier.padding(horizontal = 3.dp)
                )
                Spacer(modifier = Modifier.height(13.dp))

                Text(
                    text = "Filtrar por Status:",
                    style = tituloConteudoAzul,
                    modifier = Modifier.padding(horizontal = 16.dp)
                )

                Spacer(modifier = Modifier.height(8.dp))

                StatusFilterRow(
                    filters = listOf("Pendente", "Em Andamento", "Finalizado"),
                    selectedStatus = selectedStatus,
                    onStatusSelected = { selectedStatus = it }
                )
            }
        }
    }
}

@Composable
fun StatusFilterRow(
    filters: List<String>,
    selectedStatus: String?,
    onStatusSelected: (String) -> Unit
) {
    Column(Modifier.padding(horizontal = 16.dp)) {
        for (filter in filters) {
            StatusFilterButton(
                text = filter,
                isSelected = filter == selectedStatus,
                onStatusSelected = { selectedStatus ->
                    onStatusSelected(selectedStatus)
                }
            )
        }
    }
}

@Composable
fun StatusFilterButton(
    text: String,
    isSelected: Boolean,
    onStatusSelected: (String) -> Unit
) {
    Column(

        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier.clickable { onStatusSelected(text) }
    ) {
        Canvas(modifier = Modifier.size(24.dp)) {
            drawCircle(
                color = if (isSelected) Color.Blue else Color.Transparent,
                style = Fill
            )
            drawCircle(
                color = Color.Blue,
                style = Stroke(width = 2.dp.toPx())
            )
        }
        Spacer(modifier = Modifier.width(8.dp))
        Text(
            text = text,
            style = tituloConteudoAzul,
            color = if (isSelected) Color.Blue else Color.Black
        )
    }
}

@Preview(showBackground = true)
@Composable
fun MinhasNegociacoesPreview() {
    MinhasNegociacoes(rememberNavController())
}
