package com.bignerdranch.android.a7minuteworkoutkotlin

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.CountDownTimer
import android.view.View
import android.widget.Toast
import com.bignerdranch.android.a7minuteworkoutkotlin.databinding.ActivityExerciseBinding

class ExerciseActivity : AppCompatActivity() {
    private lateinit var be: ActivityExerciseBinding

    private var restTimer: CountDownTimer? = null
    private var restProgress = 0
    private var exerciseTimer: CountDownTimer? = null
    private var exerciseProgress = 0
    private var exerciseTimeDuration: Long = 30

    private var exerciseList: ArrayList<ExerciseModel>? = null
    private var currentExercisePosition = -1

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

        setupRestView()

        exerciseList = Constants.defaultExerciseList()
    }

    override fun onDestroy() {
        super.onDestroy()
        if(restTimer != null) {
            restTimer!!.cancel()
            restProgress = 0
        }
    }

    private fun setRestProgressBar() {
        be.progressBar.progress = restProgress
        restTimer = object: CountDownTimer(10000, 1000) {

            override fun onFinish() {
                currentExercisePosition++
                setupExerciseView()
                Toast.makeText(this@ExerciseActivity,
                    "Here now we will start the exercise", Toast.LENGTH_SHORT).show()
            }

            override fun onTick(millisUntilFinished: Long) {
              restProgress++
              be.progressBar.progress = 10 - restProgress
              be.tvTimer.text = (10 - restProgress).toString()
            }
        }.start()
    }

    private fun setupRestView() {

        be.llRestView.visibility = View.VISIBLE
        be.llExerciseView.visibility = View.GONE

        if(restTimer != null) {
            restTimer!!.cancel()
            restProgress = 0
        }

        setRestProgressBar()
    }

    private fun setExerciseProgressBar() {
        be.progressBarExercise.progress = exerciseProgress
        exerciseTimer = object: CountDownTimer(exerciseTimeDuration*1000, 1000) {

            override fun onFinish() {
               if(currentExercisePosition < exerciseList?.size!! - 1) {
                   setupRestView()
               } else {
                   Toast.makeText(this@ExerciseActivity,
                       "Congratulations! You have completed the exercise!", Toast.LENGTH_LONG).show()
               }
            }

            override fun onTick(millisUntilFinished: Long) {
                exerciseProgress++
                be.progressBarExercise.progress = exerciseTimeDuration.toInt() - exerciseProgress
                be.tvTimerExercise.text = (exerciseTimeDuration.toInt() - exerciseProgress).toString()
            }
        }.start()
    }

    private fun setupExerciseView() {

        be.llRestView.visibility = View.GONE
        be.llExerciseView.visibility = View.VISIBLE

        if(exerciseTimer != null) {
            exerciseTimer!!.cancel()
            exerciseProgress = 0
        }

        setExerciseProgressBar()

        be.ivImage.setImageResource(exerciseList!![currentExercisePosition].getImage())
        be.tvExerciseName.text = exerciseList!![currentExercisePosition].getName()
    }
}