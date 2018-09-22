package com.kanetik.blockone.ui.blockdetail

import android.app.Application
import androidx.lifecycle.*
import com.kanetik.blockone.KanetikApplication
import com.kanetik.blockone.data.model.GetBlockResponse

class BlockDetailViewModel(application: Application) : AndroidViewModel(application) {
    private val blockDetail: MutableLiveData<GetBlockResponse> = MutableLiveData()

    fun getInfoResponse(): LiveData<GetBlockResponse> {
        return blockDetail
    }

    fun getBlockDetail(id: String) {
        KanetikApplication.fakeRepository
                .find { blockResponse -> blockResponse.id == id }
                .let { block -> blockDetail.postValue(block) }
    }
}
