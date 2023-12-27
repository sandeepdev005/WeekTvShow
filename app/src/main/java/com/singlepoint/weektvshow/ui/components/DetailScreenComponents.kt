package com.singlepoint.weektvshow.ui.components


import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.FavoriteBorder
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
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
import androidx.compose.ui.unit.dp
import coil.compose.rememberImagePainter


@Composable
fun ImageWithFavoriteButton(imageUrl: String, isInFavoriteList : Boolean, selectEvent :(Boolean)->Unit) {
    var isFavorite by remember { mutableStateOf(isInFavoriteList) }


    Box(
        modifier = Modifier
            .fillMaxWidth()
            .background(Color.Gray)
    ) {
        // Use the Image composable with rememberImagePainter
        Image(
            painter = rememberImagePainter(
                data = "https://image.tmdb.org/t/p/w500".plus(imageUrl),
                builder = {
                    crossfade(true)
                }
            ),
            contentDescription = null,
            modifier = Modifier
                .fillMaxWidth()
                .aspectRatio(1.1f)
                .background(Color.Gray)
                .clip(MaterialTheme.shapes.medium),
            contentScale = ContentScale.Crop
        )

        Box(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp),
            contentAlignment = Alignment.TopEnd
        ) {
            // Coin
            Card(
                modifier = Modifier
                    .size(48.dp)
                    .clip(CircleShape)
                    .background(MaterialTheme.colorScheme.primary),
                elevation = CardDefaults.elevatedCardElevation(4.dp)
            ) {
                Icon(
                    imageVector = Icons.Default.Add,
                    contentDescription = null,
                    tint = MaterialTheme.colorScheme.onPrimary
                )
            }


            // Favorite button
            IconButton(
                onClick = {
                    // Toggle the favorite state
                    isFavorite = !isFavorite
                    selectEvent(isFavorite)
                },
                modifier = Modifier
                    .padding(start = 8.dp)
                    .background(MaterialTheme.colorScheme.primary, shape = CircleShape)
            ) {
                Icon(
                    imageVector = if (isFavorite) Icons.Default.Favorite else Icons.Default.FavoriteBorder,
                    contentDescription = null,
                    tint = MaterialTheme.colorScheme.onPrimary
                )
            }
        }
    }


}


@Composable
fun ShowDescription(desc: String){
    Column(modifier = Modifier.padding(16.dp).fillMaxSize()) {
        Text(text = "Description", style = MaterialTheme.typography.headlineMedium, color = Color.Red)
        Text(text = desc, style = MaterialTheme.typography.bodyLarge, modifier = Modifier.padding(top = 10.dp))
    }
}


@Composable
fun SimilarShows(){}


@Composable
fun similarItem(){
    Card(elevation = CardDefaults.cardElevation(4.dp)) {
        Column(modifier = Modifier.padding(1.dp)) {
            Image(
                painter = rememberImagePainter(
                    data = "https://image.tmdb.org/t/p/w500".plus(""),
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
        }
    }
}
