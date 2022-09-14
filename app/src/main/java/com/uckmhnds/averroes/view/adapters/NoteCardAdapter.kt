package com.uckmhnds.averroes.view.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.Filter
import android.widget.Filterable
import androidx.core.view.isVisible
import androidx.recyclerview.widget.RecyclerView
import com.uckmhnds.averroes.data.preferences.AppPreferenceKeys
import com.uckmhnds.averroes.databinding.NoteCardBinding
import com.uckmhnds.averroes.domain.model.NoteModel
import com.uckmhnds.averroes.view.adapters.base.BaseListAdapter
import com.uckmhnds.averroes.view.adapters.base.BaseViewHolder

class NoteCardAdapter(
    val onNoteCardClick: ((noteModel: NoteModel) -> Unit)
    ):
    BaseListAdapter<NoteModel>(
    itemsSame = { old, new -> old.title == new.title },
    contentsSame = { old, new -> old == new }
)
//    , Filterable
{

//    var noteModelList: List<NoteModel> = emptyList()
//    var noteModelFilteredList: List<NoteModel> = emptyList()

    lateinit var layoutManagerType: String

    override fun onCreateViewHolder(
        parent: ViewGroup,
        inflater: LayoutInflater,
        viewType: Int
    ): NoteCardViewHolder
    {
        return NoteCardViewHolder(
            parent,
            LayoutInflater.from(parent.context)
        )
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        when (holder){
            is NoteCardViewHolder -> {
                holder.bindView(getItem(position), position)
            }
        }
    }

    inner class NoteCardViewHolder(
        parent: ViewGroup,
        inflater: LayoutInflater
    ) : BaseViewHolder<NoteCardBinding>(
        binding     = NoteCardBinding.inflate(inflater, parent, false)
    ){
        fun bindView(noteModel: NoteModel, position: Int){

            if (layoutManagerType == AppPreferenceKeys.LIST){
                binding.tvNote.isVisible    = false
            }
            else if (layoutManagerType == AppPreferenceKeys.GRID){
                binding.tvNote.isVisible    = true
            }

            binding.tvTitle.text    = noteModel.title
            binding.tvNote.text     = noteModel.text
            binding.tvDate.text     = noteModel.date

            binding.cv.setOnClickListener {
                onNoteCardClick.invoke(
                    NoteModel(
                        id          = noteModel.id,
                        title       = noteModel.title.orEmpty(),
                        text        = noteModel.text.orEmpty(),
                        date        = noteModel.date.orEmpty(),
                        category    = noteModel.category.orEmpty()
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