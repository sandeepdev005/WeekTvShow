package com.singlepoint.weektvshow.data.network

import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory


object RetrofitInstance {
    private const val BASE_URL = "https://api.themoviedb.org/3/"
    private const val API_KEY =
        "eyJhbGciOiJIUzI1NiJ9.eyJhdWQiOiIwZjVkY2YwYTM4ZmMxNDY3ZjdjNmYzNzgzNmE5YjYxMCIsInN1YiI6IjY1OGFhZjA4YjdiNjlkMDhiMzZlNTIxOSIsInNjb3BlcyI6WyJhcGlfcmVhZCJdLCJ2ZXJzaW9uIjoxfQ.lybwFHCo9o5YfSOvhnfIDlWB2EsEpRoS7_tY9qdeZYw"
    private val authorizationInterceptor = AuthorizationInterceptor(API_KEY)
    private val interceptor = HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BASIC)
    private val okHttpClient = OkHttpClient.Builder()
        .addInterceptor(authorizationInterceptor)
        .addInterceptor(interceptor)
        .build()


    val api: ApiService by lazy {
        val retrofit = Retrofit.Builder()
            .baseUrl(BASE_URL)
            .client(okHttpClient)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
        retrofit.create(ApiService::class.java)
    }

}