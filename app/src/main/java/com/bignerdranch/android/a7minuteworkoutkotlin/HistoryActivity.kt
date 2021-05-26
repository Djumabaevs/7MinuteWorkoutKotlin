package com.bignerdranch.android.a7minuteworkoutkotlin

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.recyclerview.widget.LinearLayoutManager
import com.bignerdranch.android.a7minuteworkoutkotlin.databinding.ActivityHistoryBinding

class HistoryActivity : AppCompatActivity() {
    private lateinit var ha: ActivityHistoryBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        ha = ActivityHistoryBinding.inflate(layoutInflater)
        super.onCreate(savedInstanceState)
        setContentView(ha.root)

        setSupportActionBar(ha.toolbarHistoryActivity)
        val actionBar = supportActionBar
        if(actionBar != null) {
            actionBar.setDisplayHomeAsUpEnabled(true)
            actionBar.title = "HISTORY"
        }

        ha.toolbarHistoryActivity.setNavigationOnClickListener {
            onBackPressed()
        }
        getAllCompletedDates()
    }

    private fun getAllCompletedDates() {
        val dbHandler = SqliteOpenHelper(this, null)
        val allCompletedDatesList = dbHandler.getAllCompletedDatesList()

        if(allCompletedDatesList.size > 0) {
            ha.tvHistory.visibility = View.VISIBLE
            ha.rvHistory.visibility = View.VISIBLE
            ha.tvNoDataAvailable.visibility = View.GONE

            ha.rvHistory.layoutManager = LinearLayoutManager(this)
            val historyAdapter = HistoryAdapter(this, allCompletedDatesList)
            ha.rvHistory.adapter = historyAdapter
        }

        for(i in allCompletedDatesList) {
            Log.i("datesHistory", "" + i)
        }
    }
}