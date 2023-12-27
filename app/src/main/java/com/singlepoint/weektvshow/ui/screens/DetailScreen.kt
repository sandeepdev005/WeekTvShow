package com.singlepoint.weektvshow.ui.screens

import androidx.compose.foundation.layout.Column
import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.LocalContext
import androidx.navigation.NavHostController
import com.singlepoint.weektvshow.ui.commonutils.MySharedPreferences
import com.singlepoint.weektvshow.ui.components.ImageWithFavoriteButton
import com.singlepoint.weektvshow.ui.components.ShowDescription
import com.singlepoint.weektvshow.ui.viewmodels.WeekTvShowViewModel


@Composable
fun DetailScreen(navController: NavHostController, viewModel: WeekTvShowViewModel) {
    val imageURL = viewModel.selectedItem?.backdropPath ?: ""
    val description = viewModel.selectedItem?.overview ?: ""
    val id = viewModel.selectedItem?.id ?: ""
    val sharedPreferences = MySharedPreferences(LocalContext.current)

    val isFavoriteSelected : Boolean = sharedPreferences.getList(sharedPreferences.FAV_KEY)
        .contains(id.toString())

    Column {
        ImageWithFavoriteButton(imageURL, isFavoriteSelected){
            val favList  = sharedPreferences.getList(sharedPreferences.FAV_KEY).toMutableList()
            favList.add(id.toString())
            sharedPreferences.saveList(sharedPreferences.FAV_KEY, favList)
        }
        ShowDescription(desc = description)
    }


}
