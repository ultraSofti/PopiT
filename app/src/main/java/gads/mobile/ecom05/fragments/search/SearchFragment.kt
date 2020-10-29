package gads.mobile.ecom05.fragments.search

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.NavDestination
import androidx.navigation.Navigation
import androidx.navigation.fragment.findNavController
import com.google.firebase.database.FirebaseDatabase
import gads.mobile.ecom05.R
import gads.mobile.ecom05.adapters.SearchViewAdapter
import gads.mobile.ecom05.fragments.detail.DetailFragment
import gads.mobile.ecom05.models.Wine
import gads.mobile.ecom05.repositories.RealTimeDatabaseRepository
import kotlinx.android.synthetic.main.fragment_search.*

enum class Filter {
    SEARCH_ALL,
    SEARCH_CHEAP_DEALS,
    SEARCH_NON_ALCOHOL
}

class SearchFragment : Fragment() {

    companion object {
        fun newInstance() = SearchFragment()
    }

    private lateinit var viewModel: SearchViewModel
    private lateinit var wines: List<Wine>

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_search, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        val application = requireActivity().application
        val realTimeDatabaseRepository = RealTimeDatabaseRepository(
            FirebaseDatabase.getInstance().reference,
            application.applicationContext
        )
        val searchViewModelFactory = SearchViewModelFactory(application, realTimeDatabaseRepository)
        viewModel = ViewModelProvider(this, searchViewModelFactory).get(SearchViewModel::class.java)


        viewModel.filterStatus.observe(viewLifecycleOwner, Observer {
            wines = when (it) {
                Filter.SEARCH_ALL -> {
                    viewModel.getAllWines()
                }
                Filter.SEARCH_NON_ALCOHOL -> viewModel.getNonAlcWines()
                Filter.SEARCH_CHEAP_DEALS -> viewModel.getCheapDeals()
            }
        })

        val adapter = SearchViewAdapter(wines) {
            this.findNavController()
                .navigate(SearchFragmentDirections.actionNavigationSearchToNavigationDetail(it))

        }
        search_recycler_view.adapter = adapter
        adapter.notifyDataSetChanged()
        search_recycler_view.setHasFixedSize(true)






        search_all.setOnCheckedChangeListener { chip, isChecked ->
            if (isChecked) {
                viewModel.setFilterStatus(Filter.SEARCH_ALL)
                chip.isChecked = false
            }

        }
        search_best_deal.setOnCheckedChangeListener { chip, isChecked ->
            if (isChecked) {
                viewModel.setFilterStatus(Filter.SEARCH_CHEAP_DEALS)
                chip.isChecked = false
            }
        }

        search_non_alcohol.setOnCheckedChangeListener { chip, isChecked ->
            if (isChecked) {
                viewModel.setFilterStatus(Filter.SEARCH_NON_ALCOHOL)
                chip.isChecked = false
            }
        }
    }

}