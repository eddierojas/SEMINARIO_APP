package quetzali.yya.categorias.zapatos


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
import quetzali.yya.dba.Calzadodba

class calzado : AppCompatActivity() {
    /**
     * Iniciando la rama principal de la base de datos
     * */
    private val zapadocoleccion: CollectionReference
    init {
        FirebaseApp.initializeApp(this)
        zapadocoleccion = FirebaseFirestore.getInstance().collection("calzado")
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.categoria_zapato)
        var salvar = findViewById<Button>(R.id.btn_salvar)
        var nombre = findViewById<EditText>(R.id.calzadonomemp)
        var zona = findViewById<EditText>(R.id.calzadozona)
        var propietario = findViewById<EditText>(R.id.calzadonombpro)
        var descripcion = findViewById<EditText>(R.id.calzadodespro)
        var cantidad = findViewById<EditText>(R.id.calzadocanti)
        var precio = findViewById<EditText>(R.id.calzadoprecio)
        salvar.setOnClickListener {
            salvarcalzado(Calzadodba(
                nombre.text.toString(),
                zona.text.toString(),
                propietario.text.toString(),
                descripcion.text.toString(),
                cantidad.text.toString().toInt(),
                precio.text.toString().toDouble()
            ))

        }
        listacalzado()
    }
    /**
     * Funcion para salvar registro en calzado
     * */
    private fun salvarcalzado(calzadodba: Calzadodba){
        zapadocoleccion.add(calzadodba).addOnSuccessListener {
            Toast.makeText(this, "Registro Guardado", Toast.LENGTH_SHORT).show()
        }.addOnFailureListener {
            Toast.makeText(this,"Error guardado",Toast.LENGTH_SHORT).show()
        }
    }
    /**
     * Funcion para leer la informacion menor escala
     * */
    private fun listacalzado(){
        zapadocoleccion.addSnapshotListener{
            snapshots, error ->
            if(error ==null){
                val changes =snapshots?.documentChanges
                if(changes !=null){
                    addChanges(changes)
                }
            }else{
                Toast.makeText(this, "Ha ocurrido un error", Toast.LENGTH_SHORT).show()
            }
        }
    }
    /**
     * Cambios
     * */
    private fun addChanges(changes: List<DocumentChange>){
        for (change in changes){
            if(change.type == DocumentChange.Type.ADDED){
                AddToList(change.document.toObject(Calzadodba::class.java))
            }
        }
    }
    /**
     * Lista
     * */
    private fun AddToList(calzadodba: Calzadodba){
        var text= findViewById<TextView>(R.id.txt_view).text.toString()
        text += calzadodba.toString()+"\n"
        text=text
    }

}
