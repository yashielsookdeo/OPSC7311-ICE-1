package com.example.wordlegame

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import androidx.appcompat.app.AppCompatActivity

class HomeActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home)

        val btnPlay = findViewById<Button>(R.id.btnPlay)
        val username = findViewById<EditText>(R.id.txtUsername)

        btnPlay.setOnClickListener() {
            val intent = Intent(this, MainActivity::class.java)

            intent.putExtra("Username", username.text.toString())
            startActivity(intent)
            this.finish()
        }
    }
}