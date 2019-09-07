package com.kazi.test.ui

import android.app.SearchManager
import android.content.Context
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.view.View
import androidx.appcompat.widget.SearchView
import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.setupActionBarWithNavController
import androidx.navigation.ui.setupWithNavController
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.kazi.test.R
import com.kazi.test.base.BaseActivity


class MainActivity : BaseActivity(), BottomNavigationView.OnNavigationItemSelectedListener {


    private var navView: BottomNavigationView? = null
    private lateinit var searchView: SearchView
    private var isShowSearchView = false;

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        navView = findViewById(R.id.nav_view)

        val navController = findNavController(com.kazi.test.R.id.nav_host_fragment)
        val appBarConfiguration = AppBarConfiguration(
            setOf(
                R.id.navigation_employee_list,
                R.id.navigation_search,
                R.id.navigation_create
            )
        )
        setupActionBarWithNavController(navController, appBarConfiguration)
        navView?.setupWithNavController(navController)
        navView?.setOnNavigationItemSelectedListener(this)


    }


    override fun onNavigationItemSelected(item: MenuItem): Boolean {


        when (item.itemId) {
            R.id.navigation_employee_list -> {
                isShowSearchView = false

//                val view = navView!!.findViewById(R.id.navigation_employee_list) as View
//                view.performClick()

            }
            R.id.navigation_search -> {
                isShowSearchView = true

//                val view = navView!!.findViewById(R.id.navigation_search) as View
//                view.performClick()

            }
            R.id.navigation_create -> {
                isShowSearchView = false

//                val view = navView!!.findViewById(R.id.navigation_create) as View
//                view.performClick()

            }
        }
        invalidateOptionsMenu()

        return true
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
