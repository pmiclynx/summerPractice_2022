package com.summer.practice.tvtracker.presentation.favorites

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.summer.practice.tvtracker.data.MovieRepositoryFactory
import com.summer.practice.tvtracker.data.networking.makeToast
import com.summer.practice.tvtracker.databinding.FragmentFavoritesBinding
import com.summer.practice.tvtracker.domain.FavoriteMovie
import com.summer.practice.tvtracker.presentation.details.DetailsActivity

class FavoritesFragment : Fragment() {
    private lateinit var binding: FragmentFavoritesBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentFavoritesBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        getData {
            binding.recyclerViewFavorites.layoutManager = LinearLayoutManager(context)
            binding.recyclerViewFavorites.adapter =
                FavoriteMovieAdapter(it, object : FavoriteMovieAdapter.ItemClickListener {
                    override fun onItemClicked(id: Int) {
                        val intent = Intent(context, DetailsActivity::class.java)
                        intent.putExtra("id", id)
                        startActivity(intent)
                    }
                })
        }
    }

    private fun getData(
        onSuccess: (List<FavoriteMovie>) -> Unit
    ) {
        MovieRepositoryFactory.repository
            .getFavorites(
                onSuccess = onSuccess,
                onError = {
                    makeToast(requireActivity(), it)
                })
    }
}