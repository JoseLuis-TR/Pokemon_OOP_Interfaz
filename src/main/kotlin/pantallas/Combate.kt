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
fun PantallaCombate()
{
    Image(
        modifier = Modifier.fillMaxSize(),
        contentScale = ContentScale.Crop,
        painter = painterResource("fondofuego.png"),
        contentDescription = "descripcion")
    Column(horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier.fillMaxWidth()) {
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceAround
        ) {
            Box(
                modifier = Modifier.size(200.dp,200.dp)
            ){

            }
                Box(
                    modifier = Modifier.size(200.dp,200.dp)
                ){
                    Image(
                        bitmap = useResource("blastoise.png") { loadImageBitmap(it) },
                        contentDescription = "Imagen de blastoise",
                        modifier = Modifier.size(200.dp).align(Alignment.BottomCenter)
                    )
                }
            }

        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceAround
        ) {


                Box(
                    modifier = Modifier.size(200.dp,200.dp)
                ){
                    Image(
                        bitmap = useResource("charizardback.png") { loadImageBitmap(it) },
                        contentDescription = "Imagen de charizardback",
                        modifier = Modifier.size(200.dp).align(Alignment.BottomCenter)
                    )

                }
                Box(
                    modifier = Modifier.size(200.dp,200.dp)
                ){

                }
            }

        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceAround
        ) {
            Box(
                modifier = Modifier.size(450.dp,300.dp).padding(top = 20.dp),

                ){

                Image(
                    bitmap = useResource("paneltexto.png") { loadImageBitmap(it) },
                    contentDescription = "Panel de texto"
                )
                Text("¡A luchar!", modifier = Modifier.align(Alignment.Center).padding(start = 10.dp))
            }
            Box(
                modifier = Modifier.size(200.dp,200.dp).padding(top = 20.dp),

                ){

                Button(
                    onClick = { "Atacar" },
                    modifier = Modifier.align(Alignment.TopCenter)
                ) {
                    Text("Atacar", fontWeight = FontWeight.Bold)
                }
                Button(
                    onClick = { "Noche de sexo" },
                    modifier = Modifier.align(Alignment.Center)
                ) {
                    Text("Curar", fontWeight = FontWeight.Bold)
                }
                Button(
                    onClick = { "Noche de sexo" },
                    modifier = Modifier.align(Alignment.BottomCenter)
                ) {
                    Text("Ataque Especial", fontWeight = FontWeight.Bold)
                }
            }
        }


    }
}