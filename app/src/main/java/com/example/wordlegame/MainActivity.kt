package com.example.wordlegame

import android.os.Bundle
import com.google.android.material.snackbar.Snackbar
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.navigateUp
import androidx.navigation.ui.setupActionBarWithNavController
import android.view.Menu
import android.view.MenuItem
import android.widget.Button
import com.example.wordlegame.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

   private lateinit var binding: ActivityMainBinding
   var numAttempts = 0
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val game = Game(binding, this)
        val word = game.getRandomWord()
        val userName = intent.getStringExtra("Username")

        binding.txtWelcome.setText("Welcome $userName")

        game.keepFocus()

        game.loopChild(word)

        val btnPlayAgain = findViewById<Button>(R.id.btnPlayAgain)

        btnPlayAgain.setOnClickListener() {
            val intent =intent
            startActivity(intent)
        }
    }
}