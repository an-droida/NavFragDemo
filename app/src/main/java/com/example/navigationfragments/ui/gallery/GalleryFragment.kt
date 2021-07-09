package com.example.navigationfragments.ui.gallery

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.findNavController
import com.example.navigationfragments.R
import com.example.navigationfragments.databinding.FragmentGalleryBinding
import com.example.navigationfragments.ui.settings.EditSecondFragment.Companion.IMAGE_REQUEST
import com.example.navigationfragments.ui.settings.SettingsFragment.Companion.FIRSTNAME
import com.example.navigationfragments.ui.settings.SettingsFragment.Companion.LASTNAME

class GalleryFragment : Fragment() {

    private lateinit var galleryViewModel: GalleryViewModel
    private var _binding: FragmentGalleryBinding? = null

    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        galleryViewModel =
            ViewModelProvider(this).get(GalleryViewModel::class.java)

        _binding = FragmentGalleryBinding.inflate(inflater, container, false)
        val root: View = binding.root

        arguments.let { result ->
            if (result!!.getInt(IMAGE_REQUEST) == 1) {
                binding.imageView1.setOnClickListener {
                    requireView().findNavController().navigate(
                        GalleryFragmentDirections.actionNavGalleryToEditSecondFragment(
                            result.getString(FIRSTNAME), result.getString(LASTNAME),
                            R.drawable.ic_menu_camera
                        )
                    )
                }

                binding.imageView2.setOnClickListener {
                    requireView().findNavController().navigate(
                        GalleryFragmentDirections.actionNavGalleryToEditSecondFragment(
                            result!!.getString(FIRSTNAME), result!!.getString(LASTNAME),
                            R.drawable.ic_baseline_language_24
                        )
                    )
                }

                binding.imageView3.setOnClickListener {
                    requireView().findNavController().navigate(
                        GalleryFragmentDirections.actionNavGalleryToEditSecondFragment(
                            result!!.getString(FIRSTNAME), result!!.getString(LASTNAME),
                            R.drawable.ic_baseline_settings_24
                        )
                    )
                }

                binding.imageView4.setOnClickListener {
                    requireView().findNavController().navigate(
                        GalleryFragmentDirections.actionNavGalleryToEditSecondFragment(
                            result!!.getString(FIRSTNAME), result!!.getString(LASTNAME),
                            R.drawable.ic_menu_gallery
                        )
                    )
                }
            }
        }

        return root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}