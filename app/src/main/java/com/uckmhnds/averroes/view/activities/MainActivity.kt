package com.uckmhnds.averroes.view.activities

import android.opengl.Visibility
import android.os.Bundle
import android.view.Menu
import android.view.View
import androidx.activity.viewModels
import com.google.android.material.bottomnavigation.BottomNavigationView
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.Observer
import androidx.lifecycle.lifecycleScope
import androidx.navigation.NavDestination
import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.setupActionBarWithNavController
import androidx.navigation.ui.setupWithNavController
import com.google.android.material.appbar.AppBarLayout
import com.uckmhnds.averroes.R
import com.uckmhnds.averroes.application.AverroesApplication
import com.uckmhnds.averroes.databinding.ActivityMainBinding
import com.uckmhnds.averroes.model.entities.Todo
import com.uckmhnds.averroes.viewmodel.SharedNoteViewModel
import com.uckmhnds.averroes.viewmodel.SharedNoteViewModelFactory
import com.uckmhnds.averroes.viewmodel.SharedTodoViewModel
import com.uckmhnds.averroes.viewmodel.SharedTodoViewModelFactory

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private lateinit var destination: NavDestination

    private val sharedNoteViewModel: SharedNoteViewModel by viewModels {
        SharedNoteViewModelFactory((application as AverroesApplication).noteRepository)
    }

    private val sharedTodoViewModel: SharedTodoViewModel by viewModels {
        SharedTodoViewModelFactory((application as AverroesApplication).todoRepository)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val navView: BottomNavigationView = binding.navView

        val navController = findNavController(R.id.nav_host_fragment_activity_main)
        // Passing each menu ID as a set of Ids because each
        // menu should be considered as top level destinations.
//        val appBarConfiguration = AppBarConfiguration(
//            setOf(
//                R.id.navigation_notes, R.id.navigation_todo
//            )
//        )
//        setupActionBarWithNavController(navController, appBarConfiguration)
        navView.setupWithNavController(navController)
//        setSupportActionBar(binding.toolbar)


        // Hide bottom nav bar at scientific calc

        navController.addOnDestinationChangedListener { _, destination, _ ->
            if(destination.id == R.id.navigation_add_note) {

                navView.visibility = View.GONE

            } else {

                navView.visibility = View.VISIBLE
            }
        }

        val topBar:AppBarLayout     = binding.appBarLayout

        // Hide top toolbar at scientific calc

        navController.addOnDestinationChangedListener { _, dest, _ ->

            destination         = dest

            // Top Bar Visibility

            if(destination.id == R.id.navigation_add_note || destination.id == R.id.navigation_note_detail) {

                topBar.visibility = View.GONE

            } else {

                topBar.visibility = View.VISIBLE
            }

            if (destination.id == R.id.navigation_notes){

                binding.tvNumberOfNotes.visibility      = View.VISIBLE
                binding.tvNumberOfTodos.visibility      = View.GONE

            }
            else if (destination.id == R.id.navigation_todo){

                binding.tvNumberOfNotes.visibility      = View.GONE
                binding.tvNumberOfTodos.visibility      = View.VISIBLE

            }
        }

        // Observe the LiveData, passing in this activity as the LifecycleOwner and the observer.
        sharedNoteViewModel.size.observe(this, noteSizeObserver())

        // Observe the LiveData, passing in this activity as the LifecycleOwner and the observer.
        sharedTodoViewModel.size.observe(this, todoSizeObserver())
    }

    private fun todoSizeObserver(): Observer<Int>{

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

    private fun noteSizeObserver(): Observer<Int>{


        // Create the observer which updates the number of total notes.
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

}