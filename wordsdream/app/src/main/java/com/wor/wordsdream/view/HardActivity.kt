package com.wor.wordsdream.view

import android.annotation.SuppressLint
import android.content.Intent
import android.graphics.Typeface
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Gravity
import android.view.animation.AnimationUtils
import android.widget.EditText
import android.widget.LinearLayout
import android.widget.TextView
import android.widget.Toast
import com.wor.wordsdream.R
import com.wor.wordsdream.databinding.ActivityHardBinding
import java.util.*

class HardActivity : AppCompatActivity() {

    private var presCounter = 0
    private var maxPresCounter = 5
    private var keys = arrayOf("М","Е","Ч","Т","А")
    private val textAnswer = "МЕЧТА"

    private lateinit var binding: ActivityHardBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityHardBinding.inflate(layoutInflater)
        setContentView(binding.root)

        keys = shuffleArray(keys)
        for (key in keys) {
            addView(
                binding.layoutParent,key,
                binding.editText
            )
        }
        //
        maxPresCounter = 5
    }

    // mix arrayOf
    private fun shuffleArray(arr: Array<String>): Array<String> {
        for (i in arr.size - 1 downTo 1) {
            val index = Random().nextInt(i + 1)
            val a = arr[index]
            arr[index] = arr[i]
            arr[i] = a
        }
        return arr
    }

    @SuppressLint("UseCompatLoadingForDrawables", "SetTextI18n")
    private fun addView(viewParent: LinearLayout, text: String, editText: EditText) {

        val linearLayoutParams = LinearLayout.LayoutParams(
            LinearLayout.LayoutParams.WRAP_CONTENT,
            LinearLayout.LayoutParams.WRAP_CONTENT
        )
        linearLayoutParams.rightMargin = 30

        val textView = TextView(this)
        textView.layoutParams = linearLayoutParams
        textView.background = this.resources.getDrawable(R.drawable.background_pink)
        textView.setTextColor(this.resources.getColor(R.color.colorPurple))
        textView.gravity = Gravity.CENTER
        textView.text = text
        textView.isClickable = true
        textView.isFocusable = true
        textView.textSize = 32f

        val typeface = Typeface.MONOSPACE
        binding.textQuestion.typeface = typeface
        binding.textScreen.typeface = typeface
        binding.textTitle.typeface = typeface

        editText.typeface = typeface
        textView.typeface = typeface

        textView.setOnClickListener {
            if (presCounter < maxPresCounter) {
                if (presCounter == 0) editText.setText("")
                editText.setText(editText.text.toString() + text)
                textView.startAnimation(AnimationUtils.loadAnimation(this, R.anim.am_smallbigforth))
                textView.animate().alpha(0f).duration = 300
                presCounter++
                if (presCounter == maxPresCounter) doValidate()
            }
        }
        viewParent.addView(textView)
    }

    private fun doValidate() {
        presCounter = 0
        if (binding.editText.text.toString() == textAnswer) {
            //Toast.makeText(MainActivity.this, "Correct", Toast.LENGTH_SHORT).show();
            val a = Intent(this@HardActivity, FinishActivity::class.java)
            startActivity(a)
            binding.editText.setText("")
        } else {
            Toast.makeText(this@HardActivity, "Неправильно написанное слово!", Toast.LENGTH_SHORT).show()
            binding.editText.setText("")
        }

        keys = shuffleArray(keys)!!
        binding.layoutParent.removeAllViews()
        binding.layoutParent.removeAllViews()
        for (key in keys) {
            addView(binding.layoutParent, key, binding.editText)
        }
    }
}