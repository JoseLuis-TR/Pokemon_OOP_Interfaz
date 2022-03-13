// Copyright 2000-2021 JetBrains s.r.o. and contributors. Use of this source code is governed by the Apache 2.0 license that can be found in the LICENSE file.
import androidx.compose.material.MaterialTheme
import androidx.compose.desktop.ui.tooling.preview.Preview
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.loadImageBitmap
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.useResource
import androidx.compose.ui.unit.DpSize
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Window
import androidx.compose.ui.window.WindowState
import androidx.compose.ui.window.application
import pantallas.Final

@Preview
@Composable
fun App() {
    Image(
        modifier = Modifier.fillMaxSize(),
        contentScale = ContentScale.Crop,
        painter = painterResource("fondoseleccion.png"),
        contentDescription = "descripcion")
    Column(horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier.fillMaxWidth()) {
        Box(
            modifier = Modifier.size(400.dp, 200.dp).padding(top = 20.dp),

            ){
            Image(
                bitmap = useResource("blastoiseback.png") { loadImageBitmap(it) },
                contentDescription = "hola"
            )
            Image(
                bitmap = useResource("paneltexto.png") { loadImageBitmap(it) },
                contentDescription = "logo del juego"
            )
            Text("¡Bienvenido!", modifier = Modifier.align(Alignment.Center).padding(bottom = 110.dp))
            Text("ELIGE A UN POKEMON PARA COMBATIR", modifier = Modifier.align(Alignment.Center).padding(bottom = 30.dp))
        }
    }
}

fun main() = application {
    Window(onCloseRequest = ::exitApplication,
            icon = painterResource("icono.png"),
            title = "Pokemon Edicion OOP",
            resizable = false,
            state = WindowState(size = DpSize(800.dp, 600.dp))
    ) {
       
        Final()

    }
}
