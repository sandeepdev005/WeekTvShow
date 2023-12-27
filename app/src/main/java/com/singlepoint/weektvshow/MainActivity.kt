package com.singlepoint.weektvshow

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.singlepoint.weektvshow.data.network.RetrofitInstance
import com.singlepoint.weektvshow.data.repository.TvShowRepository
import com.singlepoint.weektvshow.ui.screens.DetailScreen
import com.singlepoint.weektvshow.ui.screens.HomeScreen
import com.singlepoint.weektvshow.ui.theme.WeekTvShowTheme
import com.singlepoint.weektvshow.ui.viewmodels.WeekTvShowViewModel

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            WeekTvShowTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    MyApp()
                }
            }
        }
    }
}

@Composable
fun MyApp(modifier: Modifier = Modifier){
    val navController = rememberNavController()
    val repository  = TvShowRepository(RetrofitInstance.api)
    val viewModel = WeekTvShowViewModel(repository, LocalContext.current)

    NavHost(navController = navController, startDestination = "HomeScreen"){
        composable("HomeScreen"){
            HomeScreen(navController,viewModel)
        }
        composable("DetailScreen"){
            DetailScreen(navController,viewModel)
        }
    }
}
