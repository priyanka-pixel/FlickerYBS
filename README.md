# Flickr Photo Viewer App

This is a simple Android application that allows users to view photos from the Flickr API. Users can either view the most recent photos or search for specific photos using keywords.

Features
-View most recent photos from the Flickr API.
-Search for photos based on keywords (tags, title, etc.).
-Pull to refresh to update the photo list.
![Screenshot 2023-07-27 022802](https://github.com/priyanka-pixel/FlickerYBS/assets/59287277/a2980ce0-5243-439b-8064-d24c5db412d8)
![Screenshot 2023-07-27 022845](https://github.com/priyanka-pixel/FlickerYBS/assets/59287277/08852414-bc69-4ba6-bb4b-c9d5fdce397b)
![Screenshot 2023-07-27 023000](https://github.com/priyanka-pixel/FlickerYBS/assets/59287277/1d77c07e-9553-4f06-852c-d278a110e0fe)
![Screenshot 2023-07-27 023102](https://github.com/priyanka-pixel/FlickerYBS/assets/59287277/ad2e408d-ed5e-4215-8d8a-e3ec692603e6)
![Screenshot 2023-07-27 023142](https://github.com/priyanka-pixel/FlickerYBS/assets/59287277/0acfbf35-af8c-4bde-8179-3873b316fe6c)

#Architecture

The app follows the Model-View-ViewModel (MVVM) architecture pattern. It uses Android's ViewModel to manage UI-related data and the repository pattern to fetch data from the Flickr API.

-Model: Represents the data used in the app, including data classes for the Flickr API responses.
-View: Includes the layout XML files and activities/fragments responsible for displaying data to the user.
-ViewModel: Contains the business logic and state management for the UI.
-Repository: Handles data operations and abstracts the data source (API, database, etc.) from the ViewModel.

#Libraries Used

-Kotlin Coroutines - For managing asynchronous tasks and background threads.
-Retrofit - For making API requests to the Flickr API.
-Glide - For loading and displaying images from URLs.
-Room - For local database storage of favorite photos.
-Hilt - For dependency injection.
-Flow - For handling asynchronous data streams in a reactive way.
-Navigation - Navigation in compose.

#How to Build

-Clone this repository.
-Open the project in Android Studio.
-Build and run the app on an emulator or physical Android device.

#API Key

To use the Flickr API, you need to obtain an API key from the Flickr Developer portal and add it to the ApiService class in the BASE_URL constant.
const val BASE_URL = "https://api.flickr.com/services/rest/"
const val FLICKR_API_KEY = "YOUR_FLICKR_API_KEY_HERE"



