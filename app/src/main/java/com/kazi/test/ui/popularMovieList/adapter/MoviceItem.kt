package com.kazi.test.ui.popularMovieList.adapter

import android.content.Context
import androidx.swiperefreshlayout.widget.CircularProgressDrawable
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.kazi.test.R
import com.kazi.test.data.db.entities.MovieResultsItem
import com.kazi.test.data.network.APIService
import com.kazi.test.databinding.ItemEmployeeBinding
import com.xwray.groupie.databinding.BindableItem

/**
 * Created by Kazi Md. Saidul Email: Kazimdsaidul@gmail.com  Mobile: +8801675349882 on 2019-09-07.
 */
class MoviceItem(val employee: MovieResultsItem, private val ctx: Context) : BindableItem<ItemEmployeeBinding>() {

    override fun getLayout() = R.layout.item_employee

    override fun bind(viewBinding: ItemEmployeeBinding, position: Int) {
        viewBinding.employee = employee
//        viewBinding.imageView.


        val options = RequestOptions()
            .placeholder(R.drawable.free_avatars_cons)
            .error(R.drawable.free_avatars_cons)

        val url = employee.posterPath

        val fullURL = "https://image.tmdb.org/t/p/original"+url+"?api_key="+ APIService.API_KEY

        val circularProgressDrawable = CircularProgressDrawable(ctx)
        circularProgressDrawable.strokeWidth = 5f
        circularProgressDrawable.centerRadius = 30f
        circularProgressDrawable.start()

        Glide.with(viewBinding.ivEmpImage.context).load(fullURL)
            .apply(options)
            .placeholder(circularProgressDrawable)
            .into(viewBinding.ivEmpImage);
    }
}