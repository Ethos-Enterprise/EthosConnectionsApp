package com.example.ethosconnections.ui.screen.plataforma.components

import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.example.ethosconnections.ui.theme.letraButton

@Composable
fun FillButtonEthos(acao: () -> Unit, nomeAcao: String) {
    Button(
        onClick = { acao()},
        shape = RoundedCornerShape(5.dp),
        colors = ButtonDefaults.buttonColors(
            Color(0xFF01A2C3)
        )
    ) {
        Text(
            text = "${nomeAcao}",
            style = letraButton
        )
    }
}