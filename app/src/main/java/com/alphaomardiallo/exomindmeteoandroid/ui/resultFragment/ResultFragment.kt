package com.alphaomardiallo.exomindmeteoandroid.ui.resultFragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.alphaomardiallo.exomindmeteoandroid.R
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
        restartOnClickSetup()
    }

    /**
     * Get current weather
     */
    private fun getCurrentWeatherData() = viewModel.getCurrentWeatherDataForEachCity()

    private fun observeData() {
        viewModel.apiCurrentProgress.observe(requireActivity()) {
            incrementProgress(it)
        }

        viewModel.messageToDisplay.observe(requireActivity()) {
            textToDisplay(it)
        }
    }

    /**
     * Progress bar setup
     */
    private fun incrementProgress(progress: Int) {
        binding.progressBar.progress = progress
        if (progress == 100) {
            hideProgressBar()
            hideTextView()
            buttonRestartVisible()
        }
    }

    private fun hideProgressBar() {
        binding.progressBar.visibility = View.INVISIBLE
    }

    private fun showProgressBarAndSetToZero(){
        binding.progressBar.visibility = View.VISIBLE
        binding.progressBar.progress = 0
    }

    /**
     * TextView
     */
    private fun textToDisplay(index: Int) {
        when (index) {
            1 -> binding.textView2.setText(R.string.waiting_message_2)
            2 -> binding.textView2.setText(R.string.waiting_message_3)
            else -> binding.textView2.setText(R.string.waiting_message_1)
        }
    }

    private fun hideTextView() {
        binding.textView2.visibility = View.INVISIBLE
    }

    private fun showTextView(){
        binding.textView2.visibility = View.VISIBLE
    }

    /**
     * Restart
     */

    private fun buttonRestartVisible() {
        binding.button.visibility = View.VISIBLE
    }

    private fun buttonRestartInvisible(){
        binding.button.visibility = View.GONE
    }

    private fun restartOnClickSetup() {
        binding.button.setOnClickListener {
            restartAPICall()
        }
    }

    private fun restartAPICall() {
        buttonRestartInvisible()
        showTextView()
        showProgressBarAndSetToZero()
        getCurrentWeatherData()
    }
}