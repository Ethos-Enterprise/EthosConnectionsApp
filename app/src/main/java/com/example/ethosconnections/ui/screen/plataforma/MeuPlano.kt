package com.example.ethosconnections.ui.screen.plataforma

import android.content.Context
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
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
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.example.ethosconnections.R
import com.example.ethosconnections.datastore.EmpresaDataStore
import com.example.ethosconnections.ui.screen.plataforma.components.BoxEthos
import com.example.ethosconnections.ui.screen.plataforma.components.Rodape
import com.example.ethosconnections.ui.theme.tituloConteudoAzul
import com.example.ethosconnections.ui.theme.tituloConteudoBranco
import com.example.ethosconnections.ui.theme.tituloConteudoBrancoNegrito
import com.example.ethosconnections.ui.theme.tituloPagina
import com.example.ethosconnections.viewmodel.empresa.EmpresaViewModel

data class Plano(
    val nome: String,
    val descricao: String,
    val beneficios: List<String>,
    val desPreço: String,
    val tipoPlano: String,
    val preco: Double
)

fun getPlanos(context: Context): List<Plano> {


    return listOf(
        Plano(
            context.getString(R.string.titulo_Plano_Free),
            context.getString(R.string.descricao_plano_free),
            context.getString(R.string.beneficios_plano_free).split(","),
            context.getString(R.string.descricao_preco_plano_free),
            context.getString(R.string.tipo_plano),
            context.getString(R.string.preco_plano_free).toDouble()
        ),
        Plano(
            context.getString(R.string.titulo_Plano_Analytics),
            context.getString(R.string.descricao_plano_Analytics),
            context.getString(R.string.beneficios_plano_Analytics).split(","),
            context.getString(R.string.descricao_preco_plano_Analytics),
            context.getString(R.string.tipo_plano_Analytics),
            context.getString(R.string.preco_plano_Analytics).toDouble()
        ),
        Plano(
            context.getString(R.string.titulo_plano_Provider),
            context.getString(R.string.plano_provider_descricao),
            context.getString(R.string.beneficios_provider).split(","),
            context.getString(R.string.descricao_preco_provider),
            context.getString(R.string.tipo_plano_provider),
            context.getString(R.string.preco_plano_Provider).toDouble()
        )
    )

}
@Composable
fun MeuPlano(navController: NavController, empresaDataStore: EmpresaDataStore) {


    val planoAtualNome = empresaDataStore.getPlanoFlow().collectAsState(initial = "Free").value
    val todosPlanos = getPlanos(LocalContext.current)

    val planoAtual = todosPlanos.find { it.nome.contains(planoAtualNome.toString())}
    val outrosPlanos = todosPlanos.filter { it.nome != "Plano ${planoAtualNome.toString()}" }

    Column {
        Text(
            stringResource(R.string.titulo_meu_plano),
            style = tituloPagina)
        Spacer(modifier = Modifier.height(5.dp))

        BoxEthos {

            Text(
                stringResource(R.string.titulo_plano_atual),
                style = tituloPagina)
            planoAtual?.let {
                PlanoCaixa(plano = it, planoAtual = true, onClick = {
                })
            }

            Spacer(modifier = Modifier.height(5.dp))
            Text(
                stringResource(R.string.titulo_outros_plano),
                style = tituloPagina)
            Spacer(modifier = Modifier.height(5.dp))

            outrosPlanos.forEach { plano ->
                PlanoCaixa(plano = plano, planoAtual = false, onClick = {
                    navController.navigate("contrato/${plano.nome}/${plano.preco}")
                })
            }


        }
//        Spacer(modifier = Modifier.height(5.dp))
//        Rodape(
//            acaoBotaoEsquerda = { },
//            nomeBotaoEsquerda = "Editar Dados",
//            acaoBotaoDireita = {},
//            nomeBotaoDireita = "Salvar"
//        )
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
        Divider(modifier = Modifier.padding(top = 8.dp, bottom = 8.dp))

        Row(
            horizontalArrangement = Arrangement.Center,
            modifier = Modifier.fillMaxWidth(),
        ) {
            Text(
                text = plano.desPreço,
                style = tituloConteudoBrancoNegrito
            )
        }

        Row(
            horizontalArrangement = Arrangement.Center,
            modifier = Modifier.fillMaxWidth(),
        ) {
            Text(
                text = plano.tipoPlano,
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

    PlanoCaixa(plano =      Plano(
        nome = "Plano Free",
        descricao = "Ideal para buscar serviços para sua empresa.",
        beneficios = listOf("Acesso a portfolios de empresas", "Filtros de busca de serviços", "Intermediação de contato"),
        desPreço = "GRATUITO",
        tipoPlano = "Plano Padrão",
        preco = 0.0

    ), planoAtual = true, onClick={})

}