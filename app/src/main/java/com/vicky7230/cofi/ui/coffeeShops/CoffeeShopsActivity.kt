package com.vicky7230.cofi.ui.coffeeShops

import android.arch.lifecycle.ViewModelProviders
import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.Toolbar
import com.vicky7230.cofi.R
import com.vicky7230.cofi.ViewModelFactory
import com.vicky7230.cofi.data.DataManager
import dagger.android.AndroidInjection
import io.reactivex.disposables.CompositeDisposable
import kotlinx.android.synthetic.main.activity_coffee_shops.*
import javax.inject.Inject


class CoffeeShopsActivity : AppCompatActivity() {

    @Inject
    lateinit var dataManager: DataManager
    @Inject
    lateinit var compositeDisposable: CompositeDisposable

    private lateinit var coffeeShopsViewModel: CoffeeShopsViewModel

    private val animationDuration = 300

    companion object {
        @JvmStatic
        fun getStartIntent(context: Context): Intent {
            return Intent(context, CoffeeShopsActivity::class.java)
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        AndroidInjection.inject(this)
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_coffee_shops)

        coffeeShopsViewModel = ViewModelProviders.of(this,
                ViewModelFactory.getInstance(
                        application,
                        dataManager)).get(CoffeeShopsViewModel::class.java)

        init()
    }

    private fun init() {
        setSupportActionBar(toolbar as Toolbar?)

        pulseView.startPulse()

        /*val bottomMargin = DisplayUtils.dpToPx(160)
        val windowSize = DisplayUtils.getDisplaySize(windowManager)
        swipeView.builder
                .setDisplayViewCount(3)
                .setIsUndoEnabled(true)
                .setSwipeVerticalThreshold(DisplayUtils.dpToPx(50))
                .setSwipeHorizontalThreshold(DisplayUtils.dpToPx(50))
                .setHeightSwipeDistFactor(10F)
                .setWidthSwipeDistFactor(5F)
                .setSwipeDecor(SwipeDecor()
                        .setViewWidth(windowSize.x)
                        .setViewHeight(windowSize.y - bottomMargin)
                        .setViewGravity(Gravity.TOP)
                        .setPaddingTop(20)
                        .setSwipeAnimTime(animationDuration)
                        .setRelativeScale(0.01f)
                        .setSwipeInMsgLayoutId(R.layout.tinder_swipe_in_msg_view)
                        .setSwipeOutMsgLayoutId(R.layout.tinder_swipe_out_msg_view))*/
    }

    override fun onDestroy() {
        compositeDisposable.dispose()
        super.onDestroy()
    }
}
