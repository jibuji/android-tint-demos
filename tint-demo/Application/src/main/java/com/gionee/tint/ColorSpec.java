package com.gionee.tint;

import android.content.res.ColorStateList;
import android.content.res.Resources;
import android.graphics.Color;
import android.view.View;

import com.example.android.recyclerview.R;

import java.util.HashMap;

/**
 * Created by jiengfei on 14-11-30.
 */
public class ColorSpec {

    public interface Tinter {
        public void tint(View v);
    }

    public static final String COLOR_1 = "color1";
    public static final String COLOR_2 = "color2";
    public static final String COLOR_3 = "color3";
    public static final String COLOR_4 = "color4";
    public static final String COLOR_5 = "color5";
    public static final String COLOR_6 = "color6";
    public static final String COLOR_7 = "color7";
    public static final String COLOR_8 = "color8";
    public static final String COLOR_9 = "color9";
    public static final String COLOR_10 = "color10";

    public static final String SL_COLOR_1 = "sl_color1";
    public static final String SL_COLOR_2 = "sl_color2";
    public static final String SL_COLOR_3 = "sl_color3";
    public static final String SL_COLOR_4 = "sl_color4";
    public static final String SL_COLOR_5 = "sl_color5";

    private final HashMap<String, Tinter> sMap = new HashMap<>();
    private static ColorSpec sSpec = null;
    private final Resources mRes;

    private ColorSpec(Resources res) {
        mRes = res;
        refillColorMap();
    }

    public static ColorSpec getInstance(Resources res) {
        if (sSpec == null) {
            sSpec = new ColorSpec(res);
        }
        return sSpec;
    }

    public void refillColorMap() {
        sMap.clear();
        sMap.put(COLOR_1, randomColorTinter());
        sMap.put(COLOR_2, randomColorTinter());
        sMap.put(COLOR_3, randomColorTinter());
        sMap.put(COLOR_4, randomColorTinter());
        sMap.put(COLOR_5, randomColorTinter());
        sMap.put(COLOR_6, randomColorTinter());
        sMap.put(COLOR_7, randomColorTinter());
        sMap.put(COLOR_8, randomColorTinter());
        sMap.put(COLOR_9, randomColorTinter());
        sMap.put(COLOR_10, randomColorTinter());

        sMap.put(SL_COLOR_1, randomColorStateTinter());
        sMap.put(SL_COLOR_2, randomColorStateTinter());
        sMap.put(SL_COLOR_3, randomColorStateTinter());
        sMap.put(SL_COLOR_4, randomColorStateTinter());
        sMap.put(SL_COLOR_5, randomColorStateTinter());
    }

    public void blankColorMap() {
        sMap.clear();
        sMap.put(COLOR_1, blankColorTinter());
        sMap.put(COLOR_2, blankColorTinter());
        sMap.put(COLOR_3, blankColorTinter());
        sMap.put(COLOR_4, blankColorTinter());
        sMap.put(COLOR_5, blankColorTinter());
        sMap.put(COLOR_6, blankColorTinter());
        sMap.put(COLOR_7, blankColorTinter());
        sMap.put(COLOR_8, blankColorTinter());
        sMap.put(COLOR_9, blankColorTinter());
        sMap.put(COLOR_10, blankColorTinter());

        sMap.put(SL_COLOR_1, blankColorStateTinter());
        sMap.put(SL_COLOR_2, blankColorStateTinter());
        sMap.put(SL_COLOR_3, blankColorStateTinter());
        sMap.put(SL_COLOR_4, blankColorStateTinter());
        sMap.put(SL_COLOR_5, blankColorStateTinter());
    }

    private Tinter blankColorStateTinter() {
        return null;
    }

    private Tinter blankColorTinter() {
        return new blankColorTinter();
    }

    public Tinter getColorTinter(String key) {
        return sMap.get(key);
    }

    //TODO
    private int randomColor() {
        int color = (int)(Integer.MAX_VALUE * Math.random());
        return color | 0xFF000000;
    }

    //TODO
    private ColorStateList randomColorState() {
        return mRes.getColorStateList(R.color.color_state_list);
    }

    private Tinter randomColorStateTinter() {
        ColorStateTinter tinter = new ColorStateTinter();
        tinter.addState(ColorStateTinter.PRESSED_STATE_SET, randomColor());
        tinter.addState(ColorStateTinter.FOCUSED_STATE_SET, randomColor());
        tinter.addState(ColorStateTinter.EMPTY_STATE_SET, randomColor());
        return tinter;
    }

    private Tinter randomColorTinter() {
        return new ColorTinter(randomColor());
    }
}
