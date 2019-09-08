package com.kazi.test.ui.drashboard

import android.app.SearchManager
import android.content.Context
import android.os.Bundle
import android.util.Log
import android.view.Menu
import android.view.MenuItem
import android.view.View
import androidx.appcompat.widget.SearchView
import androidx.viewpager.widget.ViewPager
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.kazi.test.R
import com.kazi.test.base.BaseActivity
import com.kazi.test.ui.ViewPagerAdapter
import com.kazi.test.ui.employeesList.EmployeesListFragment
import com.kazi.test.ui.home.HomeFragment
import com.kazi.test.ui.notifications.EmployeeCreateFragment

/**
 * Created by Kazi Md. Saidul Email: Kazimdsaidul@gmail.com  Mobile: +8801675349882 on 2019-09-08.
 */
class DashboardActivity : BaseActivity() {

    private lateinit var searchView: SearchView
    internal lateinit var bottomNavigationView: BottomNavigationView

    //This is our viewPager
    private var viewPager: ViewPager? = null

    //Fragments
    internal lateinit var employeesListFragment: EmployeesListFragment
    internal lateinit var homeFragment: HomeFragment
    internal lateinit var employeeCreateFragment: EmployeeCreateFragment
    internal var prevMenuItem: MenuItem? = null

    private var isShowSearchView = false


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        initializingVIew()
        setupViewPager(viewPager!!)

        setToolbarWithOutBack(getString(R.string.employees_list))

    }

    private fun initializingVIew() {
        //Initializing viewPager
        viewPager = findViewById<View>(R.id.viewpager) as ViewPager

        //Initializing the bottomNavigationView
        bottomNavigationView = findViewById<View>(R.id.bottom_navigation) as BottomNavigationView

        bottomNavigationView.setOnNavigationItemSelectedListener { item ->
            when (item.itemId) {
                R.id.navigation_employee_list -> {
                    isShowSearchView = false
                    viewPager!!.currentItem = 0
                    setToolbarWithOutBack(getString(R.string.employees_list))
                }
                R.id.navigation_search -> {
                    isShowSearchView = true
                    viewPager!!.currentItem = 1
                    setToolbarWithOutBack(getString(R.string.search))
                }
                R.id.navigation_create -> {
                    isShowSearchView = false
                    viewPager!!.currentItem = 2
                    setToolbarWithOutBack(getString(R.string.create_employees))

                }
            }

            invalidateOptionsMenu()

            false
        }

        viewPager!!.addOnPageChangeListener(object : ViewPager.OnPageChangeListener {
            override fun onPageScrolled(
                position: Int,
                positionOffset: Float,
                positionOffsetPixels: Int
            ) {

            }

            override fun onPageSelected(position: Int) {
                if (prevMenuItem != null) {
                    prevMenuItem!!.isChecked = false
                } else {
                    bottomNavigationView.menu.getItem(0).isChecked = false
                }
                Log.d("page", "onPageSelected: $position")
                bottomNavigationView.menu.getItem(position).isChecked = true
                prevMenuItem = bottomNavigationView.menu.getItem(position)

            }

            override fun onPageScrollStateChanged(state: Int) {

            }
        })
    }


    private fun setupViewPager(viewPager: ViewPager) {
        val adapter = ViewPagerAdapter(supportFragmentManager)
        employeesListFragment = EmployeesListFragment()
        homeFragment = HomeFragment()
        employeeCreateFragment = EmployeeCreateFragment()
        adapter.addFragment(employeesListFragment)
        adapter.addFragment(homeFragment)
        adapter.addFragment(employeeCreateFragment)
        viewPager.adapter = adapter
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        menuInflater.inflate(R.menu.menu_main, menu)

        // Associate searchable configuration with the SearchView
        val searchManager = getSystemService(Context.SEARCH_SERVICE) as SearchManager
        searchView = menu.findItem(R.id.action_search)
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
