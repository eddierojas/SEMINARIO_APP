package com.example.usuarios_yya

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import com.google.firebase.FirebaseApp
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore




enum class ProviderType{
    BASIC
}
class HomeActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home)
        val db = FirebaseFirestore.getInstance()
        val bundle = intent.extras
        val email= bundle?.getString("email")
        val provider= bundle?.getString("provider")

        setup(email?:"", provider?:"")
    }

    private fun setup(email: String, provider:String){
        title = "Inicio"
        val emailU = findViewById<TextView>(R.id.emailText)
        val prov = findViewById<TextView>(R.id.provider)
        emailU.text=email;
        prov.text=provider

        findViewById<Button>(R.id.logout).setOnClickListener{
            FirebaseAuth.getInstance().signOut()
            onBackPressed()
        }

    }
}