package com.uckmhnds.averroes.view.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.fragment.app.FragmentActivity
import androidx.recyclerview.widget.RecyclerView
import com.uckmhnds.averroes.databinding.NoteCardBinding
import com.uckmhnds.averroes.data.room.entities.Note

class NoteAdapter(
    private val activity: FragmentActivity?,
    private val notes : List<Note>
): RecyclerView.Adapter<NoteAdapter.ViewHolder>()  {

    var onCardClick: ((Note) -> Unit)? = null

    inner class ViewHolder (view: NoteCardBinding): RecyclerView.ViewHolder(view.root){

        private val cv              = view.cv
        val tvTitle                 = view.tvTitle
        val tvNote                  = view.tvNote
        val tvDate                  = view.tvDate

        // Set onClickListeners

        init {

            cv.setOnClickListener { onCardClick?.invoke(notes[adapterPosition]) }

        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {

        val binding : NoteCardBinding   = NoteCardBinding.inflate(LayoutInflater.from(activity), parent, false)

        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val note                = notes[position]

        holder.tvNote.text      = note.text
        holder.tvDate.text      = note.date
        holder.tvTitle.text     = note.title
    }

    override fun getItemCount(): Int {
        return notes.size
    }

}