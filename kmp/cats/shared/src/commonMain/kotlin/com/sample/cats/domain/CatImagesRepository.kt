package com.sample.cats.domain

internal interface CatImagesRepository {
    suspend fun getAll(): List<CatImage>
}