package pantallas

import androidx.compose.desktop.ui.tooling.preview.Preview
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material.Button
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.loadImageBitmap
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.useResource
import androidx.compose.ui.unit.dp
import pokemons.Bulbasaur
import pokemons.Charmander
import pokemons.Pokemon
import pokemons.Squirtle
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp

@Preview
@Composable
fun PantallaCombate(cambiarPantalla:(Int) -> Unit, pokJugador:Pokemon, fase:Int)
{
    val listaPosiblesEnemigos: ArrayList<Pokemon> = arrayListOf(Squirtle(),Charmander(),Bulbasaur())
    var enemigo:Pokemon = generarEnemigo(listaPosiblesEnemigos,pokJugador,fase)

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
                Box(
                    modifier = Modifier.background(color = Color.White.copy(alpha = 0.7f)).align(Alignment.BottomCenter).padding(bottom = 15.dp)
                )
                {Text("${pokJugador.nombre}", modifier = Modifier.padding(0.dp,0.dp),fontWeight = FontWeight.Bold)}
                Box(modifier = Modifier.size(100.dp,15.dp).background(color = Color.DarkGray).align(Alignment.BottomCenter)) {
                    Box(modifier = Modifier.size(100.dp,15.dp).background(color = Color.Green))
                }
            }
                Box(
                    modifier = Modifier.size(200.dp,200.dp)
                ){
                    Image(
                        bitmap = useResource("${enemigo.nombre}.png") { loadImageBitmap(it) },
                        contentDescription = "Pokemon enemigo",
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
                        bitmap = useResource("${pokJugador.nombre}back.png") { loadImageBitmap(it) },
                        contentDescription = "Espalda Pokemon Jugaodr",
                        modifier = Modifier.size(200.dp).align(Alignment.BottomCenter)
                    )

                }
                Box(
                    modifier = Modifier.size(200.dp,200.dp)
                ){
                    Box(
                        modifier = Modifier.background(color = Color.White).align(Alignment.TopCenter).padding(top = 15.dp)
                    )
                    {Text("${enemigo.nombre}", modifier = Modifier.padding(5.dp,0.dp),fontWeight = FontWeight.Bold,fontSize = 15.sp)}
                    Box(modifier = Modifier.size(100.dp,15.dp).background(color = Color.DarkGray).align(Alignment.TopCenter)) {
                        Box(modifier = Modifier.size(100.dp,15.dp).background(color = Color.Green))
                    }
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
                    modifier = Modifier.align(Alignment.TopCenter),
                    colors = ButtonDefaults.textButtonColors(
                        backgroundColor = Color(204,0,0),
                        contentColor = Color.White)
                ) {
                    Text("Atacar", fontWeight = FontWeight.Bold)
                }
                Button(
                    onClick = { "Curar" },
                    modifier = Modifier.align(Alignment.Center),
                    colors = ButtonDefaults.textButtonColors(
                        backgroundColor = Color(0,179,89),
                        contentColor = Color.White)
                ) {
                    Text("Curar", fontWeight = FontWeight.Bold)
                }
                Button(
                    onClick = { "Especial" },
                    modifier = Modifier.align(Alignment.BottomCenter),
                    colors = ButtonDefaults.textButtonColors(
                        backgroundColor = Color(204,122,0),
                        contentColor = Color.White)
                ) {
                    Text("Ataque Especial", fontWeight = FontWeight.Bold)
                }
            }
        }


    }
}
fun generarEnemigo(listaEnemigos:ArrayList<Pokemon>,pokJugador:Pokemon,fase:Int):Pokemon
{
    var enemigoDevueto:Pokemon = Charmander()
    when (fase) {
        0 -> {
            for (x in listaEnemigos)
                if (x.tipo == pokJugador.fortalezas)
                    enemigoDevueto = x
        }
        1 -> {
            for (x in listaEnemigos)
                if (x.tipo != pokJugador.fortalezas && x.tipo != pokJugador.debilidades)
                    enemigoDevueto = x
            for(evo in 0 until fase)
                enemigoDevueto.evolucionar()
        }
        2 -> {
            for(x in listaEnemigos)
                if(x.tipo == pokJugador.debilidades)
                    enemigoDevueto = x
            for(evo in 0 until fase)
                enemigoDevueto.evolucionar()
        }
    }
    return enemigoDevueto
}