package com.priyanka.flickerybs.presentation.home

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.DateRange
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import coil.compose.rememberAsyncImagePainter
import com.priyanka.flickerybs.core.components.CustomCircularProgressBar
import com.priyanka.flickerybs.core.components.navigation.PHOTOLIST_SCREEN
import com.priyanka.flickerybs.core.components.navigation.PHOTO_DETAIL_SCREEN
import com.priyanka.flickerybs.domain.model.Photo


@Composable
fun PhotosHomeScreen(
    navController: NavController,
    viewModel: PhotosViewModel = hiltViewModel()
) {

    val state by viewModel.uistate.collectAsState()

    // Launch a coroutine bound to the scope of the composable, viewModel relaunched

    if (state.photos.isNotEmpty()) {
        Column(
            modifier = Modifier.fillMaxSize()
        ) {

            Spacer(
                modifier = Modifier.padding(
                    horizontal = 16.dp
                )
            )
            com.priyanka.flickerybs.core.components.TopAppBarContent()

            Spacer(modifier = Modifier.padding(vertical = 16.dp))


            Row(
                modifier = Modifier
                    .padding(12.dp),
                horizontalArrangement = Arrangement.End
            ) {
                Text(
                    text = "List of Pictures",
                    style = TextStyle(color = Color.White, fontSize = 16.sp)
                )
                Spacer(Modifier.weight(1f))
                Text(text = "More", style = TextStyle(color = Color.Red, fontSize = 16.sp),
                    modifier = Modifier.clickable {
                        navController.navigate(route = PHOTOLIST_SCREEN)
                    }
                )
            }

            LazyRow(
                modifier = Modifier
                    .padding(12.dp)
            ) {
                itemsIndexed(state.photos.drop(1)) { index, it ->
                    val photo = state.photos[index]
                    PhotoItem_(
                        photo = photo,
                        modifier = Modifier
                            .fillMaxWidth()
                            .clickable {
                                navController.navigate(route = "$PHOTO_DETAIL_SCREEN/${index + 1}")
                            }
                    )

                    if (index < state.photos.size) {
                        Divider(
                            modifier = Modifier.padding(
                                horizontal = 10.dp
                            )
                        )
                    }
                }
            }

            Spacer(modifier = Modifier.padding(vertical = 20.dp))
            Row(
                modifier = Modifier
                    .padding(12.dp),
                horizontalArrangement = Arrangement.End
            ) {
                Text(
                    text = "Top Pictures",
                    style = TextStyle(color = Color.White, fontSize = 16.sp)
                )
                Spacer(Modifier.weight(1f))
                Text(text = "More", style = TextStyle(color = Color.Red, fontSize = 16.sp),
                    modifier = Modifier.clickable {
                        navController.navigate(route = PHOTOLIST_SCREEN)
                    }
                )
            }


            LazyRow(
                modifier = Modifier
                    .padding(12.dp)
            ) {
                itemsIndexed(state.photos.drop(1)) { index, it ->
                    val photo = state.photos[index]
                    PhotoSmallItem(
                        photo = photo,
                        modifier = Modifier
                            .fillMaxWidth()
                            .clickable {
                                navController.navigate(route = "${PHOTO_DETAIL_SCREEN}/${index + 1}")
                            }
                    )
                    if (index < state.photos.size) {
                        Divider(
                            modifier = Modifier.padding(
                                horizontal = 8.dp
                            )
                        )
                    }
                }
            }
        }
    } else {
        if (state.error.isNotBlank()) {
            Text(
                text = state.error,
                color = MaterialTheme.colors.error,
                textAlign = TextAlign.Center,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 20.dp)
                //  .align(Alignment.Center)
            )
        }
        if (state.isLoading) {
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
fun PhotoItem_(
    photo: Photo,
    modifier: Modifier = Modifier
) {
    val imagePainter = rememberAsyncImagePainter(photo.url)
    val title = photo.title

    ImageCard(
        painter = imagePainter,
        title = title,
        modifier = modifier
    )

}

@Composable
fun PhotoSmallItem(
    photo: Photo,
    modifier: Modifier = Modifier
) {
    val imagePainter = rememberAsyncImagePainter(photo.url)
    val title = photo.title

    SmallImageCard(
        painter = imagePainter,
        modifier = modifier,
        name = title
    )

}


@Composable
private fun toolbarColor(darkTheme: Boolean = isSystemInDarkTheme()): Color {
    return if (darkTheme) MaterialTheme.colors.secondary else MaterialTheme.colors.background
}

@Composable
fun ImageCard(
    painter: Painter,
    title: String,
    modifier: Modifier = Modifier
) {
    Card(
        modifier = modifier.fillMaxWidth(),
        shape = RoundedCornerShape(10.dp),
        elevation = 5.dp
    ) {
        Box(
            modifier = Modifier
                .height(200.dp)
                .width(300.dp)
        ) {
            Image(
                modifier = Modifier.fillMaxSize(),
                contentDescription = "",
                painter = painter,
                contentScale = ContentScale.FillBounds
            )

            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .background(
                        Brush.verticalGradient(
                            colors = listOf(
                                Color.Transparent,
                                Color.Black
                            ),
                            startY = 50f
                        )
                    )
            )

            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(start = 12.dp),
                contentAlignment = Alignment.BottomStart
            ) {
                Column {
                    Text(text = title, style = TextStyle(color = Color.White, fontSize = 20.sp))
                    Row(modifier = Modifier) {
                        Icon(
                            imageVector = Icons.Outlined.DateRange,
                            contentDescription = "Title",
                            tint = Color.White
                        )
                        Text(
                            text = title,
                            style = TextStyle(color = Color.White, fontSize = 20.sp)
                        )
                    }
                }

            }

        }

    }
}


@Composable
fun SmallImageCard(
    painter: Painter,
    name: String,
    modifier: Modifier = Modifier
) {
    Card(
        modifier = modifier.fillMaxWidth(),
        shape = RectangleShape,
        elevation = 5.dp,
        backgroundColor = Color.Gray
    ) {
        Column(
            modifier = Modifier
                .height(220.dp)
                .width(150.dp)
        ) {
            Image(
                painter = painter,
                contentDescription = "",
                contentScale = ContentScale.FillBounds,
                alignment = Alignment.Center,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(10.dp)
            )
            Column {
                Text(
                    text = name,
                    style = TextStyle(color = Color.White, fontSize = 16.sp),
                    modifier = Modifier.padding(start = 10.dp)
                )
                Text(
                    text = "",
                    style = TextStyle(color = Color.White, fontSize = 16.sp),
                    modifier = Modifier.padding(start = 10.dp)
                )


            }

        }

    }
}


