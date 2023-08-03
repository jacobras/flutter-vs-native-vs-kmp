package com.example.cats

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import com.example.cats.ui.theme.CatsTheme
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import org.json.JSONArray
import org.json.JSONObject
import java.net.URL

class MainActivity : ComponentActivity() {
    @OptIn(ExperimentalMaterial3Api::class)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            CatsTheme {
                Scaffold(
                    topBar = { TopAppBar(title = { Text(text = "Cats") }) }
                ) {
                    CatImagesScreen(it)
                }
            }
        }
    }
}

@Composable
fun CatImagesScreen(paddingValues: PaddingValues) {
    val catImages = remember { mutableStateOf<List<CatImage>>(emptyList()) }
    val selectedCatImage = remember { mutableStateOf<CatImage?>(null) }

    LaunchedEffect(Unit) {
        val images = fetchCatImages()
        catImages.value = images
    }

    Column(Modifier.padding(paddingValues)) {
        LazyRow {
            items(catImages.value) { catImage ->
                AsyncImage(
                    model = catImage.url,
                    contentDescription = null,
                    modifier = Modifier
                        .size(100.dp)
                        .padding(8.dp)
                        .clickable {
                            selectedCatImage.value = catImage
                        },
                    contentScale = ContentScale.Crop
                )
            }
        }

        if (selectedCatImage.value != null) {
            AsyncImage(
                model = selectedCatImage.value!!.url,
                contentDescription = null,
                modifier = Modifier
                    .fillMaxSize()
                    .padding(16.dp),
                contentScale = ContentScale.FillWidth
            )
        }
    }
}

data class CatImage(val id: String, val url: String)

suspend fun fetchCatImages(): List<CatImage> = withContext(Dispatchers.IO) {
    val jsonString = URL(catApiUrl).readText()
    return@withContext parseCatImages(jsonString)
}

fun parseCatImages(jsonString: String): List<CatImage> {
    val jsonArray = JSONArray(jsonString)
    val images = mutableListOf<CatImage>()

    for (i in 0 until jsonArray.length()) {
        val jsonObject: JSONObject = jsonArray.getJSONObject(i)
        val id = jsonObject.getString("id")
        val url = jsonObject.getString("url")
        images.add(CatImage(id, url))
    }

    return images
}

private val catApiUrl = "https://api.thecatapi.com/v1/images/search?limit=10"