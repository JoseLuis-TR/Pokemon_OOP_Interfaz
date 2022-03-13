package pantallas

import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.runtime.Composable

@Composable
fun pantallaCombate(cambiarPantalla:(Int) -> Unit){

    Button(onClick = {cambiarPantalla(3)}){
        Text("Cambiar a pantalla final")
    }

}