package com.alphaomardiallo.exomindmeteoandroid.ui.resultFragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.alphaomardiallo.exomindmeteoandroid.databinding.FragmentResultBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class ResultFragment : Fragment() {

    private lateinit var binding: FragmentResultBinding
    private lateinit var viewModel: ResultViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        binding = FragmentResultBinding.inflate(inflater, container, false)
        viewModel = ViewModelProvider(requireActivity())[ResultViewModel::class.java]
        return binding.root

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        /**
         * testing that API call with delay works correctly before implementation
         */
        getCurrentWeatherData()
        observeData()
    }

    /**
     * Get current weather
     */
    private fun getCurrentWeatherData() = viewModel.getCurrentWeatherDataForEachCity()

    private fun observeData() {
        viewModel.apiCurrentProgress.observe(requireActivity()) {
            incrementProgress(it)
        }
    }

    /**
     * Progress bar setup
     */
    private fun incrementProgress(progress: Int){
        binding.progressBar.progress = progress
        if (progress == 100) binding.progressBar.visibility = View.INVISIBLE
    }
}