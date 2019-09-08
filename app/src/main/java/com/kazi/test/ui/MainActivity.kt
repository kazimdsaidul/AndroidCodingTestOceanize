package com.kazi.test.ui

import android.app.SearchManager
import android.content.Context
import android.os.Bundle
import android.view.Menu
import android.view.View
import androidx.appcompat.widget.SearchView
import androidx.fragment.app.Fragment
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.kazi.test.R
import com.kazi.test.base.BaseActivity
import com.kazi.test.ui.employeesList.EmployeesListFragment
import com.kazi.test.ui.home.HomeFragment
import com.kazi.test.ui.notifications.EmployeeCreateFragment


class MainActivity : BaseActivity(){


    private var navView: BottomNavigationView? = null
    private lateinit var searchView: SearchView
    private var isShowSearchView = false;

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        navView = findViewById(R.id.nav_view)

        navView?.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener)
        setToolbarWithOutBack("Employees List")

        loadFragment(EmployeesListFragment())


    }

    private val mOnNavigationItemSelectedListener = BottomNavigationView.OnNavigationItemSelectedListener { item ->
            val fragment: Fragment
            when (item.itemId) {
                R.id.navigation_employee_list -> {
                    isShowSearchView = false
                    setToolbarWithOutBack("Employees List")
                    fragment = EmployeesListFragment()
                    loadFragment(fragment)
                    invalidateOptionsMenu()
                    return@OnNavigationItemSelectedListener true
                }
                R.id.navigation_search -> {
                    isShowSearchView = true

                    fragment = HomeFragment()
                    loadFragment(fragment)
                    invalidateOptionsMenu()
                    return@OnNavigationItemSelectedListener true
                }
                R.id.navigation_create -> {
                    isShowSearchView = false
                    setToolbarWithOutBack("Create Employees")
                    fragment = EmployeeCreateFragment()
                    loadFragment(fragment)
                    invalidateOptionsMenu()
                    return@OnNavigationItemSelectedListener true
                }

            }



            false
        }

    private fun loadFragment(fragment: Fragment) {
        // load fragment
        val transaction = supportFragmentManager.beginTransaction()
        transaction.replace(R.id.nav_host_fragment, fragment)
        transaction.addToBackStack(null)
        transaction.commit()
    }


    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        menuInflater.inflate(com.kazi.test.R.menu.menu_main, menu)

        // Associate searchable configuration with the SearchView
        val searchManager = getSystemService(Context.SEARCH_SERVICE) as SearchManager
        searchView = menu.findItem(com.kazi.test.R.id.action_search)
            .getActionView() as SearchView
        searchView.setSearchableInfo(
            searchManager
                .getSearchableInfo(componentName)
        )
        searchView.setMaxWidth(Integer.MAX_VALUE)

        // listening to search query text change
        searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String): Boolean {
                // filter recycler view when query submitted
                //mAdapter.getFilter().filter(query)
                return false
            }

            override fun onQueryTextChange(query: String): Boolean {
                // filter recycler view when text is changed
                //  mAdapter.getFilter().filter(query)
                return false
            }
        })

        if (isShowSearchView) {

            searchView.visibility = View.VISIBLE
            searchView.onActionViewExpanded()
            return true
        } else {
            searchView.visibility = View.GONE
            hideUserKeyboard()
            return false
        }

    }


}
