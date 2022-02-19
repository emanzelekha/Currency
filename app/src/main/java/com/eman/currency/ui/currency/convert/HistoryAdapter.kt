package com.eman.currency.ui.currency.convert

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.domain.common.model.ConvertCurrencyResult
import eman.currency.databinding.LayoutHistoryListItemBinding

class HistoryAdapter :
    ListAdapter<ConvertCurrencyResult, HistoryAdapter.HistoryViewHolder>(ItemDiff()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): HistoryViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding = LayoutHistoryListItemBinding.inflate(inflater, parent, false)
        return HistoryViewHolder(binding)
    }

    override fun onBindViewHolder(holder: HistoryViewHolder, position: Int) {
        holder.bind(position, getItem(position))
    }

    class HistoryViewHolder(val binding: LayoutHistoryListItemBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(position: Int, item: ConvertCurrencyResult) {
            binding.tvHistoryTitle.text = item.type
            binding.tvPriceDay.text = item.price.toString()
            binding.tvHistoryDate.text = item.day
        }
    }

    class ItemDiff : DiffUtil.ItemCallback<ConvertCurrencyResult>() {
        override fun areItemsTheSame(
            oldItem: ConvertCurrencyResult,
            newItem: ConvertCurrencyResult
        ): Boolean {
            return oldItem.day == newItem.day
        }

        override fun areContentsTheSame(
            oldItem: ConvertCurrencyResult,
            newItem: ConvertCurrencyResult
        ): Boolean {
            return oldItem.equals(newItem)
        }


    }

}