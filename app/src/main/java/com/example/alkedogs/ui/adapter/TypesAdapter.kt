package com.example.alkedogs.ui.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.cardview.widget.CardView
import androidx.recyclerview.widget.RecyclerView
import com.example.alkedogs.R
import com.example.alkedogs.util.OnItemClickListenerType


class TypesAdapter(private val typeList: List<String>) :
    RecyclerView.Adapter<TypesAdapter.ViewHolder>() {

    private var typelistener: OnItemClickListenerType? = null

    // create new views
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        // inflates the card_view_design view
        // that is used to hold list item
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.card_view_design, parent, false)
        if (parent.context is OnItemClickListenerType) {
            typelistener = parent.context as OnItemClickListenerType
        }
        return ViewHolder(view)
    }

    // binds the list items to a view
    override fun onBindViewHolder(holder: ViewHolder, position: Int) {

        val itemsTypes = typeList[position]

        // sets the text to the textview from our itemHolder class
        holder.textView.text = itemsTypes
        holder.carView.setOnClickListener {
            typelistener?.onItemClick(position)
        }

    }

    // return the number of the items in the list
    override fun getItemCount(): Int {
        return typeList.size
    }

    // Holds the views for adding it to text
    class ViewHolder(ItemView: View) : RecyclerView.ViewHolder(ItemView) {
        val textView: TextView = itemView.findViewById(R.id.category)
        val carView : CardView = itemView.findViewById(R.id.card_view)
    }
}