package com.kanetik.blockone.ui.blocklist

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MediatorLiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.kanetik.blockone.data.api.ChainClient
import com.kanetik.blockone.data.model.GetBlockRequest
import com.kanetik.blockone.data.model.GetBlockResponse
import com.kanetik.blockone.data.model.GetInfoResponse
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class BlockListViewModel : ViewModel() {
    // Temporary
    private val MAX = 5
    private var index = 0

    private val infoResponse: MutableLiveData<GetInfoResponse> = MutableLiveData()
    private val blockResponse: MutableLiveData<GetBlockResponse> = MutableLiveData()

    // Normally this would be a Room collection (sqlite), but for purposes of the exercise, I'm just gonna store this in memory
    private val fakeRepository: MutableList<GetBlockResponse> = ArrayList()

    // Also, we'd typically want to map to a domain model instead of using the response model, but again, time.
    private val blocks: MediatorLiveData<List<GetBlockResponse>> = MediatorLiveData()

    fun getInfoResponse(): LiveData<GetInfoResponse> {
        return infoResponse
    }

    fun getBlocks(): LiveData<List<GetBlockResponse>> {
        return blocks
    }

    init {
        blocks.addSource(blockResponse) { blockResponse ->
            fakeRepository += blockResponse

            index += 1
            if (index < MAX) {
                requestBlocks(blockResponse.previous)
            } else {
                // Debounce is the better way, but this works for this exercise
                blocks.postValue(fakeRepository)
            }
        }

        ChainClient.getInfo(object : Callback<GetInfoResponse> {
            override fun onFailure(call: Call<GetInfoResponse>?, t: Throwable?) {

            }

            override fun onResponse(call: Call<GetInfoResponse>?, response: Response<GetInfoResponse>?) {
                if (response?.isSuccessful == true && response.body() != null) {
                    infoResponse.postValue(response.body())
                }
            }
        })
    }

    fun requestBlocks(id: String) {
        ChainClient.getBlock(GetBlockRequest(id), object : Callback<GetBlockResponse> {
            override fun onFailure(call: Call<GetBlockResponse>?, t: Throwable?) {
                Log.w("GetBlock", t)
            }

            override fun onResponse(call: Call<GetBlockResponse>?, response: Response<GetBlockResponse>?) {
                if (response?.isSuccessful == true && response.body() != null) {
                    blockResponse.postValue(response.body())
                }
            }
        })
    }
}
