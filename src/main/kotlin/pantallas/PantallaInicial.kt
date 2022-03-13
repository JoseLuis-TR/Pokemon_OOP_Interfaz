package pantallas

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.loadImageBitmap
import androidx.compose.ui.res.useResource
import androidx.compose.ui.unit.dp

@Composable
fun pantallaInicial(cambiarPantalla:(Int) -> Unit){
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier.fillMaxWidth()) {

        Box(
            modifier = Modifier.size(400.dp, 200.dp).padding(top = 20.dp),
        ){
            Image(
                bitmap = useResource("paneltexto.png") { loadImageBitmap(it) },
                contentDescription = "Caja de texto con mensajes de bienvenida"
            )
            Text("¡Bienvenido!", modifier = Modifier.align(Alignment.Center).padding(bottom = 110.dp))
            Text("ELIGE A UN POKEMON PARA COMBATIR", modifier = Modifier.align(Alignment.Center).padding(bottom = 30.dp))

            Image(
                bitmap = useResource("blastoiseback.png") { loadImageBitmap(it) },
                contentDescription = "hola"
            )

            Button(onClick = {cambiarPantalla(1)}){
                Text("Estas en la pantalla Inicial, cambiar a combate")
            }
        }
    }

}