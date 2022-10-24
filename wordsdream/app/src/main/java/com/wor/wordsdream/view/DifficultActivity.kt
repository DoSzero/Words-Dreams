package com.wor.wordsdream.view

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.wor.wordsdream.model.DifficultConstants.DIFFICULT
import com.wor.wordsdream.model.DifficultConstants.EASY
import com.wor.wordsdream.model.DifficultConstants.MEDIUM_
import com.wor.wordsdream.databinding.ActivityDifficultBinding

class DifficultActivity : AppCompatActivity() {

    private lateinit var binding: ActivityDifficultBinding
    private val btnEasy by lazy { binding.btnLvlEasy }
    private val btnMiddle by lazy { binding.btnLvlMiddle}
    private val btnDifficult by lazy { binding.btnLvlDifficult }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityDifficultBinding.inflate(layoutInflater)
        setContentView(binding.root)

        btnEasy.setOnClickListener {
            val intent = Intent(this, EasyActivity::class.java)
            intent.putExtra("Easy",EASY)
            startActivity(intent)
            finish()
        }

        btnMiddle.setOnClickListener {
            val intent = Intent(this, MediumActivity::class.java)
            intent.putExtra("Medium",MEDIUM_)
            startActivity(intent)
            finish()
        }

        btnDifficult.setOnClickListener {
            val intent = Intent(this, HardActivity::class.java)
            intent.putExtra("Difficult", DIFFICULT)
            startActivity(intent)
            finish()
        }
    }
}