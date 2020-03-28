package com.niv.factsapp.ui.adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.recyclerview.widget.RecyclerView
import com.niv.factsapp.BR
import com.niv.factsapp.R
import com.niv.factsapp.models.FactsListItem


/**
 * FactsListAdapter - Adapter class for facts listing
 *
 * @author Nivedith
 * @since 2020-03-28.
 */
class FactsListAdapter(var context: Context, private val list: List<FactsListItem>) :
    RecyclerView.Adapter<FactsListAdapter.FactViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FactViewHolder {

        val layoutInflater = LayoutInflater.from(parent.context)
        val binding: ViewDataBinding =
            DataBindingUtil.inflate(layoutInflater, R.layout.row_item_fact_list, parent, false)

        return FactViewHolder(binding)
    }

    override fun getItemCount(): Int = list.size

    override fun onBindViewHolder(holder: FactViewHolder, position: Int) =
        holder.bind(list[position])


    inner class FactViewHolder(val binding: ViewDataBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(data: Any) {
            binding.setVariable(BR.factItem, data)
            binding.executePendingBindings()
        }
    }
}