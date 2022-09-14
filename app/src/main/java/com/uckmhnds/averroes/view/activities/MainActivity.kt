package com.uckmhnds.averroes.view.activities

import android.os.Bundle
import android.util.Log
import android.view.Menu
import android.view.MenuItem
import android.view.View
import com.google.android.material.bottomnavigation.BottomNavigationView
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.lifecycleScope
import androidx.navigation.NavController
import androidx.navigation.findNavController
import androidx.navigation.ui.setupWithNavController
import com.google.android.material.appbar.AppBarLayout
import com.uckmhnds.averroes.R
import com.uckmhnds.averroes.data.preferences.AppPreferenceKeys
import com.uckmhnds.averroes.data.preferences.AppPreferences
import com.uckmhnds.averroes.databinding.ActivityMainBinding
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch
import javax.inject.Inject


@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private lateinit var navController: NavController
    private lateinit var bottomNavigationView: BottomNavigationView
    private lateinit var topBar: AppBarLayout

    @Inject
    lateinit var preferences: AppPreferences


    private lateinit var gridMenuItem: MenuItem
    private lateinit var listMenuItem: MenuItem

//    private val sharedNoteViewModel: SharedNoteViewModel by viewModels {
//        SharedNoteViewModelFactory((application as AverroesApplication).noteRepository)
//    }
//
//    private val sharedTodoViewModel: SharedTodoViewModel by viewModels {
//        SharedTodoViewModelFactory((application as AverroesApplication).todoRepository)
//    }

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

        gridMenuItem        = binding.toolbar.menu.findItem(R.id.popup_menu_item_grid_view)
        listMenuItem        = binding.toolbar.menu.findItem(R.id.popup_menu_item_list_view)

        setNoteFragmentLayoutPreferenceOnCreate()

        noteNumberObserver()
        todoNumberObserver()

    }

    private fun destinationChangedListener(): NavController.OnDestinationChangedListener{

        return NavController.OnDestinationChangedListener { _, destination, _ ->

            // Hide bottom nav bar at add/edit note fragments

            if (destination.id == R.id.navigation_note_detail) {

                bottomNavigationView.visibility = View.GONE

            } else {

                bottomNavigationView.visibility = View.VISIBLE
            }

            // Hide top toolbar at add/edit note fragments

            if (destination.id == R.id.navigation_note_detail) {

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

            R.id.popup_menu_item_grid_view -> {showGridViewOnNoteFragment()}
            R.id.popup_menu_item_list_view -> {showListViewOnNoteFragment()}

        }

    }

    private fun showGridViewOnNoteFragment(){

        preferences.gridView()
        gridMenuItem.isVisible  = false
        listMenuItem.isVisible  = true

    }

    private fun showListViewOnNoteFragment(){

        preferences.listView()
        gridMenuItem.isVisible  = true
        listMenuItem.isVisible  = false

    }

    private fun setNoteFragmentLayoutPreferenceOnCreate(){

        val layoutPreference    = preferences.getView()

        if (layoutPreference == AppPreferenceKeys.GRID){

            gridMenuItem.isVisible      = false

        }else if (layoutPreference == AppPreferenceKeys.LIST){

            listMenuItem.isVisible      = false

        }
    }

    private fun noteNumberObserver(){

        preferences.getLiveNoteNumber().observe(this) {

            binding.tvNumberOfNotes.text = resources.getString(R.string.number_of_notes, it.toString())

        }

    }

    private fun todoNumberObserver(){

        preferences.getLiveTodoNumber().observe(this) {

            binding.tvNumberOfTodos.text = resources.getString(R.string.number_of_todos, it.toString())

        }

    }

}