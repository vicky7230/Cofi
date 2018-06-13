package com.vicky7230.cofi.di.module

import com.vicky7230.cofi.ui.coffeeShops.CoffeeShopsActivity
import com.vicky7230.cofi.ui.coffeeShops.CoffeeShopsActivityModule
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
abstract class ActivityBindingModule {

    @ContributesAndroidInjector(modules = [(CoffeeShopsActivityModule::class)])
    abstract fun bindCoffeeShopsActivity(): CoffeeShopsActivity
}