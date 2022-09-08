package com.alphaomardiallo.exomindmeteoandroid.ui.resultFragment

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.alphaomardiallo.exomindmeteoandroid.data.model.openWeather.ResponseCurrentWeather

class ResultAdapter(
    diffCallback: DiffUtil.ItemCallback<ResponseCurrentWeather>
) :
    ListAdapter<ResponseCurrentWeather, ResultAdapter.ViewHolder>(diffCallback) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        TODO("Not yet implemented")
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        TODO("Not yet implemented")
    }
    class ListDiff : DiffUtil.ItemCallback<ResponseCurrentWeather>() {
        override fun areItemsTheSame(
            oldItem: ResponseCurrentWeather,
            newItem: ResponseCurrentWeather
        ): Boolean {
            TODO("Not yet implemented")
        }

        override fun areContentsTheSame(
            oldItem: ResponseCurrentWeather,
            newItem: ResponseCurrentWeather
        ): Boolean {
            TODO("Not yet implemented")
        }

    }

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {


        fun bind(photo: String?) {

        }

        init {

        }
    }

}

