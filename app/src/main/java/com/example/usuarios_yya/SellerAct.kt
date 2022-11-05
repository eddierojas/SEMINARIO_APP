package com.example.usuarios_yya

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import androidx.databinding.DataBindingUtil
import com.example.usuarios_yya.databinding.ActivitySellerBinding

class SellerAct : AppCompatActivity() {
    private lateinit var binding: ActivitySellerBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)


        val id: Int
        val name: String
        val category: String
        val price: Float
        val offerPercentage: Float? = null
        val description: String? = null
        val colors: List<String>? = null
        val sizes: List<String>? = null
        val images: List<String>






    }
    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.toolbar_menu, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return super.onOptionsItemSelected(item)
    }
}