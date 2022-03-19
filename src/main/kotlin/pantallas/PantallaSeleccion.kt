package pantallas

import androidx.compose.desktop.ui.tooling.preview.Preview
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.loadImageBitmap
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.useResource
import androidx.compose.ui.unit.dp
import androidx.compose.runtime.getValue
import pokemons.Bulbasaur
import pokemons.Charmander
import pokemons.Pokemon
import pokemons.Squirtle
import androidx.compose.runtime.getValue
import androidx.compose.runtime.setValue
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight

@Preview
@Composable
fun PantallaSeleccion()
{
    val listaInicial: ArrayList<Pokemon> = arrayListOf(Charmander(), Bulbasaur(), Squirtle())
    var seleccion:Pokemon? by remember { mutableStateOf(null) }
    Image(
        modifier = Modifier.fillMaxSize(),
        contentScale = ContentScale.Crop,
        painter = painterResource("fondoseleccion.png"),
        contentDescription = "descripcion")
    Column(horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier.fillMaxWidth()) {
        Box(
            modifier = Modifier.size(400.dp,200.dp).padding(top = 20.dp),

            ){
            Image(
                bitmap = useResource("blastoiseback.png") { loadImageBitmap(it) },
                contentDescription = "hola"
            )
            Image(
                bitmap = useResource("paneltexto.png") { loadImageBitmap(it) },
                contentDescription = "Panel de texto"
            )
            Text("¡Bienvenido!", modifier = Modifier.align(Alignment.Center).padding(bottom = 110.dp))
            Text("ELIGE A UN POKEMON PARA COMBATIR", modifier = Modifier.align(Alignment.Center).padding(bottom = 30.dp))
        }
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceAround
        ) {
            for(pokemon in listaInicial)
            {
                Box(
                    modifier = Modifier.size(200.dp,200.dp)
                ){
                    Image(
                        bitmap = useResource("${pokemon.nombre}.png") { loadImageBitmap(it) },
                        contentDescription = "Imagen de ${pokemon.nombre}",
                        modifier = Modifier.padding(25.dp).size(200.dp).align(Alignment.TopCenter).padding(0.dp,10.dp)
                    )
                    Button(onClick = { seleccion = pokemon },
                        modifier = Modifier.padding(25.dp).align(Alignment.BottomCenter)){
                        Text("${pokemon.nombre}")
                    }
                }
            }
        }
        if (seleccion != null){
            Button(
                onClick = { "Noche de sexo" },
                modifier = Modifier.padding(25.dp)
            ) {
                Text("A pelear!", fontWeight = FontWeight.Bold)
            }
        }
    }
}