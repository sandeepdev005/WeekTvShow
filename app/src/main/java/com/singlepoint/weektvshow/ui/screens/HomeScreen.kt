package com.singlepoint.weektvshow.ui.screens

import android.annotation.SuppressLint
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import com.singlepoint.weektvshow.data.models.Results
import com.singlepoint.weektvshow.data.models.TrendingMovies
import com.singlepoint.weektvshow.data.network.ApiState
import com.singlepoint.weektvshow.ui.components.ErrorDisplay
import com.singlepoint.weektvshow.ui.components.HomeScreenTopBar
import com.singlepoint.weektvshow.ui.components.NoDataFound
import com.singlepoint.weektvshow.ui.components.showingMovieList
import com.singlepoint.weektvshow.ui.viewmodels.WeekTvShowViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.debounce


@SuppressLint("StateFlowValueCalledInComposition")
@Composable
fun HomeScreen(navController: NavHostController, viewModel: WeekTvShowViewModel) {


    val movieState by viewModel.movieState.collectAsState()
    val searchMovieState by viewModel.searchMovieState.collectAsState()


    var moviesOrSerials by remember { mutableStateOf<List<Results>>(emptyList()) }
    var moviesBySearch by remember { mutableStateOf<List<Results>>(emptyList()) }


    var searchText by remember { mutableStateOf("") }
    val searchQueryState = remember { MutableStateFlow("") }


    var showError = remember { MutableStateFlow(false) }




    when(movieState){
        is ApiState.Error -> {
            showError.value = true
        }
        ApiState.Loading ->{}
        is ApiState.Success -> {
            showError.value = false
            val movies = (movieState as ApiState.Success<TrendingMovies>).data
            moviesOrSerials = movies.results
        }


    }


    when(searchMovieState){
        is ApiState.Error -> {
            showError.value = true
        }
        ApiState.Loading ->{}
        is ApiState.Success -> {
            showError.value = false
            val searchList = (searchMovieState as ApiState.Success<TrendingMovies>).data
            moviesBySearch = searchList.results
        }


    }


    LaunchedEffect(searchQueryState) {
        searchQueryState
            .debounce(500)
            .collect { query ->
                viewModel.searchTvShows(query)
            }
    }






    Column(modifier = Modifier.fillMaxWidth()) {
        if(showError.value){
            ErrorDisplay("Please check your Internet Connection !"){
                viewModel.fetchMovies()
            }
        }else{
            HomeScreenTopBar{text->
                moviesBySearch = emptyList()
                searchText = text
                searchQueryState.value = text
            }
            if(searchText.isEmpty()){
                showingMovieList(moviesOrSerials,navController,viewModel)
            }else{
                if(moviesBySearch.isEmpty()) NoDataFound()
                else showingMovieList(moviesBySearch,navController,viewModel)
            }
        }
    }
}
