package com.example.alkedogs.data.network

import com.example.alkedogs.data.model.NotBoredActivityResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query


interface NotBoredApiService {
    @GET
    suspend fun getRandomActivity(@Query("participants") participants: Int): Response<NotBoredActivityResponse>

    @GET
    suspend fun getActivity(@Query("participants") participants: Int, @Query("type") type:String):Response<NotBoredActivityResponse>
}