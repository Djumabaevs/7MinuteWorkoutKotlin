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

        setSupportActionBar(bmi.toolbarBmiActivity)
        val actionBar = supportActionBar
        if(actionBar != null) {
            actionBar.setDisplayHomeAsUpEnabled(true)
            actionBar.title = "CALCULATE BMI"
        }
        bmi.toolbarBmiActivity.setNavigationOnClickListener {
            onBackPressed()
        }
        bmi.btnCalculateUnits.setOnClickListener {

        }
    }

    private fun validateMetricUnits(): Boolean {
        var isValid = true

        if(bmi.etMetricUnitWeight.text.toString().isEmpty() || bmi.etMetricUnitHeight.text.toString().isEmpty())
            isValid = false

        return isValid
    }
}