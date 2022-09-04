package com.uckmhnds.averroes.view.fragments

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.fragment.app.viewModels
import androidx.navigation.NavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.uckmhnds.averroes.R
import com.uckmhnds.averroes.application.AverroesApplication
import com.uckmhnds.averroes.databinding.FragmentTodosBinding
import com.uckmhnds.averroes.model.entities.Todo
import com.uckmhnds.averroes.view.adapters.TodoAdapter
import com.uckmhnds.averroes.viewmodel.*
import java.util.*

class TodosFragment : Fragment(), View.OnClickListener {

    private lateinit var binding: FragmentTodosBinding

    private lateinit var recyclerview: RecyclerView

    private lateinit var adapter: TodoAdapter

    private lateinit var navController: NavController

    private lateinit var calendar: Calendar

    private val sharedViewModel: SharedTodoViewModel by activityViewModels {
        SharedTodoViewModelFactory((requireActivity().application as AverroesApplication).todoRepository)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        binding                         = FragmentTodosBinding.inflate(inflater, container, false)

        recyclerview                    = binding.rvTodos

        recyclerview.layoutManager      = LinearLayoutManager(activity, LinearLayoutManager.VERTICAL, false)

        sharedViewModel.notes.observe(viewLifecycleOwner){
            adapter                     = TodoAdapter(activity, it)
            recyclerview.adapter        = adapter
        }

        binding.mbAddButton.setOnClickListener(this)
        binding.mbDelButton.setOnClickListener(this)

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        recyclerview            = binding.rvTodos

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

    override fun onClick(view: View?) {

        if (view != null){

            when(view.id){

                R.id.mb_add_button -> {
                    val date        = getDate() + " " + getTime()
                    val todo        = Todo(0, "test", date, false)

                    sharedViewModel.insert(todo)

                }

                R.id.mb_del_button -> {

                    Log.i("click", "ok")

                    sharedViewModel.deleteAll()

                }

            }

        }

    }

}