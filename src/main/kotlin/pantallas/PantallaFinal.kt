package pantallas

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.loadImageBitmap
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.useResource
import androidx.compose.ui.unit.dp

@Composable
<<<<<<<< HEAD:src/main/kotlin/pantallas/PantallaFinal.kt
fun pantallaFinal(cambiarPantalla:(Int) -> Unit)
{
    Box(){
        Button(onClick = {cambiarPantalla(0)}){
            Text("Estas en pantalla final, cambiar a pantalla inicial")
        }
========
fun Final(cambiarPantalla:(Int) -> Unit)
{
    Box(){
        Button(onClick = {cambiarPantalla(0)}){
            Text("Cambiar a pantalla inicial")
        }
        Text("Pantalla Final",modifier = Modifier.align(Alignment.Center).padding(bottom = 30.dp))
>>>>>>>> ffcc19fb4f0f6ff8930ae5e116509f173cbee8a9:src/main/kotlin/pantallas/Final.kt
    }


    var text by remember { mutableStateOf("Reintentar") }

    Image(
        modifier = Modifier.fillMaxSize(),
        contentScale = ContentScale.Crop,
        painter = painterResource("fondoseleccion.png"),
        contentDescription = "descripcion")
    Column(horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier.fillMaxWidth()) {
        Box(
            modifier = Modifier.size(400.dp, 200.dp).padding(top = 50.dp),

            ){

            Image(
                bitmap = useResource("paneltexto.png") { loadImageBitmap(it) },
                contentDescription = "logo del juego"
            )
            Text("HAS GANADO", modifier = Modifier.align(Alignment.Center).padding(bottom = 40.dp))

        }
        Button(onClick = { text = "Animo valiente"},
            modifier = Modifier.padding(10.dp)
        ){
            Text(text)
        }
    }

}


