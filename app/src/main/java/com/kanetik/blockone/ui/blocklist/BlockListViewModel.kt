package com.kanetik.blockone.ui.blocklist

import android.app.Application
import android.util.Log
import androidx.lifecycle.*
import com.kanetik.blockone.KanetikApplication
import com.kanetik.blockone.data.api.ChainClient
import com.kanetik.blockone.data.model.GetBlockRequest
import com.kanetik.blockone.data.model.GetBlockResponse
import com.kanetik.blockone.data.model.GetInfoResponse
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class BlockListViewModel(application: Application, maxBlocks: Int) : AndroidViewModel(application) {
    private var index = 1

    private val blockResponse: MutableLiveData<GetBlockResponse> = MutableLiveData()

    // We'd typically want to map to a domain model instead of using the response model
    private val blocks: MediatorLiveData<List<GetBlockResponse>> = MediatorLiveData()

    fun getBlocks(): LiveData<List<GetBlockResponse>> {
        return blocks
    }

    init {
        blocks.addSource(blockResponse) { blockResponse ->
            // Local cache for later
            KanetikApplication.fakeRepository += blockResponse

            val newList = blocks.value?.plus(blockResponse) ?: listOf(blockResponse)
            blocks.postValue(newList)

            index += 1
            if (index <= maxBlocks) {
                requestBlocks(blockResponse.previous)
            }
        }

        if (KanetikApplication.fakeRepository.size < maxBlocks) {
            ChainClient.getInfo(object : Callback<GetInfoResponse> {
                override fun onFailure(call: Call<GetInfoResponse>?, t: Throwable?) {
                    // Handle failure better
                }

                override fun onResponse(call: Call<GetInfoResponse>?, response: Response<GetInfoResponse>?) {
                    if (response?.isSuccessful == true) {
                        requestBlocks(response.body()?.lastIrreversibleBlockId ?: "")
                    }
                }
            })
        } else {
            // Don't retrieve again - use the cache
            index = 20
            blocks.postValue(KanetikApplication.fakeRepository)
        }
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

    class Factory(private val application: Application, private val maxBlocks: Int) : ViewModelProvider.NewInstanceFactory() {
        override fun <T : ViewModel?> create(modelClass: Class<T>): T {
            @Suppress("UNCHECKED_CAST")
            return BlockListViewModel(application, maxBlocks) as T
        }
    }
}
