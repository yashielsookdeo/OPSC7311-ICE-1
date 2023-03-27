package com.example.wordlegame

import android.content.Context
import android.text.InputFilter
import android.text.InputType
import android.view.Gravity
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
import android.widget.LinearLayout
import com.example.wordlegame.databinding.ActivityMainBinding

class WordBoard {
    private fun addEditText(context : Context) : EditText {
        //Create EditText
        val editText = EditText(context)

        editText.layoutParams = LinearLayout.LayoutParams(
            ViewGroup.LayoutParams.MATCH_PARENT,
            ViewGroup.LayoutParams.WRAP_CONTENT
        )
        editText.setPadding(20,20,20,20)
        editText.gravity =Gravity.CENTER
        editText.setBackgroundResource(R.drawable.bg_edttxt)
        editText.filters = arrayOf(InputFilter.LengthFilter(1))
        editText.textSize = 23F
        editText.isAllCaps = true
        editText.inputType = InputType.TYPE_CLASS_TEXT

        val params = editText.layoutParams as LinearLayout.LayoutParams
        params.setMargins(10,10,10,10)
        editText.layoutParams = params

        editText.layoutParams.width = 120

        return editText
    }

    private fun addLinearLayout(context: Context, binding: ActivityMainBinding) : LinearLayout {
        val linearLayout = LinearLayout(context)
        linearLayout.layoutParams = LinearLayout.LayoutParams(
            ViewGroup.LayoutParams.MATCH_PARENT,
            ViewGroup.LayoutParams.WRAP_CONTENT
        )
        linearLayout.orientation = LinearLayout.HORIZONTAL
        linearLayout.gravity = Gravity.CENTER

        for (i in 0 .. 4) {
            val editText= addEditText(context)
            linearLayout.addView(editText)
        }

        binding.mainLayout.addView(linearLayout)

        return linearLayout
    }

    fun createRow(context: Context, binding: ActivityMainBinding) {
        val linearLayout = addLinearLayout(context, binding)
    }
}