package com.nureddinelmas.findnummer

import android.content.Context
import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.EditText
import android.widget.TextView
import kotlin.random.Random

class MainActivity : AppCompatActivity() {
    private lateinit var editNumber: EditText
    private lateinit var textView: TextView
    private lateinit var textScore: TextView

    var number = Random.nextInt(0,10)
    var findTimes : Int = 0
    lateinit var sharedPreferences: SharedPreferences
    var scoreFromPreferences : Int? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        editNumber = findViewById(R.id.editNumber)
        textView = findViewById(R.id.textView)
        textScore = findViewById(R.id.textScore)

        sharedPreferences = this.getSharedPreferences("com.nureddinelmas.findnummer", Context.MODE_PRIVATE)
        scoreFromPreferences = sharedPreferences.getInt("score", -1)

        if(editNumber == null){
            editNumber.setError("Please enter a number")
        }
        textScore.setText("Final Score : 10 times")
        scoreFromPreferences = 10


    }

    fun finder(view : View){

        var guess =editNumber.text.toString().toInt()

            if(guess < number){
                findTimes++
                textView.setText("Write a high number")
                editNumber.hint = "Enter another number"
            }
            if(guess > number){
                findTimes++
                textView.setText("Write a low number")
                editNumber.hint = "Enter another number"
            }
            if (guess == number){
                textView.setText("Bra!! Klart $findTimes")


                if(findTimes < scoreFromPreferences!!){
                    textScore.setText("Final Score : $findTimes times")
                    sharedPreferences.edit().putInt("score", findTimes).apply()
                }
            }
    }

    fun reset(view : View){
        editNumber.setText("")
        findTimes = 0
        number = Random.nextInt(0,10)
        textView.setText("")
        sharedPreferences.edit().remove("age").apply()

    }


}