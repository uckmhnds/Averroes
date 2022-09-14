package com.uckmhnds.averroes.view.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.fragment.app.FragmentActivity
import androidx.recyclerview.widget.RecyclerView
import com.uckmhnds.averroes.databinding.TodoCardBinding
import com.uckmhnds.averroes.data.room.entities.Todo

class TodoAdapter(
    private val activity: FragmentActivity?,
    private val todos : List<Todo>
): RecyclerView.Adapter<TodoAdapter.ViewHolder>()  {

    var onCardClick: ((Todo) -> Unit)? = null

    inner class ViewHolder (view: TodoCardBinding): RecyclerView.ViewHolder(view.root){

        val cv                      = view.cv
        val tvTodo                  = view.tvTodo
        val tvDate                  = view.tvDate

        // Set onClickListeners

        init {

            cv.setOnClickListener { onCardClick?.invoke(todos[adapterPosition]) }

        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding: TodoCardBinding    = TodoCardBinding.inflate(LayoutInflater.from(activity), parent, false)

        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val todo                = todos[position]

        holder.tvTodo.text      = todo.text
        holder.tvDate.text      = todo.date

    }

    override fun getItemCount(): Int {
        return todos.size
    }
}