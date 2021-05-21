package com.bignerdranch.android.a7minuteworkoutkotlin

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.bignerdranch.android.a7minuteworkoutkotlin.databinding.ActivityFinishBinding

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

    }
}