package com.example.ethosconnections.ui.theme

import androidx.compose.material3.Typography
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp
import com.example.compose.cor_primaria
import com.example.compose.md_theme_dark_background
import com.example.compose.md_theme_light_onSurfaceVariant
import com.example.compose.md_theme_light_outline
import com.example.compose.md_theme_light_primary
import com.example.ethosconnections.R

//-------------LETRAS PADROES-------------------------------------------

//pagina de ..... exemplo(Soluções ESG)
val tituloPagina = TextStyle(
    fontFamily = FontFamily(Font(R.font.poppins_medium)),
    fontSize = 15.sp,
    color = Color.White,
)

//usar pro  titulo de cada box de conteúdo exemplo (CATEGORIAS ESG)
val tituloConteudoBranco = TextStyle(
    fontFamily = FontFamily(Font(R.font.poppins_regular)),
    fontSize = 14.sp,
    color = Color.White,
)

val tituloConteudoPreto = TextStyle(
    fontFamily = FontFamily(Font(R.font.poppins_regular)),
    fontSize = 14.sp,
    color = Color.Black,
    fontWeight = FontWeight.Bold
)

val tituloConteudoCinza = TextStyle(
    fontFamily = FontFamily(Font(R.font.poppins_regular)),
    fontSize = 14.sp,
    color = Color.LightGray,
)

val tituloConteudoCinzaNegrito = TextStyle(
    fontFamily = FontFamily(Font(R.font.poppins_regular)),
    fontSize = 14.sp,
    color = Color.LightGray,
    fontWeight = FontWeight.Bold
)

val tituloConteudoBrancoNegrito = TextStyle(
    fontFamily = FontFamily(Font(R.font.poppins_regular)),
    fontSize = 14.sp,
    color = Color.White,
    fontWeight = FontWeight.Bold
)

val tituloConteudoAzul = TextStyle(
    fontFamily = FontFamily(Font(R.font.poppins_medium)),
    fontSize = 14.sp,
    color = Color(0xFF01A2C3),
)

//usar para  texto corrido/ texto que nao precise de muita enfase
val letraPadrao = TextStyle(
    fontFamily = FontFamily(Font(R.font.poppins_light)),
    color = Color.White,
    fontSize = 13.sp,
    )

val letraPadraoExtraLigth = TextStyle(
    fontFamily = FontFamily(Font(R.font.poppins_extra_light)),
    color = Color.White,
    fontSize = 13.sp,
)

// usar para letras de botao
val letraButton = TextStyle(
    fontFamily = FontFamily(Font(R.font.poppins_medium)),
    color = Color.Black
)

val tituloServico = TextStyle(
    fontFamily = FontFamily(Font(R.font.poppins_medium)),
    fontSize = 13.5.sp,
    color = Color.White,
)

val tituloFormularioCard = TextStyle(
    fontFamily = FontFamily(Font(R.font.poppins_medium)),
    fontSize = 13.5.sp,
    color = cor_primaria,
)

//----------------------------------------------------------------
val textoTop = TextStyle(
    fontFamily = FontFamily(Font(R.font.poppins_semi_bold)),
    fontWeight = FontWeight.SemiBold,
    fontSize = 20.sp,
    lineHeight = 24.sp,
    letterSpacing = 1.3.sp,
    color = Color.White
)

val letraProgresso = TextStyle(
    fontFamily = FontFamily(Font(R.font.poppins_bold)),
)

val letraClicavel = TextStyle(
    fontFamily = FontFamily(Font(R.font.poppins_medium)),
    color = cor_primaria
)

val corLetra = TextStyle(
    fontFamily = FontFamily(Font(R.font.poppins_extra_light)),
    fontSize = 12.sp,
    fontWeight = FontWeight.ExtraLight,
    color = Color.White
)

val letraDescricao = TextStyle(
    fontFamily = FontFamily(Font(R.font.poppins_extra_light)),
    fontSize = 14.sp,
    fontWeight = FontWeight.ExtraLight,
    color = Color.White
)

val tituloMenu = TextStyle(
    fontFamily = FontFamily(Font(R.font.poppins_medium)),
    fontSize = 16.sp,
    color = Color.White,
    )

val letraMenu = TextStyle(
    fontFamily = FontFamily(Font(R.font.poppins_light)),
    fontSize = 14.sp,
)
