package com.example.ethosconnections.ui.screen.plataforma.components

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.example.ethosconnections.ui.theme.letraPadrao

@Composable
fun OutlinedButtonEthos(acao: () -> Unit, nomeAcao: String) {
    OutlinedButton(
        onClick = { acao() },
        shape = RoundedCornerShape(2.dp),
        border = BorderStroke(2.dp, Color(0xFF01A2C3)),
        modifier = Modifier.padding(end = 8.dp)
    ) {
        Text(
            text = nomeAcao,
            style = letraPadrao,
            color = Color(0xFF01A2C3)
        )
    }
}
