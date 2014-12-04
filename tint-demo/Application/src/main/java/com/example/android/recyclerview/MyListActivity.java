package com.example.android.recyclerview;

import android.content.res.Resources;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentTransaction;
import android.util.Log;
import android.view.View;
import android.view.ViewTreeObserver;
import android.view.animation.AnimationUtils;
import android.widget.TextView;

import com.gionee.tint.ColorSpec;
import com.gionee.tint.TintHelper;

/**
 * Created by jiengfei on 14-12-2.
 */
public class MyListActivity extends FragmentActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.mylist_main);

        if (savedInstanceState == null) {
            FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
            RecyclerViewFragment fragment = new RecyclerViewFragment();
            transaction.replace(R.id.sample_content_fragment, fragment);
            transaction.commit();
        }

        final View topView = getWindow().getDecorView();
        final View randomBtn = findViewById(R.id.random);
        final Resources res = getResources();
        randomBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ColorSpec.getInstance(res).refillColorMap();
                long s1 = AnimationUtils.currentAnimationTimeMillis();
                TintHelper.tint(res, topView);
                long s2 = AnimationUtils.currentAnimationTimeMillis();
                Log.d("tint", "random tinted.time="+(s2 - s1));
            }
        });
        long s1 = AnimationUtils.currentAnimationTimeMillis();
        for (int i=0; i<1000; ++i) {
            TintHelper.tint(getResources(), topView);
        }
        long s2 = AnimationUtils.currentAnimationTimeMillis();
        android.util.Log.d("tint", "onCreate time="+(s2 - s1));

        final TextView fpsView = (TextView)findViewById(R.id.fps);

        topView.getViewTreeObserver().addOnPreDrawListener(new ViewTreeObserver.OnPreDrawListener() {
            private long lastDrawTime = AnimationUtils.currentAnimationTimeMillis();
            int drawTimes = 0;
            @Override
            public boolean onPreDraw() {
                ++drawTimes;
                long now = AnimationUtils.currentAnimationTimeMillis();
                long diff = now - lastDrawTime;
                if (diff < 100) {
                    return true;
                }
                //1000 * 100 times
                float fps = drawTimes * 1000.0f / diff;
                drawTimes = 0;
                lastDrawTime = now;
                String fpsString = String.format("%.2f", fps);
                fpsView.setText(fpsString);
                return true;
            }
        });
    }
}
