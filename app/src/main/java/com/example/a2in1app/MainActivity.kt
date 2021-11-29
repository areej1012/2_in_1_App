package com.example.a2in1app

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.widget.Button
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {
    lateinit var nunGame :Button
    lateinit var phGame :Button
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        nunGame = findViewById(R.id.numberG)
        phGame = findViewById(R.id.PhraseG)

        numberG.setOnClickListener {
            CreateNumGameActvity()
        }
        phGame.setOnClickListener { CreatePharesGameActvity() }
    }

    private fun CreateNumGameActvity() {
        val intent = Intent(this,NumberGame::class.java)
        startActivity(intent)
    }
    private fun CreatePharesGameActvity() {
        val intent = Intent(this, GuessThePhrase::class.java)
        startActivity(intent)
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.main_menu,menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when(item.itemId){
            R.id.numGame -> {
                CreateNumGameActvity()
                return true
            }
            R.id.phrGameItem -> {
                CreatePharesGameActvity()
                return true
            }
        }
        return super.onOptionsItemSelected(item)
    }
}