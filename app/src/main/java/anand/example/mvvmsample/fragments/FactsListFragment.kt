package anand.example.mvvmsample.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

import anand.example.mvvmsample.R
import anand.example.mvvmsample.model.FactsResponse
import anand.example.mvvmsample.viewmodels.FactsViewModel
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider

/**
 * A simple [Fragment] subclass.
 */
class FactsListFragment : Fragment() {
    private lateinit var factsViewModel: FactsViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        factsViewModel = ViewModelProvider(this).get(FactsViewModel::class.java)
        factsViewModel.getTitleWithFacts().observe(this, Observer<FactsResponse> { factsResponse ->
            Toast.makeText(requireContext(), "OnChanged!!!", Toast.LENGTH_LONG).show()
        })
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_facts_list, container, false)
    }

    override fun onResume() {
        super.onResume()
        factsViewModel.fetchFacts()
    }

}
