package com.kanetik.blockone.ui.blockdetail

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.kanetik.blockone.KanetikApplication
import com.kanetik.blockone.R

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

        viewModel = ViewModelProviders.of(this).get(BlockDetailViewModel::class.java)

        viewModel.getInfoResponse().observe(this, Observer { response ->
            Log.i("Details", response?.id ?: "")
        })

        arguments?.getString(INTENT_BLOCK_ID).let {
            viewModel.getBlockDetail(it.toString())
            initLayout()
        }
    }

    private fun initLayout() {

    }

}
