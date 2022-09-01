package com.uckmhnds.averroes.view.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import com.uckmhnds.averroes.R
import com.uckmhnds.averroes.application.AverroesApplication
import com.uckmhnds.averroes.databinding.FragmentAddNoteBinding
import com.uckmhnds.averroes.model.entities.Note
import com.uckmhnds.averroes.viewmodel.AddNoteViewModel
import com.uckmhnds.averroes.viewmodel.AddNoteViewModelFactory
import java.util.*

class AddNoteFragment : Fragment(), View.OnClickListener {

    private lateinit var binding: FragmentAddNoteBinding

    private val viewModel: AddNoteViewModel by viewModels {
        AddNoteViewModelFactory((requireActivity().application as AverroesApplication).repository)
    }

    private lateinit var calendar: Calendar

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        binding             = FragmentAddNoteBinding.inflate(inflater, container, false)

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)


        binding.btnAddNote.setOnClickListener(this)


    }

    override fun onClick(v: View?) {

        if (v!=null){
            when(v.id){
                R.id.btn_add_note -> {
                    val now = System.currentTimeMillis()
                    val title   = binding.etAddTitle.text.toString()
                    val text    = binding.etAddNote.text.toString()
                    val id      = 0
                    val date    = getDate() + " " + getTime()
                    val ctgry   = "No category"

                    val note    = Note(id, title, text, date, ctgry)

                    viewModel.insert(note)

                }
            }
        }

    }

    private fun getDate(): String{

        calendar                    = Calendar.getInstance()

        val year                    = calendar.get(Calendar.YEAR)
        val month                   = calendar.get(Calendar.MONTH)
        val day                     = calendar.get(Calendar.DAY_OF_MONTH)

        return "${day}/${month}/${year}"
    }

    private fun getTime(): String{

        calendar                    = Calendar.getInstance()

        val hour                    = calendar.get(Calendar.HOUR_OF_DAY)
        val minute                  = calendar.get(Calendar.MINUTE)
        val second                  = calendar.get(Calendar.SECOND)

        var hourString              = ""
        var minuteString            = ""
        var secondString            = ""

        hourString = if (hour < 10){
            "0${hour}"
        } else{
            "$hour"
        }
        minuteString = if (minute < 10){
            "0${minute}"
        } else{
            "$minute"
        }
        secondString = if (second < 10){
            "0${second}"
        } else{
            "$second"
        }

        return "$hourString:$minuteString:$secondString"

    }

}