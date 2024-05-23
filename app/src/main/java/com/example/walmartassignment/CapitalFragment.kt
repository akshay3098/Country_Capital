package com.example.walmartassignment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.activity.OnBackPressedCallback
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.example.walmartassignment.databinding.FragmentCapitalBinding
import com.example.walmartassignment.repository.CountryRepo
import com.example.walmartassignment.viewmodel.CountryViewModel
import com.example.walmartassignment.viewmodel.CountryViewModelFactory

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [CapitalFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class CapitalFragment : Fragment() {

    private lateinit var binding: FragmentCapitalBinding
    private lateinit var sharedViewModel: CountryViewModel



    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentCapitalBinding.inflate(layoutInflater)
        val repo = CountryRepo()
        val factory = CountryViewModelFactory(repo)
        sharedViewModel = ViewModelProvider(requireActivity(), factory).get(CountryViewModel::class.java)

        sharedViewModel.capital.observe(viewLifecycleOwner){ capital ->
            binding.capital.text = capital
        }


        val callback = object : OnBackPressedCallback(true) {
            override fun handleOnBackPressed() {
                // Navigate back to FragmentOne
                findNavController().navigate(R.id.action_capitalFragment_to_countryFragment2)
            }
        }
        requireActivity().onBackPressedDispatcher.addCallback(viewLifecycleOwner, callback)

        return binding.root
    }





}