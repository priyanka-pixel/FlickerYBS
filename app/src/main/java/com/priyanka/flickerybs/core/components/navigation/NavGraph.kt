package com.priyanka.flickerybs.core.components.navigation

import androidx.compose.runtime.Composable
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import com.priyanka.flickerybs.presentation.home.PhotosHomeScreen
import com.priyanka.flickerybs.presentation.search.SearchPhotoViewModel
import com.priyanka.flickerybs.presentation.settings.SettingScreen
import com.priyanka.flickerybs.presentation.home.PhotoDetailScreen
import com.priyanka.flickerybs.presentation.search.PhotoListScreen
import com.priyanka.flickerybs.presentation.search.SearchPhotoListingsScreen


@Composable
fun NavGraph(navController: NavHostController,
             photoListingViewModel: SearchPhotoViewModel = hiltViewModel()) {
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

        composable("${SHOW_DETAIL_SCREEN}/{id}",
            arguments = listOf(navArgument("id"){type= NavType.StringType})
        ) {
            PhotoDetailScreen( navController=navController, index = Int.MAX_VALUE)
        }
        composable(route= SHOWLIST_SCREEN){
            PhotoListScreen(navController = navController)
        }

    }
}

