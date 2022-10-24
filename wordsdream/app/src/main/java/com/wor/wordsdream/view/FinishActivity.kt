package com.wor.wordsdream.view

import android.content.Intent
import android.graphics.Typeface
import android.os.Bundle
import android.view.animation.Animation
import android.view.animation.AnimationUtils
import androidx.appcompat.app.AppCompatActivity
import com.wor.wordsdream.R
import com.wor.wordsdream.databinding.ActivityFinishBinding

class FinishActivity : AppCompatActivity() {

    private var smalltobig: Animation? = null
    private lateinit var binding: ActivityFinishBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityFinishBinding.inflate(layoutInflater)
        setContentView(binding.root)

        smalltobig = AnimationUtils.loadAnimation(this, R.anim.am_smalltobig)
        binding.bigboss.startAnimation(smalltobig)

        val typeface = Typeface.MONOSPACE
        binding.textQuestion.typeface = typeface
        binding.textScreen.typeface = typeface

        // to Difficult
        binding.btnToHome.typeface = typeface
        binding.btnToHome.setOnClickListener {
            val intent = Intent(this, DifficultActivity::class.java)
            startActivity(intent)
            finish()
        }

    }
}