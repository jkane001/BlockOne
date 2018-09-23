package com.kanetik.blockone.ui.blockdetail

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.kanetik.blockone.KanetikApplication
import com.kanetik.blockone.R
import com.kanetik.blockone.data.model.GetBlockResponse
import kotlinx.android.synthetic.main.block_detail_fragment.*

private const val INTENT_BLOCK_ID = "id"

class BlockDetailFragment : Fragment() {
    companion object {
        fun newInstance(id: String): BlockDetailFragment {
            val fragment = BlockDetailFragment()
            val args = Bundle()
            args.putString(INTENT_BLOCK_ID, id)
            fragment.arguments = args
            return fragment
        }
    }

    private lateinit var viewModel: BlockDetailViewModel

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View {
        return inflater.inflate(R.layout.block_detail_fragment, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        val factory = BlockDetailViewModel.Factory(KanetikApplication(), arguments?.getString(INTENT_BLOCK_ID) ?: "")
        viewModel = ViewModelProviders.of(this, factory).get(BlockDetailViewModel::class.java)

        viewModel.getInfoResponse().observe(this, Observer { block ->
            initLayout(block)
        })
    }

    private fun initLayout(block: GetBlockResponse) {
        producer.text = block.producer
        producerSignature.text = block.producerSignature
        transactionCount.text = block.transactions.count().toString()

        showTransactions.setOnCheckedChangeListener { _, isChecked -> if (isChecked) rawTransactions.text = block.transactions.toString() else rawTransactions.text = "" }
    }
}
