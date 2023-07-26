package com.priyanka.flickerybs.presentation.search

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.google.accompanist.swiperefresh.SwipeRefresh
import com.google.accompanist.swiperefresh.rememberSwipeRefreshState
import com.priyanka.flickerybs.core.components.CustomCircularProgressBar
import com.priyanka.flickerybs.core.components.PhotoItem
import com.priyanka.flickerybs.core.components.TopAppBarContent
import com.priyanka.flickerybs.core.components.navigation.SHOW_DETAIL_SCREEN


@Composable
fun SearchPhotoListingsScreen(
    navController: NavController,
    viewModel: SearchPhotoViewModel = hiltViewModel()
) {
    val uiState by viewModel.uiState.collectAsState()
    val photos = uiState.photos

    val swipeRefreshState = rememberSwipeRefreshState(
        isRefreshing = uiState.isRefreshing
    )

    // Launch a coroutine bound to the scope of the composable, viewModel relaunched
    LaunchedEffect(key1 = viewModel) {
        viewModel.uiState
    }

    Column(
        modifier = Modifier.fillMaxSize()
    ) {
        TopAppBarContent()

        OutlinedTextField(
            value = uiState.searchQuery,
            onValueChange = { newSearchQuery ->
                viewModel.onEvent(SearchPhotosEvent.OnSearchQueryChange(newSearchQuery))
            },
            modifier = Modifier
                .padding(10.dp)
                .fillMaxWidth(),
            placeholder = {
                Text(text = "Search...")
            },
            maxLines = 1,
            singleLine = true,
            label = { Text("Search") }
        )

        SwipeRefresh(
            state = swipeRefreshState,
            onRefresh = {
                viewModel.onEvent(SearchPhotosEvent.Refresh)
            }
        ) {
            if (photos.isNotEmpty()) {
                LazyColumn(
                    modifier = Modifier.padding(10.dp)
                ) {
                    items(photos) { photo ->
                        Box(
                            modifier = Modifier
                                .fillMaxWidth()
                                .clickable {
                                    navController.navigate(route = "${SHOW_DETAIL_SCREEN}/${photo.id}")
                                }
                                .padding(8.dp)
                        ) {
                            PhotoItem(photo = photo)
                        }
                        Divider(
                            modifier = Modifier.padding(
                                vertical = 16.dp
                            )
                        )
                    }
                }
            } else {
                if (uiState.error.isNotBlank()) {
                    Text(
                        text = uiState.error,
                        color = MaterialTheme.colors.error,
                        textAlign = TextAlign.Center,
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(top = 20.dp, bottom = 50.dp)
                    )
                }
                if (uiState.isLoading) {
                    Column(
                        horizontalAlignment = Alignment.CenterHorizontally,
                        modifier = Modifier.fillMaxWidth()
                    ) {
                        CustomCircularProgressBar()
                    }
                }
            }
        }
    }
}
