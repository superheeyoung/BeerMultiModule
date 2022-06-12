package com.example.data

import com.google.gson.annotations.SerializedName

//@keep
data class BeerEntity(
    @SerializedName("name")
    val name: String,
    @SerializedName("tagline")
    val tagline: String,
    @SerializedName("description")
    val description: String,
    @SerializedName("image_url")
    val imgUrl: String?
)