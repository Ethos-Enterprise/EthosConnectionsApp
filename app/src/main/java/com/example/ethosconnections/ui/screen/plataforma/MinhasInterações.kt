import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.example.ethosconnections.R
import com.example.ethosconnections.ui.screen.plataforma.components.BoxEthos
import com.example.ethosconnections.ui.theme.tituloConteudoBrancoNegrito
import com.example.ethosconnections.ui.theme.tituloPagina

data class Interacoes(
    val contatoAndamento: String,
    val contatoFinalizado: String
)

@Composable
fun MinhasInterações(navController: NavController) {
    Column {
        Text(text = "Minhas Interações", style = tituloPagina)
        BoxEthos {
            Text(text = "Categoria das interações", style = tituloConteudoBrancoNegrito)
            Spacer(modifier = Modifier.height(14.dp))

            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Clicavel(R.mipmap.contato) {
                    // Ação ao clicar na imagem 1
                }
                Spacer(modifier = Modifier.width(5.dp))
                Clicavel(R.mipmap.favoritos) {
                    // Ação ao clicar na imagem 2
                }
            }
        }

        Text(text = "Empresas Contatadas", style = tituloPagina)

        BoxEthos {
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                CampoInteracao(
                    emAndamento = "Em andamento:",
                    eFinalizado = obterInteracao().contatoAndamento
                )

                CampoInteracao(
                    emAndamento = "Finalizado:",
                    eFinalizado = obterInteracao().contatoFinalizado
                )
            }
        }

        BoxEthos {
            Column(
                modifier = Modifier.fillMaxWidth(),
                verticalArrangement = Arrangement.SpaceBetween
            ) {
                EmpresaImagem(R.mipmap.deloitte_logo) {
                    Text(text = "Deloitte")
                }

            }
        }
    }
}

@Composable
fun CampoInteracao(emAndamento: String, eFinalizado: String) {
    Row(
        verticalAlignment = Alignment.CenterVertically,
        modifier = Modifier.fillMaxWidth(),
    ) {
        Text(
            text = emAndamento,
            style = tituloConteudoBrancoNegrito,
            modifier = Modifier.width(140.dp) // Largura fixa para o nome do campo
        )
        Text(
            text = eFinalizado,
            style = tituloConteudoBrancoNegrito,
            modifier = Modifier.width(140.dp) // Largura fixa para o nome do campo

        )
    }
}

fun obterInteracao(): Interacoes {
    return Interacoes(
        contatoAndamento = "1 Empresa",
        contatoFinalizado = "3 Empresas"
    )
}

@Composable
fun EmpresaImagem(imagemResId: Int, onClick: () -> Unit) {
    Box(
        modifier = Modifier
            .size(100.dp)
            .padding(1.dp)
            .clickable(onClick = onClick)
    ) {
        androidx.compose.foundation.Image(
            painter = painterResource(id = imagemResId),
            contentDescription = null,
            modifier = Modifier.fillMaxSize()
        )
    }
}

@Composable
fun Clicavel(imagemResId: Int, onClick: () -> Unit) {
    Box(
        modifier = Modifier
            .size(200.dp)
            .padding(1.dp)
            .clickable(onClick = onClick)
    ) {
    }
}

@Preview(showBackground = true)
@Composable
fun MinhasInteraçõesPreview() {
    val navController = rememberNavController()
    MinhasInterações(navController)
}
