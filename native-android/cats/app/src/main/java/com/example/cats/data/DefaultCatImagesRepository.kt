package com.example.cats.data

import com.example.cats.domain.CatImage
import com.example.cats.domain.CatImagesRepository
import io.ktor.client.HttpClient
import io.ktor.client.engine.android.Android
import io.ktor.client.request.get
import io.ktor.client.statement.bodyAsText
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import kotlinx.serialization.json.Json

internal class DefaultCatImagesRepository : CatImagesRepository {
    private val client = HttpClient(Android)
    private val json = Json { ignoreUnknownKeys = true }

    override suspend fun getAll(): List<CatImage> = withContext(Dispatchers.IO) {
        val response = client.get(catApiUrl)
        val jsonString = response.bodyAsText()
        val dto = json.decodeFromString<List<CatImageDto>>(jsonString)
        return@withContext dto.map { it.toModel() }
    }
}

private fun CatImageDto.toModel(): CatImage {
    return CatImage(id, url)
}

private const val catApiUrl = "https://api.thecatapi.com/v1/images/search?limit=10"