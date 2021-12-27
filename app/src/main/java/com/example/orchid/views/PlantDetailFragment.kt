package com.example.orchid.views

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.os.bundleOf
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.example.orchid.data.PlantData
import com.example.orchid.databinding.FragmentPlantDetailBinding
import com.example.orchid.viewmodels.PlantDetailViewModel


class PlantDetailFragment : Fragment() {

    lateinit var binding: FragmentPlantDetailBinding

    private val viewModel: PlantDetailViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        if (savedInstanceState == null) {
            viewModel.plantData = requireArguments().getParcelable(ARG_PLANT)
        }
    }

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

    override fun onStart() {
        super.onStart()
        viewModel.navigator = (activity as? Navigator)
    }

    companion object {
        private const val ARG_PLANT = "arg_plant"

        @JvmStatic
        fun newInstance(plantData: PlantData): PlantDetailFragment {
            return PlantDetailFragment().also {
                it.arguments = bundleOf(ARG_PLANT to plantData)
            }
        }
    }
}
