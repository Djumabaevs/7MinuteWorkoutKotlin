package com.bignerdranch.android.a7minuteworkoutkotlin

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import com.bignerdranch.android.a7minuteworkoutkotlin.databinding.ActivityBMIBinding
import java.math.BigDecimal
import java.math.RoundingMode

class BMIActivity : AppCompatActivity() {
private lateinit var bmi: ActivityBMIBinding
    val METRIC_UNITS_VIEW = "METRIC_UNIT_VIEW"
    val US_UNITS_VIEW = "US_UNIT_VIEW"
    var currentVisibleView: String = METRIC_UNITS_VIEW

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
            if(validateMetricUnits()) {
                val heightValue: Float = bmi.etMetricUnitHeight.text.toString().toFloat()/ 100
                val weightValue: Float = bmi.etMetricUnitWeight.text.toString().toFloat()
                val bmi = weightValue / (heightValue * heightValue)

                displayBMIResult(bmi)
            } else {
                Toast.makeText(this@BMIActivity, "please enter valid values", Toast.LENGTH_SHORT).show()
            }
        }
    }

    private fun makeVisibleMetricUnitsView() {
        currentVisibleView = METRIC_UNITS_VIEW
        bmi.tilMetricUnitWeight.visibility = View.VISIBLE
        bmi.tilMetricUnitHeight.visibility = View.VISIBLE

        bmi.tilUsUnitWeight.visibility = View.VISIBLE
        bmi.llUsUnitsHeight.visibility = View.GONE

        bmi.llDiplayBMIResult.visibility = View.GONE
    }

    private fun displayBMIResult(bmiR: Float) {
        val bmiLabel: String
        val bmiDescription: String
        if (bmiR.compareTo(15f) <= 0) {
            bmiLabel = "Very severely underweight"
            bmiDescription = "Oops! You really need to take better care of yourself! Eat more!"
        } else if (bmiR.compareTo(15f) > 0 && bmiR.compareTo(16f) <= 0
        ) {
            bmiLabel = "Severely underweight"
            bmiDescription = "Oops!You really need to take better care of yourself! Eat more!"
        } else if (bmiR.compareTo(16f) > 0 && bmiR.compareTo(18.5f) <= 0
        ) {
            bmiLabel = "Underweight"
            bmiDescription = "Oops! You really need to take better care of yourself! Eat more!"
        } else if (bmiR.compareTo(18.5f) > 0 && bmiR.compareTo(25f) <= 0
        ) {
            bmiLabel = "Normal"
            bmiDescription = "Congratulations! You are in a good shape!"
        } else if (java.lang.Float.compare(bmiR, 25f) > 0 && java.lang.Float.compare(
                        bmiR,
                        30f
                ) <= 0
        ) {
            bmiLabel = "Overweight"
            bmiDescription = "Oops! You really need to take care of your yourself! Workout maybe!"
        } else if (bmiR.compareTo(30f) > 0 && bmiR.compareTo(35f) <= 0
        ) {
            bmiLabel = "Obese Class | (Moderately obese)"
            bmiDescription = "Oops! You really need to take care of your yourself! Workout maybe!"
        } else if (bmiR.compareTo(35f) > 0 && bmiR.compareTo(40f) <= 0
        ) {
            bmiLabel = "Obese Class || (Severely obese)"
            bmiDescription = "OMG! You are in a very dangerous condition! Act now!"
        } else {
            bmiLabel = "Obese Class ||| (Very Severely obese)"
            bmiDescription = "OMG! You are in a very dangerous condition! Act now!"
        }

        bmi.llDiplayBMIResult.visibility = View.VISIBLE
     /*   bmi.tvYourBMI.visibility = View.VISIBLE
        bmi.tvBMIValue.visibility = View.VISIBLE
        bmi.tvBMIType.visibility = View.VISIBLE
        bmi.tvBMIDescription.visibility = View.VISIBLE*/

        // This is used to round the result value to 2 decimal values after "."
        val bmiValue = BigDecimal(bmiR.toDouble()).setScale(2, RoundingMode.HALF_EVEN).toString()

        bmi.tvBMIValue.text = bmiValue // Value is set to TextView
        bmi.tvBMIType.text = bmiLabel // Label is set to TextView
        bmi.tvBMIDescription.text = bmiDescription // Description is set to TextView
    }


    private fun validateMetricUnits(): Boolean {
        var isValid = true

        if(bmi.etMetricUnitWeight.text.toString().isEmpty() || bmi.etMetricUnitHeight.text.toString().isEmpty())
            isValid = false

        return isValid
    }
}