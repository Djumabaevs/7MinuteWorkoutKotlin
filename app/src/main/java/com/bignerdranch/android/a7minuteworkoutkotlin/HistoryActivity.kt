package com.bignerdranch.android.a7minuteworkoutkotlin

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
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

    }
}