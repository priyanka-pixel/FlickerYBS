package com.priyanka.flickerybs.core.components.navigation

import androidx.compose.runtime.Composable
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.priyanka.flickerybs.presentation.home.PhotoDetailScreen
import com.priyanka.flickerybs.presentation.home.PhotosHomeScreen
import com.priyanka.flickerybs.presentation.home.PhotosViewModel
import com.priyanka.flickerybs.presentation.search.PhotoListScreen
import com.priyanka.flickerybs.presentation.search.SearchPhotoListingsScreen
import com.priyanka.flickerybs.presentation.search.SearchPhotoViewModel
import com.priyanka.flickerybs.presentation.settings.SettingScreen


@Composable
fun NavGraph(
    navController: NavHostController,
    photoListingViewModel: SearchPhotoViewModel = hiltViewModel(),
    viewModel: PhotosViewModel = hiltViewModel()
) {
    NavHost(navController, startDestination = BottomNavItem.Home.screen_route) {
        composable(BottomNavItem.Home.screen_route) {
            PhotosHomeScreen(navController)
        }
        composable(BottomNavItem.Search.screen_route) {
            SearchPhotoListingsScreen(navController, viewModel = photoListingViewModel)
        }
        composable(BottomNavItem.Setting.screen_route) {
            SettingScreen()
        }

        composable(
            "${PHOTO_DETAIL_SCREEN}/{index}",
        )
        {
            it.arguments?.getString("index").let { index ->
                index?.toInt()?.let { it1 ->
                    PhotoDetailScreen(
                        navController = navController, index = it1,
                        viewModel = viewModel
                    )
                }
            }
        }
        composable(route = PHOTOLIST_SCREEN) {
            PhotoListScreen(navController = navController)
        }

    }
}

