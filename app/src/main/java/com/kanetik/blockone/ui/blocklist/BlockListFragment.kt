package com.kanetik.blockone.ui.blocklist

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import com.kanetik.blockone.KanetikApplication
import com.kanetik.blockone.R
import com.kanetik.blockone.data.model.GetBlockResponse
import kotlinx.android.synthetic.main.block_list_fragment.*
import java.util.*

private const val DEFAULT_MAX_BLOCK_COUNT = 1
private const val INTENT_BLOCK_COUNT = "maxBlocks"

class BlockListFragment : Fragment() {
    companion object {
        fun newInstance(maxBlocks: Int): BlockListFragment {
            val fragment = BlockListFragment()
            val args = Bundle()
            args.putInt(INTENT_BLOCK_COUNT, maxBlocks)
            fragment.arguments = args
            return fragment
        }
    }

    private lateinit var viewModel: BlockListViewModel

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        return inflater.inflate(R.layout.block_list_fragment, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        val factory = BlockListViewModel.Factory(KanetikApplication(), arguments?.getInt(INTENT_BLOCK_COUNT) ?: DEFAULT_MAX_BLOCK_COUNT)
        viewModel = ViewModelProviders.of(this, factory).get(BlockListViewModel::class.java)

        viewModel.getBlocks().observe(this, Observer { response ->
            blockList.adapter = BlockAdapter(context!!, response) { block : GetBlockResponse -> blockClicked(block) }
        })

        viewModel.getInfoResponse().observe(this, Observer {
            viewModel.requestBlocks(it.lastIrreversibleBlockId)
        })

        initLayout()
    }

    private fun initLayout() {
        title.text = String.format(Locale.getDefault(), getString(R.string.blocks_title), arguments?.getInt(INTENT_BLOCK_COUNT)
                ?: DEFAULT_MAX_BLOCK_COUNT)

        blockList.layoutManager = LinearLayoutManager(context)
        blockList.adapter = BlockAdapter(context!!, ArrayList()) { block : GetBlockResponse -> blockClicked(block) }
    }

    private fun blockClicked(block : GetBlockResponse) {
        Log.i("Clicked", block.id)
    }
}
