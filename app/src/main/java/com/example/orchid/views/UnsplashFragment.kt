package com.example.orchid.views

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.example.orchid.databinding.FragmentUnsplashListBinding
import com.example.orchid.viewmodels.UnsplashListViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class UnsplashListFragment : Fragment() {

    lateinit var binding: FragmentUnsplashListBinding

    private val viewModel: UnsplashListViewModel by viewModels()

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        super.onCreateView(inflater, container, savedInstanceState)

        return FragmentUnsplashListBinding.inflate(inflater, container, false).also {
            binding = it
        }.root
    }

    override fun onStart() {
        super.onStart()
        binding.recyclerView.adapter = viewModel.adapter

        viewModel.data.observe(this, {
            viewModel.submitList(it)
        })
    }

    override fun onStop() {
        super.onStop()
        viewModel.data.removeObservers(this)
    }
}
