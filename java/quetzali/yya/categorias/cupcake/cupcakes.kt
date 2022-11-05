package quetzali.yya.categorias.cupcake

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
import quetzali.yya.dba.Cosmeticodba
import quetzali.yya.dba.Cupcakedba

class cupcakes : AppCompatActivity() {
    /**
     * Iniciando la rama principal de la base de datos
     * */
    private val cupcoleccion: CollectionReference
    init {
        FirebaseApp.initializeApp(this)
        cupcoleccion = FirebaseFirestore.getInstance().collection("cupcakes")
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.categoria_cupcakes)

        var salvar = findViewById<Button>(R.id.btn_cupcakes)
        var nombre = findViewById<EditText>(R.id.calzadonomemp)
        var zona = findViewById<EditText>(R.id.calzadozona)
        var propietario = findViewById<EditText>(R.id.cuppromi)
        var descripcion = findViewById<EditText>(R.id.calzadodespro)
        var cantidad = findViewById<EditText>(R.id.calzadocanti)
        var precio = findViewById<EditText>(R.id.calzadoprecio)
        salvar.setOnClickListener {
            cupcakerropa(
                Cupcakedba(
                    nombre.text.toString(),
                    zona.text.toString(),
                    propietario.text.toString(),
                    descripcion.text.toString(),
                    cantidad.text.toString().toInt(),
                    precio.text.toString().toDouble()
                )
            )

        }
        listacupcake()
    }
    /**
     * Funcion para salvar registro en ropaje
     * */
    private fun cupcakerropa(cupcakedba: Cupcakedba){
        cupcoleccion.add(cupcakedba).addOnSuccessListener {
            Toast.makeText(this, "Registro Guardado", Toast.LENGTH_SHORT).show()
        }.addOnFailureListener {
            Toast.makeText(this,"Error guardado", Toast.LENGTH_SHORT).show()
        }
    }
    /**
     * Funcion para leer la informacion menor escala
     * */
    private fun listacupcake(){
        cupcoleccion.addSnapshotListener{
                snapshots, error ->
            if(error ==null){
                val changes =snapshots?.documentChanges
                if(changes !=null){
                    addChangescup(changes)
                }
            }else{
                Toast.makeText(this, "Ha ocurrido un error", Toast.LENGTH_SHORT).show()
            }
        }
    }
    /**
     * Cambios
     * */
    private fun addChangescup(changes: List<DocumentChange>){
        for (change in changes){
            if(change.type == DocumentChange.Type.ADDED){
                AddToListcup(change.document.toObject(Cupcakedba::class.java))
            }
        }
    }
    /**
     * Lista
     * */
    private fun AddToListcup(cupcakedba: Cupcakedba){
        var text= findViewById<TextView>(R.id.textView12).text.toString()
        text += cupcakedba.toString()+"\n"
        text=text
    }
}