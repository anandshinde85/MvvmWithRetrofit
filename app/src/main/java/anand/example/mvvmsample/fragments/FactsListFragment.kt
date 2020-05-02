package anand.example.mvvmsample.fragments

import anand.example.mvvmsample.R
import anand.example.mvvmsample.adapters.FactListAdapter
import anand.example.mvvmsample.model.Rows
import anand.example.mvvmsample.viewmodels.FactsViewModel
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.View.GONE
import android.view.View.VISIBLE
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.Navigation
import androidx.recyclerview.widget.LinearLayoutManager
import kotlinx.android.synthetic.main.fragment_facts_list.*

/**
 * A simple [Fragment] subclass.
 */
class FactsListFragment : Fragment() {
    private lateinit var factsViewModel: FactsViewModel
    private lateinit var factsAdapter: FactListAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        factsViewModel = ViewModelProvider(this).get(FactsViewModel::class.java)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ) = inflater.inflate(R.layout.fragment_facts_list, container, false)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initUi()
        initObserver()
    }

    private fun initObserver() {
        factsViewModel.getTitleWithFacts().observe(viewLifecycleOwner, Observer { factsResponse ->
            swipeRefresh.isRefreshing = false
            factsResponse?.let {
                requireActivity().title = it.title
                factsAdapter.updateList(it.rows)
            }
        })

        factsViewModel.getDataLoading().observe(viewLifecycleOwner, Observer {
            if (it && progressBar.visibility == VISIBLE) {
                progressBar.visibility = GONE
                recyclerView.visibility = VISIBLE
            } else {
                swipeRefresh.isRefreshing = it
            }
        })
    }

    override fun onResume() {
        super.onResume()
        recyclerView.visibility = GONE
        progressBar.visibility = VISIBLE
        factsViewModel.fetchFacts()
    }

    private fun initUi() {
        factsAdapter =
            FactListAdapter(mutableListOf(), onFactClicked = { fact ->
                onFactClicked(fact)
            })
        recyclerView.layoutManager = LinearLayoutManager(context)
        recyclerView.adapter = factsAdapter
        swipeRefresh.setOnRefreshListener {
            swipeRefresh.isRefreshing = true
            factsViewModel.fetchFacts()
        }
    }

    private fun onFactClicked(rows: Rows) {
        Navigation.findNavController(recyclerView)
            .navigate(FactsListFragmentDirections.factsListFragmentToFactDetailsFragment(rows))
    }
}