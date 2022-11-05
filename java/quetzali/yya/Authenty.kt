package quetzali.yya

import android.app.AlertDialog
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import com.google.firebase.auth.FirebaseAuth

class Authenty : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        setup()
    }
    /**
     * setup
     * */
    private fun setup(){
        title="Autenticación"
        var btnlg = findViewById<Button>(R.id.btn_aceptar)
        var btn = findViewById<Button>(R.id.btn_registrar)
        var user = findViewById<EditText>(R.id.username)
        var pass = findViewById<EditText>(R.id.password)

        btn.setOnClickListener{
            if (user.text.isNotEmpty() && pass.text.isNotEmpty()){ FirebaseAuth.getInstance().createUserWithEmailAndPassword(user.text.toString(),
                pass.text.toString()).addOnCompleteListener {
                if (it.isSuccessful){
                    showHome(it.result?.user?.email?:"", ProviderType.BASIC)
                }else{
                    showAlert();
                }
            }

            }
        }

        btnlg.setOnClickListener{
            if (user.text.isNotEmpty() &&
                pass.text.isNotEmpty()){
                FirebaseAuth.getInstance().
                signInWithEmailAndPassword(user.text.toString(),
                    pass.text.toString()).addOnCompleteListener {
                    if (it.isSuccessful){
                        showHome(it.result?.user?.email?:"", ProviderType.BASIC)
                    }else{
                        showAlert();
                    }
                }

            }
        }
    }
    /**
     * Mostrar alerta
     * */
    private fun showAlert(){
        val builder = AlertDialog.Builder(this)
        builder.setTitle("Error")
        builder.setMessage("Se ha producido un error autentificando el usuario, no lo conozco")
        builder.setPositiveButton("Aceptar",null)
        val dialog: AlertDialog =builder.create()
        dialog.show()
    }
    /**
     * Mostrar actividad de inicio
     * */
    private fun showHome(email: String, provider:ProviderType){
        val homeIntent = Intent(this, HomeActivity::class.java).apply {
            putExtra("email",email)
            putExtra("provider",provider.name)
        }
        startActivity(homeIntent)

    }
}