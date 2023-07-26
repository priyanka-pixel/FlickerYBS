package com.priyanka.flickerybs.presentation.search

sealed class SearchPhotosEvent {
    object Refresh : SearchPhotosEvent()
    object LoadPhoto : SearchPhotosEvent()
    data class OnSearchQueryChange(val query: String) : SearchPhotosEvent()

}
