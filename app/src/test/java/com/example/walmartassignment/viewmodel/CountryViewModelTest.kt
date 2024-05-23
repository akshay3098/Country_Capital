import com.example.walmartassignment.model.Country
import com.example.walmartassignment.model.CountryItem
import com.example.walmartassignment.model.Currency
import com.example.walmartassignment.model.Language
import com.example.walmartassignment.repository.CountryRepo
import com.example.walmartassignment.response.ApiResponse
import com.example.walmartassignment.viewmodel.CountryViewModel
import junit.framework.TestCase.assertEquals
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.launch
import kotlinx.coroutines.test.TestCoroutineDispatcher
import kotlinx.coroutines.test.UnconfinedTestDispatcher
import kotlinx.coroutines.test.advanceUntilIdle
import kotlinx.coroutines.test.resetMain
import kotlinx.coroutines.test.runTest
import kotlinx.coroutines.test.setMain
import org.junit.After
import org.junit.Before
import org.junit.Test
import org.mockito.Mock
import org.mockito.Mockito.`when`
import org.mockito.MockitoAnnotations

@ExperimentalCoroutinesApi
class CountryViewModelTest {

    private val testDispatcher = TestCoroutineDispatcher()

    @Mock
    private lateinit var mockRepo: CountryRepo

    private lateinit var viewModel: CountryViewModel

    @Before
    fun setUp() {
        MockitoAnnotations.openMocks(this)
        Dispatchers.setMain(testDispatcher)
        viewModel = CountryViewModel(mockRepo)
    }

    @After
    fun tearDown() {
        Dispatchers.resetMain()
        testDispatcher.cleanupTestCoroutines()
    }

    @Test
    fun `getCountryList emits success states`() = runTest {
        val countryItem = CountryItem(
            "Pago Pago",
            "AS",
            Currency("USD", "United State Dollar", "$"),
            "",
            "https://restcountries.eu/data/asm.svg",
            Language("en", "", "English", ""),
            "American Samoa",
            "OC"
        )
        val arr = arrayListOf(countryItem)
        val country = Country()
        country.addAll(arr)
        val flow =  flow<ApiResponse<Country>> {
            emit(ApiResponse.Success(country))
        }
        `when`(mockRepo.getCountryList()).thenReturn(flow)
        val emittedStates = mutableListOf<ApiResponse<Country?>>()

        // This will run all the pending coroutine tasks
        val job = launch {
            viewModel.countries.collect {
                emittedStates.add(it)
            }
        }
        viewModel.getCountryList()
        advanceUntilIdle()
        job.cancel() // Stop collecting to avoid infinite loop
        assert(emittedStates[0] is ApiResponse.Loading)
    }

    @Test
    fun `getCountryList emits loading states on network exception`() = runTest {
        val flow = flow<ApiResponse<Country>> {
            emit(ApiResponse.Loading)
        }

        `when`(mockRepo.getCountryList()).thenReturn(flow)
        val emittedStates = mutableListOf<ApiResponse<Country?>>()
        viewModel.getCountryList()
        advanceUntilIdle() // This will run all the pending coroutine tasks
        val job = launch(UnconfinedTestDispatcher()) {
            viewModel.countries.collect {
                emittedStates.add(it)
            }
        }
        job.cancel() // Stop collecting to avoid infinite loop
        assertEquals(emittedStates.size,1)

    }

    @Test
    fun `getCountryList emits error states on network exception`() = runTest {
        val flow = flow<ApiResponse<Country>> {
            emit(ApiResponse.Failure("Network Error Occurred"))
        }

        `when`(mockRepo.getCountryList()).thenReturn(flow)
        val emittedStates = mutableListOf<ApiResponse<Country?>>()
        viewModel.getCountryList()
        advanceUntilIdle() // This will run all the pending coroutine tasks
        val job = launch {
            viewModel.countries.collect {
                emittedStates.add(it)
            }
        }
        job.cancel() // Stop collecting to avoid infinite loop
        assert(emittedStates[0] is ApiResponse.Failure)
    }
}
