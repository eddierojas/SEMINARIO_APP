package quetzali.yya

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import androidx.cardview.widget.CardView
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

findViewById<CardView>(R.id.card1).setOnClickListener{
    val intent = Intent(this, Latiendita::class.java)
    startActivity(intent)

}

        findViewById<CardView>(R.id.card2).setOnClickListener{
            val intent = Intent(this, Pedidos::class.java)
            startActivity(intent)

        }

        findViewById<CardView>(R.id.card3).setOnClickListener{
            val intent = Intent(this, HistorialPedidos::class.java)
            startActivity(intent)

        }

        findViewById<CardView>(R.id.card4).setOnClickListener{
            val intent = Intent(this, Perfil::class.java)
            startActivity(intent)

        }

        findViewById<CardView>(R.id.card5).setOnClickListener{
            FirebaseAuth.getInstance().signOut()
            onBackPressed()

        }

    }

    private fun setup(email: String, provider:String){
        title = "Inicio"
        val emailU = findViewById<TextView>(R.id.emailText)
        val prov = findViewById<TextView>(R.id.provider)
        emailU.text=email;
        prov.text=provider



    }
}