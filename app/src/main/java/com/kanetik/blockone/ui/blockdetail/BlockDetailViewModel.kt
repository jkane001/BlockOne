package com.kanetik.blockone.ui.blockdetail

import android.app.Application
import androidx.lifecycle.*
import com.kanetik.blockone.KanetikApplication
import com.kanetik.blockone.data.model.GetBlockResponse

class BlockDetailViewModel(application: Application, id: String) : AndroidViewModel(application) {
    private val blockDetail: MutableLiveData<GetBlockResponse> = MutableLiveData()

    fun getInfoResponse(): LiveData<GetBlockResponse> {
        return blockDetail
    }

    fun getBlockDetail(id: String) {
        KanetikApplication.fakeRepository
                .find { blockResponse -> blockResponse.id == id }
                .let { block -> blockDetail.postValue(block) }
    }

    init {
        getBlockDetail(id)
    }

    class Factory(private val application: Application, private val id: String) : ViewModelProvider.NewInstanceFactory() {
        override fun <T : ViewModel?> create(modelClass: Class<T>): T {
            @Suppress("UNCHECKED_CAST")
            return BlockDetailViewModel(application, id) as T
        }
    }
}
