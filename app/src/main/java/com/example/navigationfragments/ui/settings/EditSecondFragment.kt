package com.example.navigationfragments.ui.settings

import android.os.Bundle
import android.util.Log.d
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.findNavController
import com.example.navigationfragments.R
import com.example.navigationfragments.databinding.FragmentEditSecondBinding
import com.example.navigationfragments.ui.ViewModelFactory
import com.example.navigationfragments.ui.data.ToDoDatabase
import com.example.navigationfragments.ui.data.ToDoRepository


class EditSecondFragment : Fragment() {

    lateinit var binding: FragmentEditSecondBinding
//    lateinit var viewModel: SettingsViewModel
    var image: Int? = null
    var firstname: String? = null
    var lastname: String? = null

    companion object {
        const val IMAGE_REQUEST = "requestImg"
        const val IMAGE_RESULT = "resultImg"
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentEditSecondBinding.inflate(inflater, container, false)

//        val viewModelFactory = ViewModelFactory(ToDoRepository(ToDoDatabase.db.dao()))
//        viewModel = ViewModelProvider(this, viewModelFactory).get(SettingsViewModel::class.java)

        arguments.let {
            firstname = it!!.getString(SettingsFragment.FIRSTNAME)
            lastname = it.getString(SettingsFragment.LASTNAME)
            image = it.getInt(IMAGE_RESULT)
            binding.firstnameTV.text = firstname
            binding.lastnameTV.text = lastname
            binding.profileImageView.setImageResource(image!!)
        }

        binding.addImgBtn.setOnClickListener {
            requireView().findNavController().navigate(
                EditSecondFragmentDirections.actionEditSecondFragmentToNavGallery(
                    1,
                    firstname,
                    lastname
                )
            )
        }

        binding.doneBtn.setOnClickListener {
            requireView().findNavController().navigate(
                EditSecondFragmentDirections.actionEditSecondFragmentToNavSettings(
                    binding.firstnameTV.text.toString(),
                    binding.lastnameTV.text.toString(),
                    image!!
                )
            )
        }
//
//        viewModel.firstname.observe(viewLifecycleOwner, {
//            binding.firstnameTV.text = it.toString()
//        })
//        viewModel.lastname.observe(viewLifecycleOwner, {
//            binding.lastnameTV.text = it.toString()
//        })

        return binding.root
    }
}