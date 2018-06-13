package com.vicky7230.cofi.ui.splash;

import android.annotation.SuppressLint;
import android.os.Build.VERSION;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.view.ViewTreeObserver.OnGlobalLayoutListener;
import android.view.animation.Animation;
import android.view.animation.Animation.AnimationListener;
import android.view.animation.AnimationUtils;
import android.widget.RelativeLayout.LayoutParams;

import com.vicky7230.cofi.R;
import com.vicky7230.cofi.ui.coffeeShops.CoffeeShopsActivity;


@SuppressLint("LogNotTimber")
public class SplashActivity extends AppCompatActivity {

    public static final String TAG = "UNBXD";

    private View ivLogo;
    private View rlLogo;
    private View viewBackgroundLogo;
    private View viewBackground;

    class MyGlobalLayoutListener implements OnGlobalLayoutListener {
        final SplashActivity splashActivity;

        MyGlobalLayoutListener(SplashActivity splashActivity) {
            this.splashActivity = splashActivity;
        }

        public void onGlobalLayout() {
            if (this.splashActivity != null) {
                try {
                    if (VERSION.SDK_INT >= 16) {
                        this.splashActivity.findViewById(R.id.root).getViewTreeObserver().removeOnGlobalLayoutListener(this);
                    } else {
                        this.splashActivity.findViewById(R.id.root).getViewTreeObserver().removeGlobalOnLayoutListener(this);
                    }
                } catch (Exception e) {
                    Log.e(TAG, e.getMessage());
                }
                this.splashActivity.findViewById(R.id.root).getMeasuredHeight();
            }
        }
    }

    class MyRunnable implements Runnable {
        final SplashActivity splashActivity;

        MyRunnable(SplashActivity splashActivity) {
            this.splashActivity = splashActivity;
        }

        public void run() {
            this.splashActivity.runImageAnimation();
        }
    }

    class ImageAnimationListener implements AnimationListener {
        final SplashActivity splashActivity;

        ImageAnimationListener(SplashActivity splashActivity) {
            this.splashActivity = splashActivity;
        }

        public void onAnimationEnd(Animation animation) {
            Log.d(TAG, "runImageAnimation onAnimationEnd");
            if (splashActivity != null) {
                animation.setAnimationListener(null);
                splashActivity.rlLogo.clearAnimation();
                splashActivity.runBackgroundAnimation();
            }
        }

        public void onAnimationRepeat(Animation animation) {
        }

        public void onAnimationStart(Animation animation) {
            Log.d(TAG, "runImageAnimation onAnimationStart");
        }
    }

    class BackgroundAnimationListener implements AnimationListener {
        final SplashActivity splashActivity;

        BackgroundAnimationListener(SplashActivity splashActivity) {
            this.splashActivity = splashActivity;
        }

        public void onAnimationEnd(Animation animation) {
            Log.d(TAG, "runBackgroundAnimation onAnimationEnd");
            if (splashActivity != null) {
                Log.d(TAG, "runBackgroundAnimation onAnimationEnd setting null");
                animation.setAnimationListener(null);
                splashActivity.viewBackground.clearAnimation();
                splashActivity.viewBackground.setBackgroundResource(R.color.colorPrimary);
                LayoutParams layoutParams = (LayoutParams) splashActivity.viewBackground.getLayoutParams();
                layoutParams.height = -1;
                layoutParams.width = -1;
                splashActivity.viewBackground.setLayoutParams(layoutParams);
            }

            startActivity(CoffeeShopsActivity.getStartIntent(SplashActivity.this));
            finish();
        }

        public void onAnimationRepeat(Animation animation) {
        }

        public void onAnimationStart(Animation animation) {
        }
    }

    class AnotherRunnable implements Runnable {
        final SplashActivity splashActivity;

        AnotherRunnable(SplashActivity splashActivity) {
            this.splashActivity = splashActivity;
        }

        public void run() {
            try {
                this.splashActivity.viewBackgroundLogo.setVisibility(View.GONE);
            } catch (Exception e) {
                Log.e(TAG, e.getMessage());
            }
        }
    }

    private void runImageAnimation() {
        Log.d(TAG, "runImageAnimation");
        Animation loadAnimation = AnimationUtils.loadAnimation(this, R.anim.splash_image_enter);
        loadAnimation.setAnimationListener(new ImageAnimationListener(this));
        this.rlLogo.startAnimation(loadAnimation);
    }

    private void runBackgroundAnimation() {
        Log.d(TAG, "runBackgroundAnimation");
        viewBackground.setVisibility(View.VISIBLE);
        viewBackground.clearAnimation();
        Animation loadAnimation = AnimationUtils.loadAnimation(this, R.anim.splash_background);
        loadAnimation.setAnimationListener(new BackgroundAnimationListener(this));
        viewBackground.startAnimation(loadAnimation);
        viewBackgroundLogo.postDelayed(new AnotherRunnable(this), 50);
    }


    @Override
    protected void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        Log.d(TAG, "SplashActivity onCreate");
        getWindow().setFlags(1024, 1024);
        setContentView(R.layout.activity_splash);
        viewBackground = findViewById(R.id.viewBackground);
        viewBackgroundLogo = findViewById(R.id.viewBackgroundLogo);
        ivLogo = findViewById(R.id.ivLogo);
        rlLogo = findViewById(R.id.rlLogo);
        findViewById(R.id.root).getViewTreeObserver().addOnGlobalLayoutListener(new MyGlobalLayoutListener(this));
        rlLogo.clearAnimation();
        rlLogo.post(new MyRunnable(this));
    }
}