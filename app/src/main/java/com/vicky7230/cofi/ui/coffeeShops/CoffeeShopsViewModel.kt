package com.vicky7230.cofi.ui.coffeeShops

import android.app.Application
import android.arch.lifecycle.AndroidViewModel
import com.vicky7230.cofi.data.DataManager

class CoffeeShopsViewModel(
        context: Application,
        private val dataManager: DataManager)
    : AndroidViewModel(context) {

    /*fun getArticlesFromNetwork(): Observable<Headlines?> {
        return dataManager.getArticles()
                .map { it: Headlines? ->
                    if (it?.articles != null) {
                        dataManager.deleteArticles()
                        dataManager.insertArticles(it.articles as MutableList<Article>)
                    }
                    it
                }
                .subscribeOn(Schedulers.io())
    }*/
}
