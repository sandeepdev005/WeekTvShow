package com.singlepoint.weektvshow.data.network

import com.singlepoint.weektvshow.data.models.TrendingMovies
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query


interface ApiService {


    @GET("trending/all/day?language=en-US")
    suspend fun getTrendingMovie() : TrendingMovies


    @GET("search/tv")
    suspend fun getSearchTvShowList(@Query("query") query: String,
                                    @Query("include_adult") includeAdult: Boolean = false,
                                    @Query("language") language: String = "en-US",
                                    @Query("page") page: Int = 1) : TrendingMovies


    @GET("tv/{series_id}/similar")
    fun getSimilarTvShows(
        @Path("series_id") series_id: String,
        @Query("language") language: String ="en-US",
        @Query("page") page: Int = 1
    ): TrendingMovies
}
