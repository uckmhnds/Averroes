package com.uckmhnds.averroes.view.fragments

import android.os.Bundle
import android.util.Log
import android.view.*
import androidx.core.os.bundleOf
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.LifecycleEventObserver
import androidx.lifecycle.LifecycleOwner
import androidx.navigation.NavController
import androidx.navigation.Navigation
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.savedstate.SavedStateRegistry
import androidx.savedstate.SavedStateRegistryOwner
import com.uckmhnds.averroes.R
import com.uckmhnds.averroes.application.AverroesApplication
import com.uckmhnds.averroes.databinding.FragmentNotesBinding
import com.uckmhnds.averroes.model.entities.Note
import com.uckmhnds.averroes.view.adapters.NoteAdapter
import com.uckmhnds.averroes.viewmodel.SharedNoteViewModel
import com.uckmhnds.averroes.viewmodel.SharedNoteViewModelFactory
import java.util.*

class NotesFragment : Fragment(), View.OnClickListener {

    private lateinit var binding: FragmentNotesBinding

    private lateinit var recyclerview: RecyclerView

    private lateinit var adapter: NoteAdapter

    private lateinit var navController: NavController

    private lateinit var calendar: Calendar

    private val sharedViewModel: SharedNoteViewModel by activityViewModels {
        SharedNoteViewModelFactory((requireActivity().application as AverroesApplication).noteRepository)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        binding                = FragmentNotesBinding.inflate(inflater, container, false)

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        navController           = Navigation.findNavController(view)

        binding.mbAddButton.setOnClickListener(this)
        binding.mbDelButton.setOnClickListener(this)

//        recyclerview                = binding.rvGridLayout

        recyclerview                = binding.rvNotes

        observeRecyclerViewLayoutManagerState(viewLifecycleOwner)

        observeNotes(viewLifecycleOwner)

    }

    override fun onClick(v: View?) {

        if (v!=null){

            when(v.id){

                R.id.mb_add_button -> {
                    val date        = getDate() + " " + getTime()
                    sharedViewModel.setSpecificNote(Note(0, "", "", date, "no category"))
                    navController.navigate(R.id.action_navigation_notes_to_navigation_add_note)
                }

                R.id.mb_del_button -> {sharedViewModel.deleteAll()}
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

    private fun observeRecyclerViewLayoutManagerState(viewLifecycleOwner: LifecycleOwner) {
        sharedViewModel.listGridViewBoolean.observe(viewLifecycleOwner){

            if (it){
                recyclerview.layoutManager  = LinearLayoutManager(activity, LinearLayoutManager.VERTICAL, false)
            }else{
                recyclerview.layoutManager  = GridLayoutManager(activity, 2, GridLayoutManager.VERTICAL, false)
            }

        }
    }

    private fun observeNotes(viewLifecycleOwner: LifecycleOwner){

        sharedViewModel.notes.observe(viewLifecycleOwner) {

            adapter                 = NoteAdapter(activity, it)
            recyclerview.adapter    = adapter

            adapter.onCardClick     = { item ->

                sharedViewModel.setSpecificNote(item)
                navController.navigate(R.id.action_navigation_notes_to_navigation_note_detail)

            }
        }
    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        outState.putString("test", "t1")
    }

    override fun onViewStateRestored(savedInstanceState: Bundle?) {
        super.onViewStateRestored(savedInstanceState)
        Log.i(savedInstanceState?.getString("test"), "thrown")
    }

}


