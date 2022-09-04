package com.uckmhnds.averroes.view.activities

import android.os.Bundle
import android.view.Menu
import android.view.View
import com.google.android.material.bottomnavigation.BottomNavigationView
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.setupActionBarWithNavController
import androidx.navigation.ui.setupWithNavController
import com.google.android.material.appbar.AppBarLayout
import com.uckmhnds.averroes.R
import com.uckmhnds.averroes.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

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

        navController.addOnDestinationChangedListener { _, destination, _ ->
            if(destination.id == R.id.navigation_add_note) {

                topBar.visibility = View.GONE

            } else {

                topBar.visibility = View.VISIBLE
            }
        }

    }

//    override fun onCreateOptionsMenu(menu: Menu): Boolean {
//        // Inflate the menu; this adds items to the action bar if it is present.
//        menuInflater.inflate(R.menu.popup_menu, menu)
//        return true
//    }
}