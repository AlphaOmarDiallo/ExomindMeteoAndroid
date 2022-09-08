package com.alphaomardiallo.exomindmeteoandroid.ui.resultFragment

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import coil.load
import com.alphaomardiallo.exomindmeteoandroid.R
import com.alphaomardiallo.exomindmeteoandroid.data.model.openWeather.ResponseCurrentWeather
import com.alphaomardiallo.exomindmeteoandroid.databinding.WeatherItemBinding

class ResultAdapter(
    diffCallback: DiffUtil.ItemCallback<ResponseCurrentWeather>
) :
    ListAdapter<ResponseCurrentWeather, ResultAdapter.ViewHolder>(diffCallback) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding = WeatherItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(binding.root)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(getItem(position))
    }

    class ListDiff : DiffUtil.ItemCallback<ResponseCurrentWeather>() {
        override fun areItemsTheSame(
            oldItem: ResponseCurrentWeather,
            newItem: ResponseCurrentWeather
        ): Boolean {
            return oldItem.name == newItem.name
        }

        override fun areContentsTheSame(
            oldItem: ResponseCurrentWeather,
            newItem: ResponseCurrentWeather
        ): Boolean {
            return oldItem.name == newItem.name
                    && oldItem.main.temp == newItem.main.temp
                    && oldItem.main.temp_min == newItem.main.temp_min
                    && oldItem.main.temp_max == newItem.main.temp_max
                    && oldItem.main.humidity == newItem.main.humidity
                    && oldItem.main.pressure == newItem.main.pressure
        }
    }

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private var icon: ImageView
        private var city: TextView
        private var otherInfo: TextView
        private var currentTemp: TextView

        init {
            icon = itemView.findViewById(R.id.ivIcon)
            city = itemView.findViewById(R.id.tvCityName)
            otherInfo = itemView.findViewById(R.id.tvOtherInfo)
            currentTemp = itemView.findViewById(R.id.tvCurrentTemp)
        }

        fun bind(responseCurrentWeather: ResponseCurrentWeather) {
            val data =
                "https://openweathermap.org/img/wn/${responseCurrentWeather.weather[0].icon}@2x.png"
            icon.load(data)
            city.text = responseCurrentWeather.name
            currentTemp.text = String.format("${responseCurrentWeather.main.temp.toInt()}°C")
            otherInfo.text = responseCurrentWeather.weather[0].description
        }
    }

}

