package com.uckmhnds.averroes.view.fragments

import android.content.SharedPreferences
import android.util.Log
import android.widget.SearchView
import androidx.fragment.app.viewModels
import androidx.lifecycle.LiveData
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import com.uckmhnds.averroes.data.preferences.AppPreferenceKeys
import com.uckmhnds.averroes.data.preferences.AppPreferences
import com.uckmhnds.averroes.databinding.FragmentNotesBinding
import com.uckmhnds.averroes.domain.model.NoteModel
import com.uckmhnds.averroes.view.adapters.NoteCardAdapter
import com.uckmhnds.averroes.view.fragments.base.BaseFragment
import com.uckmhnds.averroes.viewmodel.NotesViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch
import javax.inject.Inject

@AndroidEntryPoint
class NotesFragment : BaseFragment<NotesViewModel, FragmentNotesBinding>(FragmentNotesBinding::inflate) {

    override val viewModel  by viewModels<NotesViewModel>()

    @Inject
    lateinit var preferences: AppPreferences

    private val noteCardAdapter by lazy {
        NoteCardAdapter(
            onNoteCardClick = {
                noteModel -> navigateWithAction(
                    NotesFragmentDirections.
                    actionNotesToNoteDetail(
                        NoteModel(
                            id = noteModel.id,
                            title = noteModel.title,
                            text = noteModel.text,
                            date = noteModel.date,
                            category = noteModel.category
                        )
                    )
                )
            }
        )
    }

    private val linearLayoutManager by lazy {
        LinearLayoutManager(activity, LinearLayoutManager.VERTICAL, false)
    }

    private val gridLayoutManager by lazy {
        GridLayoutManager(activity, 2)
    }

    override fun setupViews() {
        binding.apply {

            rvNotes.adapter         = noteCardAdapter

        }
    }

    override fun setupListeners() {
        binding.apply {
            mbAddButton.setOnClickListener { addButtonAction() }
            mbDelButton.setOnClickListener { delButtonAction() }
            etSearch.setOnClickListener {  }

//            svSearch.setOnQueryTextListener(object : SearchView.OnQueryTextListener{
//                override fun onQueryTextSubmit(p0: String?): Boolean {
//
//                    return false
//                }
//
//                override fun onQueryTextChange(p0: String?): Boolean {
//                    noteCardAdapter.filter.filter(p0)
//                    noteCardAdapter.submitList(noteCardAdapter.noteModelFilteredList)
//                    return false
//                }
//
//            })
        }
    }

    override fun setupObservers()
    {
        observeNoteRoomDatabase()
        observePreferences()
        observeNoteNumber()
    }

    private fun addButtonAction(){
        viewModel.pushNote(
            NoteModel(
            id = 0,
            title = "Test",
            text = "This is a test",
            date = getTime() + " " + getDate(),
            category = "no category"
            )
        )

    }

    private fun delButtonAction(){

        lifecycleScope.launch{
            viewModel.deleteAll()
        }

    }

    private fun observeNoteRoomDatabase(){

        lifecycleScope.launch{

            viewModel.notes.observe(viewLifecycleOwner){

                    noteModelList -> noteCardAdapter.submitList(noteModelList)

            }

        }
    }

    private fun observePreferences(){

        lifecycleScope.launch {

            preferences.getLiveView().observe(viewLifecycleOwner){
                if (it == AppPreferenceKeys.LIST){

                    noteCardAdapter.layoutManagerType   = AppPreferenceKeys.LIST
                    binding.rvNotes.layoutManager       = linearLayoutManager

                }else if (it == AppPreferenceKeys.GRID){

                    noteCardAdapter.layoutManagerType   = AppPreferenceKeys.GRID
                    binding.rvNotes.layoutManager       = gridLayoutManager

                }
            }

        }
    }

    private fun observeNoteNumber(){

        lifecycleScope.launch {

            viewModel.size.observe(viewLifecycleOwner){

                numberOfNotes -> preferences.setNoteNumber(numberOfNotes)

            }

        }
    }

}


