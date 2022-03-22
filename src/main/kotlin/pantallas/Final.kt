package pantallas


import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Button
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.loadImageBitmap
import androidx.compose.ui.res.useResource
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp

@Composable
fun Final(cambiarPantalla:(Int) -> Unit,ganaJugador:Boolean)
{
    //Pantalla del final, dependiendo de si el jugador gana o no se mostrará algo diferente
    var mensajeFinal by remember { mutableStateOf("") }
    var mensajeAdicional by remember { mutableStateOf("") }
    if(ganaJugador)
    {
        mensajeFinal = "HAS GANADO"
        mensajeAdicional = "Llegaste hasta el final sin perder"
    }
    else
    {
        mensajeFinal = "HAS PERDIDO"
        mensajeAdicional = "No has derrotado a todos los Pokemon enemigos"
    }

    Column(horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier.fillMaxWidth()) {
        Box(
            modifier = Modifier.size(400.dp, 200.dp).padding(top = 50.dp),

            ){

            Image(
                bitmap = useResource("paneltexto.png") { loadImageBitmap(it) },
                contentDescription = "Panel de Texto"
            )
            Text(mensajeFinal,
                fontFamily = FontFamily.Monospace,
                fontWeight = FontWeight.Bold,
                modifier = Modifier.align(Alignment.Center).padding(bottom = 40.dp))
            Text(mensajeAdicional,
                fontFamily = FontFamily.Monospace,
                fontWeight = FontWeight.Bold,
                modifier = Modifier.align(Alignment.Center).padding(bottom = 5.dp))
        }
        Button(onClick = { cambiarPantalla(0)},
            modifier = Modifier.padding(10.dp),
            colors = ButtonDefaults.textButtonColors(
                backgroundColor = Color(128,0,0),
                contentColor = Color.White),
            shape = RoundedCornerShape(50)
        ){
            Text("REINTENTAR",
                fontFamily = FontFamily.Monospace,
                fontWeight = FontWeight.Bold)
        }
        Image(
            bitmap = useResource("$ganaJugador.png") { loadImageBitmap(it) },
            contentDescription = "Imagen de Pikachu segun ganes o pierdas",
            alignment = Alignment.CenterEnd,
            modifier = Modifier.fillMaxHeight()
        )
    }

}


