package com.example.shoppingchartapp

import android.content.Intent
import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.shoppingchartapp.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private lateinit var sharedPreferences: SharedPreferences

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        sharedPreferences = getSharedPreferences("prefs", MODE_PRIVATE)

        binding.shoppingChartButton.setOnClickListener{
            startActivity(Intent(this, ShoppingListActivity::class.java))
        }

        binding.settingsButton.setOnClickListener{
            startActivity(Intent(this, OptionsActivity::class.java))
        }

        binding.accountButton.setOnClickListener {
            startActivity(Intent(this, LoginActivity::class.java))
        }
    }
}