package interfacesTipos

interface PokFuego {
    //temperatura marca el modificador en ataque por el movimiento especial
    var temperatura:Float
    //limite marca el limite, si se llega el Pokemon pierde el modificador
    var limite:Float

    //Modifica temperatura dependiendo de si llega al limite o no
    //si se llega al limite, elimina automaticamente el modificador con temperaturaNormal()
    fun calentarFuego(): String {
        when(temperatura)
        {
            limite ->
            {
                temperaturaNormal()
                return "llegó al limite, pierde el bonus."
            }
            else ->
            {
                temperatura += (1..10).random()
                return "recibe un bonus de ataque calentandose"
            }
        }
    }

    //Devuelve directamente a 20 el modificador
    fun temperaturaNormal()
    {
        temperatura = 20f
    }

    //Es la función usada para bajar x puntos el modificador de este pokemon
    fun nerfeo()
    {
        if(temperatura > 20)
        {
            temperatura -= (1..5).random()
            if(temperatura < 20f) temperatura = 20f
        }
    }
}