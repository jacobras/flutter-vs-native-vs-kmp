package com.example.cats.ui

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import com.example.cats.data.DefaultCatImagesRepository
import com.example.cats.ui.theme.CatImagesViewModel

@Composable
fun CatImagesScreen(paddingValues: PaddingValues) {
    // TODO fix
    val viewModel = remember { CatImagesViewModel(repository = DefaultCatImagesRepository()) }
    val catImages by viewModel.catImages.collectAsState()
    val selectedCatImage by viewModel.selectedCatImage

    Column(Modifier.padding(paddingValues)) {
        LazyRow {
            items(catImages) { catImage ->
                AsyncImage(
                    model = catImage.url,
                    contentDescription = null,
                    modifier = Modifier
                        .size(100.dp)
                        .padding(8.dp)
                        .clickable {
                            viewModel.selectCat(catImage)
                        },
                    contentScale = ContentScale.Crop
                )
            }
        }

        selectedCatImage?.let { selected ->
            AsyncImage(
                model = selected.url,
                contentDescription = null,
                modifier = Modifier
                    .fillMaxSize()
                    .padding(16.dp),
                contentScale = ContentScale.FillWidth
            )
        }
    }
}