package com.kazi.test.ui.employeesList

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import com.cloudwell.paywell.consumer.utils.viewUtil.hide
import com.cloudwell.paywell.consumer.utils.viewUtil.show
import com.kazi.test.R
import com.kazi.test.data.db.entities.Employee
import com.kazi.test.ui.details.DetailsActivity
import com.kazi.test.ui.employeesList.adapter.EmployeeItem
import com.kazi.test.ui.employeesList.employeesViewModelFactory.EmployeesViewModelFactory
import com.kazi.test.ui.employeesList.view.IVIewEmployerList
import com.kazi.test.utils.Coroutines
import com.xwray.groupie.GroupAdapter
import com.xwray.groupie.Item
import com.xwray.groupie.OnItemClickListener
import com.xwray.groupie.ViewHolder
import kotlinx.android.synthetic.main.fragment_employees_list.*
import org.kodein.di.KodeinAware
import org.kodein.di.android.x.kodein
import org.kodein.di.generic.instance


class EmployeesListFragment : Fragment(), IVIewEmployerList, KodeinAware {
    override fun openEmpDetailsActivity(employee: Employee) {
        val intent = Intent(activity?.applicationContext, DetailsActivity::class.java)
        intent.putExtra("data", employee);
        activity?.startActivity(intent)
    }


    override val kodein by kodein()

    private val factory: EmployeesViewModelFactory  by instance()
    private lateinit var viewModel: EmployeesListViewModel


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        viewModel = ViewModelProviders.of(this, factory).get(EmployeesListViewModel::class.java)
        val root = inflater.inflate(R.layout.fragment_employees_list, container, false)


        return root
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

    }

    override fun onResume() {
        super.onResume()
        viewModel.view = this
        viewModel.getEmployeesList()
        bindUI()
    }


    private fun bindUI() = Coroutines.main {
        progress_bar.show()
        viewModel.listOfEmployees.observe(this, Observer {
            progress_bar.hide()
            initRecyclerView(it.toQuoteItem())
        })
    }

    private fun initRecyclerView(quoteItem: List<EmployeeItem>) {

        val mAdapter = GroupAdapter<ViewHolder>().apply {
            addAll(quoteItem)
        }

        recyclerview.apply {
            layoutManager = LinearLayoutManager(context)
            setHasFixedSize(true)
            adapter = mAdapter
        }



        mAdapter.setOnItemClickListener(object : OnItemClickListener {
            override fun onItemClick(item: Item<*>, view: View) {
                val employeeItem = item as EmployeeItem
                 viewModel.onItemClick(employeeItem.employee)

            }
        })
    }


    private fun List<Employee>.toQuoteItem(): List<EmployeeItem> {
        return this.map {
            EmployeeItem(it)
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