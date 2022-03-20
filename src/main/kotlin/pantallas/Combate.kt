package pantallas

import GameManager
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material.Button
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
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
import androidx.compose.runtime.*

@Composable
fun PantallaCombate(cambiarPantalla:(Int) -> Unit, pokJugador:Pokemon)
{
    var fase by remember { mutableStateOf(0) }
    var enemigo by remember { mutableStateOf(generarEnemigo(pokJugador,fase)) }
    var vidaTotalEnemigo by remember { mutableStateOf(enemigo.vidaTotal) }
    var vidaTotalJugador by remember { mutableStateOf(pokJugador.vidaTotal) }
    var gameManager by remember { mutableStateOf(GameManager(pokJugador,enemigo)) }
    var mensaje by remember { mutableStateOf("Comienza la batalla entre ${pokJugador.nombre} vs ${enemigo.nombre}") }

    fun refrescarInterfaz() {
        vidaTotalEnemigo = enemigo.vidaTotal
        gameManager.maquinaAtaca()
        vidaTotalJugador = pokJugador.vidaTotal
        if(vidaTotalJugador == 0f || vidaTotalEnemigo == 0f)
            if(fase == 2)
                cambiarPantalla(2)
            else{
                fase += 1
                pokJugador.evolucionar()
                enemigo = generarEnemigo(pokJugador, fase)
                gameManager.maquina = enemigo
                pokJugador.vidaTotal = pokJugador.vida
                enemigo.vidaTotal = enemigo.vida
            }
    }

    Image(
        modifier = Modifier.fillMaxSize(),
        contentScale = ContentScale.Crop,
        painter = painterResource("fondo${enemigo.tipo}.png"),
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
                Box(modifier = Modifier.size(100.dp,15.dp).background(color = Color.Gray).align(Alignment.BottomCenter)) {
                    Box(modifier = Modifier.size(((vidaTotalJugador * 100)/pokJugador.vida).toInt().dp,15.dp).background(color = Color.Green))
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
                        contentDescription = "Espalda Pokemon Jugador",
                        modifier = Modifier.align(Alignment.BottomCenter).fillMaxSize()
                    )
                }
                Box(
                    modifier = Modifier.size(200.dp,200.dp)
                ){
                    Box(
                        modifier = Modifier.background(color = Color.White).align(Alignment.TopCenter).padding(top = 15.dp)
                    )
                    {Text("${enemigo.nombre}", modifier = Modifier.padding(5.dp,0.dp),fontWeight = FontWeight.Bold,fontSize = 15.sp)}
                    Box(modifier = Modifier.size(100.dp,15.dp).background(color = Color.Gray).align(Alignment.TopCenter)) {
                        Box(modifier = Modifier.size(((vidaTotalEnemigo * 100)/enemigo.vida).toInt().dp,15.dp).background(color = Color.Green))
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
                Text(mensaje+" $vidaTotalEnemigo  ${enemigo.vidaTotal}", modifier = Modifier.align(Alignment.Center).padding(start = 10.dp))
            }
            Box(
                modifier = Modifier.size(200.dp,200.dp).padding(top = 20.dp),

                ){

                Button(
                    onClick = { mensaje = gameManager.jugadorAtaca();refrescarInterfaz() },
                    modifier = Modifier.align(Alignment.TopCenter),
                    colors = ButtonDefaults.textButtonColors(
                        backgroundColor = Color(204,0,0),
                        contentColor = Color.White)
                ) {
                    Text("Atacar", fontWeight = FontWeight.Bold)
                }
                Button(
                    onClick = { mensaje = gameManager.jugadorCura(); refrescarInterfaz() },
                    modifier = Modifier.align(Alignment.Center),
                    colors = ButtonDefaults.textButtonColors(
                        backgroundColor = Color(0,179,89),
                        contentColor = Color.White)
                ) {
                    Text("Curar", fontWeight = FontWeight.Bold)
                }
                Button(
                    onClick = { mensaje = gameManager.jugadorEspecial(); refrescarInterfaz() },
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
fun generarEnemigo(pokJugador:Pokemon,fase:Int):Pokemon
{
    val listaEnemigos: ArrayList<Pokemon> = arrayListOf(Squirtle(), Charmander(), Bulbasaur())
    var enemigoDevuelto: Pokemon? = null
    when (fase) {
        0 -> {
            for (x in listaEnemigos)
                if (x.tipo == pokJugador.fortalezas)
                    enemigoDevuelto = x
        }
        1 -> {
            for (x in listaEnemigos)
                if (x.tipo != pokJugador.fortalezas && x.tipo != pokJugador.debilidades)
                    enemigoDevuelto = x
            for(evo in 0 until fase)
                enemigoDevuelto!!.evolucionar()
        }
        2 -> {
            for(x in listaEnemigos)
                if(x.tipo == pokJugador.debilidades)
                    enemigoDevuelto = x
            for(evo in 0 until fase)
                enemigoDevuelto!!.evolucionar()
        }
    }
    return enemigoDevuelto!!
}