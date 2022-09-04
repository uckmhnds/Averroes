package com.uckmhnds.averroes.view.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.fragment.app.FragmentActivity
import androidx.recyclerview.widget.RecyclerView
import com.uckmhnds.averroes.R
import com.uckmhnds.averroes.databinding.DialogNoteCategoriesItemBinding

class NoteCategoryAdapter(
    private val activity: FragmentActivity?,
    private val categories : ArrayList<String>,
    private val colors: ArrayList<String>
): RecyclerView.Adapter<NoteCategoryAdapter.ViewHolder>() {

    var onItemClick: ((String) -> Unit)? = null

    inner class ViewHolder (view: DialogNoteCategoriesItemBinding): RecyclerView.ViewHolder(view.root){

        val tvCategory                  = view.tvCategory
        val ivCategory                  = view.ivCategory
        val llCategory                  = view.llCategory

        // Set onClickListeners

        init {

            llCategory.setOnClickListener { onItemClick?.invoke(categories[adapterPosition]) }

        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {

        val binding         = DialogNoteCategoriesItemBinding.inflate(LayoutInflater.from(activity), parent, false)

        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val category            = categories[position]
        val color               = colors[position]

        holder.ivCategory.setColorFilter(R.color.travel)
        holder.tvCategory.text  = category

    }

    override fun getItemCount(): Int {
        return categories.size
    }

}

