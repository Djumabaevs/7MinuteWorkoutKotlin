package com.bignerdranch.android.a7minuteworkoutkotlin

import android.media.MediaPlayer
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.CountDownTimer
import android.speech.tts.TextToSpeech
import android.util.Log
import android.view.View
import android.widget.Toast
import com.bignerdranch.android.a7minuteworkoutkotlin.databinding.ActivityExerciseBinding
import java.util.*
import kotlin.collections.ArrayList

class ExerciseActivity : AppCompatActivity(), TextToSpeech.OnInitListener {
    private lateinit var be: ActivityExerciseBinding

    private var restTimer: CountDownTimer? = null
    private var restProgress = 0
    private var exerciseTimer: CountDownTimer? = null
    private var exerciseProgress = 0
    private var exerciseTimeDuration: Long = 30

    private var exerciseList: ArrayList<ExerciseModel>? = null
    private var currentExercisePosition = -1

    private var tts: TextToSpeech? = null
    private var player: MediaPlayer? = null

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

        tts = TextToSpeech(this, this)

        exerciseList = Constants.defaultExerciseList()

        setupRestView()
    }

    override fun onDestroy() {
        if(restTimer != null) {
            restTimer!!.cancel()
            restProgress = 0
        }
        if(exerciseTimer != null) {
            exerciseTimer!!.cancel()
            exerciseProgress = 0
        }
        if(tts != null) {
            tts!!.stop()
            tts!!.shutdown()
        }
        super.onDestroy()
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

        player = MediaPlayer.create(applicationContext, R.raw.welldone)

        be.llRestView.visibility = View.VISIBLE
        be.llExerciseView.visibility = View.GONE

        if(restTimer != null) {
            restTimer!!.cancel()
            restProgress = 0
        }

        be.tvUpcomingExerciseName.text = exerciseList!![currentExercisePosition + 1].getName()

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

        speakOut(exerciseList!![currentExercisePosition].getName())

        setExerciseProgressBar()

        be.ivImage.setImageResource(exerciseList!![currentExercisePosition].getImage())
        be.tvExerciseName.text = exerciseList!![currentExercisePosition].getName()
    }

    override fun onInit(status: Int) {
        if(status == TextToSpeech.SUCCESS) {
            val result = tts!!.setLanguage(Locale.US)
            if( result == TextToSpeech.LANG_MISSING_DATA ||
                result == TextToSpeech.LANG_NOT_SUPPORTED) {
                Log.e("TTS", "The language specified is not supported")
            }
        } else {
            Log.e("TTS", "Initialization failed")
        }
    }

    private fun speakOut(text: String) {
        tts!!.speak(text, TextToSpeech.QUEUE_FLUSH, null, "")
    }
}