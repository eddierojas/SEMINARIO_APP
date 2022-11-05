package quetzali.yya.dba

class Ropadba(val name: String = "",val zona: String = "", val propietario: String = "", val productodes: String = "", val canti : Int = 0, val precio: Double = 0.0 ) {
    override fun toString()= "$name, $zona, $propietario, $productodes, $canti, $precio"
}