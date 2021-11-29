package com.example.a2in1app

import android.content.DialogInterface
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.widget.Button
import android.widget.TextView
import androidx.appcompat.app.AlertDialog
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.core.text.isDigitsOnly
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.snackbar.Snackbar
import kotlin.random.Random

class NumberGame : AppCompatActivity() {
    lateinit var inputUser : TextView
    lateinit var submit : Button
    var  items = ArrayList<String>()
    private lateinit var lymain : ConstraintLayout
    private lateinit var itemsAapter : RecyclerViewAdapter
    private var guessed = 3
    private var   randNum = 0
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_number_game)

        val Rv = findViewById<RecyclerView>(R.id.rv)
        submit = findViewById(R.id.submit)
        inputUser = findViewById(R.id.tvInputUser)
        lymain = findViewById(R.id.lyMain)
        items = arrayListOf("","")
        val randNum = Random.nextInt(10)
        //set Adapter
        itemsAapter =  RecyclerViewAdapter(items)

        // set recycler view adapter
        Rv.layoutManager = LinearLayoutManager(this)
        Rv.adapter = itemsAapter
        submit.setOnClickListener {
            updateinfo()

        }

    }

    private fun updateinfo() {
        randNum = Random.nextInt(10)
        val text = inputUser.text.toString()
        if(!text.isDigitsOnly()){
            Snackbar.make(lymain,"Please make sure to enter number", Snackbar.LENGTH_SHORT).show()
        }else {
            if(text.equals(randNum)) {
                inputUser.isEnabled = false
                submit.isEnabled = false
                showAlert("You win!! \nPlay again?")
            }

            else{
                guessed--
                items.add("You guessed $text")
                items.add("You have $guessed guessed left")
            }
            if(guessed==0){
                inputUser.isEnabled = false
                submit.isEnabled = false
                items.add("You lose - The correct answer was $randNum")
                items.add("Game Over")
                showAlert("You lose...\nThe correct answer was $randNum.\n\nPlay again?")
            }
            inputUser.text = ""
            itemsAapter.notifyDataSetChanged()
        }
    }


    private fun showAlert(title: String) {
        val dialogBuilder = AlertDialog.Builder(this)
        dialogBuilder.setMessage(title)
            .setCancelable(false)
            .setPositiveButton("Yes", DialogInterface.OnClickListener {
                    dia, id -> this.recreate()
            })
            // negative button text and action
            .setNegativeButton("No", DialogInterface.OnClickListener {
                    dia, id -> dia.cancel()
            })

        // create dialog box
        val alert = dialogBuilder.create()
        // set title for alert dialog box
        alert.setTitle("Game Over")
        // show alert dialog
        alert.show()
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

    override fun onPrepareOptionsMenu(menu: Menu?): Boolean {
        menu?.findItem(R.id.newGame)!!.isVisible = true
        return super.onPrepareOptionsMenu(menu)
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
            R.id.newGame ->{
                this.recreate()
                return true
            }
        }
        return super.onOptionsItemSelected(item)
    }
}