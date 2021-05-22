package com.bignerdranch.android.a7minuteworkoutkotlin

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.bignerdranch.android.a7minuteworkoutkotlin.databinding.ActivityBMIBinding

class BMIActivity : AppCompatActivity() {
private lateinit var bmi: ActivityBMIBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        bmi = ActivityBMIBinding.inflate(layoutInflater)
        super.onCreate(savedInstanceState)
        setContentView(bmi.root)


    }
}