package com.uckmhnds.averroes.view.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.navigation.NavController
import androidx.navigation.Navigation
import com.uckmhnds.averroes.R
import com.uckmhnds.averroes.application.AverroesApplication
import com.uckmhnds.averroes.databinding.FragmentNoteDetailBinding
import com.uckmhnds.averroes.model.entities.Note
import com.uckmhnds.averroes.view.dialogs.NoteCategoriesDialogFragment
import com.uckmhnds.averroes.viewmodel.*
import java.util.*

class NoteDetailFragment : Fragment(), View.OnClickListener, NoteCategoriesDialogFragment.NoteCategoriesDialogListener {

    private lateinit var binding: FragmentNoteDetailBinding
    private lateinit var navController: NavController
    private lateinit var noteToBeUpdated: Note

//    private val viewModel: NoteDetailViewModel by viewModels {
//        NoteDetailViewModelFactory((requireActivity().application as AverroesApplication).noteRepository)
//    }

    private val sharedViewModel: SharedNoteViewModel by activityViewModels{
        SharedNoteViewModelFactory((requireActivity().application as AverroesApplication).noteRepository)
    }

    private lateinit var calendar: Calendar

    private lateinit var dialog: NoteCategoriesDialogFragment

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        binding                     = FragmentNoteDetailBinding.inflate(inflater, container, false)

        noteToBeUpdated             = sharedViewModel.getSpecificNote()

        bindNoteView(noteToBeUpdated)

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {

        super.onViewCreated(view, savedInstanceState)

        navController       = Navigation.findNavController(view)

        // Top Item Container Listeners in order
        binding.ivBackButton.setOnClickListener(this)
        binding.ivPrevious.setOnClickListener(this)
        binding.ivForward.setOnClickListener(this)
        binding.ivAddNote.setOnClickListener(this)

        // Middle Item Container Listener (Note category)
        binding.llSelectCategory.setOnClickListener(this)

    }

    override fun onClick(v: View?) {

        if (v!=null){

            when(v.id){

                R.id.iv_add_note -> {

                    noteToBeUpdated.title       = binding.etAddTitle.text.toString()
                    noteToBeUpdated.text        = binding.etAddNote.text.toString()
                    noteToBeUpdated.category    = binding.tvCategory.text.toString()
                    noteToBeUpdated.date        = getDate() + " " + getTime()

                    sharedViewModel.update(noteToBeUpdated)

                }

                R.id.iv_back_button -> {

                    navController.navigate(R.id.action_navigation_note_detail_to_navigation_notes)

                }

                R.id.ll_select_category -> {

                    showNoteCategoriesDialogFragment()

                }

                R.id.iv_forward -> {

                }

                R.id.iv_previous -> {

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

    private fun showNoteCategoriesDialogFragment(){

        // Create an instance of the dialog fragment and show it

        dialog                      = NoteCategoriesDialogFragment()

        dialog.show(childFragmentManager, "NoteCategoriesDialogFragment")

    }

    override fun onNoteCategoryClick(category: String) {

        binding.tvCategory.text     = category

        dialog.dismiss()

    }

    private fun bindNoteView(note: Note){

        binding.etAddNote.setText(note.text)
        binding.etAddTitle.setText(note.title)

        binding.tvCategory.text     = note.category
        binding.tvDate.text         = note.date

    }

}