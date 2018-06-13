package com.vicky7230.cofi.di.component

import com.vicky7230.cofi.CoffeeShopsApplication
import com.vicky7230.cofi.di.module.ActivityBindingModule
import com.vicky7230.cofi.di.module.ApplicationModule
import com.vicky7230.cofi.di.module.NetworkModule
import dagger.BindsInstance
import dagger.Component
import dagger.android.support.AndroidSupportInjectionModule
import javax.inject.Singleton

@Singleton
@Component(modules = [
    AndroidSupportInjectionModule::class,
    NetworkModule::class,
    ApplicationModule::class,
    ActivityBindingModule::class
])
interface ApplicationComponent {

    @Component.Builder
    interface Builder {
        @BindsInstance
        fun application(coffeeShopsApplication: CoffeeShopsApplication): Builder

        fun build(): ApplicationComponent
    }

    fun inject(coffeeShopsApplication: CoffeeShopsApplication)

}