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
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.example.compose.AppTheme
import com.example.ethosconnections.R
import com.example.ethosconnections.ui.screen.Plataforma
import com.example.ethosconnections.ui.screen.plataforma.components.BoxEthos
import com.example.ethosconnections.ui.screen.plataforma.components.FillButtonEthos
import com.example.ethosconnections.ui.theme.letraPadrao
import com.example.ethosconnections.ui.theme.letraPadraoExtraLigth
import com.example.ethosconnections.ui.theme.tituloConteudoBranco
import com.example.ethosconnections.ui.theme.tituloPagina

@Composable
fun Formulario(navController: NavController) {

    Column {

    FillButtonEthos(acao = { navController.navigate("meuProgresso") }, nomeAcao = "Continuar Depois")

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
        Text(text = "Questionário Ambiental", style = tituloConteudoBranco)

        Text(
            text = "Sabemos que a sustentabilidade ambiental é uma preocupação cada vez mais relevante para empresas comprometidas com práticas responsáveis. Este formulário tem como objetivo ajudá-lo a avaliar e melhorar suas iniciativas ambientais.\n" +
                    "\n" +
                    "Sua participação é fundamental para entendermos sua pegada ambiental e recomendar as soluções ESG mais adequadas às suas necessidades. Ao responder a estas perguntas, você estará dando um passo importante em direção a um mundo mais sustentável e ético.\n" +
                    "\n" +
                    "Juntos, podemos criar um impacto positivo na saúde do planeta e no sucesso do seu negócio. Vamos começar a avaliar suas práticas e a definir metas para um futuro mais verde!",
            style = letraPadraoExtraLigth
        )
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