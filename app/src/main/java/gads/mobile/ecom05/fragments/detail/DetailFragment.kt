package gads.mobile.ecom05.fragments.detail

import androidx.lifecycle.ViewModelProviders
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.bumptech.glide.Glide
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

        val selectedWine=DetailFragmentArgs.fromBundle(requireArguments()).selectedWine
           bindDetailView(selectedWine)
        materialButton2.setOnClickListener {
            viewModel.addSelectedWine(selectedWine)
            parentFragment?.view?.let { it1 -> Snackbar.make(it1,"Succeed",Snackbar.LENGTH_SHORT).show() }
        }
    }

    private fun bindDetailView(selectedWine: Wine) {
        with(selectedWine){
            textView6.text=this.product_desc
            detail_wine_origin.text=product_origin
            detail_wine_amt.text=price
            detail_wine_name.text=name
            Glide.with(requireView()).load(this.product_img).into(detail_wine_img)
            detail_wine_volume.text=product_size
            detail_wine_alcohol_lvl.text=this.alc

        }
    }

}


