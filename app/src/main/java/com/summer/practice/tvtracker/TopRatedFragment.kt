package com.summer.practice.tvtracker

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import com.summer.practice.tvtracker.databinding.FragmentPopularBinding
import com.summer.practice.tvtracker.databinding.FragmentTopRatedBinding

class TopRatedFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        var binding: FragmentTopRatedBinding = FragmentTopRatedBinding.inflate(inflater,container,false)

        val exampleList = generateDummyList(50)

        binding.recyclerViewTopRated.adapter = Adaptor(exampleList, requireContext())
        binding.recyclerViewTopRated.layoutManager = LinearLayoutManager(context)
        binding.recyclerViewTopRated.setHasFixedSize(true)

        return binding.root
    }

    private fun generateDummyList(size: Int): List<ExampleItem> {

        val list = ArrayList<ExampleItem>()

        for (i in 0 until size) {
            val drawable = R.drawable.ic_baseline_local_movies_24

            val item = ExampleItem(drawable, "#$i Top Rated Movie")
            list += item
        }

        return list
    }
}