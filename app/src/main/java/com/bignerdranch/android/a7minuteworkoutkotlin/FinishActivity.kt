package com.bignerdranch.android.a7minuteworkoutkotlin

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import com.bignerdranch.android.a7minuteworkoutkotlin.databinding.ActivityFinishBinding
import java.text.SimpleDateFormat
import java.util.*

class FinishActivity : AppCompatActivity() {
    private lateinit var fe: ActivityFinishBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        fe = ActivityFinishBinding.inflate(layoutInflater)
        super.onCreate(savedInstanceState)
        setContentView(fe.root)

        setSupportActionBar(fe.toolbarFinishActivity)
        val actionBar = supportActionBar
        if(actionBar != null) {
            actionBar.setDisplayHomeAsUpEnabled(true)
        }

        fe.toolbarFinishActivity.setNavigationOnClickListener {
            onBackPressed()
        }
        fe.btnFinish.setOnClickListener {
            finish()
        }

        addDateToDatabase()

    }

    private fun addDateToDatabase() {
        val calendar = Calendar.getInstance()
        val dateTime = calendar.time
        Log.i("Date", "" + dateTime)

        val sdf = SimpleDateFormat("dd MMM yyyy HH:mm:ss", Locale.getDefault())
        val date = sdf.format(dateTime)
        val dbHandler = SqliteOpenHelper(this, null)
        dbHandler.addDate(date)
        Log.e("Date : ", "Added...")
    }
}