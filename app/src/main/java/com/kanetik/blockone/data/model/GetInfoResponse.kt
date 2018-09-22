package com.kanetik.blockone.data.model

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class GetInfoResponse(
        @Json(name = "last_irreversible_block_id")
        var lastIrreversibleBlockId: String
)