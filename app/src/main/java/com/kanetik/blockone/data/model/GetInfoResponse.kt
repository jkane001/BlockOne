package com.kanetik.blockone.data.model

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class GetInfoResponse(
        @Json(name = "chain_id")
        val chainId: String,

        @Json(name = "head_block_id")
        var headBlockId: String
)