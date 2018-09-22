package com.kanetik.blockone.data.api

import com.kanetik.blockone.data.model.GetBlockRequest
import com.kanetik.blockone.data.model.GetBlockResponse
import com.kanetik.blockone.data.model.GetInfoResponse

import retrofit2.Call
import retrofit2.Callback
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST

object ChainClient {
    private const val INFO_PATH = "chain/get_info"
    private const val BLOCK_PATH = "chain/get_block"

    interface ChainService {
        @get:GET(INFO_PATH)
        val info: Call<GetInfoResponse>

        @POST(BLOCK_PATH)
        fun getBlock(@Body request: GetBlockRequest): Call<GetBlockResponse>
    }

    fun getInfo(callback: Callback<GetInfoResponse>) {
        val service = ApiManager.getService(ChainService::class.java)
        val call = service.info
        call.enqueue(callback)
    }

    fun getBlock(request: GetBlockRequest, callback: Callback<GetBlockResponse>) {
        val service = ApiManager.getService(ChainService::class.java)
        val call = service.getBlock(request)
        call.enqueue(callback)
    }
}
