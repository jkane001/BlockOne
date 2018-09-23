package com.kanetik.blockone.ui.blocklist

import androidx.recyclerview.widget.DiffUtil
import com.kanetik.blockone.data.model.GetBlockResponse

class BlockDiffCallback: DiffUtil.ItemCallback<GetBlockResponse>() {
    override fun areItemsTheSame(oldItem: GetBlockResponse, newItem: GetBlockResponse): Boolean {
        return oldItem.id == newItem.id
    }

    override fun areContentsTheSame(oldItem: GetBlockResponse, newItem: GetBlockResponse): Boolean {
        return oldItem == newItem
    }
}