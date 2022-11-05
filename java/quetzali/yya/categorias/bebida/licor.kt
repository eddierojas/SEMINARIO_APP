package quetzali.yya.categorias.bebida

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
import quetzali.yya.R.id.bnt_licor
import quetzali.yya.dba.Cupcakedba
import quetzali.yya.dba.Licodba

class licor : AppCompatActivity() {
    /**
     * Iniciando la rama principal de la base de datos
     * */
    private val licorcoleccion: CollectionReference
    init {
        FirebaseApp.initializeApp(this)
        licorcoleccion = FirebaseFirestore.getInstance().collection("licor")
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.categoria_licor)

        var salvar = findViewById<Button>(bnt_licor)
        var nombre = findViewById<EditText>(R.id.calzadonomemp)
        var zona = findViewById<EditText>(R.id.calzadozona)
        var propietario = findViewById<EditText>(R.id.nomlic)
        var descripcion = findViewById<EditText>(R.id.calzadodespro)
        var cantidad = findViewById<EditText>(R.id.calzadocanti)
        var precio = findViewById<EditText>(R.id.calzadoprecio)
        salvar.setOnClickListener {
            licors(
                Licodba(
                    nombre.text.toString(),
                    zona.text.toString(),
                    propietario.text.toString(),
                    descripcion.text.toString(),
                    cantidad.text.toString().toInt(),
                    precio.text.toString().toDouble()
                )
            )

        }
        listalicor()
    }

    /**
     * Funcion para salvar registro en ropaje
     * */
    private fun licors(licodba: Licodba){
        licorcoleccion.add(licodba).addOnSuccessListener {
            Toast.makeText(this, "Registro Guardado", Toast.LENGTH_SHORT).show()
        }.addOnFailureListener {
            Toast.makeText(this,"Error guardado", Toast.LENGTH_SHORT).show()
        }
    }
    /**
     * Funcion para leer la informacion menor escala
     * */
    private fun listalicor(){
        licorcoleccion.addSnapshotListener{
                snapshots, error ->
            if(error ==null){
                val changes =snapshots?.documentChanges
                if(changes !=null){
                    addChangeslicor(changes)
                }
            }else{
                Toast.makeText(this, "Ha ocurrido un error", Toast.LENGTH_SHORT).show()
            }
        }
    }
    /**
     * Cambios
     * */
    private fun addChangeslicor(changes: List<DocumentChange>){
        for (change in changes){
            if(change.type == DocumentChange.Type.ADDED){
                AddToListlicor(change.document.toObject(Licodba::class.java))
            }
        }
    }
    /**
     * Lista
     * */
    private fun AddToListlicor(licodba: Licodba){
        var text= findViewById<TextView>(R.id.textView13).text.toString()
        text += licodba.toString()+"\n"
        text=text
    }
}