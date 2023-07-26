package com.priyanka.flickerybs.presentation.search

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.priyanka.flickerybs.domain.repository.PhotoRepo
import com.priyanka.flickerybs.util.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineDispatcher
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

    fun onEvent(event: SearchPhotosEvent) {
        when (event) {
            is SearchPhotosEvent.Refresh -> {
                getPhotoListings(fetchFromRemote = true)
            }
            is SearchPhotosEvent.OnSearchQueryChange -> {
                _uiState.update { it.copy(searchQuery = event.query) }

                searchJob = viewModelScope.launch {
                    delay(500L)
                    getPhotoListings()
                }
            }
            is SearchPhotosEvent.LoadPhoto -> {
                getPhotoListings()
            }
        }
    }
    private fun getPhotoListings(
        query: String = _uiState.value.searchQuery.lowercase(),

        fetchFromRemote: Boolean = false
    ) {
        viewModelScope.launch() {
            repository.searchPhotos(query, fetchFromRemote.toString())
                .collect { result ->
                    when (result) {
                        is Resource.Success -> {
                            result.data?.let { listings ->
                                _uiState.update { it.copy(photos=listings) }
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