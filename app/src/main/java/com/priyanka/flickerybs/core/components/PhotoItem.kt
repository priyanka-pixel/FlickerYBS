package com.priyanka.flickerybs.core.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.unit.dp
import coil.compose.rememberAsyncImagePainter
import com.priyanka.flickerybs.domain.model.Photo


@Composable
fun PhotoItem(
    photo: Photo,
    modifier: Modifier = Modifier
) {
    val imagePainter = rememberAsyncImagePainter(photo.url)
    Row(
        modifier = modifier
            .background(MaterialTheme.colors.surface)
            .fillMaxWidth(),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Box(
            modifier = Modifier
                .padding(start = 2.dp)
                .clip(RoundedCornerShape(8.dp))
        ) {
            Image(
                modifier = Modifier
                    .height(150.dp)
                    .width(100.dp),
                painter = imagePainter, contentDescription = "Pictures",
            )
        }
        Column {
            Text(
                text = photo.title,
                style = MaterialTheme.typography.h5,
                modifier = Modifier.padding(start = 20.dp)
            )
            Text(
                text = "(${photo.title})",
                style = MaterialTheme.typography.body2,
                modifier = Modifier.padding(start = 20.dp)
            )
        }
    }
}