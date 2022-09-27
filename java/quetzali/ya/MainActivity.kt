package quetzali.ya

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import quetzali.ya.categorias.bebida.licor
import quetzali.ya.categorias.cosmeticos.cosmetiquera
import quetzali.ya.categorias.cupcake.cupcakes
import quetzali.ya.categorias.platillos.platos
import quetzali.ya.categorias.ropa.ropaje
import quetzali.ya.categorias.vidrio.vasos
import quetzali.ya.categorias.zapatos.calzado
import quetzali.ya.categorias.juego.videojuego

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }
    /*
     * Ingreso a categoria Zapato
      * */
    fun categoriaZapato(view: View){
        val intent: Intent = Intent(this, calzado::class.java).apply {  }
        startActivity(intent)
    }
    /*
    * Ingreseo a categorioa Ropaje
    * */
    fun categoriaRopaje(view: View){
        val intent: Intent = Intent(this, ropaje::class.java).apply {  }
        startActivity(intent)
    }
    /*
    * Ingreso a Categorioa Cosmetiquera
    * */
    fun categoriaCosmeticos(view: View){
        val intent: Intent = Intent(this, cosmetiquera::class.java).apply {  }
        startActivity(intent)
    }
    /*
    * Ingreso a Categoria Platillo
    * */
    fun categoriaPlatos(view: View){
        val intent: Intent = Intent(this, platos::class.java).apply {  }
        startActivity(intent)
    }
    /*
    * Ingreso a Categoria Cupcakes
    * */
    fun categoriaCupcakes(view: View){
        val intent: Intent = Intent(this, cupcakes::class.java).apply {  }
        startActivity(intent)
    }
    /*
    * Ingreso a Categoria Licor
    * */
    fun categoriaLicor(view: View){
        val intent: Intent = Intent(this, licor::class.java).apply {  }
        startActivity(intent)
    }
    /*
    * Ingreso a Categoria vidrio
    * */
    fun categoriaVidrio(view: View){
        val intent: Intent = Intent(this, vasos::class.java).apply {  }
        startActivity(intent)
    }
    /*
    * Ingreso a Categoria Video Juegos
    * */
    fun categoriaJuego(view: View){
        val intent: Intent = Intent(this, videojuego::class.java).apply {  }
        startActivity(intent)
    }
}