package com.example.ethosconnections.ui.screen.plataforma

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.example.ethosconnections.ui.screen.plataforma.components.BoxEthos
import com.example.ethosconnections.ui.theme.tituloConteudoBranco
import com.example.ethosconnections.ui.theme.tituloConteudoBrancoNegrito
import com.example.ethosconnections.ui.theme.tituloPagina

@Composable
fun MinhasInteracoes(navController: NavController){
    Column {

        Text(text = "Minhas interações", style = tituloPagina)

        BoxEthos{
            Text(text = "Categorias de interações", style = tituloConteudoBranco)

        }

        Text(text = "Empresas Contatadas", style = tituloPagina)

        BoxEthos{

            Row {


                Row() {
                    Text(text = "Em andamento: ", style = tituloConteudoBrancoNegrito)
                    Text(text = "1 empresas", style = tituloConteudoBranco)

                }

                Row() {
                    Text(text = "Finalizado: ", style = tituloConteudoBrancoNegrito)
                    Text(text = "1 empresas", style = tituloConteudoBranco)

                }
            }
        }

        BoxEthos{
            Text(text = "Box 1", style = tituloConteudoBranco)
        }

        BoxEthos{
            Text(text = "Box 2", style = tituloConteudoBranco)

        }
    }
}


@Preview(showBackground = true)
@Composable
fun MinhasInteracoesPreview() {
    val navController = rememberNavController()
    MinhasInteracoes(navController)
}