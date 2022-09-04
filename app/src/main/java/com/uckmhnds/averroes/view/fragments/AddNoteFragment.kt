package com.uckmhnds.averroes.view.fragments

import android.app.Dialog
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.*
import androidx.navigation.NavController
import androidx.navigation.Navigation
import com.uckmhnds.averroes.R
import com.uckmhnds.averroes.application.AverroesApplication
import com.uckmhnds.averroes.databinding.FragmentAddNoteBinding
import com.uckmhnds.averroes.model.entities.Note
import com.uckmhnds.averroes.viewmodel.SharedNoteViewModel
import com.uckmhnds.averroes.viewmodel.SharedNoteViewModelFactory
import java.util.*

class AddNoteFragment : Fragment(), View.OnClickListener, NoteCategoriesDialogFragment.NoteCategoriesDialogListener {

    private lateinit var binding: FragmentAddNoteBinding
    private lateinit var navController: NavController

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
        binding                     = FragmentAddNoteBinding.inflate(inflater, container, false)

        val date                    = getDate() + " " + getTime()

        binding.tvDate.text         = date

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

                    val title       = binding.etAddTitle.text.toString()
                    val text        = binding.etAddNote.text.toString()
                    val date        = getDate() + " " + getTime()
                    val category    = binding.tvCategory.text.toString()

                    val note        = Note(id = 0, title, text, date, category)

                    sharedViewModel.insert(note)

                }

                R.id.iv_back_button -> {

                    navController.navigate(R.id.action_navigation_add_note_to_navigation_notes)

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

}