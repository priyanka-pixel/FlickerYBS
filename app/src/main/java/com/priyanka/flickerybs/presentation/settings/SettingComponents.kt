package com.priyanka.flickerybs.presentation.settings

import androidx.annotation.StringRes
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Face
import androidx.compose.material.icons.filled.Settings
import androidx.compose.ui.graphics.vector.ImageVector
import com.priyanka.flickerybs.R

class SettingComponents {
    fun getAllData(): List<SettingListModel> {
        return listOf(
            SettingListModel(
                imageVector = Icons.Default.Settings,
                contentDescription = R.string.about,
                text = R.string.about_text
            ),
            SettingListModel(
                imageVector = Icons.Default.Face,
                contentDescription = R.string.version_,
                text = R.string.version_0_1
            )
        )
    }
}

data class SettingListModel(
    val imageVector: ImageVector,
    @StringRes val contentDescription: Int,
    @StringRes val text: Int
)