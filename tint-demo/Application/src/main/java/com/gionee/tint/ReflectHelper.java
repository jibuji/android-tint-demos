package com.gionee.tint;

import android.content.pm.ActivityInfo;
import android.util.Log;
import android.view.View;

import java.lang.reflect.Field;
import java.lang.reflect.Method;

public class ReflectHelper {
    private final static String TAG = "ReflectHelper";

    public static int getSystemUiFlagFullScreenValue(Class<View> object) {
        return getConstantValue(object, "SYSTEM_UI_FLAG_FULLSCREEN");
    }

    public static int getSystemUiFlagLayoutStableValue(Class<View> object) {
        return getConstantValue(object, "SYSTEM_UI_FLAG_LAYOUT_STABLE");
    }

    public static int getScreenOrieLockedValue(Class<ActivityInfo> object) {
        int value = -1;
        try {
            Field field = object.getField("SCREEN_ORIENTATION_LOCKED");
            value = field.getInt(object);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return value;
    }

    public static int getConstantValue(Class<View> object, String constantName) {
        int value = -1;
        try {
            Field field = object.getField(constantName);
            value = field.getInt(object);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return value;
    }

    public static void setDefaultBufferSize(Object object, int width, int height) {
        invokeObjMethod(object, "setDefaultBufferSize", width, height);
    }

    public static void announceForAccessibility(Object object, CharSequence value) {
        invokeObjMethod(object, "announceForAccessibility", value);
    }

    public static void postOnAnimation(Object object, Runnable value) {
        invokeObjMethod(object, "postOnAnimation", value);
    }

    public static void invokeObjMethod(Object ob, String methodName, int value1, int value2) {

        if (ob == null) {
            return;
        }
        try {
            Method method = ob.getClass().getMethod(methodName, int.class, int.class);
            method.invoke(ob, value1, value2);
        } catch (Exception e) {
            Log.d(TAG, "invokeObjMethod error", e);
        }
    }

    public static void invokeObjMethod(Object ob, String methodName, CharSequence value) {

        if (ob == null) {
            return;
        }
        try {
            Method method = ob.getClass().getMethod(methodName, CharSequence.class);
            method.invoke(ob, value);
        } catch (Exception e) {
            Log.d(TAG, "invokeObjMethod error", e);
        }
    }

    public static void invokeObjMethod(Object ob, String methodName, Runnable value) {

        if (ob == null) {
            return;
        }
        try {
            Method method = ob.getClass().getMethod(methodName, Runnable.class);
            method.invoke(ob, value);
        } catch (Exception e) {
            Log.d(TAG, "invokeObjMethod error", e);
        }
    }
}
