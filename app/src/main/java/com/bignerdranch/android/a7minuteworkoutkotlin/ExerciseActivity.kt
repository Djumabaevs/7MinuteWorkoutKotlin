package com.bignerdranch.android.a7minuteworkoutkotlin

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.CountDownTimer
import com.bignerdranch.android.a7minuteworkoutkotlin.databinding.ActivityExerciseBinding

class ExerciseActivity : AppCompatActivity() {
    private lateinit var be: ActivityExerciseBinding
    private var restTimer: CountDownTimer? = null
    private var restProgress = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        be = ActivityExerciseBinding.inflate(layoutInflater)
        super.onCreate(savedInstanceState)
        setContentView(be.root)

        setSupportActionBar(be.toolbarExerciseActivity)
        val actionbar = supportActionBar

        if(actionbar != null) {
            actionbar.setDisplayHomeAsUpEnabled(true)
        }
        be.toolbarExerciseActivity.setNavigationOnClickListener {
            onBackPressed()
        }
    }

    private fun setRestProgressBar() {
        be.progressBar.progress = restProgress
        restTimer = object: CountDownTimer(10000, 1000) {
            override fun onTick(millisUntilFinished: Long) {
              restProgress++
              be.progressBar.progress = 10 - restProgress
              be.tvTimer.text = (10 - restProgress).toString()
            }

        }
    }
}