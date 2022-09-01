package com.uckmhnds.averroes.view.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.FragmentActivity
import androidx.recyclerview.widget.RecyclerView
import com.uckmhnds.averroes.databinding.NoteCardBinding
import com.uckmhnds.averroes.databinding.TodoCardBinding
import com.uckmhnds.averroes.model.entities.Todo

class TodoAdapter(
    private val activity: FragmentActivity?,
    private val todos : List<Todo>
): RecyclerView.Adapter<TodoAdapter.ViewHolder>()  {

    inner class ViewHolder (view: TodoCardBinding): RecyclerView.ViewHolder(view.root){

//        val cv                      = view.cv
//        val tvTitle                 = view.tvTitle
//        val tvNote                  = view.tvNote
//        val tvDate                  = view.tvDate

        // Set onClickListeners

//        init {
//
//            view.cv.setOnClickListener { onCardClick?.invoke(notes[adapterPosition]) }
//
////            view.ivDelete.setOnClickListener { onDeleteClick?.invoke(recentActions[adapterPosition]) }
//        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding: TodoCardBinding    = TodoCardBinding.inflate(LayoutInflater.from(activity), parent, false)

        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        TODO("Not yet implemented")
    }

    override fun getItemCount(): Int {
        return todos.size
    }
}