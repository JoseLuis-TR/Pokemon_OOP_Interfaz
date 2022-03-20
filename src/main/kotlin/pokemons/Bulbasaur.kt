package pokemons

import interfacesTipos.PokPlanta
import Enum.Nombres
import Enum.Tipos

class Bulbasaur(nombre: Nombres = Nombres.BULBASAUR,
                potenciaAtaque:Float = 118f,
                vida:Float = 400f,
                defensa:Float = 111f,
                experiencia:Float = 0f,
                tipo: Tipos = Tipos.PLANTA,
                debilidades: Tipos = Tipos.FUEGO,
                fortalezas: Tipos = Tipos.AGUA,
                override var tiempoAlSol: Float= 0f,
                override var limite: Float = 3f,
                contador:Int = 0
):Pokemon(nombre, potenciaAtaque, vida, defensa, experiencia, tipo, debilidades, fortalezas, contador),PokPlanta {

    override var vidaTotal = vida

    //Su modificador tiempoAlSol afecta al ataque por lo que es necesario
    //hacer override de la función atacar
    override fun atacar(enemigo:Pokemon): Float
    {
        return super.atacar(enemigo) * (1+(tiempoAlSol/10))
    }

    //Función usada para evolucionar, en esta clase cambia el nombre
    //en la clase padre se modifican vida, ataque y defensa
    override fun evolucionar()
    {
        when(nombre)
        {
            Nombres.BULBASAUR -> nombre = Nombres.IVISAUR
            Nombres.IVISAUR -> nombre = Nombres.VENUSAUR
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
    override fun especial(): String
    {
        contador = 2
        return "$nombre " + cogerSol()
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