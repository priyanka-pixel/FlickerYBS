package com.priyanka.flickerybs.core.components

import androidx.compose.foundation.layout.padding
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.material.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import com.priyanka.flickerybs.R


@Composable
fun TopAppBarContent() {
    TopAppBar(

        title = {
            Text(

                text = stringResource(R.string.photoname),
                maxLines = 1,
                overflow = TextOverflow.Ellipsis,
                textAlign = TextAlign.Center,
                modifier = Modifier.padding(start = 20.dp)
            )
        },
        modifier = Modifier.padding(10.dp),
        backgroundColor = MaterialTheme.colors.background,
        elevation = 2.dp,
    )
}
