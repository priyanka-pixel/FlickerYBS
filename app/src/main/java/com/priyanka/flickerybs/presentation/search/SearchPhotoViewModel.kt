package com.priyanka.flickerybs.presentation.search

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.priyanka.flickerybs.domain.repository.PhotoRepo
import com.priyanka.flickerybs.util.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Job
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SearchPhotoViewModel @Inject constructor(
    private val repository: PhotoRepo
) : ViewModel() {
    private val _uiState = MutableStateFlow(SearchPhotoState())
    val uiState: StateFlow<SearchPhotoState> = _uiState.asStateFlow()

    private var searchJob: Job? = null

    init {
        // Load recent photo listings when the ViewModel is initialized
        getRecentPhotoListings()
    }

    fun onEvent(event: SearchPhotosEvent) {
        when (event) {
            is SearchPhotosEvent.Refresh -> {
                getRecentPhotoListings()
            }
            is SearchPhotosEvent.OnSearchQueryChange -> {
                // Restarts the search job whenever the search query changes
                searchJob?.cancel()
                searchJob = viewModelScope.launch {
                    delay(500L)
                    getPhotoListings(event.query)
                }
            }
            is SearchPhotosEvent.LoadPhoto -> {
                getRecentPhotoListings()
            }
        }
    }

    private fun getRecentPhotoListings() {
        viewModelScope.launch {
            repository.getRecentPhotos()
                .collect { result ->
                    when (result) {
                        is Resource.Success -> {
                            result.data?.let { listings ->
                                _uiState.update { it.copy(photos = listings) }
                            }
                            _uiState.value = _uiState.value.copy()
                        }
                        is Resource.Error -> {
                            _uiState.update { it.copy(error = "Error message") }
                        }
                        is Resource.Loading -> {
                            _uiState.update { it.copy(isLoading = result.isLoading) }
                        }
                    }
                }
        }
    }

    private fun getPhotoListings(searchText: String) {
        viewModelScope.launch {
            repository.searchPhotos(searchText)
                .collect { result ->
                    when (result) {
                        is Resource.Success -> {
                            result.data?.let { listings ->
                                _uiState.update { it.copy(photos = listings) }
                            }
                            _uiState.value = _uiState.value.copy()
                        }
                        is Resource.Error -> {
                            _uiState.update { it.copy(error = "Error message") }
                        }
                        is Resource.Loading -> {
                            _uiState.update { it.copy(isLoading = result.isLoading) }
                        }
                    }
                }
        }
    }
}

