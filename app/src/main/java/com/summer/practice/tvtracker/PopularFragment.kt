package com.summer.practice.tvtracker

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import com.summer.practice.tvtracker.databinding.FragmentPopularBinding


class PopularFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        var binding:FragmentPopularBinding = FragmentPopularBinding.inflate(inflater, container, false)

        val exampleList = generateDummyList(50)

        binding.recyclerViewFavorites.adapter = Adaptor(exampleList, requireContext())
        binding.recyclerViewFavorites.layoutManager = LinearLayoutManager(context)
        binding.recyclerViewFavorites.setHasFixedSize(true)

        return binding.root
    }

    private fun generateDummyList(size: Int): List<ExampleItem> {

        val list = ArrayList<ExampleItem>()

        for (i in 0 until size) {
            val drawable = R.drawable.ic_baseline_local_movies_24

            val item = ExampleItem(drawable, "#$i Popular Movie")
            list += item
        }

        return list
    }
}