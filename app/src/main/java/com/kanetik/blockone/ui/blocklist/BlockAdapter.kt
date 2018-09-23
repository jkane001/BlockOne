package com.kanetik.blockone.ui.blocklist

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.kanetik.blockone.R
import com.kanetik.blockone.data.model.GetBlockResponse
import kotlinx.android.synthetic.main.block_list_item.view.*

class BlockAdapter(private val context: Context,
                   private val clickListener: (GetBlockResponse) -> Unit) : ListAdapter<GetBlockResponse, ViewHolder>(BlockDiffCallback()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(LayoutInflater.from(context).inflate(R.layout.block_list_item, parent, false))
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(getItem(position), clickListener)
    }
}

class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
    private val v = view

    fun bind(block: GetBlockResponse, clickListener: (GetBlockResponse) -> Unit) {
        v.blockId?.text = block.id
        v.timestamp?.text = block.timestamp

        itemView.setOnClickListener { clickListener(block) }
    }
}