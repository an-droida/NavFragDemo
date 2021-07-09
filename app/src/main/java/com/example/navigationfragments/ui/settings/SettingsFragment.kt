package com.example.navigationfragments.ui.settings

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.findNavController
import com.example.navigationfragments.R
import com.example.navigationfragments.databinding.FragmentSettingsBinding
import com.example.navigationfragments.ui.ViewModelFactory
import com.example.navigationfragments.ui.data.ToDoDatabase.Companion.db
import com.example.navigationfragments.ui.data.ToDoRepository
import com.example.navigationfragments.ui.gallery.GalleryViewModel
import com.example.navigationfragments.ui.home.HomeFragmentDirections
import com.example.navigationfragments.ui.settings.EditSecondFragment.Companion.IMAGE_RESULT
import org.json.JSONArray
import java.util.*


class SettingsFragment : Fragment() {

    private lateinit var binding: FragmentSettingsBinding
    private var image: Int = 0
    private lateinit var viewModel: SettingsViewModel

    companion object {
        const val FIRSTNAME = "firstname"
        const val LASTNAME = "lastname"
        const val SET_IMAGE = "setImage"
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentSettingsBinding.inflate(inflater, container, false)

        val viewModelFactory = ViewModelFactory(ToDoRepository(db.dao()))
        viewModel = ViewModelProvider(this, viewModelFactory).get(SettingsViewModel::class.java)

//        viewModel.firstname.observe(viewLifecycleOwner, {
//            binding.firstnameTV.text = it
//        })
//
//        viewModel.lastname.observe(viewLifecycleOwner, {
//            binding.lastnameTV.text = it
//        })

//        viewModel.profileImage.observe(viewLifecycleOwner, {
//            binding.profileImg.setImageResource(it)
//        })


        arguments.let {
            if (it?.getString(FIRSTNAME) != null) {
                binding.firstnameTV.text = it.getString(FIRSTNAME)
                binding.lastnameTV.text = it.getString(LASTNAME)
                binding.profileImg.setImageResource(it.getInt(SET_IMAGE))
                Log.d("dsdsdsds212121", it.getInt(IMAGE_RESULT).toString())
            }
        }

        binding.editBtn.setOnClickListener {
            binding.root.findNavController()
                .navigate(
                    SettingsFragmentDirections.actionNavSettingsToEditFragment(
                        binding.firstnameTV.text.toString(),
                        binding.lastnameTV.text.toString()
                    )
                )
        }

        return binding.root
    }


}