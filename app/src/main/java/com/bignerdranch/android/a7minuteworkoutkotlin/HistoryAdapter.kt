package com.bignerdranch.android.a7minuteworkoutkotlin

import android.content.Context
import android.graphics.Color
import android.renderscript.ScriptGroup
import android.text.Layout
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bignerdranch.android.a7minuteworkoutkotlin.databinding.ActivityBMIBinding.inflate
import com.bignerdranch.android.a7minuteworkoutkotlin.databinding.ActivityExerciseBinding.inflate
import com.bignerdranch.android.a7minuteworkoutkotlin.databinding.ItemHistoryRowBinding
import org.w3c.dom.Text

class HistoryAdapter(val context: Context, val items: ArrayList<String>) :
    RecyclerView.Adapter<HistoryAdapter.ViewHolder>()

{
    class ViewHolder(view: ItemHistoryRowBinding) : RecyclerView.ViewHolder(view.root) {
        val llHistoryMainItem = view.llHistoryItemMain
        val tvItem = view.tvItem
        val tvPosition = view.tvPosition

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
      //  return ViewHolder(LayoutInflater.from(context).inflate(R.layout.item_history_row, parent, false))

        val binding: ItemHistoryRowBinding =
            ItemHistoryRowBinding.inflate(LayoutInflater.from(context), parent, false)
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val date: String = items.get(position)

        holder.tvPosition.text = (position+1).toString()
        holder.tvItem.text = date

        if(position % 2 == 0) {
            holder.llHistoryMainItem.setBackgroundColor(Color.parseColor("#ebebeb"))
        } else {
            holder.llHistoryMainItem.setBackgroundColor(Color.parseColor("#ffffff"))
        }
    }

    override fun getItemCount(): Int {
        return items.size
    }
}