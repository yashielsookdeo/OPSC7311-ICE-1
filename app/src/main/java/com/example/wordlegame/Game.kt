package com.example.wordlegame

import android.content.Context
import android.graphics.Color
import android.text.Editable
import android.text.TextWatcher
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.LinearLayout
import android.widget.TextView
import androidx.core.widget.addTextChangedListener
import com.example.wordlegame.databinding.ActivityMainBinding
import java.util.*

class Game(private val binding: ActivityMainBinding, context: Context) {
    init {
        val wordBoard = WordBoard()

        for (i in 0 .. 4){
            wordBoard.createRow(context, binding)
        }
    }

    var numAttempts =0

    private val winText= binding.Win
    private val btnPlayAgain = binding.btnPlayAgain
    private val mainLayout = binding.mainLayout

    private val words = arrayOf(
        "apple", "chive", "grasp", "flume", "hinge",
        "gloom", "grape", "horse", "knead", "scorn",
        "thump", "lemon", "mango", "night", "ocean",
        "peach", "queen", "slant", "yacht", "tiger")

    fun getRandomWord():String {
        val randomIndex = Random().nextInt(words.size)

        return words[randomIndex].uppercase()
    }

    fun validateRow(editTextList: MutableList<EditText>, word: String) {
        val wordChars = word.toCharArray()

        numAttempts++
        var correctLetters = 0

        editTextList.forEachIndexed { index, editText ->
            val editTextChar = editText.text.toString().uppercase().getOrNull(0)
            val wordChar= wordChars.getOrNull(index)

            val backgroundColor = when {
                editTextChar == null -> Color.WHITE //White when EditText is EMPTY
                editTextChar == wordChar ->{
                    correctLetters += 1 //Inc Counter
                    Color.GREEN //Correct Letter
                }
                editTextChar !in word -> Color.RED //Letter not Found
                else -> Color.YELLOW // Incorrect letter
            }

            editText.setBackgroundColor(backgroundColor)

            if (correctLetters == word.length) {
                winText.visibility = View.VISIBLE

                btnPlayAgain.visibility =View.VISIBLE

                val mainLayoutCount = mainLayout.childCount - 1

                for (i in 0..mainLayoutCount) {
                    val childLayout = mainLayout.getChildAt(i) as LinearLayout
                    val childLayoutCount = childLayout.childCount -1

                    for (j in 0 ..childLayoutCount) {
                        val editTxt = childLayout.getChildAt(j) as EditText
                        editTxt.isEnabled = false
                    }
                }
            }

            if (numAttempts == 5 && correctLetters != word.length) {
                winText.setText("Game Over")
                winText.visibility = View.VISIBLE
                btnPlayAgain.visibility = View.VISIBLE
            }
        }
    }

    fun keepFocus() {
        val editTextList = mutableListOf<EditText>()
        val mainLayoutCount = mainLayout.childCount - 1

        for (i in 0 .. mainLayoutCount) {
            val childLayout = mainLayout.getChildAt(i) as LinearLayout
            val childLayoutCount = childLayout.childCount - 1

            for (j in 0 .. childLayoutCount) {
                editTextList.add(childLayout.getChildAt(j) as EditText)
            }
        }

        for (i in 0 until editTextList.size - 1) {
            getNewLetter(editTextList[i], editTextList[i + 1])
        }
    }

    private fun getNewLetter(edt1: EditText, edt2: EditText) {
        edt1.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(edt: CharSequence?, p1: Int, p2: Int, p3: Int) {

            }

            override fun onTextChanged(edt: CharSequence?, p1: Int, p2: Int, p3: Int) {

            }

            override fun afterTextChanged(edt: Editable?) {
                if (edt?.length == 1) {
                    edt2.requestFocus()
                }
            }
        })
    }

    fun loopChild(word: String) {
        val mainLayoutCount = mainLayout.childCount - 1
        for (i in 0 .. mainLayoutCount) {
            val childLayout = mainLayout.getChildAt(i) as LinearLayout
            val childLayoutCount = childLayout.childCount - 1
            val editTextList = mutableListOf<EditText>()

            for (j in 0.. childLayoutCount) {
                populateList(editTextList, childLayout.getChildAt(j) as EditText)
            }

            val row = childLayout.getChildAt(childLayoutCount) as EditText
            row.addTextChangedListener() {
                validateRow(editTextList, word)
            }
        }
    }

    private fun populateList(input: MutableList<EditText>, childElement: EditText) {
        input.add(childElement)
    }

}