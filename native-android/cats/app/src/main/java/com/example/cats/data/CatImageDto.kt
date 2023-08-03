package com.example.cats.data

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
internal data class CatImageDto(
    @SerialName("id")
    val id: String,

    @SerialName("url")
    val url: String
)