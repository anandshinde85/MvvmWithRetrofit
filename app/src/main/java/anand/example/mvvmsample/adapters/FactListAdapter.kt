package anand.example.mvvmsample.adapters

import anand.example.mvvmsample.R
import anand.example.mvvmsample.model.Rows
import anand.example.mvvmsample.viewholders.FactsViewHolder
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.squareup.picasso.Picasso

class FactListAdapter(val factList: MutableList<Rows>) : RecyclerView.Adapter<FactsViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FactsViewHolder {
        val rootView =
            LayoutInflater.from(parent.context).inflate(R.layout.fact_list_item, parent, false)
        return FactsViewHolder(rootView)
    }

    override fun getItemCount() = factList.size

    override fun onBindViewHolder(holder: FactsViewHolder, position: Int) {
        val fact = factList[position]
        holder.tvTitle.text = fact.title
        holder.tvDescription.text = fact.description
        fact.imageHref?.let {
            Picasso.get().load(it).placeholder(R.drawable.ic_place_holder)
                .error(R.drawable.ic_download_error).into(holder.ivIcon)
        }
    }

    fun updateList(facts: List<Rows>) = factList.apply {
        clear()
        addAll(facts)
        notifyDataSetChanged()
    }
}