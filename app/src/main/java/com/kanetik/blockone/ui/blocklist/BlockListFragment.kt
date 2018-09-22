package com.kanetik.blockone.ui.blocklist

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.kanetik.blockone.R

class BlockListFragment : androidx.fragment.app.Fragment() {
    companion object {
        fun newInstance() = BlockListFragment()
    }

    private lateinit var viewModel: BlockListViewModel

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View {
        return inflater.inflate(R.layout.block_list_fragment, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProviders.of(this).get(BlockListViewModel::class.java)

        viewModel.getBlocks().observe(this, Observer { response ->
            response.let { list ->
                list.forEach { block ->
                    Log.i("GetBlock", block.previous + " (" + block.timestamp + ")")
                }
            }
        })

        viewModel.getInfoResponse().observe(this, Observer {
            Log.i("GetInfo", it.chainId)
            viewModel.requestBlocks(it.headBlockId)
        })
    }

}
