// Copyright 2000-2021 JetBrains s.r.o. and contributors. Use of this source code is governed by the Apache 2.0 license that can be found in the LICENSE file.

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.DpSize
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Window
import androidx.compose.ui.window.WindowState
import androidx.compose.ui.window.application
import pantallas.Final
import pantallas.PantallaCombate
import pantallas.PantallaSeleccion
import pokemons.Pokemon

@Composable
fun App() {
    //El centro desde donde se comienza el juego y sirve como cambio de pantallas
    var pantallaSeleccionada by remember { mutableStateOf(0) }
    val cambiarPantalla:(Int) -> Unit = {pantallaSeleccionada = it}
    var pokJugador by remember { mutableStateOf<Pokemon?>(null) }
    val elegirPokemon:(Pokemon)->Unit = { pokJugador = it }

    Image(
        modifier = Modifier.fillMaxSize(),
        contentScale = ContentScale.Crop,
        painter = painterResource("fondoseleccion.png"),
        contentDescription = "descripcion")

    when(pantallaSeleccionada){
        0 -> PantallaSeleccion(cambiarPantalla,elegirPokemon)
        1 -> PantallaCombate(cambiarPantalla,pokJugador!!)
        2 -> Final(cambiarPantalla,true)
        3 -> Final(cambiarPantalla,false)
    }
}

fun main() = application {
    Window(onCloseRequest = ::exitApplication,
        icon = painterResource("icono.png"),
        title = "Pokemon Edicion OOP",
        resizable = false,
        state = WindowState(size = DpSize(800.dp, 600.dp))
    ) {
        App()
    }
}