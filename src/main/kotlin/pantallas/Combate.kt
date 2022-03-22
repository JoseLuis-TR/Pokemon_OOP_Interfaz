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
import androidx.compose.ui.text.font.FontFamily
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

@Composable
fun PantallaCombate(cambiarPantalla:(Int) -> Unit, pokJugador:Pokemon)
{
    var fase by remember { mutableStateOf(0) }
    var enemigo by remember { mutableStateOf(generarEnemigo(pokJugador,fase)) }
    var vidaTotalEnemigo by remember { mutableStateOf(enemigo.vidaTotal) }
    var vidaTotalJugador by remember { mutableStateOf(pokJugador.vidaTotal) }
    val gameManager by remember { mutableStateOf(GameManager(pokJugador,enemigo)) }
    val scope = rememberCoroutineScope()
    var mensaje by remember { mutableStateOf("Comienza la batalla entre ${pokJugador.nombre} vs ${enemigo.nombre}") }
    var accionJugador by remember { mutableStateOf(true) }

    //Hace que se mantenga la información en pantalla actualizada
    fun refrescarInterfaz()
    {
        vidaTotalEnemigo = enemigo.vidaTotal
        vidaTotalJugador = pokJugador.vidaTotal
        gameManager.maquina = enemigo
        gameManager.jugador = pokJugador
    }

    //Tras la elección del jugador se activa la función que comprueba si alguien ha ganado
    //En el caso de que no es cuando la máquina decide que hacer
    fun cambioTurno()
    {
        accionJugador = false
        var eleccionMaquina:Int
        refrescarInterfaz()
        scope.launch()
        {
            delay(1200)
            if (vidaTotalJugador == 0f || vidaTotalEnemigo == 0f)
                if (vidaTotalEnemigo == 0f && fase == 2)
                    cambiarPantalla(2)
                else if (vidaTotalJugador == 0f)
                    cambiarPantalla(3)
                else {
                    fase += 1
                    pokJugador.evolucionar()
                    enemigo = generarEnemigo(pokJugador, fase)
                    pokJugador.vidaTotal = pokJugador.vida
                    enemigo.vidaTotal = enemigo.vida
                    refrescarInterfaz()
                    scope.launch()
                    {
                        delay(900)
                        mensaje = "Comienza la batalla entre ${pokJugador.nombre} vs ${enemigo.nombre}"
                    }
                    accionJugador = true
                }
            else {
                eleccionMaquina = (1..10).random()
                if ((vidaTotalEnemigo * 100) / enemigo.vida < 50f)
                    when (eleccionMaquina) {
                        1, 10 -> mensaje = "${enemigo.nombre} ha fallado su ataque"
                        in 2..6 -> mensaje = gameManager.maquinaCura()
                        in 7..8 -> mensaje = gameManager.maquinaAtaca()
                    }
                else if ((vidaTotalEnemigo * 100) / enemigo.vida > 50 && fase < 2)
                    when (eleccionMaquina) {
                        1, 10 -> mensaje = "${enemigo.nombre} ha fallado su ataque"
                        in 2..6 -> mensaje = gameManager.maquinaAtaca()
                        in 7..8 -> mensaje = gameManager.maquinaCura()
                    }
                else {
                    when (eleccionMaquina) {
                        1, 5, 10 -> mensaje = "${enemigo.nombre} ha fallado su ataque"
                        in 2..4 -> mensaje = gameManager.maquinaAtaca()
                        in 6..9 -> mensaje = gameManager.maquinaCura()
                    }
                }
                refrescarInterfaz()
                accionJugador = true
            }
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
                    modifier = Modifier.background(color = Color.White).align(Alignment.BottomCenter).padding(bottom = 15.dp)
                )
                {Text("${pokJugador.nombre}", modifier = Modifier.padding(5.dp,2.dp),fontWeight = FontWeight.Bold)}
                Box(modifier = Modifier.size(120.dp,15.dp).background(color = Color.Gray).align(Alignment.BottomCenter))
                { Box(modifier = Modifier.size(((vidaTotalJugador * 120)/pokJugador.vida).toInt().dp,15.dp).background(color = Color.Green)) }
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
                    {Text("${enemigo.nombre}", modifier = Modifier.padding(5.dp,2.dp),fontWeight = FontWeight.Bold,fontSize = 15.sp)}
                    Box(modifier = Modifier.size(120.dp,15.dp).background(color = Color.Gray).align(Alignment.TopCenter)) {
                        Box(modifier = Modifier.size(((vidaTotalEnemigo * 120)/enemigo.vida).toInt().dp,15.dp).background(color = Color.Green))
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
                Text(mensaje, modifier = Modifier.align(Alignment.Center).padding(start = 10.dp),
                                fontFamily = FontFamily.Monospace,
                                fontWeight = FontWeight.Bold)
            }
            Box(
                modifier = Modifier.size(200.dp,200.dp).padding(top = 20.dp),

                ){

                Button(
                    onClick = { mensaje = gameManager.jugadorAtaca();cambioTurno() },
                    modifier = Modifier.align(Alignment.TopCenter),
                    colors = ButtonDefaults.textButtonColors(
                        backgroundColor = Color(204,0,0),
                        contentColor = Color.White),
                    enabled = accionJugador
                ) {
                    Text("Atacar", fontWeight = FontWeight.Bold)
                }
                Button(
                    onClick = { mensaje = gameManager.jugadorCura(); cambioTurno() },
                    modifier = Modifier.align(Alignment.Center),
                    colors = ButtonDefaults.textButtonColors(
                        backgroundColor = Color(0,179,89),
                        contentColor = Color.White),
                    enabled = accionJugador
                ) {
                    Text("Curar", fontWeight = FontWeight.Bold)
                }
                Button(
                    onClick = { mensaje = gameManager.jugadorEspecial(); cambioTurno() },
                    modifier = Modifier.align(Alignment.BottomCenter),
                    colors = ButtonDefaults.textButtonColors(
                        backgroundColor = Color(204,122,0),
                        contentColor = Color.White),
                    enabled = accionJugador
                ) {
                    Text("Ataque Especial", fontWeight = FontWeight.Bold)
                }
            }
        }
    }
}

//Función que nos servirá para generar enemigos según las necesidades
//En la primera fase elige a un Pokemon de tipo debil respecto al jugador
//En la segunda fase elige a un Pokemon del mismo tipo que el jugador
//En la tercera y ultima fase elige a un Pokemon de tipo fuerte respecto al jugador
// Contra mayor sea la fase, más dificil será el combate
//Además evoluciona al enemigo generado para estar a la par con el jugador
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
            enemigoDevuelto!!.evolucionControlada(fase)
        }
        2 -> {
            for(x in listaEnemigos)
                if(x.tipo == pokJugador.debilidades)
                    enemigoDevuelto = x
            enemigoDevuelto!!.evolucionControlada(fase)
        }
    }
    return enemigoDevuelto!!
}