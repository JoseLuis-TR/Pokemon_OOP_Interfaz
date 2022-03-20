package pantallas

import androidx.compose.desktop.ui.tooling.preview.Preview
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
import pokemons.Bulbasaur
import pokemons.Charmander
import pokemons.Pokemon
import pokemons.Squirtle

@Preview
@Composable
fun PantallaSeleccion(cambiarPantalla:(Int) -> Unit,
                      elegirPokemon:(Pokemon)->Unit)
{
    val listaInicial: ArrayList<Pokemon> = arrayListOf(Charmander(), Bulbasaur(), Squirtle())
    var seleccion:Pokemon? by remember { mutableStateOf(null) }

    Column(horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier.fillMaxWidth()) {
        Box(
            modifier = Modifier.size(400.dp,150.dp).padding(top = 20.dp),

            ){
            Image(
                bitmap = useResource("blastoiseback.png") { loadImageBitmap(it) },
                contentDescription = "hola"
            )
            Image(
                bitmap = useResource("paneltexto.png") { loadImageBitmap(it) },
                contentDescription = "Panel de texto"
            )
            Text("¡Bienvenido!",
                fontFamily = FontFamily.Monospace,
                fontWeight = FontWeight.Bold,
                modifier = Modifier.align(Alignment.Center).padding(bottom = 40.dp))
            Text("ELIGE A UN POKEMON PARA COMBATIR",
                fontFamily = FontFamily.Monospace,
                fontWeight = FontWeight.Bold,
                modifier = Modifier.align(Alignment.Center).padding(top = 20.dp))
        }
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceAround
        ) {
            for(pokemon in listaInicial)
            {
                Box(
                    modifier = Modifier.size(200.dp,250.dp)
                ){
                    Image(
                        bitmap = useResource("${pokemon.nombre}.png") { loadImageBitmap(it) },
                        contentDescription = "Imagen de ${pokemon.nombre}",
                        modifier = Modifier.size(200.dp).align(Alignment.TopCenter).padding(10.dp)
                    )
                    Button(onClick = { seleccion = pokemon },
                        modifier = Modifier.align(Alignment.BottomCenter),
                        colors = ButtonDefaults.textButtonColors(
                            backgroundColor = Color(115,0,153),
                            contentColor = Color.White),
                        shape = RoundedCornerShape(2)){
                        Text("${pokemon.nombre}",
                            fontWeight = FontWeight.Bold,
                            fontFamily = FontFamily.Monospace)
                    }
                }
            }
        }
        if (seleccion != null){
            Button(
                onClick = { cambiarPantalla(1); elegirPokemon(seleccion!!)},
                modifier = Modifier.padding(50.dp),
                colors = ButtonDefaults.textButtonColors(
                    backgroundColor = Color(128,0,0),
                contentColor = Color.White),
                shape = RoundedCornerShape(50)
            )
             {
                Text("¡A pelear!", fontFamily = FontFamily.Monospace,fontWeight = FontWeight.Bold)
            }
        }
    }
}