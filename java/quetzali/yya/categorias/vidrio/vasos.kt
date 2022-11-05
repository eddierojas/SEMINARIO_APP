package quetzali.yya.categorias.vidrio

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
import quetzali.yya.dba.Cupcakedba
import quetzali.yya.dba.Vasodba

class vasos : AppCompatActivity() {
    /**
     * Iniciando la rama principal de la base de datos
     * */
    private val vascoleccion: CollectionReference
    init {
        FirebaseApp.initializeApp(this)
        vascoleccion = FirebaseFirestore.getInstance().collection("vidrio")
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.categoria_vidrio)

        var salvar = findViewById<Button>(R.id.btn_vasos)
        var nombre = findViewById<EditText>(R.id.calzadonomemp)
        var zona = findViewById<EditText>(R.id.calzadozona)
        var propietario = findViewById<EditText>(R.id.vasosnom)
        var descripcion = findViewById<EditText>(R.id.calzadodespro)
        var cantidad = findViewById<EditText>(R.id.calzadocanti)
        var precio = findViewById<EditText>(R.id.calzadoprecio)
        salvar.setOnClickListener {
            cupcakevas(
                Vasodba(
                    nombre.text.toString(),
                    zona.text.toString(),
                    propietario.text.toString(),
                    descripcion.text.toString(),
                    cantidad.text.toString().toInt(),
                    precio.text.toString().toDouble()
                )
            )

        }
        listavas()
    }
    /**
     * Funcion para salvar registro en ropaje
     * */
    private fun cupcakevas(vasodba: Vasodba){
        vascoleccion.add(vasodba).addOnSuccessListener {
            Toast.makeText(this, "Registro Guardado", Toast.LENGTH_SHORT).show()
        }.addOnFailureListener {
            Toast.makeText(this,"Error guardado", Toast.LENGTH_SHORT).show()
        }
    }
    /**
     * Funcion para leer la informacion menor escala
     * */
    private fun listavas(){
        vascoleccion.addSnapshotListener{
                snapshots, error ->
            if(error ==null){
                val changes =snapshots?.documentChanges
                if(changes !=null){
                    addChangesvas(changes)
                }
            }else{
                Toast.makeText(this, "Ha ocurrido un error", Toast.LENGTH_SHORT).show()
            }
        }
    }
    /**
     * Cambios
     * */
    private fun addChangesvas(changes: List<DocumentChange>){
        for (change in changes){
            if(change.type == DocumentChange.Type.ADDED){
                AddToListvas(change.document.toObject(Vasodba::class.java))
            }
        }
    }
    /**
     * Lista
     * */
    private fun AddToListvas(vasodba: Vasodba){
        var text= findViewById<TextView>(R.id.textView14).text.toString()
        text += vasodba.toString()+"\n"
        text=text
    }
}