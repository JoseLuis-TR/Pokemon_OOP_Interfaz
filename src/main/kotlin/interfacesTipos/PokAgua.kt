package interfacesTipos

interface PokAgua {
    //tiempoSumergido marca el modificador en defensa por el movimiento especial
    var tiempoSumergido:Int
    //maxResistencia marca el limite, si se llega el Pokemon pierde el modificador
    val maxResistencia:Int

    //Modifica tiempoSumergido dependiendo de si llega al limite o no
    //si se llega al limite, elimina automaticamente el modificador con respirar()
    fun sumergir(): String {
        when(tiempoSumergido)
        {
            maxResistencia ->
            {
                respirar()
                return "llegó al limite, pierde el bonus"
            }
            else -> {
                tiempoSumergido += 1
                return "recibe un bonus en defensa sumergiendose"
            }
        }
    }

    //Devuelve directamente a 0 el modificador
    fun respirar()
    {
        tiempoSumergido = 0
    }

    //Es la función usada para bajar 1 punto el modificador de este pokemon
    fun nerfeo()
    {
        if(tiempoSumergido > 0) { tiempoSumergido -= 1 }
    }
}