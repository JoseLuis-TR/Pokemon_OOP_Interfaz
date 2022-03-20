package interfacesTipos

interface PokPlanta {
    //tiempoAlSol marca el modificador en ataque por el movimiento especial
    var tiempoAlSol:Float
    //limite marca el limite, si se llega el Pokemon pierde el modificador
    var limite:Float

    //Modifica tiempoAlSol dependiendo de si llega al limite o no
    //si se llega al limite, elimina automaticamente el modificador con fueraSol()
    fun cogerSol(): String {
        when(tiempoAlSol)
        {
            limite ->
            {
                fueraSol()
                return "llegó al limite, pierde el bonus."
            }
            else ->
            {
                tiempoAlSol += 1
                return "recibe un bonus de ataque gracias al sol."
            }
        }
    }

    //Devuelve directamente a 0 el modificador
    fun fueraSol()
    {
        tiempoAlSol = 0f
    }

    //Es la función usada para bajar x puntos el modificador de este pokemon
    fun nerfeo()
    {
        if(tiempoAlSol > 0) {  tiempoAlSol -= 1 }
    }

}