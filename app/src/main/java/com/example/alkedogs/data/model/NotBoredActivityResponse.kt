package com.example.alkedogs.data.model

import com.google.gson.annotations.SerializedName

class NotBoredActivityResponse(
    @SerializedName("activity") val activity:String?,
    @SerializedName("accessibility") val accessibility:Float?,
    @SerializedName("type") val type:String?,
    @SerializedName("participants") val participants:Int?,
    @SerializedName("price") val price:Float?,
    @SerializedName("link") val link:String?,
    @SerializedName("key") val key:String?,
    @SerializedName("error") val error:String?
)