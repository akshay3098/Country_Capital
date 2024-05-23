package com.example.walmartassignment


import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AlertDialog
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.walmartassignment.adapter.CountryAdapter
import com.example.walmartassignment.databinding.FragmentCountryBinding
import com.example.walmartassignment.model.Country
import com.example.walmartassignment.network.NetworkModule
import com.example.walmartassignment.repository.CountryRepo
import com.example.walmartassignment.response.ApiResponse
import com.example.walmartassignment.viewmodel.CountryViewModel
import com.example.walmartassignment.viewmodel.CountryViewModelFactory
import kotlinx.coroutines.launch


/*
* CountryFragment responsible to display country list to the user
* */
class CountryFragment : Fragment() {

    val TAG = CountryFragment::class.simpleName

    private lateinit var binding: FragmentCountryBinding
    private lateinit var viewModel: CountryViewModel
    private lateinit var adapter: CountryAdapter
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentCountryBinding.inflate(layoutInflater)
        val network = NetworkModule()
        val retrofit = network.getRetrofit()
        val api = network.getApiService(retrofit)
        val repo = CountryRepo(api)
        val factory = CountryViewModelFactory(repo)
        viewModel = ViewModelProvider(requireActivity(), factory)[CountryViewModel::class.java]
        viewModel.getCountryList()
        setAdapter()
        setObserver()
        return binding.root
    }

    /*
    * this function is responsible to observe data coming from the view model through mutableStateFlow
    * */
    private fun setObserver() {
        lifecycleScope.launch {
            viewModel.countries.collect { result ->
                when (result) {
                    is ApiResponse.Failure -> {
                        binding.progress.visibility = View.GONE
                        binding.errorText.visibility = View.VISIBLE
                        binding.errorText.text = result.data
                    }

                    is ApiResponse.Success -> {

                        if (result.data?.isNotEmpty() == true) {
                            binding.progress.visibility = View.GONE
                            adapter.updateUI(result.data)

                        } else {
                            showAlertBox()
                        }

                    }

                    is ApiResponse.Loading -> {
                        binding.progress.visibility = View.VISIBLE
                    }

                    else -> {
                        Log.d(TAG, "Please try this again")
                    }
                }
            }
        }
    }

    private fun showAlertBox() {
        AlertDialog.Builder(requireActivity())
            .setTitle("No Countries")
            .setMessage("Please try again later...")
            .setPositiveButton("Ok") { dialog, _ ->
                dialog.dismiss()
            }
            .setNegativeButton("Cancel") { dialog, _ ->
                dialog.dismiss()
            }
            .create()
            .show()
    }

    fun setAdapter() {
        adapter = CountryAdapter(Country())
        binding.countryRecView.layoutManager = LinearLayoutManager(requireActivity())
        binding.countryRecView.adapter = adapter
    }


}