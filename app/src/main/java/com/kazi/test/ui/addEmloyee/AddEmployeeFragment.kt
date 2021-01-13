package com.kazi.test.ui.addEmloyee

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProviders
import com.kazi.test.R
import com.kazi.test.data.db.entities.MovieResultsItem
import com.kazi.test.ui.details.DetailsActivity
import com.kazi.test.ui.popularMovieList.MovieListViewModel
import com.kazi.test.ui.popularMovieList.MovieViewModelFactory.EmployeesViewModelFactory
import com.kazi.test.ui.popularMovieList.view.IVIewEmployerList
import org.kodein.di.KodeinAware
import org.kodein.di.android.x.kodein
import org.kodein.di.generic.instance


class AddEmployeeFragment : Fragment(), IVIewEmployerList, KodeinAware {
    override fun openEmpDetailsActivity(employee: MovieResultsItem) {
        val intent = Intent(activity?.applicationContext, DetailsActivity::class.java)
        intent.putExtra("data", employee);
        activity?.startActivity(intent)
    }


    override val kodein by kodein()

    private val factory: EmployeesViewModelFactory  by instance()
    private lateinit var viewModel: MovieListViewModel


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        viewModel = ViewModelProviders.of(this, factory).get(MovieListViewModel::class.java)
        viewModel.view = this
        viewModel.getEmployeesList()
        val root = inflater.inflate(R.layout.fragment_employees_add, container, false)


        return root
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

    }


    override fun noInternetConnectionFound() {
    }

    override fun showProgress() {
    }

    override fun hiddenProgress() {
    }

    override fun onFailure(message: String?) {
    }


}