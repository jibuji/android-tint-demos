package com.gionee.tint;

import android.content.res.ColorStateList;
import android.graphics.drawable.Drawable;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.android.common.logger.Log;

import java.lang.reflect.Field;
import java.util.Arrays;

/**
 * Created by jiengfei on 14-12-1.
 */
public class ColorStateTinter implements ColorSpec.Tinter {
    //    public void
    public static final int[] EMPTY_STATE_SET =
            retrieveStaticIntArray(View.class, "EMPTY_STATE_SET");
    public static final int[] ENABLED_STATE_SET =
            retrieveStaticIntArray(View.class, "ENABLED_STATE_SET");
    public static final int[] FOCUSED_STATE_SET =
            retrieveStaticIntArray(View.class, "FOCUSED_STATE_SET");
    public static final int[] SELECTED_STATE_SET =
            retrieveStaticIntArray(View.class, "SELECTED_STATE_SET");
    public static final int[] PRESSED_STATE_SET =
            retrieveStaticIntArray(View.class, "PRESSED_STATE_SET");
    private static final int MAX_SIZE = 4;
    private int mCurIndex;

    private int[][] mStates = null;
    private int[] mColors = null;
    private static final Field sColorStateList_mStateSpecs =
            getField(ColorStateList.class, "mStateSpecs");
    private static final Field sColorStateList_mColors =
            getField(ColorStateList.class, "mColors");

    public ColorStateTinter() {
        reInitialize();
    }

    public ColorStateTinter(ColorStateList colorStateList) {
        if (sColorStateList_mStateSpecs != null
                && sColorStateList_mColors != null) {
            try {
                mStates = (int[][]) sColorStateList_mStateSpecs.get(colorStateList);
                mColors = (int[]) sColorStateList_mColors.get(colorStateList);
            } catch (IllegalAccessException e) {
                Log.d("tint", "ColorStateTinter constructor", e);
            }
        }
    }

    private void reInitialize() {
        if (mStates == null) {
            mStates = new int[MAX_SIZE][];
        }
        if (mColors == null) {
            mColors = new int[MAX_SIZE];
        }

        Arrays.fill(mStates, EMPTY_STATE_SET);
        Arrays.fill(mColors, 0);
        mCurIndex = 0;
    }

    public void addState(int[] state, int color) {
        assert(mCurIndex < MAX_SIZE);
        mStates[mCurIndex] = state;
        mColors[mCurIndex] = color;
        ++mCurIndex;
    }

    public void clear() {
        reInitialize();
    }

    @Override
    public void tint(View v) {
        tintViewWithColorStateList(v, new ColorStateList(mStates, mColors));
    }

    private static int[] retrieveStaticIntArray(Class<View> cls, String fieldName) {
        try {
            Field field = cls.getDeclaredField(fieldName);
            field.setAccessible(true);
            return (int[])field.get(cls);
        } catch (NoSuchFieldException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }
        return new int[0];
    }

    private static void tintViewWithColorStateList(View root, ColorStateList colorStateList) {
        if (root instanceof ImageView) {
            ImageView imageView = (ImageView) root;
            Drawable dr = imageView.getDrawable();
            dr.setTintList(colorStateList);
            return;
        }
        if (root instanceof TextView) {
            TextView textView = (TextView) root;
            textView.setTextColor(colorStateList);
            return;
        }
    }


    private static Field getField(Class<ColorStateList> cls, String fieldName) {
        try {
            Field field = cls.getDeclaredField(fieldName);
            field.setAccessible(true);
            return field;
        } catch (NoSuchFieldException e) {
            Log.d("tint", "get field failed. name="+fieldName, e);
        }
        return null;
    }
}
