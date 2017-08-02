package com.lenovo.sunzh.layoutparamsdemo;

import android.app.Activity;
import android.content.Context;
import android.util.DisplayMetrics;
import android.view.Window;

/**
 * Created by xuxiaowei on 2016/9/19.
 */
public class WindowUtils {

    private static DisplayMetrics metrics = null;
    private static String LOCK = "Synchronous lock";

    public static int getTitleBarHeight(Context context) {
        int statusBarHeight = getStatusBarHeight(context);
        int contentTop = ((Activity) context).getWindow().findViewById(Window.ID_ANDROID_CONTENT).getTop();
        int titleBarHeight = contentTop - statusBarHeight;
        return titleBarHeight;
    }

    ;

    /**
     * 获得屏幕的宽
     *
     * @param context
     * @param metrics
     * @return
     */
    public static int widthPixels(Context context, DisplayMetrics metrics) {
    /*    synchronized (LOCK) {
            if (metrics == null) {
                metrics = new DisplayMetrics();
            }
        }*/
        DisplayMetrics displayMetrics = context.getResources().getDisplayMetrics();
        return displayMetrics.widthPixels;
//        ((Activity) context).getWindowManager().getDefaultDisplay().getMetrics(metrics);
//        return metrics.widthPixels;
    }

    /**
     * 获得屏幕的高
     *
     * @param context
     * @param metrics
     * @return
     */
    public static int heightPixels(Context context, DisplayMetrics metrics) {
        /*synchronized (LOCK) {
            if (metrics == null) {
                metrics = new DisplayMetrics();
            }
        }
        ((Activity) context).getWindowManager().getDefaultDisplay().getMetrics(metrics);
        return metrics.heightPixels;*/
        DisplayMetrics displayMetrics = context.getResources().getDisplayMetrics();
        return displayMetrics.heightPixels;
    }


    /**
     * 计算状态栏高度
     *
     * @param context
     * @return
     */
    public static int getStatusBarHeight(Context context) {
        int result = 0;
        int resourceId = context.getResources().getIdentifier("status_bar_height", "dimen", "android");
        if (resourceId > 0) {
            result = context.getResources().getDimensionPixelSize(resourceId);
        }
        return result;
    }

    public static int getScreenHeight(){

        return 0;
    }


}