package anand.example.mvvmsample.viewholders

import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.fact_list_item.view.*

class FactsViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
    val tvTitle: TextView = itemView.tvTitle
    val tvDescription: TextView = itemView.tvDescription
    val ivIcon: ImageView = itemView.ivIcon
}