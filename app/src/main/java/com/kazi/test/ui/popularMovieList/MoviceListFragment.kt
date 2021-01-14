package com.kazi.test.ui.popularMovieList

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.cloudwell.paywell.consumer.utils.viewUtil.hide
import com.cloudwell.paywell.consumer.utils.viewUtil.show
import com.kazi.test.R
import com.kazi.test.data.db.entities.MovieResultsItem
import com.kazi.test.ui.details.DetailsActivity
import com.kazi.test.ui.popularMovieList.MovieViewModelFactory.EmployeesViewModelFactory
import com.kazi.test.ui.popularMovieList.adapter.EndlessRecyclerViewScrollListener
import com.kazi.test.ui.popularMovieList.adapter.MoviceItem
import com.kazi.test.ui.popularMovieList.view.IVIewEmployerList
import com.kazi.test.utils.Coroutines
import com.xwray.groupie.GroupAdapter
import com.xwray.groupie.Item
import com.xwray.groupie.OnItemClickListener
import com.xwray.groupie.ViewHolder
import kotlinx.android.synthetic.main.fragment_employees_list.*
import org.kodein.di.KodeinAware
import org.kodein.di.android.x.kodein
import org.kodein.di.generic.instance


class MoviceListFragment : Fragment(), IVIewEmployerList, KodeinAware {

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
        val root = inflater.inflate(R.layout.fragment_employees_list, container, false)


        viewModel.getMovieList(1);
        return root
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

    }

    override fun onResume() {
        super.onResume()
        viewModel.view = this
        bindUI()
    }


    private fun bindUI() = Coroutines.main {
        progress_bar.show()
        viewModel.listOfEmployees.observe(this, Observer {
            progress_bar.hide()
            initRecyclerView(it.toQuoteItem())
        })
    }

    private fun initRecyclerView(quoteItem: List<MoviceItem>) {

        val linearLayoutManager = LinearLayoutManager(context)

        val mAdapter = GroupAdapter<ViewHolder>().apply {
            addAll(quoteItem)
        }

        recyclerview.apply {
            layoutManager = linearLayoutManager
            setHasFixedSize(true)
            adapter = mAdapter
        }



        mAdapter.setOnItemClickListener(object : OnItemClickListener {
            override fun onItemClick(item: Item<*>, view: View) {
                val employeeItem = item as MoviceItem
                val movice = employeeItem.employee
                viewModel.onItemClick(movice)

            }
        })

        var scrollListener = object : EndlessRecyclerViewScrollListener(linearLayoutManager) {
            override fun onLoadMore(page: Int, totalItemsCount: Int, view: RecyclerView?) {
                // Triggered only when new data needs to be appended to the list
                // Add whatever code is needed to append new items to the bottom of the list

                viewModel.getMovieList(page + 1)
            }
        }

        recyclerview.addOnScrollListener(scrollListener)
    }


    private fun List<MovieResultsItem>.toQuoteItem(): List<MoviceItem> {
        return this.map {
            MoviceItem(it, context!!)
        }
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