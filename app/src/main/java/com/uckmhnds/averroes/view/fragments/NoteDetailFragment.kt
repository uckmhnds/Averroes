package com.uckmhnds.averroes.view.fragments

import android.util.Log
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.navArgs
import com.uckmhnds.averroes.databinding.FragmentNoteDetailBinding
import com.uckmhnds.averroes.databinding.FragmentNotesBinding
import com.uckmhnds.averroes.domain.model.NoteModel
import com.uckmhnds.averroes.view.fragments.base.BaseFragment
import com.uckmhnds.averroes.viewmodel.NoteDetailViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch

@AndroidEntryPoint
class NoteDetailFragment : BaseFragment<NoteDetailViewModel, FragmentNoteDetailBinding>(FragmentNoteDetailBinding::inflate){

    private val args by navArgs<NoteDetailFragmentArgs>()

    override val viewModel  by viewModels<NoteDetailViewModel>()

    override fun setupViews() {
        binding.apply {

            etAddTitle.setText(args.noteModel.title)
            etAddNote.setText(args.noteModel.text)

            tvDate.text             = args.noteModel.date
            tvCategory.text         = args.noteModel.category

        }
    }

    override fun setupListeners() {
        binding.apply {
            ivAddNote.setOnClickListener { addButtonAction() }
            ivBackButton.setOnClickListener { backButtonAction() }
            llSelectCategory.setOnClickListener { categorySelectAction() }
            ivPrevious.setOnClickListener { previousButtonAction() }
            ivForward.setOnClickListener { forwardButtonAction() }
        }
    }

    override fun setupObservers() {
    }

    private fun updateNoteModel(noteModel: NoteModel){

        lifecycleScope.launch{

            viewModel.updateNote(noteModel)

        }

    }

    private fun addButtonAction(){

        updateNoteModel(
            NoteModel(

                id          = args.noteModel.id,
                title       = binding.etAddTitle.text.toString(),
                text        = binding.etAddNote.text.toString(),
                date        = binding.tvDate.text.toString(),
                category    = binding.tvCategory.text.toString()

            )
        )
    }

    private fun backButtonAction(){

        navigateWithAction(
            NoteDetailFragmentDirections.actionNoteDetailToNotes()
        )
    }

    private fun categorySelectAction(){

    }

    private fun previousButtonAction(){

    }

    private fun forwardButtonAction(){

    }

}