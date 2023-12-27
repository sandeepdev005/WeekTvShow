package com.singlepoint.weektvshow.data.repository

import com.singlepoint.weektvshow.data.network.ApiService
import com.singlepoint.weektvshow.data.network.ApiState
import kotlinx.coroutines.flow.flow


class TvShowRepository(private val apiService: ApiService) {
    fun getMovieList() = flow{
        emit(ApiState.Loading)
        emit(ApiState.Success(apiService.getTrendingMovie()))
    }

    fun getSearchTvShow(searchText : String) = flow{
        emit(ApiState.Loading)
        emit(ApiState.Success(apiService.getSearchTvShowList(searchText)))
    }


    fun getSimilarMovies(id : Int) = flow{
        emit(ApiState.Loading)
        emit(ApiState.Success(apiService.getSimilarTvShows(id.toString())))
    }
}
