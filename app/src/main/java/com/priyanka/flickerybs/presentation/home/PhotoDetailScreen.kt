package com.priyanka.flickerybs.presentation.home

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import coil.compose.rememberAsyncImagePainter
import com.priyanka.flickerybs.ui.theme.DarkBlue

@Composable
fun PhotoDetailScreen(
    viewModel: PhotosViewModel = hiltViewModel(), navController: NavController,
    index: Int
) {
    val state = viewModel.uistate.collectAsState().value.photos[index]
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(DarkBlue)
            .padding(16.dp)
    ) {
        state.let { state ->
            val imagePainter = rememberAsyncImagePainter(state.url)
            val photoName = state.title
            TopAppBarContent(
                navController, photoName
            )
            Spacer(modifier = Modifier.height(8.dp))
            Row(modifier = Modifier.padding(20.dp)) {
                Image(
                    painter = imagePainter, contentDescription = "", Modifier.size(300.dp)
                )

            }

            Spacer(modifier = Modifier.height(8.dp))
            Text(
                text = "Name: ${state.title}",
                fontWeight = FontWeight.Bold,
                fontSize = 18.sp,
                overflow = TextOverflow.Ellipsis,
                modifier = Modifier.fillMaxWidth()
            )
            Spacer(modifier = Modifier.height(8.dp))
            Spacer(modifier = Modifier.height(8.dp))
            Divider(
                modifier = Modifier.fillMaxWidth()
            )
            Spacer(modifier = Modifier.height(8.dp))
            Text(
                text = "Description: ${state.title}",
                fontSize = 14.sp,
                modifier = Modifier.fillMaxWidth(),
                overflow = TextOverflow.Ellipsis
            )
            Spacer(modifier = Modifier.height(8.dp))
            Text(
                text = "Title:  ${state.title}",
                fontSize = 14.sp,
                modifier = Modifier.fillMaxWidth(),
                overflow = TextOverflow.Ellipsis
            )

            Spacer(modifier = Modifier.height(8.dp))
            Divider(
                modifier = Modifier.fillMaxWidth()
            )
            Spacer(modifier = Modifier.height(8.dp))
        }
    }
}

@Composable
fun TopAppBarContent(navController: NavController, name: String) {
    TopAppBar(title = {
        Text(
            text = name,
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
