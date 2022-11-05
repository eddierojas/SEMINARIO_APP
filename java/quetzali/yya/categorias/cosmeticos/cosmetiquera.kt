package quetzali.yya.categorias.cosmeticos

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
import quetzali.yya.dba.Ropadba

class cosmetiquera : AppCompatActivity() {
    /**
     * Iniciando la rama principal de la base de datos
     * */
    private val cosmocoleccion: CollectionReference
    init {
        FirebaseApp.initializeApp(this)
        cosmocoleccion = FirebaseFirestore.getInstance().collection("cosmetiquera")
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.categoria_cosmetiquera)

        var salvar = findViewById<Button>(R.id.btn_cosmo)
        var nombre = findViewById<EditText>(R.id.calzadonomemp)
        var zona = findViewById<EditText>(R.id.calzadozona)
        var propietario = findViewById<EditText>(R.id.nompro)
        var descripcion = findViewById<EditText>(R.id.calzadodespro)
        var cantidad = findViewById<EditText>(R.id.calzadocanti)
        var precio = findViewById<EditText>(R.id.calzadoprecio)
        salvar.setOnClickListener {
            cosmorropa(
                Cosmeticodba(
                    nombre.text.toString(),
                    zona.text.toString(),
                    propietario.text.toString(),
                    descripcion.text.toString(),
                    cantidad.text.toString().toInt(),
                    precio.text.toString().toDouble()
                )
            )

        }
        listacosmo()
    }
    /**
     * Funcion para salvar registro en ropaje
     * */
    private fun cosmorropa(cosmeticodba: Cosmeticodba){
        cosmocoleccion.add(cosmeticodba).addOnSuccessListener {
            Toast.makeText(this, "Registro Guardado", Toast.LENGTH_SHORT).show()
        }.addOnFailureListener {
            Toast.makeText(this,"Error guardado", Toast.LENGTH_SHORT).show()
        }
    }
    /**
     * Funcion para leer la informacion menor escala
     * */
    private fun listacosmo(){
        cosmocoleccion.addSnapshotListener{
                snapshots, error ->
            if(error ==null){
                val changes =snapshots?.documentChanges
                if(changes !=null){
                    addChangescosmo(changes)
                }
            }else{
                Toast.makeText(this, "Ha ocurrido un error", Toast.LENGTH_SHORT).show()
            }
        }
    }
    /**
     * Cambios
     * */
    private fun addChangescosmo(changes: List<DocumentChange>){
        for (change in changes){
            if(change.type == DocumentChange.Type.ADDED){
                AddToListcosmo(change.document.toObject(Cosmeticodba::class.java))
            }
        }
    }
    /**
     * Lista
     * */
    private fun AddToListcosmo(cosmeticodba: Cosmeticodba){
        var text= findViewById<TextView>(R.id.txt_view2).text.toString()
        text += cosmeticodba.toString()+"\n"
        text=text
    }
}