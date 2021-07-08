package com.example.navigationfragments.ui.home.add_todo

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.findNavController
import com.example.navigationfragments.R
import com.example.navigationfragments.databinding.FragmentAddToDoBinding
import com.example.navigationfragments.ui.ViewModelFactory
import com.example.navigationfragments.ui.data.ToDoDatabase.Companion.db
import com.example.navigationfragments.ui.data.ToDoModel
import com.example.navigationfragments.ui.data.ToDoRepository



class AddToDoFragment : Fragment() {

    private lateinit var viewModel: AddToDoViewModel
    private lateinit var viewModelFactory: ViewModelFactory

    private var _binding: FragmentAddToDoBinding? = null
    private val binding get() = _binding!!
    private var todoId: Long? = null

    companion object {
        val TODO_ID = "todoId"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            todoId = it.getLong(TODO_ID)
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentAddToDoBinding.inflate(inflater, container, false)

        viewModelFactory = ViewModelFactory(ToDoRepository(db.dao()))
        viewModel = ViewModelProvider(this, viewModelFactory).get(AddToDoViewModel::class.java)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        if (todoId != null && todoId != 0L) {
            viewModel.getToDoById(todoId!!)
            viewModel.loadToDoModel.observe(viewLifecycleOwner, {
                binding.titleET.setText(it.title)
                binding.descriptionET.setText(it.description)
            })
        }

        binding.saveFabBtn.setOnClickListener {
            if (todoId != null && todoId != 0L) {
                viewModel.editToDo(
                    binding.titleET.text.toString(),
                    binding.descriptionET.text.toString(),
                    todoId!!
                )
            } else {
                val time = System.currentTimeMillis()
                val todoModel =
                    ToDoModel(
                        time,
                        binding.titleET.text.toString(),
                        binding.descriptionET.text.toString(),
                        false
                    )
                viewModel.addToDo(todoModel)
            }
            binding.root.findNavController().navigate(R.id.action_addToDoFragment_to_nav_home2)
        }
    }
}