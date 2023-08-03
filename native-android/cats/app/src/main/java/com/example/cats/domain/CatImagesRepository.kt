package com.example.cats.domain

internal interface CatImagesRepository {
    suspend fun getAll(): List<CatImage>
}