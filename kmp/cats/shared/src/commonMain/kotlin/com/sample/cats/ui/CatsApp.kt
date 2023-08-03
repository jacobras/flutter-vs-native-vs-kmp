package com.sample.cats.ui

import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import com.sample.cats.ui.theme.CatsTheme

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CatsApp() {
    CatsTheme {
        Scaffold(
            topBar = { TopAppBar(title = { Text(text = "Cats") }) }
        ) {
            CatImagesScreen(it)
        }
    }
}