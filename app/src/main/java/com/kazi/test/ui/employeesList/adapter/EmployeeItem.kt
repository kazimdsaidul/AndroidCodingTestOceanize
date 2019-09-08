package com.kazi.test.ui.employeesList.adapter

import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.kazi.test.R
import com.kazi.test.data.db.entities.Employee
import com.kazi.test.databinding.ItemEmployeeBinding
import com.xwray.groupie.databinding.BindableItem

/**
 * Created by Kazi Md. Saidul Email: Kazimdsaidul@gmail.com  Mobile: +8801675349882 on 2019-09-07.
 */
class EmployeeItem( val employee: Employee) : BindableItem<ItemEmployeeBinding>() {

    override fun getLayout() = R.layout.item_employee

    override fun bind(viewBinding: ItemEmployeeBinding, position: Int) {
        viewBinding.employee = employee
//        viewBinding.imageView.


        val options = RequestOptions()
            .placeholder(R.drawable.free_avatars_cons)
            .error(R.drawable.free_avatars_cons)

        val url = employee.profileImage
        Glide.with(viewBinding.ivEmpImage.context).load(url)
            .apply(options)
            .into(viewBinding.ivEmpImage);
    }
}