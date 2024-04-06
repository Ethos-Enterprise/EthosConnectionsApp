package com.example.ethosconnections.ui.screen.plataforma.components

import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.ethosconnections.ui.theme.letraButton

@Composable
fun FillButtonEthos(acao: () -> Unit, nomeAcao: String) {
    Button(
        onClick = { acao()},
        shape = RoundedCornerShape(2.dp),
    ) {
        Text(
            text = "${nomeAcao}",
            style = letraButton
        )
    }
}