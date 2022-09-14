package com.uckmhnds.averroes.view.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.uckmhnds.averroes.databinding.TodoCardBinding
import com.uckmhnds.averroes.domain.model.TodoModel
import com.uckmhnds.averroes.view.adapters.base.BaseListAdapter
import com.uckmhnds.averroes.view.adapters.base.BaseViewHolder

class TodoCardAdapter(
    val onTodoCardClick: ((todoModel: TodoModel) -> Unit)
):
    BaseListAdapter<TodoModel>(
        itemsSame = { old, new -> old.text == new.text },
        contentsSame = { old, new -> old == new }
    ) {

//    var noteModelList: List<NoteModel> = emptyList()
//    var noteModelFilteredList: List<NoteModel> = emptyList()

    override fun onCreateViewHolder(
        parent: ViewGroup,
        inflater: LayoutInflater,
        viewType: Int
    ): TodoCardViewHolder
    {
        return TodoCardViewHolder(
            parent,
            LayoutInflater.from(parent.context)
        )
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        when (holder){
            is TodoCardViewHolder -> {
                holder.bindView(getItem(position), position)
            }
        }
    }

    inner class TodoCardViewHolder(
        parent: ViewGroup,
        inflater: LayoutInflater
    ) : BaseViewHolder<TodoCardBinding>(
        binding     = TodoCardBinding.inflate(inflater, parent, false)
    ){
        fun bindView(todoModel: TodoModel, position: Int){

            binding.tvTodo.text     = todoModel.text
            binding.tvDate.text     = todoModel.date

            binding.cv.setOnClickListener {
                onTodoCardClick.invoke(
                    TodoModel(
                        id          = todoModel.id,
                        text        = todoModel.text.orEmpty(),
                        date        = todoModel.date.orEmpty(),
                        done        = todoModel.done
                    )
                )
            }

        }
    }

//    override fun getFilter(): Filter {
//
//        return object : Filter() {
//            override fun performFiltering(constraint: CharSequence?): FilterResults {
//                val charString = constraint?.toString() ?: ""
//                if (charString.isEmpty()) noteModelFilteredList = noteModelList else {
//                    val filteredList = listOf<NoteModel>()
//                    noteModelList
//                        .filter {
//                            (it.title!!.contains(constraint!!)) or
//                                    (it.text!!.contains(constraint))
//
//                        }
//                        .forEach { filteredList + it }
//                    noteModelFilteredList = filteredList
//
//                }
//                return FilterResults().apply { values = noteModelFilteredList }
//            }
//
//            override fun publishResults(constraint: CharSequence?, results: FilterResults?) {
//
//                noteModelFilteredList = if (results?.values == null)
//                    emptyList()
//                else
//                    results.values as List<NoteModel>
//                notifyDataSetChanged()
//            }
//        }
//    }


}