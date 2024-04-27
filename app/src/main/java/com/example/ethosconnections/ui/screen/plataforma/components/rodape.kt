package com.example.ethosconnections.ui.screen.plataforma.components

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.example.ethosconnections.ui.theme.letraButton

@Composable
fun Rodape(
    nomeBotaoEsquerda: String,
    nomeBotaoDireita: String
) {
    Spacer(modifier = Modifier.height(14.dp))

    // Rodapé
    val color = Color(0xFF020202)

    Box(
        modifier = Modifier
            .fillMaxWidth()
            .background(color) // Adicionando retângulo por trás dos botões
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Button(
                onClick = { /* TODO */ },
                modifier = Modifier
                    .weight(1f)
                    .padding(horizontal = 10.dp),
                shape = RoundedCornerShape(5.dp),
            ) {
                Text(
                    text = nomeBotaoEsquerda,
                    style = letraButton
                )
            }

            OutlinedButton(
                onClick = { /* TODO */ },
                modifier = Modifier
                    .weight(1f)
                    .padding(horizontal = 10.dp),
                shape = RoundedCornerShape(5.dp),
                border = BorderStroke(2.dp, Color(0xFF01A2C3)),
            ) {
                Text(
                    text = nomeBotaoDireita,
                    style = letraButton,
                    color = Color(0xFF01A2C3)
                )
            }
        }
    }
}
