package com.singlepoint.weektvshow.ui.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardCapitalization
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import coil.compose.rememberImagePainter
import com.singlepoint.weektvshow.data.models.Results
import com.singlepoint.weektvshow.ui.viewmodels.WeekTvShowViewModel


@Composable
fun NoDataFound() {
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(MaterialTheme.colorScheme.background)
            .padding(16.dp),
        contentAlignment = Alignment.Center
    ) {
        Text("No Data Found")
    }
}


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeScreenTopBar(enteredText:(String) -> Unit){
    var searchText by remember { mutableStateOf("") }
    TopAppBar(title = {
        TextField(
            value =searchText  ,
            onValueChange = { it->
                searchText = it
                enteredText(searchText)
            },
            placeholder = { Text(text = "search") },
            singleLine = true,
            keyboardOptions = KeyboardOptions(
                keyboardType = KeyboardType.Text,
                imeAction = ImeAction.Search,
                capitalization = KeyboardCapitalization.Sentences),
            leadingIcon = {
                Icon(imageVector = Icons.Default.Search, contentDescription = null)
            }, modifier = Modifier.fillMaxWidth())
    },
        modifier = Modifier
            .fillMaxWidth()
    )
}


@Composable
fun showingMovieList(
    results: List<Results>,
    navController: NavHostController,
    viewModel: WeekTvShowViewModel
) {
    LazyVerticalGrid(columns = GridCells.Fixed(2),
        modifier = Modifier
            .fillMaxSize()
            .padding(8.dp),
        verticalArrangement = Arrangement.spacedBy(16.dp),
        horizontalArrangement = Arrangement.spacedBy(16.dp)
    ){
        items(count = results.size){ index->
            GridItem(results[index]){ item->
                viewModel.selectedItem = item
                navController.navigate("DetailScreen") //check the spelling and pass the res
            }
        }
    }
}


@Composable
fun GridItem(item: Results, onItemClick:(Results)-> Unit) {
    Card(elevation = CardDefaults.cardElevation(4.dp),
        modifier = Modifier.clickable { onItemClick(item) }) {
        Column(modifier = Modifier.padding(1.dp)) {
            Image(
                painter = rememberImagePainter(
                    data = "https://image.tmdb.org/t/p/w500".plus(item.posterPath),
                    builder = {
                        crossfade(true)
                    }
                ),
                contentDescription = null,
                modifier = Modifier
                    .fillMaxWidth()
                    .height(200.dp)
                    .background(Color.Gray)
                    .clip(MaterialTheme.shapes.medium),
                contentScale = ContentScale.Crop
            )


            Text("${if(item.title.isNullOrBlank()) "Anonymous Movie" else item.title}", fontWeight = FontWeight.W700, maxLines = 1,
                modifier = Modifier.padding(5.dp)
            )
        }
    }
}


@Composable
fun ErrorDisplay(message : String,buttonCLick:()->Unit) {
    var count by remember { mutableStateOf(0) }
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(MaterialTheme.colorScheme.background)
            .padding(16.dp),
        contentAlignment = Alignment.Center
    ) {
        Column(horizontalAlignment = Alignment.CenterHorizontally) {
            Text(message, style = MaterialTheme.typography.bodyMedium)
            Button(onClick = { buttonCLick()}) {
                Text(text = "Try Again")
            }
        }
    }
}


@Preview
@Composable
fun previewOfToolBar(){
    HomeScreenTopBar{
    }
}
