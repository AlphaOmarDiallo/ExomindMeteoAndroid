package com.alphaomardiallo.exomindmeteoandroid.ui.resultFragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.alphaomardiallo.exomindmeteoandroid.R
import com.alphaomardiallo.exomindmeteoandroid.databinding.FragmentResultBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class ResultFragment : Fragment() {

    private lateinit var binding: FragmentResultBinding
    private lateinit var viewModel: ResultViewModel
    private val adapter: ResultAdapter = ResultAdapter(ResultAdapter.ListDiff())

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
        setupRecyclerView()
        observeData()
        if (viewModel.currentWeatherList.value!!.isEmpty()) {
            getCurrentWeatherData()
        }
        restartOnClickSetup()
    }

    override fun onPause() {
        super.onPause()
        viewModel.currentWeatherList.removeObservers(requireActivity())
    }

    /**
     * RecyclerView setup
     */
    private fun setupRecyclerView() {
        binding.recyclerView.setHasFixedSize(true)
        binding.recyclerView.layoutManager =
            (LinearLayoutManager(view?.context, LinearLayoutManager.VERTICAL, false))
        binding.recyclerView.adapter = adapter
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
        if (isAdded) {
            binding.progressBarAPILoad.progress = progress
            if (progress == 100) {
                hideProgressBar()
                hideTextView()
                buttonRestartVisible()

                viewModel.currentWeatherList.observe(requireActivity(), adapter::submitList)
            }
        }
    }

    private fun hideProgressBar() {
        binding.progressBarAPILoad.visibility = View.INVISIBLE
    }

    private fun showProgressBarAndSetToZero() {
        binding.progressBarAPILoad.visibility = View.VISIBLE
        binding.progressBarAPILoad.progress = 0
    }

    /**
     * TextView
     */
    private fun textToDisplay(index: Int) {
        when (index) {
            1 -> binding.tvWaitingMessage.setText(R.string.waiting_message_2)
            2 -> binding.tvWaitingMessage.setText(R.string.waiting_message_3)
            else -> binding.tvWaitingMessage.setText(R.string.waiting_message_1)
        }
    }

    private fun hideTextView() {
        binding.tvWaitingMessage.visibility = View.INVISIBLE
    }

    private fun showTextView() {
        binding.tvWaitingMessage.visibility = View.VISIBLE
    }

    /**
     * Restart
     */

    private fun buttonRestartVisible() {
        binding.btnRestart.visibility = View.VISIBLE
    }

    private fun buttonRestartInvisible() {
        binding.btnRestart.visibility = View.GONE
    }

    private fun restartOnClickSetup() {
        binding.btnRestart.setOnClickListener {
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