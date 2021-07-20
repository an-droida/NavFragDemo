package com.example.navigationfragments.ui.home

import android.graphics.Color
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.findNavController
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.navigationfragments.R
import com.example.navigationfragments.databinding.FragmentHomeBinding
import com.example.navigationfragments.ui.SwipeToDeleteCallback
import com.example.navigationfragments.ui.ViewModelFactory
import com.example.navigationfragments.ui.data.ToDoDatabase.Companion.db
import com.example.navigationfragments.ui.data.ToDoModel
import com.example.navigationfragments.ui.data.ToDoRepository

class HomeFragment : Fragment() {

    lateinit var homeViewModel: HomeViewModel
    private lateinit var viewModelFactory: ViewModelFactory
    private var _binding: FragmentHomeBinding? = null
    lateinit var adapter: RecyclerViewAdapter
    private val todoList = mutableListOf<ToDoModel>()
    private val binding get() = _binding!!


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentHomeBinding.inflate(inflater, container, false)

        viewModelFactory = ViewModelFactory(ToDoRepository(db.dao()))
        homeViewModel = ViewModelProvider(this, viewModelFactory).get(HomeViewModel::class.java)

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        homeViewModel.todoList.observe(viewLifecycleOwner, {
            todoList.clear()
            todoList.addAll(it)
            adapter.notifyDataSetChanged()
        })
        adapter = RecyclerViewAdapter(todoList) { position, isFavorite ->
            homeViewModel.setFavorite(todoList[position].id, isFavorite)
        }
        binding.recyclerView.layoutManager = LinearLayoutManager(requireContext())
        binding.recyclerView.adapter = adapter


        homeViewModel.isFavoriteCall.observe(viewLifecycleOwner, {
            if (it) {
                binding.homeFragmentContainer.setBackgroundColor(Color.parseColor("#007AD8"))
            } else {
                binding.homeFragmentContainer.setBackgroundColor(Color.parseColor("#05CF09"))
            }
        })

        val swipeHandler = object : SwipeToDeleteCallback(requireContext()) {
            override fun onSwiped(viewHolder: RecyclerView.ViewHolder, direction: Int) {
                homeViewModel.deleteToDo(todoList[viewHolder.adapterPosition].id)
                todoList.removeAt(viewHolder.adapterPosition)
            }

        }
        val itemTouchHelper = ItemTouchHelper(swipeHandler)
        itemTouchHelper.attachToRecyclerView(binding.recyclerView)

        binding.addFabBtn.setOnClickListener {
            it.findNavController().navigate(R.id.action_nav_home_to_addToDoFragment)
        }

//        homeViewModel.isFirstRun().observe(viewLifecycleOwner){
//            if (it == UserPreferencesRepository.Selection.NOT_SELECTED){
//                findNavController().navigate(R.id.action_nav_home_to_addToDoFragment)
//            }
//        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}