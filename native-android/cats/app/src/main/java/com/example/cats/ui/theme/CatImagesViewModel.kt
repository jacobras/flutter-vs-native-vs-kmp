package com.example.cats.ui.theme

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import com.example.cats.domain.CatImage
import com.example.cats.domain.CatImagesRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

internal class CatImagesViewModel(
    private val repository: CatImagesRepository
) {
    private val _catImages = MutableStateFlow<List<CatImage>>(emptyList())
    internal val catImages: StateFlow<List<CatImage>> = _catImages

    private var _selectedCatImage = mutableStateOf<CatImage?>(null)
    internal val selectedCatImage: State<CatImage?> = _selectedCatImage

    init {
        // TODO: use VM scope
        GlobalScope.launch(Dispatchers.Main) {
            withContext(Dispatchers.IO) {
                _catImages.value = repository.getAll()
            }
        }
    }

    fun selectCat(cat: CatImage) {
        _selectedCatImage.value = cat
    }
}