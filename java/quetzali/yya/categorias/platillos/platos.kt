package quetzali.yya.categorias.platillos

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
import quetzali.yya.dba.Comidadba
import quetzali.yya.dba.Cosmeticodba
import quetzali.yya.dba.Ropadba

class platos : AppCompatActivity() {
    /**
     * Iniciando la rama principal de la base de datos
     * */
    private val cosmidacoleccion: CollectionReference
    init {
        FirebaseApp.initializeApp(this)
        cosmidacoleccion = FirebaseFirestore.getInstance().collection("comida")
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.categoria_platos)

        var salvar = findViewById<Button>(R.id.btn_comida)
        var nombre = findViewById<EditText>(R.id.calzadonomemp)
        var zona = findViewById<EditText>(R.id.calzadozona)
        var propietario = findViewById<EditText>(R.id.propiet)
        var descripcion = findViewById<EditText>(R.id.calzadodespro)
        var cantidad = findViewById<EditText>(R.id.calzadocanti)
        var precio = findViewById<EditText>(R.id.calzadoprecio)
        salvar.setOnClickListener {
            cosmida(
                Comidadba(
                    nombre.text.toString(),
                    zona.text.toString(),
                    propietario.text.toString(),
                    descripcion.text.toString(),
                    cantidad.text.toString().toInt(),
                    precio.text.toString().toDouble()
                )
            )

        }
        listacosmida()
    }
    /**
     * Funcion para salvar registro en ropaje
     * */
    private fun cosmida(comidadba: Comidadba){
        cosmidacoleccion.add(comidadba).addOnSuccessListener {
            Toast.makeText(this, "Registro Guardado", Toast.LENGTH_SHORT).show()
        }.addOnFailureListener {
            Toast.makeText(this,"Error guardado", Toast.LENGTH_SHORT).show()
        }
    }
    /**
     * Funcion para leer la informacion menor escala
     * */
    private fun listacosmida(){
        cosmidacoleccion.addSnapshotListener{
                snapshots, error ->
            if(error ==null){
                val changes =snapshots?.documentChanges
                if(changes !=null){
                    addChangescosmida(changes)
                }
            }else{
                Toast.makeText(this, "Ha ocurrido un error", Toast.LENGTH_SHORT).show()
            }
        }
    }
    /**
     * Cambios
     * */
    private fun addChangescosmida(changes: List<DocumentChange>){
        for (change in changes){
            if(change.type == DocumentChange.Type.ADDED){
                AddToListcosmida(change.document.toObject(Comidadba::class.java))
            }
        }
    }
    /**
     * Lista
     * */
    private fun AddToListcosmida(comidadba: Comidadba){
        var text= findViewById<TextView>(R.id.textView11).text.toString()
        text += comidadba.toString()+"\n"
        text=text
    }
}