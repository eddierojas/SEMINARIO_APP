package quetzali.yya.categorias.juego

import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.google.firebase.FirebaseApp
import com.google.firebase.firestore.CollectionReference
import com.google.firebase.firestore.DocumentChange
import com.google.firebase.firestore.FirebaseFirestore
import quetzali.yya.R
import quetzali.yya.dba.Videojeugodba

class videojuego : AppCompatActivity() {
    /**
     * Iniciando la rama principal de la base de datos
     * */
    private val gamecoleccion: CollectionReference
    init {
        FirebaseApp.initializeApp(this)
        gamecoleccion = FirebaseFirestore.getInstance().collection("videojuego")
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.categoria_juego)

        var salvar = findViewById<Button>(R.id.btn_videojuego)
        var nombre = findViewById<EditText>(R.id.calzadonomemp)
        var zona = findViewById<EditText>(R.id.calzadozona)
        var propietario = findViewById<EditText>(R.id.nomjuego)
        var descripcion = findViewById<EditText>(R.id.calzadodespro)
        var cantidad = findViewById<EditText>(R.id.calzadocanti)
        var precio = findViewById<EditText>(R.id.calzadoprecio)
        salvar.setOnClickListener {
            gamesx(
                Videojeugodba(
                    nombre.text.toString(),
                    zona.text.toString(),
                    propietario.text.toString(),
                    descripcion.text.toString(),
                    cantidad.text.toString().toInt(),
                    precio.text.toString().toDouble()
                )
            )

        }
        listagame()
    }
    /**
     * Funcion para salvar registro en ropaje
     * */
    private fun gamesx(videojeugodba: Videojeugodba){
        gamecoleccion.add(videojeugodba).addOnSuccessListener {
            Toast.makeText(this, "Registro Guardado", Toast.LENGTH_SHORT).show()
        }.addOnFailureListener {
            Toast.makeText(this,"Error guardado", Toast.LENGTH_SHORT).show()
        }
    }
    /**
     * Funcion para leer la informacion menor escala
     * */
    private fun listagame(){
        gamecoleccion.addSnapshotListener{
                snapshots, error ->
            if(error ==null){
                val changes =snapshots?.documentChanges
                if(changes !=null){
                    addChangesgame(changes)
                }
            }else{
                Toast.makeText(this, "Ha ocurrido un error", Toast.LENGTH_SHORT).show()
            }
        }
    }
    /**
     * Cambios
     * */
    private fun addChangesgame(changes: List<DocumentChange>){
        for (change in changes){
            if(change.type == DocumentChange.Type.ADDED){
                AddToListgame(change.document.toObject(Videojeugodba::class.java))
            }
        }
    }
    /**
     * Lista
     * */
    private fun AddToListgame(videojeugodba: Videojeugodba){
        var text= findViewById<TextView>(R.id.textView15).text.toString()
        text += videojeugodba.toString()+"\n"
        text=text
    }
}