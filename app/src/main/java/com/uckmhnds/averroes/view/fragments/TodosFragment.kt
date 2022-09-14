package com.uckmhnds.averroes.view.fragments

import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import com.uckmhnds.averroes.data.preferences.AppPreferences
import com.uckmhnds.averroes.data.room.entities.Todo
import com.uckmhnds.averroes.databinding.FragmentTodosBinding
import com.uckmhnds.averroes.domain.model.TodoModel
import com.uckmhnds.averroes.view.adapters.TodoCardAdapter
import com.uckmhnds.averroes.view.fragments.base.BaseFragment
import com.uckmhnds.averroes.viewmodel.TodosViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch
import javax.inject.Inject

@AndroidEntryPoint
class TodosFragment : BaseFragment<TodosViewModel, FragmentTodosBinding>(FragmentTodosBinding::inflate) {

    override val viewModel by viewModels<TodosViewModel>()

    @Inject
    lateinit var preferences: AppPreferences

    private val todoCardAdapter by lazy {
        TodoCardAdapter(
            onTodoCardClick = { todoModel ->
                TodoModel(
                id = todoModel.id,
                text = todoModel.text,
                date = todoModel.date,
                done = todoModel.done
                )
            }
        )
    }

    override fun setupViews() {
        binding.apply {
            rvTodos.adapter         = todoCardAdapter
            rvTodos.layoutManager   = LinearLayoutManager(activity, LinearLayoutManager.VERTICAL, false)
        }
    }

    override fun setupListeners() {
        binding.apply {
            mbAddButton.setOnClickListener { addButtonAction() }
            mbDelButton.setOnClickListener { delButtonAction() }
        }
    }

    override fun setupObservers() {
        observeTodoRoomDatabase()
        observeTodoNumber()
    }

    private fun addButtonAction(){
        viewModel.pushTodo(
            Todo(
                id = 0,
                text = "TEST",
                date = getTime() + " " + getDate(),
                done = false
            )
        )
    }

    private fun delButtonAction(){
        lifecycleScope.launch{

            viewModel.deleteAll()

        }
    }

    private fun observeTodoRoomDatabase(){
        lifecycleScope.launch{

            viewModel.todos.observe(viewLifecycleOwner){

                    todoModelList -> todoCardAdapter.submitList(todoModelList)

            }

        }
    }

    private fun observeTodoNumber(){

        lifecycleScope.launch {

            viewModel.size.observe(viewLifecycleOwner){

                    numberOfTodos -> preferences.setTodoNumber(numberOfTodos)

            }

        }
    }

}