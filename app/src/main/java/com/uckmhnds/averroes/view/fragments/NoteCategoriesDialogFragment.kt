package com.uckmhnds.averroes.view.fragments

import android.app.Dialog
import android.content.Context
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.Window
import androidx.appcompat.app.AlertDialog
import androidx.fragment.app.DialogFragment
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.uckmhnds.averroes.R
import com.uckmhnds.averroes.databinding.DialogNoteCategoriesBinding
import com.uckmhnds.averroes.utils.Constants
import com.uckmhnds.averroes.view.adapters.NoteCategoryAdapter

class NoteCategoriesDialogFragment : DialogFragment() {

    internal lateinit var listener: NoteCategoriesDialogListener
    internal lateinit var binding: DialogNoteCategoriesBinding

    private lateinit var recyclerView: RecyclerView
    private lateinit var adapter: NoteCategoryAdapter
    private lateinit var layoutManager: LinearLayoutManager

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        binding                         = DialogNoteCategoriesBinding.inflate(inflater, container, false)

        recyclerView                    = binding.rvNoteCategories

        layoutManager                   = LinearLayoutManager(activity, LinearLayoutManager.VERTICAL, false)
        adapter                         = NoteCategoryAdapter(activity, Constants.categoryNameList(), Constants.categoryColorList())

        recyclerView.layoutManager      = layoutManager
        recyclerView.adapter            = adapter

        listener                        = parentFragment as NoteCategoriesDialogListener

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {

        super.onViewCreated(view, savedInstanceState)

        adapter.onItemClick             =  { item -> listener.onCategoryClick(item) }

    }

    /* The activity that creates an instance of this dialog fragment must
     * implement this interface in order to receive event callbacks.
     * Each method passes the DialogFragment in case the host needs to query it. */
    interface NoteCategoriesDialogListener {
        fun onCategoryClick(category: String)
    }

}
