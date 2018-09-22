package com.kanetik.blockone

import android.app.Application
import com.kanetik.blockone.data.model.GetBlockResponse

class KanetikApplication : Application() {
    companion object {
        // Normally this would be a Room collection (sqlite), but for purposes of the exercise, I'm just gonna store this in memory
        val fakeRepository: MutableList<GetBlockResponse> = ArrayList()

        fun getBlock(id: String) : GetBlockResponse {
            return fakeRepository.find { it.id == id } ?: GetBlockResponse("","", "")
        }
    }
}