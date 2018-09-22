package com.kanetik.blockone.data.model

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class Transaction(
        @Json(name = "status")
        val string: String,

        @Json(name = "cpu_usage_us")
        var cpuUsageUs: Int,

        @Json(name = "net_usage_words")
        val netUsageWords: Int,

        @Json(name = "trx")
        val trx: Any
)
