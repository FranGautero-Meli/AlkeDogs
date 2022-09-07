package com.example.alkedogs.data.network

import com.example.alkedogs.data.model.NotBoredActivity
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query


interface NotBoredApiService {
    @GET
    suspend fun getRandomActivity(@Query("participants") participants: Int): Response<NotBoredActivity>

    @GET
    suspend fun getActivity(@Query("participants") participants: Int, @Query("type") type:String):Response<NotBoredActivity>
}