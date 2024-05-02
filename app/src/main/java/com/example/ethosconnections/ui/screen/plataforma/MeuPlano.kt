package com.example.ethosconnections.ui.screen.plataforma

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Divider
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.example.ethosconnections.ui.screen.plataforma.components.BoxEthos
import com.example.ethosconnections.ui.theme.tituloConteudoAzul
import com.example.ethosconnections.ui.theme.tituloConteudoBranco
import com.example.ethosconnections.ui.theme.tituloConteudoBrancoNegrito
import com.example.ethosconnections.ui.theme.tituloPagina

@Composable
fun MeuPlano(navController: NavController) {
    Column {
        Text(text = "Meu Plano", style = tituloPagina)

        BoxEthos {
            Column {
                Text(text = "Plano atual", style = tituloPagina)

                Column(
                    modifier = Modifier
                        .padding(2.dp)
                        .background(Color.Transparent)
                        .border(1.dp, Color.White, shape = RoundedCornerShape(5.dp))
                        .padding(8.dp) // Adiciona padding interno
                ){
                    Column {
                        Text(text = "Plano Free", style = tituloConteudoAzul)
                        Divider(color = Color.White, thickness = 1.dp, modifier = Modifier.fillMaxWidth())
                        Text(text = "Ideal para buscar serviços para sua empresa.", style = tituloConteudoBranco)
                        Text(text = "Acesso a portfolios de empresas ", style = tituloConteudoBranco)
                        Text(text = "Filtros de busca de serviços ", style = tituloConteudoBranco)
                        Text(text = "Intermediação de contato  ", style = tituloConteudoBranco)


                    }
                }
            }
            Text(text = "Outros Planos", style = tituloPagina)

            Column {
                Column(
                    modifier = Modifier
                        .padding(2.dp)
                        .background(Color.Transparent)
                        .border(1.dp, Color.White, shape = RoundedCornerShape(5.dp))
                        .padding(8.dp) // Adiciona padding interno
                ){
                    Column {
                        Text(text = "Plano Analytics", style = tituloConteudoAzul)
                        Divider(color = Color.White, thickness = 1.dp, modifier = Modifier.fillMaxWidth())
                        Text(text = "Ideal para buscar serviços e analisar o crescimento ESG na sua empresa.", style = tituloConteudoBranco)
                        Text(text = "Benefícios do Plano Free ", style = tituloConteudoBranco)
                        Text(text = "Acesso ao formulário ESG", style = tituloConteudoBranco)
                        Text(text = "Gráficos que mostram o crescimento ESG na sua empresa ", style = tituloConteudoBranco)

                    }
                }

            }
            Column {
                Column(
                    modifier = Modifier
                        .padding(2.dp)
                        .background(Color.Transparent)
                        .border(1.dp, Color.White, shape = RoundedCornerShape(5.dp))
                        .padding(8.dp) // Adiciona padding interno
                ){
                    Column {
                        Text(text = "Plano Provider", style = tituloConteudoAzul)
                        Divider(color = Color.White, thickness = 1.dp, modifier = Modifier.fillMaxWidth())
                        Text(text = "Permite a criação de um portfolio da sua empresa na plataforma.", style = tituloConteudoBranco)
                        Text(text = "Benefícios do Plano Free", style = tituloConteudoBranco)
                        Text(text = "Criação de Potfolio", style = tituloConteudoBranco)
                        Text(text = "Intermediação de contato com as empresas contratantes ", style = tituloConteudoBranco)
                    }
                }

            }
        }

    }
}



@Composable
fun CampoValor(
    nomeCampo: String,
    valorCampo: String,
) {
    Row(
        verticalAlignment = Alignment.CenterVertically,
        modifier = Modifier.fillMaxWidth(),
    ) {
        Text(
            text = nomeCampo,
            style = tituloConteudoBrancoNegrito,
            modifier = Modifier.width(140.dp) // Largura fixa para o nome do campo
        )
        Spacer(modifier = Modifier.weight(1f)) // Preenche o espaço restante na linha
        Text(
            text = valorCampo,
            style = tituloConteudoBranco,
        )
    }
}




@Preview(showBackground = true)
@Composable
fun MeuPlanoPreview() {
    val navController = rememberNavController()
    MeuPlano(navController)
}