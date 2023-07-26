package com.priyanka.flickerybs.presentation.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.priyanka.flickerybs.domain.repository.PhotoRepo
import com.priyanka.flickerybs.util.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject


@HiltViewModel
class PhotosViewModel @Inject constructor(
    private val repository: PhotoRepo

) : ViewModel() {
    private val _uiState = MutableStateFlow(PhotosState())
    val uistate: StateFlow<PhotosState> = _uiState.asStateFlow()

    init {
        getRecentData()
    }

    fun getRecentData() {
        //viewModelScope to launch a coroutine in the IO dispatcher
        viewModelScope.launch(Dispatchers.IO) {
            repository.getRecentPhotos()
                .collect { result ->
                    when (result) {
                        is Resource.Success -> {
                            result.data?.let { listings ->
                                _uiState.update { it.copy(photos = listings, isLoading = false) }
                            }
                            _uiState.value = _uiState.value.copy()
                        }
                        is Resource.Error -> {
                            _uiState.value = _uiState.value.copy(error = "error")
                        }
                        is Resource.Loading -> {
                            _uiState.value = _uiState.value.copy(isLoading = true)
                        }
                    }
                }
        }
    }
}