package com.bignerdranch.android.a7minuteworkoutkotlin

import android.content.Context
import android.text.Layout
import android.view.View
import android.widget.LinearLayout
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import org.w3c.dom.Text

class HistoryAdapter(val context: Context, val items: ArrayList<String>) :
    RecyclerView.Adapter<HistoryAdapter.ViewHolder>()

{
    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val llHistoryMainItem = view.findViewById<LinearLayout>(R.id.ll_history_item_main)
        val tvItem = view.findViewById<TextView>(R.id.tv_item)
        val tvPosition = view.findViewById<TextView>(R.id.tvPosition)

    }


}