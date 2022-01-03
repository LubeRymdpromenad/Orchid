package com.example.orchid.views

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.example.orchid.databinding.FragmentPlantDetailBinding
import com.example.orchid.viewmodels.PlantDetailViewModel


class PlantDetailFragment : Fragment() {

    lateinit var binding: FragmentPlantDetailBinding

    private val viewModel: PlantDetailViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        super.onCreateView(inflater, container, savedInstanceState)

        return FragmentPlantDetailBinding.inflate(inflater, container, false).also {
            binding = it
            binding.viewModel = viewModel
        }.root
    }
}
