package quetzali.yya.categorias.ropa

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
import quetzali.yya.dba.Ropadba

class ropaje : AppCompatActivity() {
    /**
     * Iniciando la rama principal de la base de datos
     * */
    private val ropacoleccion: CollectionReference
    init {
        FirebaseApp.initializeApp(this)
        ropacoleccion = FirebaseFirestore.getInstance().collection("ropaje")
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.categoria_ropaje)

        var salvar = findViewById<Button>(R.id.btn_ropa)
        var nombre = findViewById<EditText>(R.id.calzadonomemp)
        var zona = findViewById<EditText>(R.id.calzadozona)
        var propietario = findViewById<EditText>(R.id.calzadonombpros)
        var descripcion = findViewById<EditText>(R.id.calzadodespro)
        var cantidad = findViewById<EditText>(R.id.calzadocanti)
        var precio = findViewById<EditText>(R.id.calzadoprecio)
        salvar.setOnClickListener {
            salvarropa(
                Ropadba(
                nombre.text.toString(),
                zona.text.toString(),
                propietario.text.toString(),
                descripcion.text.toString(),
                cantidad.text.toString().toInt(),
                precio.text.toString().toDouble()
            )
            )

        }
        listaropa()
    }
    /**
     * Funcion para salvar registro en ropaje
     * */
    private fun salvarropa(ropadba: Ropadba){
        ropacoleccion.add(ropadba).addOnSuccessListener {
            Toast.makeText(this, "Registro Guardado", Toast.LENGTH_SHORT).show()
        }.addOnFailureListener {
            Toast.makeText(this,"Error guardado", Toast.LENGTH_SHORT).show()
        }
    }
    /**
     * Funcion para leer la informacion menor escala
     * */
    private fun listaropa(){
        ropacoleccion.addSnapshotListener{
                snapshots, error ->
            if(error ==null){
                val changes =snapshots?.documentChanges
                if(changes !=null){
                    addChangesropa(changes)
                }
            }else{
                Toast.makeText(this, "Ha ocurrido un error", Toast.LENGTH_SHORT).show()
            }
        }
    }
    /**
     * Cambios
     * */
    private fun addChangesropa(changes: List<DocumentChange>){
        for (change in changes){
            if(change.type == DocumentChange.Type.ADDED){
                AddToListropa(change.document.toObject(Ropadba::class.java))
            }
        }
    }
    /**
     * Lista
     * */
    private fun AddToListropa(ropadba: Ropadba){
        var text= findViewById<TextView>(R.id.txt_view2).text.toString()
        text += ropadba.toString()+"\n"
        text=text
    }


}