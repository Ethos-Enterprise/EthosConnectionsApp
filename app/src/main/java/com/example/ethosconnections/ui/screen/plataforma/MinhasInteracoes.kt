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


@Composable
fun MinhasInteracoes(navController: NavController) {
    Column {
        Text(text = "Minhas Interações", style = tituloPagina)
        BoxEthos {
            Text(text = "Categoria das interações", style = tituloConteudoBrancoNegrito)
            Spacer(modifier = Modifier.height(14.dp))

        }
    }
}


@Preview(showBackground = true)
@Composable
fun MinhasInteraçõesPreview() {
    val navController = rememberNavController()
    MinhasInteracoes(navController)
}
