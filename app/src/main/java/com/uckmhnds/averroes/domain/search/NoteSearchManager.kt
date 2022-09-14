package com.uckmhnds.averroes.domain.search

import android.util.Log
import android.widget.SearchView
import com.uckmhnds.averroes.data.repository.NoteRepository
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class NoteSearchManager(private val noteRepo: NoteRepository) {

//    fun testNoteSearchManager(){
//        Log.i("testNoteSearchManager", "testNoteSearchManager")
//    }
//
//    fun search(searchText: String)          = CoroutineScope(Dispatchers.IO).launch {
//        noteRepo.search(searchText)
//    }
//
//    var result                              = noteRepo.searchResults
//
//    inner class QueryListener: SearchView.OnQueryTextListener{
//
//        override fun onQueryTextSubmit(query: String?): Boolean {
//            TODO("Not yet implemented")
//        }
//
//        override fun onQueryTextChange(newText: String?): Boolean {
//            TODO("Not yet implemented")
////            if (newText != null) {
////                noteRepo.search(newText)
////            }
////            return true
//        }
//
//    }

}