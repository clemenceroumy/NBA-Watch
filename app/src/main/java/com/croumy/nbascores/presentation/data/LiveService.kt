package com.croumy.nbascores.presentation.data

import TodayGames
import okhttp3.OkHttpClient
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.http.GET
import retrofit2.converter.gson.GsonConverterFactory

class LiveService {
    private val okHttpClient = OkHttpClient.Builder().build()

    private val retrofit = Retrofit.Builder()
        .baseUrl("https://nba-prod-us-east-1-mediaops-stats.s3.amazonaws.com/")
        .addConverterFactory(GsonConverterFactory.create())
        .client(okHttpClient)
        .build()
        .create(AuthRestApi::class.java)

    interface AuthRestApi {
        // FOUND ON https://github.com/NBABot-Development-Team/NBABot/blob/master/src/live-channel-updater.js
        // BASED ON https://rapidapi.com/api-sports/api/api-nba/
        @GET("NBA/liveData/scoreboard/todaysScoreboard_00.json")
        suspend fun getGames(): Response<TodayGames>
    }


    suspend fun getTodayGames(): TodayGames? {
        val response = retrofit.getGames()

        if (response.isSuccessful && response.body() != null) {
            return response.body()!!
        } else {
            throw Exception(response.message())
        }
    }
}