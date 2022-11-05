package com.example.usuarios_yya

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import androidx.cardview.widget.CardView
import com.google.firebase.FirebaseApp
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore
import androidx.databinding.DataBindingUtil
import com.example.usuarios_yya.databinding.ActivityHomeBinding
import com.google.firebase.auth.ktx.auth
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase




enum class ProviderType{
    BASIC
}
class HomeActivity : AppCompatActivity() {
    private lateinit var binding: ActivityHomeBinding
    private lateinit var auth: FirebaseAuth


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_home)


        val bundle = intent.extras
        val email= bundle?.getString("email")
        val provider= bundle?.getString("provider")


        auth = Firebase.auth

        val currentUser = auth.currentUser
        val uid = currentUser!!.uid
        val db = Firebase.firestore

        db.collection("users").document(uid).get().addOnSuccessListener {
            binding.eTxtNombreUser.text = ("Nombre: " + it.get("nombre") as String?)
            binding.eTxtApellidoUser.text = ("Apellido: " + it.get("apellido") as String?)
            binding.eTxtCorreoUser.text = ("Correo: " + it.get("correo") as String?)
            binding.eTxtTelefonoUser.text = ("Telefono: " + it.get("telefono") as String?)
            binding.eTxtGeneroUser.text = ("Genero: " + it.get("genero") as String?)
            binding.eTxtfechaUser.text = ("Fecha de nacimiento: " + it.get("fechanacimiento") as String?)
            binding.eTxtPaisUser.text = ("Pais: " + it.get("pais") as String?)
            binding.eTxtProvinciaUser.text = ("Provincia: " + it.get("provincia") as String?)
            val prueba1 =it.get("seller");
            binding.eTxtDireccionUser.text = ("Direccion: " + prueba1 as String?)

            if (prueba1=="1"){
                val intent = Intent(this, SellerAct::class.java)
                startActivity(
                    intent
                )
            }

        }

        binding.btnLogout.setOnClickListener {
            cerrarSesion()
        }







    }

    private fun cerrarSesion() {
        auth.signOut()
        val intent = Intent(this, AuthActivity::class.java)
        startActivity(
            intent
        )
    }


    override fun onStart() {
        super.onStart()

        val currentUser = auth.currentUser
        if (currentUser != null) {
            reload()
        } else {

        }
    }

    private fun reload() {

    }


}