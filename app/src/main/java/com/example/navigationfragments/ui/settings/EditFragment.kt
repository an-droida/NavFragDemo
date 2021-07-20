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

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentEditBinding.inflate(inflater, container, false)

        arguments.let {
            if (it != null) {
                binding.firstname.setText(it.getString(FIRSTNAME))
                binding.lastname.setText(it.getString(LASTNAME))
            }
        }


        binding.nextBtn.setOnClickListener {
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