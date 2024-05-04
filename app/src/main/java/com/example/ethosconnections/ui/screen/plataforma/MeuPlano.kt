package com.example.ethosconnections.ui.screen.plataforma

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Divider
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.example.ethosconnections.R
import com.example.ethosconnections.datastore.EmpresaDataStore
import com.example.ethosconnections.ui.screen.plataforma.components.BoxEthos
import com.example.ethosconnections.ui.theme.tituloConteudoAzul
import com.example.ethosconnections.ui.theme.tituloConteudoBranco
import com.example.ethosconnections.ui.theme.tituloConteudoBrancoNegrito
import com.example.ethosconnections.ui.theme.tituloPagina
import com.example.ethosconnections.viewmodel.empresa.EmpresaViewModel

data class Plano(
    val nome: String,
    val descricao: String,
    val beneficios: List<String>,
    val preco: Double
)

fun getPlanos(): List<Plano> {
    return listOf(
        Plano(
            nome = "Plano Free",
            descricao = "Ideal para buscar serviços para sua empresa.",
            beneficios = listOf("Acesso a portfolios de empresas", "Filtros de busca de serviços", "Intermediação de contato"),
            preco = 0.0

        ),
        Plano(
            nome = "Plano Analytics",
            descricao = "Ideal para analisar o crescimento ESG na sua empresa.",
            beneficios = listOf("Benefícios do Plano Free", "Acesso ao formulário ESG", "Gráficos de crescimento ESG"),
            preco = 29.90
        ),
        Plano(
            nome = "Plano Provider",
            descricao = "Permite a criação de um portfolio na plataforma.",
            beneficios = listOf("Benefícios do Plano Free", "Criação de Portfolio", "Intermediação entre empresas"),
            preco = 49.90
        )
    )
}
@Composable
fun MeuPlano(navController: NavController, empresaDataStore: EmpresaDataStore) {

    val planoAtualNome = empresaDataStore.getPlanoFlow().collectAsState(initial = "Free").value
    val todosPlanos = getPlanos()

    val planoAtual = todosPlanos.find { it.nome.contains(planoAtualNome.toString())}
    val outrosPlanos = todosPlanos.filter { it.nome != "Plano ${planoAtualNome.toString()}" }

    Column {
        Text(text = "Meu Plano", style = tituloPagina)

        BoxEthos {

            planoAtual?.let {
                PlanoCaixa(plano = it, planoAtual = true, onClick = {
                })
            }

            Text(text = "Outros Planos", style = tituloPagina)

            outrosPlanos.forEach { plano ->
                PlanoCaixa(plano = plano, planoAtual = false, onClick = {
                    navController.navigate("contrato/${plano.nome}/${plano.preco}")
                })
            }

        }
    }

}

@Composable
fun PlanoCaixa(plano: Plano, planoAtual: Boolean, onClick: () -> Unit) {
    Column(
        modifier = Modifier
            .padding(2.dp)
            .background(Color.Transparent)
            .border(1.dp, Color.White, shape = RoundedCornerShape(5.dp))
            .padding(8.dp) // Adiciona padding interno
            .clickable(onClick = onClick)
    ) {
        Text(text = plano.nome, style = tituloConteudoAzul)
        Divider(modifier = Modifier.padding(bottom = 10.dp))
        Text(
            text = plano.descricao,
            style = tituloConteudoBranco
        )

        Row(
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier.fillMaxWidth(),
        ) {
            Image(
                modifier = Modifier
                    .size(15.dp)
                    .padding(end = 4.dp), // Adiciona espaço entre a imagem e o texto
                painter = painterResource(id = R.mipmap.vector),
                contentDescription = "Sinal de check"
            )
            Text(text = plano.beneficios[0], style = tituloConteudoBranco)
        }

        Row(
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier.fillMaxWidth(),
        ) {
            Image(
                modifier = Modifier
                    .size(15.dp)
                    .padding(end = 4.dp), // Adiciona espaço entre a imagem e o texto
                painter = painterResource(id = R.mipmap.vector),
                contentDescription = "Sinal de check"
            )
            Text(text = plano.beneficios[1], style = tituloConteudoBranco)
        }

        Row(
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier.fillMaxWidth(),
        ) {
            Image(
                modifier = Modifier
                    .size(15.dp)
                    .padding(end = 4.dp), // Adiciona espaço entre a imagem e o texto
                painter = painterResource(id = R.mipmap.vector),
                contentDescription = "Sinal de check"
            )
            Text(
                text = plano.beneficios[2],
                style = tituloConteudoBranco
            )
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
    //MeuPlano(navController)
}