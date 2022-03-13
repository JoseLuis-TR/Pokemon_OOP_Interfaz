package pokemons

import interfacesTipos.PokFuego
import Enum.Nombres
import Enum.Tipos

class Charmander(nombre: Nombres = Nombres.CHARMANDER,
                 potenciaAtaque:Float = 116f,
                 vida:Float = 400f,
                 defensa:Float = 93f,
                 experiencia:Float = 0f,
                 tipo: Tipos = Tipos.FUEGO,
                 debilidades: Tipos = Tipos.AGUA,
                 fortalezas: Tipos = Tipos.PLANTA,
                 override var temperatura: Float = 20f,
                 override var limite: Float = 40f,
                 contador:Int = 0
): Pokemon(nombre, potenciaAtaque, vida, defensa, experiencia, tipo,debilidades, fortalezas, contador),PokFuego {

    //Su modificador temperatura afecta al ataque por lo que es necesario
    //hacer override de la función atacar
    override fun atacar(enemigo:Pokemon): Float
    {
        return if(temperatura == 20f) super.atacar(enemigo)
        else super.atacar(enemigo) * (1+(temperatura/100))
    }

    //Función usada para evolucionar, en esta clase cambia el nombre
    //en la clase padre se modifican vida, ataque y defensa
    override fun evolucionar()
    {
        when(nombre)
        {
            Nombres.CHARMANDER -> nombre = Nombres.CHARMELEON
            Nombres.CHARMELEON -> nombre = Nombres.CHARIZARD
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
        return "$nombre" + calentarFuego()
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