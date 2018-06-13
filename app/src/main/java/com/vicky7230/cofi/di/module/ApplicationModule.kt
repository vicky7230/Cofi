package com.vicky7230.cofi.di.module

import android.app.Application
import android.content.Context
import com.vicky7230.cofi.CoffeeShopsApplication
import com.vicky7230.cofi.data.AppDataManager
import com.vicky7230.cofi.data.Config
import com.vicky7230.cofi.data.DataManager
import com.vicky7230.cofi.data.network.ApiHelper
import com.vicky7230.cofi.data.network.AppApiHelper
import com.vicky7230.cofi.di.ApplicationContext
import com.vicky7230.cofi.di.BaseUrl
import dagger.Module
import dagger.Provides
import io.reactivex.disposables.CompositeDisposable
import javax.inject.Singleton

@Module
class ApplicationModule {

    @Provides
    @ApplicationContext
    internal fun provideContext(coffeeShopsApplication: CoffeeShopsApplication): Context {
        return coffeeShopsApplication.applicationContext
    }

    @Provides
    internal fun provideApplication(coffeeShopsApplication: CoffeeShopsApplication): Application {
        return coffeeShopsApplication
    }

    @Provides
    internal fun provideCompositeDisposable(): CompositeDisposable {
        return CompositeDisposable()
    }

    @Provides
    @BaseUrl
    internal fun provideBaseUrl(): String {
        return Config.BASE_URL
    }

    @Provides
    @Singleton
    internal fun provideDataManager(appDataManager: AppDataManager): DataManager {
        return appDataManager
    }

    @Provides
    @Singleton
    internal fun provideApiHelper(appApiHelper: AppApiHelper): ApiHelper {
        return appApiHelper
    }

}