package com.kazi.test.appController

import android.app.Application
import com.itkacher.okhttpprofiler.OkHttpProfilerInterceptor
import com.kazi.test.data.db.AppDatabase
import com.kazi.test.data.network.APIService
import com.kazi.test.data.network.interceptor.HeaderTokenInterceptor
import com.kazi.test.data.network.interceptor.NetworkConnectionInterceptor
import com.kazi.test.data.repository.UserRepository
import com.kazi.test.ui.employeesList.employeesViewModelFactory.EmployeesViewModelFactory
import org.kodein.di.Kodein
import org.kodein.di.KodeinAware
import org.kodein.di.android.x.androidXModule
import org.kodein.di.generic.bind
import org.kodein.di.generic.instance
import org.kodein.di.generic.provider
import org.kodein.di.generic.singleton


/**
 * Created by Android on 12/1/2015.
 */
public open class AppController : Application(), KodeinAware {

    override val kodein = Kodein.lazy {
        import(androidXModule(this@AppController))

        bind() from singleton { NetworkConnectionInterceptor(instance()) }
        bind() from singleton { HeaderTokenInterceptor(instance()) }
        bind() from singleton { OkHttpProfilerInterceptor() }
        bind() from singleton { APIService(instance()) }
        bind() from singleton { AppDatabase(instance()) }
        bind() from singleton { UserRepository(instance(), instance()) }

        bind() from provider { EmployeesViewModelFactory(instance()) }

    }


}
