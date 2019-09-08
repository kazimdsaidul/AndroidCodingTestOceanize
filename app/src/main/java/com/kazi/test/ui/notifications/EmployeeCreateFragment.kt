package com.kazi.test.ui.notifications

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.kazi.test.R

class EmployeeCreateFragment : Fragment() {

    private lateinit var employeeCreateViewModel: EmployeeCreateViewModel

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        employeeCreateViewModel =
            ViewModelProviders.of(this).get(EmployeeCreateViewModel::class.java)
        val root = inflater.inflate(R.layout.fragment_emplyee_create, container, false)
        val textView: TextView = root.findViewById(R.id.text_notifications)
        employeeCreateViewModel.text.observe(this, Observer {
            textView.text = it
        })
        return root
    }
}