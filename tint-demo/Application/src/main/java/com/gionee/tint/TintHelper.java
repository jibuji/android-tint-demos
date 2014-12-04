package com.gionee.tint;

import android.content.res.ColorStateList;
import android.content.res.Resources;
import android.graphics.drawable.Drawable;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.android.common.logger.Log;

/**
 * Created by jiengfei on 14-12-1.
 */
public class TintHelper {
    public static void tint(Resources res, View root) {
        doTint(res, root);
        root.invalidate();
    }


    private static void doTint(Resources res, View root) {
        if (root instanceof ViewGroup) {
            ViewGroup vg = (ViewGroup)root;
            final int childCount = vg.getChildCount();
            for (int i=0; i<childCount; ++i) {
                doTint(res, vg.getChildAt(i));
            }
            return;
        }

        String tag = (String)root.getTag();
        if (tag == null) {
            return;
        }
        ColorSpec.Tinter tinter = ColorSpec.getInstance(res).getColorTinter(tag);
        if (tinter == null) {
            Log.d("tint", "can't find tinter for tag="+tag, new Exception());
            return;
        }
        tinter.tint(root);
    }

}
