package com.example.usuarios_yya

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore


class AuthActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_auth)

        val db = FirebaseFirestore.getInstance()
        setup()
    }
    private fun setup(){
        title = "Autenticación"
        var btn = findViewById<Button>(R.id.register)
        var user = findViewById<EditText>(R.id.username)
        var pass = findViewById<EditText>(R.id.password)
        var btnlg = findViewById<Button>(R.id.loginbtn)

        btn.setOnClickListener{
            if (user.text.isNotEmpty() &&
                    pass.text.isNotEmpty()){
                FirebaseAuth.getInstance().createUserWithEmailAndPassword(user.text.toString(),
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
    private fun showAlert(){
        val builder = AlertDialog.Builder(this)
        builder.setTitle("Error en Autenticación")
        builder.setMessage("Datos Erronéos")
        builder.setPositiveButton("Aceptar", null)
        val dialog: AlertDialog = builder.create()
        dialog.show()
    }

    private fun showHome (email: String, provider:ProviderType){

        val homeIntent = Intent(this,HomeActivity::class.java).apply {
            putExtra("email", email)
            putExtra("provider", provider.name)
        }
        startActivity(homeIntent)


    }
}