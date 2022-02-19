package com.eman.currency.ui.currency.countries

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions
import eman.currency.databinding.LayoutCountryListItemBinding
import com.domain.common.model.CountriesList

class CountriesAdapter(val _onClickCallBack: onClickCallBack) :
    ListAdapter<CountriesList, CountriesAdapter.CountriesViewHolder>(ItemDiff()) {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CountriesViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding = LayoutCountryListItemBinding.inflate(inflater, parent, false)
        return CountriesViewHolder(binding)
    }

    override fun onBindViewHolder(holder: CountriesViewHolder, position: Int) {
        holder.bind(position, getItem(position))
        holder.binding.root.setOnClickListener { _onClickCallBack.onClick(getItem(position)) }
    }

    class CountriesViewHolder(val binding: LayoutCountryListItemBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(position: Int, item: CountriesList) {
            binding.currencyName.text = "${item.currencyName}  (${item.currencySymbol})"
            Glide.with(binding.root)
                .load(item.flagName)
                .transition(DrawableTransitionOptions.withCrossFade())
                .into(binding.currencyFlag)
        }
    }

    class ItemDiff : DiffUtil.ItemCallback<CountriesList>() {
        override fun areItemsTheSame(
            oldItem: CountriesList,
            newItem: CountriesList
        ): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(
            oldItem: CountriesList,
            newItem: CountriesList
        ): Boolean {
            return oldItem.equals(newItem)
        }


    }

}

interface onClickCallBack {

    fun onClick(countriesList: CountriesList)
}