
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Divider
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
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
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import com.example.ethosconnections.datastore.EmpresaDataStore

// Modelo de dados do usuário
data class Usuario(
//    val nomeEmpresa: String,
//    val cnpj: String,
//    val areaAtuacao: String,
//    val tamanhoEmpresa: String,
    val endereco: String,
    val numero: String,
    val bairro: String,
    val cep: String,
//    val telefone: String,
//    val email: String
)

// Função que simula a obtenção dos dados do usuário
fun obterUsuario(): Usuario {
    return Usuario(
//        "Deloitte",
//        "12.345.678/9101-12",
//        "Consultoria",
//        "0 Funcionários",
        "Av. Brig. Faria Lima ",
        "2066",
        " Pinheiros - SP",
        "01451-001",
//        "11 3027-2900",
//        "contato@matrixenergia.com"
    )
}
@Composable
fun MeuPerfil(navController: NavController,empresaDataStore: EmpresaDataStore) {
    val usuario = obterUsuario()
    val empresa by empresaDataStore.getEmpresaFlow().collectAsState(initial = null)

    Column {
        Text(text = "Minha conta", style = tituloPagina)

        BoxEthos{
            Column {
                Text(text = "Meu Perfil", style = tituloConteudoAzul)
                Divider(modifier = Modifier.padding(bottom = 10.dp))

                Text(text = "Minhas informações", style = tituloConteudoBranco)

                // Seção de informações do usuário
                Column(
                    modifier = Modifier
                        .padding(2.dp)
                        .background(Color.Transparent)
                        .border(1.dp, Color.White, shape = RoundedCornerShape(5.dp))
                        .padding(8.dp) // Adiciona padding interno
                ) {
                    CampoValor(
                        nomeCampo = "Nome da empresa:",
                        valorCampo = empresa?.razaoSocial ?: "N/A"
                    )
                    CampoValor(
                        nomeCampo = "CNPJ:",
                        valorCampo = empresa?.cnpj ?: "N/A"
                    )
                    CampoValor(
                        nomeCampo = "Área de atuação:",
                        valorCampo = empresa?.setor ?: "N/A"
                    )
                    CampoValor(
                        nomeCampo = "Tamanho da Empresa:",
                        valorCampo = "${empresa?.qtdFuncionarios ?: "N/A"}"
                    )

                }
                Text(text = "Endereço e contatos", style = tituloConteudoBranco)

                // Seção de endereço e contatos
                Column(
                    modifier = Modifier
                        .padding(2.dp)
                        .background(Color.Transparent)
                        .border(1.dp, Color.White, shape = RoundedCornerShape(5.dp))
                        .padding(8.dp) // Adiciona padding interno
                ) {
                        Column {
                            CampoValor(
                                nomeCampo = "Logradouro:",
                                valorCampo = usuario.endereco
                            )
                            CampoValor(
                                nomeCampo = "Número:",
                                valorCampo = usuario.numero
                            )
                            CampoValor(
                                nomeCampo = "Bairro:",
                                valorCampo = usuario.bairro
                            )
                            CampoValor(
                                nomeCampo = "CEP:",
                                valorCampo = usuario.cep
                            )
                            CampoValor(
                                nomeCampo = "Telefone Corporativo:",
                                valorCampo = empresa?.telefone ?: "N/A"

                            )
                            CampoValor(
                                nomeCampo = "Email Corporativo:",
                                valorCampo = empresa?.email?: "N/A",
                            )
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
        Text(
            text = valorCampo,
            style = tituloConteudoBranco,
        )
    }
}


@Preview(showBackground = true)
@Composable
fun MeuPerfilPreview() {
    val navController = rememberNavController()
    //MeuPerfil(navController)
}