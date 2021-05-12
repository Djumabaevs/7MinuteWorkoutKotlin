package com.bignerdranch.android.a7minuteworkoutkotlin

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.bignerdranch.android.a7minuteworkoutkotlin.databinding.ActivityExerciseBinding

class ExerciseActivity : AppCompatActivity() {
    private lateinit var be: ActivityExerciseBinding

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
}