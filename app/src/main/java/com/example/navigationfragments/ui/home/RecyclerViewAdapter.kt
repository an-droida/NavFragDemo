package com.example.navigationfragments.ui.home

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.navigation.findNavController
import androidx.recyclerview.widget.RecyclerView
import com.example.navigationfragments.R
import com.example.navigationfragments.databinding.HomeRvLayoutBinding
import com.example.navigationfragments.ui.data.ToDoModel


class RecyclerViewAdapter(
    private val todos: MutableList<ToDoModel>,
    private val favoriteCallback: FavoriteCallback
) :
    RecyclerView.Adapter<RecyclerViewAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(
            HomeRvLayoutBinding.inflate(
                LayoutInflater.from(parent.context),
                parent, false
            )
        )
    }


    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.onBind()
    }

    override fun getItemCount() = todos.size

    inner class ViewHolder(private val binding: HomeRvLayoutBinding) :
        RecyclerView.ViewHolder(binding.root) {
        lateinit var model: ToDoModel
        fun onBind() {
            model = todos[adapterPosition]
            binding.titleTV.text = model.title
            binding.descriptionTV.text = model.description
            binding.itemContainerCV.setOnClickListener {
                binding.root.findNavController()
                    .navigate(HomeFragmentDirections.actionNavHomeToAddToDoFragment(model.id))
            }
            if (model.isFavorite == true) {
                binding.setFavoriteBtn.setImageResource(R.drawable.ic_baseline_favorite_24)
            } else {
                binding.setFavoriteBtn.setImageResource(R.drawable.ic_baseline_favorite_border_24)
            }
            binding.setFavoriteBtn.setOnClickListener {
                if (model.isFavorite == true) {
                    model.isFavorite = false
                    binding.setFavoriteBtn.setImageResource(R.drawable.ic_baseline_favorite_border_24)
                    favoriteCallback.setFavorite(adapterPosition, false)
                } else {
                    model.isFavorite = true
                    binding.setFavoriteBtn.setImageResource(R.drawable.ic_baseline_favorite_24)
                    favoriteCallback.setFavorite(adapterPosition, true)
                }
            }

        }

    }


}