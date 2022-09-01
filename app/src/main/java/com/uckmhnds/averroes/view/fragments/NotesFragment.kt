package com.uckmhnds.averroes.view.fragments

import android.os.Bundle
import android.util.Log
import android.view.*
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.NavController
import androidx.navigation.Navigation
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.uckmhnds.averroes.R
import com.uckmhnds.averroes.application.AverroesApplication
import com.uckmhnds.averroes.databinding.FragmentNotesBinding
import com.uckmhnds.averroes.view.adapters.NoteAdapter
import com.uckmhnds.averroes.viewmodel.NotesViewModel
import com.uckmhnds.averroes.viewmodel.NotesViewModelFactory

class NotesFragment : Fragment(), View.OnClickListener {

    private lateinit var binding: FragmentNotesBinding

    private lateinit var recyclerview: RecyclerView

    private lateinit var adapter: NoteAdapter

    lateinit var navController: NavController

    private val viewModel: NotesViewModel by viewModels {
        NotesViewModelFactory((requireActivity().application as AverroesApplication).repository)
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

        recyclerview                = binding.rvLinearLayout
//        recyclerview.layoutManager  = LinearLayoutManager(activity, LinearLayoutManager.VERTICAL, false)
        recyclerview.layoutManager  = GridLayoutManager(activity, 2, GridLayoutManager.VERTICAL, false)


        viewModel.notes.observe(viewLifecycleOwner) {
            adapter                 = NoteAdapter(activity, it)
            recyclerview.adapter    = adapter
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
    }

    override fun onClick(v: View?) {

        if (v!=null){

            when(v.id){
                R.id.mb_add_button -> {navController.navigate(R.id.action_navigation_notes_to_navigation_add_note)}
                R.id.mb_del_button -> {viewModel.deleteAll()}
            }

        }

    }

}