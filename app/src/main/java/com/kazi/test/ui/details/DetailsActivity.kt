package com.kazi.test.ui.details

import android.os.Bundle
import android.widget.RatingBar
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProviders
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.kazi.test.R
import com.kazi.test.base.BaseActivity
import com.kazi.test.data.db.entities.Employee
import com.kazi.test.databinding.ActivityDetailsBinding
import com.kazi.test.ui.employeesList.EmployeesListViewModel
import com.kazi.test.ui.employeesList.employeesViewModelFactory.EmployeesViewModelFactory
import com.kazi.test.ui.employeesList.view.IVIewEmployerList
import kotlinx.android.synthetic.main.activity_details.*
import kotlinx.android.synthetic.main.content_details.*
import org.kodein.di.KodeinAware
import org.kodein.di.android.kodein
import org.kodein.di.generic.instance


class DetailsActivity : BaseActivity(), KodeinAware, IVIewEmployerList {


    override fun openEmpDetailsActivity(employee: Employee) {

    }

    private lateinit var viewModel: EmployeesListViewModel
    override val kodein by kodein()

    private val factory: EmployeesViewModelFactory by instance()


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val binding: ActivityDetailsBinding =
            DataBindingUtil.setContentView(this, R.layout.activity_details)
        viewModel = ViewModelProviders.of(this, factory).get(EmployeesListViewModel::class.java)
        viewModel.view = this
        binding.viewmodel = viewModel


        val employee = intent.getParcelableExtra("data") as Employee
        viewModel.mEmployee.value = employee

        setEmpImageFormServer(employee)
        setToolbar(getString(R.string.employee_details))


        ratingBar.onRatingBarChangeListener =
            RatingBar.OnRatingBarChangeListener { ratingBar, rating, fromUser ->
                Toast.makeText(this@DetailsActivity, "Rating :-  $rating", Toast.LENGTH_SHORT)
                    .show()
                if (rating < 1.0f)
                    ratingBar.rating = 1.0f

            }


    }

    private fun setEmpImageFormServer(employee: Employee) {
        val options = RequestOptions()
            .placeholder(R.drawable.free_avatars_cons)
            .error(R.drawable.free_avatars_cons)

        val url = employee.profileImage
        Glide.with(applicationContext).load(url)
            .apply(options)
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
