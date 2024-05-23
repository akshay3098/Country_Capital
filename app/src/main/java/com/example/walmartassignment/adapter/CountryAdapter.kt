package com.example.walmartassignment.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.walmartassignment.databinding.CountryListBinding
import com.example.walmartassignment.model.Country
import com.example.walmartassignment.model.CountryItem

//Recycler Adapter Class
// Bind the data to the recycler view
class CountryAdapter(var countryItem: Country) :
    RecyclerView.Adapter<CountryAdapter.MyViewHolder>() {
    private lateinit var binding: CountryListBinding

    inner class MyViewHolder(binding: CountryListBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(countryItem: CountryItem) {
            binding.countryNameRegion.text = "${countryItem.name}, ${countryItem.region}"
            binding.countryCapital.text = countryItem.capital
            binding.countryCode.text = countryItem.code
        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        binding = CountryListBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return MyViewHolder(binding)

    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        val country = countryItem[position]
        holder.bind(country)
    }

    override fun getItemCount(): Int {
        return countryItem.size
    }

    fun updateUI(countryUpdate: Country) {
        countryItem = countryUpdate
        notifyDataSetChanged()
    }
}