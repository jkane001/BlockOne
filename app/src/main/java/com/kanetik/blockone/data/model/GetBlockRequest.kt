package com.kanetik.blockone.data.model

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
class GetBlockRequest(
        @Json(name = "block_num_or_id")
        val blockNumOrId: String
)
