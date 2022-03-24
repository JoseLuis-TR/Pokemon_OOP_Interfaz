import pokemons.Pokemon

class GameManager(var jugador: Pokemon, var maquina: Pokemon)
{
    fun jugadorAtaca(): String
    {
        val danyo = maquina.danyoRecibido(jugador.atacar(maquina)).toInt()
        return "${jugador.nombre} ataca a ${maquina.nombre} y le hace $danyo de daño"
    }

    fun maquinaAtaca(): String
    {
        val danyo = jugador.danyoRecibido(maquina.atacar(jugador)).toInt()
        return "${maquina.nombre} ataca a ${jugador.nombre} y le hace $danyo de daño"
    }

    fun jugadorCura(): String
    {
        return "${jugador.nombre} recupera ${jugador.curar()} puntos de vida"
    }

    fun maquinaCura(): String
    {
        return "${maquina.nombre} recupera ${maquina.curar()}"
    }

    fun jugadorEspecial(): String
    {
        return jugador.especial()
    }

    fun maquinaEspecial(): String
    {
        return maquina.especial()
    }
}