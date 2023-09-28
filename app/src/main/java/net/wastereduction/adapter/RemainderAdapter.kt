package net.wastereduction.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import net.wastereduction.R
import net.wastereduction.Remainder

class RemainderAdapter(private val remainderList: List<Remainder>) : RecyclerView.Adapter<RemainderViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RemainderViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        return RemainderViewHolder(layoutInflater.inflate(R.layout.activity_item_remainder,parent,false))
    }

    override fun onBindViewHolder(holder: RemainderViewHolder, position: Int) {
        val item = remainderList[position]
        holder.render(item)
    }

    override fun getItemCount(): Int {
        return remainderList.size
    }
}