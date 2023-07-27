package com.priyanka.flickerybs.presentation.search

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.google.accompanist.swiperefresh.SwipeRefresh
import com.google.accompanist.swiperefresh.rememberSwipeRefreshState
import com.priyanka.flickerybs.core.components.CustomCircularProgressBar
import com.priyanka.flickerybs.core.components.PhotoItem
import com.priyanka.flickerybs.core.components.navigation.PHOTO_DETAIL_SCREEN

@Composable
fun PhotoListScreen(
    navController: NavController, viewModel: SearchPhotoViewModel = hiltViewModel(),
) {
    val uiState by viewModel.uiState.collectAsState()
    val swipeRefreshState = rememberSwipeRefreshState(
        isRefreshing = uiState.isRefreshing
    )
    // Launch a coroutine bound to the scope of the composable, viewModel relaunched
    LaunchedEffect(key1 = viewModel, block = {
        viewModel.onEvent(SearchPhotosEvent.LoadPhoto)
    })

    if (uiState.photos.isNotEmpty()) {
        Column(
            modifier = Modifier.fillMaxSize()
        ) {

            TopAppBarContentForListOfShows(
                navController
            )

            SwipeRefresh(state = swipeRefreshState, onRefresh = {
                viewModel.onEvent(SearchPhotosEvent.Refresh)
            }) {
                LazyColumn(
                    modifier = Modifier.padding(10.dp)
                ) {
                    items(uiState.photos.size) {  i ->
                        val photo = uiState.photos[i]

                        Box(
                            modifier = Modifier.fillMaxWidth(),
                            contentAlignment = Alignment.CenterEnd )
                            {
                        PhotoItem(photo = photo, modifier = Modifier
                            .fillMaxWidth()
                            .clickable {
                                navController.navigate(route = "$PHOTO_DETAIL_SCREEN/${photo.id}")
                            }
                            .padding(8.dp))
                    }
                        if (i < uiState.photos.size) {
                            Divider(
                                modifier = Modifier.padding(
                                    vertical = 16.dp
                                )
                            )
                        }
                    }
                }
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
                    .padding(horizontal = 20.dp)
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


@Composable
fun TopAppBarContentForListOfShows(navController: NavController) {
    TopAppBar(title = {
        Text(
            text = "List of Pictures",
            maxLines = 1,
            overflow = TextOverflow.Ellipsis,
            textAlign = TextAlign.Center,
        )
    }, backgroundColor = MaterialTheme.colors.background, elevation = 4.dp, navigationIcon = {
        IconButton(onClick = {
            navController.navigateUp()
        }) {
            Icon(
                Icons.Filled.ArrowBack,
                contentDescription = "Go back",
            )
        }
    })
}
