# Flickr Photo Viewer App

This is a simple Android application that allows users to view photos from the Flickr API. Users can either view the most recent photos or search for specific photos using keywords.

## Features
#### View most recent photos from the Flickr API.
#### Search for photos based on keywords (tags, title, etc.).
#### Pull to refresh to update the photo list.

#### ![Screenshot_20230727_132258](https://github.com/priyanka-pixel/FlickerYBS/assets/59287277/16d1e282-4aeb-4660-8b10-c0362ba8e20c)
#### ![Screenshot_20230727_132246](https://github.com/priyanka-pixel/FlickerYBS/assets/59287277/001cd0a8-8a82-4f83-855a-b1275876034b)
#### ![Screenshot_20230727_132220](https://github.com/priyanka-pixel/FlickerYBS/assets/59287277/e6ad3e24-ad2b-45a9-baf4-d7010ad87c23)
#### ![Screenshot_20230727_132150](https://github.com/priyanka-pixel/FlickerYBS/assets/59287277/3efa7e09-1776-46e6-89ad-934a3823256c)
#### ![Screenshot_20230727_132052](https://github.com/priyanka-pixel/FlickerYBS/assets/59287277/973d8b7a-2ee6-4199-a1be-0203ecaa3df4)


## Architecture

### The app follows the Model-View-ViewModel (MVVM) architecture pattern. It uses Android's ViewModel to manage UI-related data and the repository pattern to fetch data from the Flickr API.

### Model: Represents the data used in the app, including data classes for the Flickr API responses.
### View: Includes the layout XML files and activities/fragments responsible for displaying data to the user.
### ViewModel: Contains the business logic and state management for the UI.
### Repository: Handles data operations and abstracts the data source (API, database, etc.) from the ViewModel.

## Libraries Used

### Kotlin Coroutines - For managing asynchronous tasks and background threads.
### Retrofit - For making API requests to the Flickr API.
### Glide - For loading and displaying images from URLs.
### Hilt - For dependency injection.
### Flow - For handling asynchronous data streams in a reactive way.
### Navigation - Navigation in compose.

## How to Build

### Clone this repository.
### Open the project in Android Studio.
### Build and run the app on an emulator or physical Android device.

## API Key

### To use the Flickr API, you need to obtain an API key from the Flickr Developer portal and add it to the ApiService class in the BASE_URL constant.

### const val BASE_URL = "https://api.flickr.com/services/rest/"

### const val FLICKR_API_KEY = "YOUR_FLICKR_API_KEY_HERE"



