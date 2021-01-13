package com.kazi.test.ui.popularMovieList.view

import com.kazi.test.base.IView
import com.kazi.test.data.db.entities.MovieResultsItem

/**
 * Created by Kazi Md. Saidul Email: Kazimdsaidul@gmail.com  Mobile: +8801675349882 on 2019-09-07.
 */
interface IVIewEmployerList : IView {
    fun openEmpDetailsActivity(employee: MovieResultsItem)
}