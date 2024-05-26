package com.example.ethosconnections.ui.screen.plataforma

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.example.compose.AppTheme
import com.example.ethosconnections.R
import com.example.ethosconnections.datastore.EmpresaDataStore
import com.example.ethosconnections.ui.screen.plataforma.components.BoxEthos
import com.example.ethosconnections.ui.screen.plataforma.components.FillButtonEthos
import com.example.ethosconnections.ui.theme.letraPadraoExtraLigth
import com.example.ethosconnections.ui.theme.tituloConteudoBranco

@Composable
fun Formulario(navController: NavController, categoria: String, empresaDataStore: EmpresaDataStore) {


    Column {

    FillButtonEthos(acao = { navController.navigate("meuProgresso") }, nomeAcao = stringResource(R.string.continuar_depois))

    BoxEthos {
        Image(
            painter = painterResource(id = R.mipmap.capa_formulario_ambiental),
            contentDescription = "Logo Deloitte",
            modifier = Modifier
                .fillMaxWidth()
                .fillMaxHeight(0.6f)
                .clip(RoundedCornerShape(5.dp)),
        )

        Spacer(modifier = Modifier.height(14.dp))

        var titulo = when (categoria) {
            "Ambiental" -> stringResource(R.string.titulo_ambiental)
            "Social" -> stringResource(R.string.titulo_social)
            "Governamental" -> stringResource(R.string.titulo_governamental)
            else -> stringResource(R.string.titulo_geral)
        }

        Text(text = titulo, style = tituloConteudoBranco)

        Text(
            text = stringResource(R.string.descricao_formulario),
            style = letraPadraoExtraLigth
        )

        FillButtonEthos(acao = { navController.navigate("questionario/${categoria}")
        }, nomeAcao = stringResource(R.string.iniciar_questionario))
    }
}
}

@Preview(showBackground = true)
@Composable
fun FormularioPreview() {
    val navController = rememberNavController()
    navController.navigate("portfolio")

    AppTheme {
        Surface {
            //Plataforma(navController = navController)
        }
    }
}