package com.example.orchid.views

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.example.orchid.R
import com.example.orchid.common.jsonToClass
import com.example.orchid.data.PlantData
import com.example.orchid.databinding.FragmentPlantListBinding
import com.example.orchid.viewmodels.PlantListViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class PlantListFragment : Fragment() {

    lateinit var binding: FragmentPlantListBinding

    private val viewModel: PlantListViewModel by viewModels()

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        super.onCreateView(inflater, container, savedInstanceState)

        return FragmentPlantListBinding.inflate(inflater, container, false).also {
            binding = it
            binding.viewModel = viewModel
        }.root
    }

    override fun onStart() {
        super.onStart()
        binding.recyclerView.adapter = viewModel.adapter

        viewModel.data.observe(this, {
            viewModel.adapter.submitList(it)
        })

        viewModel.navigator = (activity as? Navigator)
    }

    override fun onStop() {
        super.onStop()
        viewModel.data.removeObservers(this)
    }

    companion object {
        @JvmStatic
        fun newInstance() = PlantListFragment()
    }
}
