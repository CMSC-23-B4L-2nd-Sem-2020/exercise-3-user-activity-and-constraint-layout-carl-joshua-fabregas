package com.example.fabregas_exer3

import android.content.Context
import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.view.inputmethod.InputMethodManager
import android.widget.Button
import android.widget.EditText
import android.widget.TextView

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        //2D array of ids
        val array = createArray()

        //retry button
        val ret_but = findViewById<Button>(R.id.retry)

        //add name requirement
        findViewById<Button>(R.id.done_button).setOnClickListener{
            addName(it)
            show(array)
        }
        //retry button listener
        ret_but.setOnClickListener{
            retry(array)
        }
        //game mechanics
        run(array, ret_but)


    }

    private fun addName(view: View){ //when the user clicks the done button it means that theyve inputted their name
        //var declaraton
        val textView = findViewById<TextView>(R.id.name_ent)
        val inputedText = findViewById<EditText>(R.id.name_entry)

        //visibility changer
        textView.setText(inputedText.text)
        textView.visibility = View.VISIBLE
        inputedText.visibility = View.GONE

        //keyboard
         val inputMethodManager = getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
        inputMethodManager.hideSoftInputFromWindow(view.windowToken,0)

    }

    private fun checker(view:List<List<Int>> , i: Int, j: Int, butts: Button){ //checkered referring to checking the side  of the clicked button
        //if the retry button is visible
        if(butts.visibility == View.GONE){
            butts.visibility = View.VISIBLE
        }

        //clicks the button
        is_white(view[i][j])
        if(i==0 && j==0){
            is_white(view[i+1][j])
            is_white(view[i][j+1])
        }else if (i==0 && j==4){
            is_white(view[i][j-1])
            is_white(view[i+1][j])
        }else if(i == 4 && j == 0){
            is_white(view[i-1][j])
            is_white(view[i][j+1])
        }else if(i==4 && j == 4){
            is_white(view[i][j-1])
            is_white(view[i-1][j])
        }else if(i==0 && j!=0 && j!=4){
            is_white(view[i][j-1])
            is_white(view[i][j+1])
            is_white(view[i+1][j])
        }else if(i!=0 && i!= 4 && j==0){
            is_white(view[i+1][j])
            is_white(view[i-1][j])
            is_white(view[i][j+1])
        }else if(i==4 && j!= 4 && j!=0){
            is_white(view[i-1][j])
            is_white(view[i][j+1])
            is_white(view[i][j-1])
        }else if (i!=4 && i!=0 && j==4){
            is_white(view[i][j-1])
            is_white(view[i-1][j])
            is_white(view[i+1][j])
        }else{
            is_white(view[i][j-1])
            is_white(view[i][j+1])
            is_white(view[i+1][j])
            is_white(view[i-1][j])
        }
    }

    private fun is_white(view:Int){ //checks using the set tag intially 0, when the tag is 1, it changes the color
        val temp = findViewById<Button>(view)
        if(temp.getTag() == 1){
            temp.setBackgroundColor(Color.parseColor("Teal"))
            temp.setTag(0)
        }else{
            temp.setBackgroundColor(Color.CYAN)
            temp.setTag(1)
        }
    }

    private fun run(view: List<List<Int>>, butts: Button){ //the whole game process
        for (i in 0..4){
            for (j in 0..4){
                //uses individual ids to see if clicked
                findViewById<Button>(view[i][j]).setOnClickListener {
                    checker(view, i, j, butts)
                }
            }
        }
    }

    private fun show(view: List<List<Int>>) { //shows the buttons/lights
        for (i in 0..4) {
            for (j in 0..4) {
                val obj = findViewById<Button>(view[i][j])
                if (obj.visibility == View.GONE) {
                    obj.visibility = View.VISIBLE
                } else {
                    //if its already visible, it means the rest are too, no need to show
                    break
                }
            }
        }
    }


    private fun retry(view: List<List<Int>>){ //resets the game without changing the name
        for (i in 0..4){
            for (j in 0..4){
                val temp = findViewById<Button>(view[i][j])
                temp.setTag(0)
                temp.setBackgroundColor(Color.parseColor("TEAL"))
            }
        }
    }

    private fun createArray(): List<List<Int>>{ //creates 5x5 array by creating 5 list arrays to represent each row then combining them to form the 2d array
         val first_row: List<Int> = listOf(
             R.id.lights_1,
             R.id.lights_2,
             R.id.lights_3,
             R.id.lights_4,
             R.id.lights_5

         )
        val second_row: List<Int> = listOf(
            R.id.lights_6,
            R.id.lights_7,
            R.id.lights_8,
            R.id.lights_9,
            R.id.lights_10
        )
        val third_row: List<Int> = listOf(
            R.id.lights_11,
            R.id.lights_12,
            R.id.lights_13,
            R.id.lights_14,
            R.id.lights_15

        )
        val fourth_row: List<Int> = listOf(
            R.id.lights_16,
            R.id.lights_17,
            R.id.lights_18,
            R.id.lights_19,
            R.id.lights_20
        )
        val fifth_row: List<Int> = listOf(
            R.id.lights_21,
            R.id.lights_22,
            R.id.lights_23,
            R.id.lights_24,
            R.id.lights_25
        )

        //Making of the 2d part
        val twoD_Array : List<List<Int>> = listOf(first_row,second_row,third_row,fourth_row,fifth_row)

        return twoD_Array
    }


}
