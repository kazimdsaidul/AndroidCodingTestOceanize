package com.kazi.test.base

import android.text.Spannable
import android.text.SpannableString
import android.text.style.ForegroundColorSpan
import android.view.MenuItem
import androidx.appcompat.app.AppCompatActivity

/**
 * Created by Kazi Md. Saidul Email: Kazimdsaidul@gmail.com  Mobile: +8801675349882 on 2019-09-08.
 */
open class BaseActivity : AppCompatActivity() {


    fun setToolbar(title: String, color: Int) {
        assert(supportActionBar != null)
        if (supportActionBar != null) {
            supportActionBar!!.setDisplayHomeAsUpEnabled(true)
            val spannablerTitle = SpannableString(title)
            spannablerTitle.setSpan(
                ForegroundColorSpan(color),
                0,
                spannablerTitle.length,
                Spannable.SPAN_EXCLUSIVE_EXCLUSIVE
            )
            supportActionBar!!.title = spannablerTitle
        }
    }

    fun setToolbar(title: String) {
        assert(supportActionBar != null)
        if (supportActionBar != null) {
            supportActionBar!!.title = title
            supportActionBar!!.setDisplayHomeAsUpEnabled(true)

        }
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if (item.itemId == android.R.id.home) {
            this.onBackPressed()
            return true
        }
        return super.onOptionsItemSelected(item)
    }
}