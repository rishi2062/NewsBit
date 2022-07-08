package com.example.newsbit

import retrofit2.Call
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Query

//https://newsapi.org/v2/top-headlines?country=us&category=business&apiKey=c9ac818f4bb24de286c62c9339157d78
const val BASE_URL = "https://newsapi.org/v2/"
const val API_KEY = "c9ac818f4bb24de286c62c9339157d78"
interface NewsService {
    @GET("top-headlines?apiKey=$API_KEY")
    fun getNews(@Query("country") country : String,@Query("page") page:Int) : Call<News>
}

object NewsObject{
     val newsInstance : NewsService
    init {
        val retrofit = Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
        newsInstance = retrofit.create(NewsService::class.java)
    }
}