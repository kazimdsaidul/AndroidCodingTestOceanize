package com.kazi.test.ui.details

import android.os.Bundle
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProviders
import androidx.swiperefreshlayout.widget.CircularProgressDrawable
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.kazi.test.R
import com.kazi.test.base.BaseActivity
import com.kazi.test.data.db.entities.MovieResultsItem
import com.kazi.test.data.network.APIService
import com.kazi.test.databinding.ActivityDetailsBinding
import com.kazi.test.ui.popularMovieList.MovieListViewModel
import com.kazi.test.ui.popularMovieList.MovieViewModelFactory.EmployeesViewModelFactory
import com.kazi.test.ui.popularMovieList.view.IVIewEmployerList
import kotlinx.android.synthetic.main.activity_details.*
import kotlinx.android.synthetic.main.content_details.*
import org.kodein.di.KodeinAware
import org.kodein.di.android.kodein
import org.kodein.di.generic.instance


class DetailsActivity : BaseActivity(), KodeinAware, IVIewEmployerList {

    override fun openEmpDetailsActivity(employee: MovieResultsItem) {

    }

    private lateinit var viewModel: MovieListViewModel
    override val kodein by kodein()

    private val factory: EmployeesViewModelFactory by instance()


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val binding: ActivityDetailsBinding =
            DataBindingUtil.setContentView(this, R.layout.activity_details)
        viewModel = ViewModelProviders.of(this, factory).get(MovieListViewModel::class.java)
        viewModel.view = this
        binding.viewmodel = viewModel

        val m = intent.getParcelableExtra("data") as MovieResultsItem
        viewModel.mEmployee.value = m

        setEmpImageFormServer(m)
        setToolbar(getString(R.string.movie_details))
    }

    private fun setEmpImageFormServer(employee: MovieResultsItem) {
        val options = RequestOptions()
            .placeholder(R.drawable.free_avatars_cons)
            .error(R.drawable.free_avatars_cons)

        val url = employee.posterPath
        val fullURL = "https://image.tmdb.org/t/p/original" + url + "?api_key=" + APIService.API_KEY

        val circularProgressDrawable = CircularProgressDrawable(applicationContext)
        circularProgressDrawable.strokeWidth = 5f
        circularProgressDrawable.centerRadius = 30f
        circularProgressDrawable.start()

        Glide.with(applicationContext).load(fullURL)
            .apply(options)
            .placeholder(circularProgressDrawable)
            .into(ivEmpImage);



        getSupportActionBar()?.setDisplayHomeAsUpEnabled(true);
        getSupportActionBar()?.setDisplayShowHomeEnabled(true);
        setSupportActionBar(toolbar)
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
