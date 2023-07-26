package com.priyanka.flickerybs

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Scaffold
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.compose.rememberNavController
import com.priyanka.flickerybs.core.components.navigation.BottomNav
import com.priyanka.flickerybs.core.components.navigation.NavGraph
import com.priyanka.flickerybs.presentation.search.SearchPhotoViewModel
import com.priyanka.flickerybs.ui.theme.FlickerYBSTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            FlickerYBSTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colors.background
                ) {
                    Photos()

                }
            }
        }
    }
}
@Composable
fun Photos() {
    val navController = rememberNavController()
    val   photoListingViewModel: SearchPhotoViewModel = hiltViewModel()

    Scaffold(
        bottomBar = { BottomNav(navController = navController) },
        content = { padding -> Column(modifier = Modifier.padding(padding)){
            NavGraph(navController = navController,
                photoListingViewModel=photoListingViewModel
            )
        } },
        backgroundColor = MaterialTheme.colors.surface,
        modifier = Modifier.fillMaxWidth()
    )
}


