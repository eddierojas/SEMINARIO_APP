package com.example.usuarios_yya

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import com.example.usuarios_yya.databinding.ActivityAuthBinding
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.auth.FirebaseUser
import com.google.firebase.ktx.Firebase


class AuthActivity : AppCompatActivity() {

    lateinit var auth: FirebaseAuth
    lateinit var binding: ActivityAuthBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this,R.layout.activity_auth)

        binding.register.setOnClickListener{
            val intentRegistro = Intent(this, RegistrationAct::class.java)
            startActivity(intentRegistro)
        }
        val db = FirebaseFirestore.getInstance()



        binding.loginbtn.setOnClickListener {
            signIn(
                binding.username.text.toString(),
                binding.password.text.toString()
            )
        }




    }



    private fun signIn(email: String, password: String) {
        // [START sign_in_with_email]
        FirebaseAuth.getInstance().signInWithEmailAndPassword(email, password)
            .addOnCompleteListener(this) { task ->
                if (task.isSuccessful) {

                    Toast.makeText(this, "Sesion iniciada", Toast.LENGTH_SHORT).show()
                    showHome(email, ProviderType.BASIC)

                } else {

                    Toast.makeText(
                        baseContext, "Authentication failed.",
                        Toast.LENGTH_SHORT
                    ).show()

                }
            }
        // [END sign_in_with_email]
    }




    private fun setup(){
        title = "Autenticación"
        var btn = findViewById<Button>(R.id.register)
        var user = findViewById<EditText>(R.id.username)
        var pass = findViewById<EditText>(R.id.password)
        var btnlg = findViewById<Button>(R.id.loginbtn)

      /*  btn.setOnClickListener{
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
        }*/
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