package com.kanetik.blockone.data.model

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class GetBlockResponse(
        @Json(name = "previous")
        val previous: String,

        @Json(name = "timestamp")
        var timestamp: String
)