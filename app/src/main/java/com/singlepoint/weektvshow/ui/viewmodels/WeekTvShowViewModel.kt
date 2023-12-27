package com.singlepoint.weektvshow.ui.viewmodels

import android.content.Context
import android.util.Log
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.singlepoint.weektvshow.data.models.Results
import com.singlepoint.weektvshow.data.models.TrendingMovies
import com.singlepoint.weektvshow.data.network.ApiState
import com.singlepoint.weektvshow.data.repository.TvShowRepository
import com.singlepoint.weektvshow.ui.commonutils.isNetworkAvailable
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch


class WeekTvShowViewModel(private val repository: TvShowRepository, val context: Context) : ViewModel() {


    private val _movieState = MutableStateFlow<ApiState<TrendingMovies>>(ApiState.Loading)
    val movieState : StateFlow<ApiState<TrendingMovies>> get() =_movieState


    private val _searchMovieState = MutableStateFlow<ApiState<TrendingMovies>>(ApiState.Loading)
    val searchMovieState : StateFlow<ApiState<TrendingMovies>> get() =_searchMovieState




    private val _similarMovieState = MutableStateFlow<ApiState<TrendingMovies>>(ApiState.Loading)
    val similarMovieState : StateFlow<ApiState<TrendingMovies>> get() = _similarMovieState


    var selectedItem by mutableStateOf<Results?>(null)




    init {
        fetchMovies()
    }


    fun fetchMovies(){
        viewModelScope.launch {
            try{
                if (isNetworkAvailable(context)){
                    repository.getMovieList().collect{ result ->
                        _movieState.value = result
                    }
                }else{
                    _movieState.value = ApiState.Error("")
                }
            }catch (exception : Exception){
                //Handle Exception
            }
        }
    }


    fun searchTvShows(searchText : String){
        viewModelScope.launch {
            try{
                if (isNetworkAvailable(context)){
                    repository.getSearchTvShow(searchText).collect{ result->
                        _searchMovieState.value = result
                    }
                }else{
                    _searchMovieState.value = ApiState.Error("")
                }
            }catch (exception : Exception){
                //Handle Exception
            }
        }
    }


    fun searchSimilarTvShows(id : Int){
        viewModelScope.launch {
            try{
                if (isNetworkAvailable(context)){
                    repository.getSimilarMovies(id).collect{ result->
                        _similarMovieState.value = result
                    }
                }else{
                    _similarMovieState.value = ApiState.Error("")
                }
            }catch (exception : Exception){
                Log.d("Test","Test${exception.message}")
            }
        }
    }
}
