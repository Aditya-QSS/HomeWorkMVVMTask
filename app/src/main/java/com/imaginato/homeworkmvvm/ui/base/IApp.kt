package com.imaginato.homeworkmvvm.ui.base

import android.app.Application
import com.imaginato.homeworkmvvm.domain.apiModules
import com.imaginato.homeworkmvvm.domain.databaseModule
import com.imaginato.homeworkmvvm.domain.netModules
import com.imaginato.homeworkmvvm.domain.repositoryModules
import com.imaginato.homeworkmvvm.domain.viewModelModules
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin
import org.koin.core.logger.Level

class IApp : Application() {
    override fun onCreate() {
        super.onCreate()
        initKoin()
    }

    private fun initKoin() {
        startKoin {
            androidLogger(Level.ERROR)
            androidContext(this@IApp)
            modules(
                databaseModule, netModules, apiModules, repositoryModules, viewModelModules
            )
        }
    }
}