package pokemons

import Enum.Nombres
import Enum.Tipos

abstract class Pokemon(var nombre: Nombres = Nombres.MISSIGNO,
                       var potenciaAtaque:Float = 5f,
                       var vida:Float = 100f,
                       var defensa:Float = 10f,
                       var experiencia:Float = 0f,
                       var tipo: Tipos,
                       var debilidades: Tipos,
                       var fortalezas: Tipos,
                       var contador:Int) {

    open var vidaTotal:Float = vida
    //La vida que se modifica durante la pelea es esta variable, asi mantenemos la vida del pokemon intacta

    fun saludo(): String
    {
        return "Hola muy buenas soy $nombre"
    }

    //Función que cura al pokemon una cantidad al azar entre 20 y 100
    //Al ser llamada, llama a la función contador que reduce el contador de la hab. especial
    fun curar(): Int {
        val cura = (20..100).random()
        vidaTotal += cura
        if(vidaTotal > vida) vidaTotal=vida
        contador()
        return cura
    }

    fun graciasPorLaCura()
    {
        println("¡Gracias por la cura entrenador!")
    }

    //Funcion usada para calcular el ataque, teniendo en cuenta la potencia de ataque del pokemon que ataca,
    //la defensa del pokemon atacado y el tipo del pokemon atacado
    open fun atacar(enemigo:Pokemon): Float
    {
        var modificador: Float
        when
        {
            //Dependiendo de si el pokemon al que se ataca es debil, fuerte o indiferente al tipo del pokemon
            //que ataca el modificador usado sera mayor o menor
            enemigo.tipo == fortalezas -> modificador = 1.5f
            enemigo.tipo == debilidades -> modificador = 0.5f
            else -> modificador = 1f
        }
        contador()

        return (potenciaAtaque/enemigo.defensa) * (potenciaAtaque*modificador) * 0.5f * (1..2).random()+1
    }

    //Función usada para aumentar los parametros del pokemon al evolucionar
    open fun evolucionar()
    {
        experiencia = 0f
        vida *= 1.2f
        potenciaAtaque *= 1.2f
    }

    //Función usada cuando necesitamos que el pokemon usado por la maquina
    //evolucine al mismo nivel que el pokemon del jugador
    open fun evolucionControlada(turno:Int)
    {
        for(x in 0 until turno)
            evolucionar()
    }

    //Función usada para bajar la cantidad de vida del pokemon
    open fun danyoRecibido(danyo:Float): Float
    {
        vidaTotal -= danyo
        if(vidaTotal < 0)
            vidaTotal = 0f
        return danyo
    }

    //Función que se sobrescribe según que pokemon la llame y se usa para aumentar el modificador de ataque/defensa
    open fun especial(): String
    {
        return "Accion especial"
    }

    //Función usada para modificar el contador
    open fun contador()
    {
        if(contador == 0) contador = 2
        else contador -= 1
    }
}