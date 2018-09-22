package com.kanetik.blockone.data.model

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class GetBlockResponse(
        @Json(name = "id")
        val id: String,

        @Json(name = "timestamp")
        var timestamp: String,

        @Json(name = "previous")
        val previous: String
)