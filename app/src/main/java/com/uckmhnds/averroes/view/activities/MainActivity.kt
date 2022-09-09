package com.uckmhnds.averroes.view.activities

import android.os.Bundle
import android.view.MenuItem
import android.view.View
import androidx.activity.viewModels
import com.google.android.material.bottomnavigation.BottomNavigationView
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.navigation.NavController
import androidx.navigation.findNavController
import androidx.navigation.ui.setupWithNavController
import com.google.android.material.appbar.AppBarLayout
import com.uckmhnds.averroes.R
import com.uckmhnds.averroes.application.AverroesApplication
import com.uckmhnds.averroes.databinding.ActivityMainBinding
import com.uckmhnds.averroes.viewmodel.SharedNoteViewModel
import com.uckmhnds.averroes.viewmodel.SharedNoteViewModelFactory
import com.uckmhnds.averroes.viewmodel.SharedTodoViewModel
import com.uckmhnds.averroes.viewmodel.SharedTodoViewModelFactory

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private lateinit var navController: NavController
    private lateinit var bottomNavigationView: BottomNavigationView
    private lateinit var topBar: AppBarLayout

    private val sharedNoteViewModel: SharedNoteViewModel by viewModels {
        SharedNoteViewModelFactory((application as AverroesApplication).noteRepository)
    }

    private val sharedTodoViewModel: SharedTodoViewModel by viewModels {
        SharedTodoViewModelFactory((application as AverroesApplication).todoRepository)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding                 = ActivityMainBinding.inflate(layoutInflater)

        setContentView(binding.root)

        bottomNavigationView    = binding.bottomNavigationView

        topBar                  = binding.appBarLayout

        navController           = findNavController(R.id.nav_host_fragment_activity_main)

        bottomNavigationView.setupWithNavController(navController)

        // Listen to destinations changes to reflect some events on UI
        navController.addOnDestinationChangedListener(destinationChangedListener())

        // Observe the LiveData<Int> of Notes, passing in this activity as the LifecycleOwner and the observer.
        sharedNoteViewModel.size.observe(this, numberOfNotesObserver())

        // Observe the LiveData<Int> of Todos, passing in this activity as the LifecycleOwner and the observer.
        sharedTodoViewModel.size.observe(this, numberOfTodosObserver())


    }

    private fun numberOfTodosObserver(): Observer<Int>{

        // Create the observer which updates the number of total notes.
        val observer                            = Observer<Int> { size ->

            // Update the UI, in this case, a TextView.

            if (size != null){

                val numberOfTodos: Int          = size
                val text                        = "$numberOfTodos to-dos"
                binding.tvNumberOfTodos.text    = text

            }else{

                val text                        = "0 to-dos"
                binding.tvNumberOfTodos.text    = text

            }
        }
        return observer
    }

    private fun numberOfNotesObserver(): Observer<Int>{

        // Create the observer which updates the number of total todos.
        val observer                            = Observer<Int> { size ->

            // Update the UI, in this case, a TextView.

            if (size != null){

                val numberOfNotes: Int          = size
                val text                        = "$numberOfNotes notes"
                binding.tvNumberOfNotes.text    = text

            }else{

                val text                        = "0 notes"
                binding.tvNumberOfNotes.text    = text

            }
        }
        return observer
    }

    private fun destinationChangedListener(): NavController.OnDestinationChangedListener{

        return NavController.OnDestinationChangedListener { _, destination, _ ->

            // Hide bottom nav bar at add/edit note fragments

            if (destination.id == R.id.navigation_add_note || destination.id == R.id.navigation_note_detail) {

                bottomNavigationView.visibility = View.GONE

            } else {

                bottomNavigationView.visibility = View.VISIBLE
            }

            // Hide top toolbar at add/edit note fragments

            if (destination.id == R.id.navigation_add_note || destination.id == R.id.navigation_note_detail) {

                topBar.visibility = View.GONE

            } else {

                topBar.visibility = View.VISIBLE
            }

            // Switch between #NUMBER_OF_ITEMS Notes/To-dos top bar TextView visibilities

            if (destination.id == R.id.navigation_notes) {

                binding.tvNumberOfNotes.visibility = View.VISIBLE
                binding.tvNumberOfTodos.visibility = View.GONE

            } else if (destination.id == R.id.navigation_todo) {

                binding.tvNumberOfNotes.visibility = View.GONE
                binding.tvNumberOfTodos.visibility = View.VISIBLE

            }

        }
    }

    fun popUpMenuClick(item: MenuItem) {

        when(item.itemId){

            R.id.popup_menu_item_grid_view -> {sharedNoteViewModel.showGridView()}
            R.id.popup_menu_item_list_view -> {sharedNoteViewModel.showListView()}

        }

    }

}