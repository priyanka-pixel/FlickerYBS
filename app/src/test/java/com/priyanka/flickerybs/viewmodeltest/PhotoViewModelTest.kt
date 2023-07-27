package com.priyanka.flickerybs.viewmodeltest

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.priyanka.flickerybs.domain.model.Photo
import com.priyanka.flickerybs.domain.repository.PhotoRepo
import com.priyanka.flickerybs.presentation.home.PhotosState
import com.priyanka.flickerybs.presentation.home.PhotosViewModel
import com.priyanka.flickerybs.util.Resource
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.test.StandardTestDispatcher
import kotlinx.coroutines.test.resetMain
import kotlinx.coroutines.test.runTest
import kotlinx.coroutines.test.setMain
import org.junit.After
import org.junit.Assert.*
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.rules.TestRule
import org.mockito.Mock
import org.mockito.MockitoAnnotations
import org.mockito.kotlin.whenever

class PhotoViewModelTest {

    @get:Rule
    val instantTaskExecutionRule: TestRule = InstantTaskExecutorRule()

    @Mock
    private lateinit var repository: PhotoRepo

    @OptIn(ExperimentalCoroutinesApi::class)
    private val testDispatcher = StandardTestDispatcher()

    private lateinit var viewModel: PhotosViewModel

    @OptIn(ExperimentalCoroutinesApi::class)
    @Before
    fun setUp() {
        Dispatchers.setMain(testDispatcher)
        MockitoAnnotations.openMocks(this)
        viewModel= PhotosViewModel( repository)
    }

    @OptIn(ExperimentalCoroutinesApi::class)
    @Test
    fun `check viewModel initial state`() {
        runTest {
            val expectedState = PhotosState()
            whenever(repository.getRecentPhotos()).then { flow<Resource<List<Photo>>>{
                Resource.Success(expectedState.photos)
            } }
            viewModel.getRecentData()
            val updatedState = viewModel.uistate.first()
            assertEquals(expectedState, updatedState)
        }
    }
    @OptIn(ExperimentalCoroutinesApi::class)
    @Test
    fun `check viewModel updated state`() {
        runTest {
            val expectedState = PhotosState(isLoading = false)
            // Await the change
            testDispatcher.scheduler.advanceUntilIdle()
            val updatedState = viewModel.uistate.value
            assertEquals(expectedState, updatedState)
        }
    }

    @OptIn(ExperimentalCoroutinesApi::class)
    @After
    fun tearDown() {
        Dispatchers.resetMain()
    }
}