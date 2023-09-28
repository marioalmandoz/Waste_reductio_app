package net.wastereduction.adapter

import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import net.wastereduction.R
import net.wastereduction.Remainder

class RemainderViewHolder(view: View):RecyclerView.ViewHolder(view) {
    //Esto son las variables que se declaran
    val title = view.findViewById<TextView>(R.id.remainderTitle)
    val timeLeft = view.findViewById<TextView>(R.id.timeLeftRemainder)
    val photo = view.findViewById<ImageView>(R.id.imageViewRemainder)
    val text = view.findViewById<TextView>(R.id.textRemainder)

    fun render(remainderModel: Remainder){
        title.text = remainderModel.title
        timeLeft.text = remainderModel.timeLeft
        text.text = remainderModel.text
        //aqui va la foto pero eso mas adelante
    }
}