package pokemons

import interfacesTipos.PokAgua
import Enum.Nombres
import Enum.Tipos

class Squirtle(nombre: Nombres = Nombres.SQUIRTLE,
               potenciaAtaque:Float = 94f,
               vida:Float = 400f,
               defensa:Float = 121f,
               experiencia:Float = 0f,
               tipo: Tipos = Tipos.AGUA,
               debilidades: Tipos = Tipos.PLANTA,
               fortalezas: Tipos = Tipos.FUEGO,
               override var tiempoSumergido: Int = 0,
               override val maxResistencia: Int = 4,
               contador:Int = 0
): Pokemon(nombre,potenciaAtaque,vida,defensa,experiencia,tipo,debilidades,fortalezas,contador),PokAgua {

    override var vidaTotal = vida

    //Su modificador tiempoSumergido afecta a la defensa por lo que es necesario
    //hacer override de la función danyoRecibido
    override fun danyoRecibido(danyo:Float): Float {
        var danyoTotal = danyo + (danyo * (0 + (tiempoSumergido/10)))
        vidaTotal -= danyoTotal
        if(vidaTotal < 0f)
            vidaTotal = 0f
        return danyoTotal
    }

    //Función usada para evolucionar, en esta clase cambia el nombre
    //en la clase padre se modifican vida, ataque y defensa
    override fun evolucionar()
    {
        when(nombre)
        {
            Nombres.SQUIRTLE -> nombre = Nombres.WARTORTLE
            Nombres.WARTORTLE -> nombre = Nombres.BLASTOISE
        }
        super.evolucionar()
    }

    //Función usada cuando necesitamos que el pokemon usado por la maquina
    //evolucine al mismo nivel que el pokemon del jugador
    override fun evolucionControlada(turno: Int) {
        for(x in 0 until turno)
            evolucionar()
    }

    //Llama a la habilidad especial que aumenta su modificador
    //además reinicia el contador a 2
    override fun especial(): String {
        contador = 2
        return "$nombre" + sumergir()
    }

    //Controla el contador, si este llega a 0 se nerfea el modificador
    //del pokemon. Si el modificador esta a cero, no hace nada
    override fun contador()
    {
        if(contador == 0)
        {
            nerfeo()
            contador = 2
        }
        else contador -= 1
    }
}