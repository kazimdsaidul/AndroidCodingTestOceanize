package com.kazi.test.ui.drashboard

import android.os.Bundle
import com.kazi.test.base.BaseActivity

/**
 * Created by Kazi Md. Saidul Email: Kazimdsaidul@gmail.com  Mobile: +8801675349882 on 2019-09-08.
 */
class DashboardActivity : BaseActivity() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(com.kazi.test.R.layout.activity_main)
        setToolbarWithOutBack(getString(com.kazi.test.R.string.employees_list))

    }
}
