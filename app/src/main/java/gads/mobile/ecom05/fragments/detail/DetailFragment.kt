package gads.mobile.ecom05.fragments.detail

import androidx.lifecycle.ViewModelProviders
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.google.android.material.snackbar.Snackbar
import gads.mobile.ecom05.R
import gads.mobile.ecom05.database.LocalDataBase
import gads.mobile.ecom05.database.getInstance
import gads.mobile.ecom05.models.Wine
import gads.mobile.ecom05.repositories.WineRepository
import kotlinx.android.synthetic.main.fragment_detail.*
import java.util.concurrent.locks.LockSupport

class DetailFragment : Fragment() {

    companion object {
        fun newInstance() = DetailFragment()
    }

    private lateinit var viewModel: DetailViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_detail, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        val application=requireActivity().application
        val wineDao= getInstance(application.applicationContext).wineDao
        val wineRepository=WineRepository(wineDao)
        val detailViewModelFactory=DetailViewModelFactory(application,wineRepository)
        viewModel = ViewModelProvider(this,detailViewModelFactory)[DetailViewModel::class.java]
                              //  Use the ViewModel

        materialButton2.setOnClickListener {

           /* get a selected wine from navigation argument and add it to database
             eg:
           val selectedWine=  arguments?.getBundle(key:String)
            viewModel.addSelectedWine(selectedWine)*/

            parentFragment?.view?.let { it1 -> Snackbar.make(it1,"Succeed",Snackbar.LENGTH_SHORT).show() }
        }
    }

}


