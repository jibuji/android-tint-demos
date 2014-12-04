package com.gionee.tint;

import android.graphics.drawable.Drawable;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.android.common.logger.Log;

/**
 * Created by jiengfei on 14-12-1.
 */

public class ColorTinter implements ColorSpec.Tinter {
    private final int mColor;

    public ColorTinter(int color) {
        mColor = color;
    }

    @Override
    public void tint(View v) {
        tintViewWithColor(v, mColor);
    }

    private static void tintViewWithColor(View root, int color) {
        if (root instanceof ImageView) {
            ImageView imageView = (ImageView) root;
            Drawable dr = imageView.getDrawable();
            dr.setTint(color);
            return;
        }
        if (root instanceof TextView) {
            TextView textView = (TextView) root;
            textView.setTextColor(color);
            return;
        }
    }
}
