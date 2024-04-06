package com.example.ethosconnections.ui.screen.plataforma.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp

@Composable
fun BoxEthos(content: @Composable () -> Unit) {

    Box(
        modifier = Modifier
            .fillMaxWidth()
            .padding(top = 8.dp)
            .background(color = Color(0xFF1B1F23), shape = RoundedCornerShape(5.dp))
            .padding(15.dp)

    ){

    Column {

        content()
    }
    }

}
