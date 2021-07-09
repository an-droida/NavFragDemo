package com.example.navigationfragments.ui.settings

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.findNavController
import com.example.navigationfragments.databinding.FragmentEditBinding
import com.example.navigationfragments.ui.ViewModelFactory
import com.example.navigationfragments.ui.data.ToDoDatabase.Companion.db
import com.example.navigationfragments.ui.data.ToDoRepository
import com.example.navigationfragments.ui.settings.SettingsFragment.Companion.FIRSTNAME
import com.example.navigationfragments.ui.settings.SettingsFragment.Companion.LASTNAME

class EditFragment : Fragment() {

    lateinit var binding: FragmentEditBinding
    var image:Int?=null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
//        arguments.let {
//            if (it != null ) {
//                image = (it.getInt(EditSecondFragment.IMAGE_RESULT))
//            }
//        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentEditBinding.inflate(inflater, container, false)

//        val viewModelFactory = ViewModelFactory(ToDoRepository(db.dao()))
//        viewModel = ViewModelProvider(this, viewModelFactory).get(SettingsViewModel::class.java)

//        viewModel.firstname.observe(viewLifecycleOwner, {
//            binding.firstname.setText(it)
//        })
//
//        viewModel.lastname.observe(viewLifecycleOwner, {
//            binding.lastname.setText(it)
//        })

        arguments.let {
            if (it != null) {
                binding.firstname.setText(it.getString(FIRSTNAME))
                binding.lastname.setText(it.getString(LASTNAME))
            }
        }


        binding.nextBtn.setOnClickListener {
//            viewModel.firstname.postValue(binding.firstname.text.toString())
//            viewModel.lastname.postValue(binding.lastname.text.toString())
            requireView().findNavController().navigate(
                EditFragmentDirections.actionEditFragmentToEditSecondFragment(
                    binding.firstname.text.toString(),
                    binding.lastname.text.toString(),
                    0
                )
            )
        }
        return binding.root
    }

}