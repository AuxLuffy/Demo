package com.lenovo.sunzh.layoutparamsdemo;

import android.content.Context;

/**
 * Created by xuxiaowei on 2016/9/19.
 */
public class DisplayUtils {
    /**
     * 将px值转换为dip或dp值，保证尺寸大小不变
     * @param context
     * @param pxValue
     * @return 转换后的值
     */
    public static int pxTodip(Context context, float pxValue) {
        final float scale = context.getResources().getDisplayMetrics().density;
        return (int) (pxValue / scale + 0.5f);
    }

    /**
     * 将dip或dp值转换为px值，保证尺寸大小不变
     * @param context
     * @param dipValue
     * @return 转换后的值
     */
    public static int dipTopx(Context context, float dipValue) {
        final float scale = context.getResources().getDisplayMetrics().density;
        return (int) (dipValue * scale + 0.5f);
    }

    /**
     * 将px值转换为sp值，保证文字大小不变
     * @param context
     * @param pxValue
     * @return 转换后的值
     */
    public static int pxTosp(Context context, float pxValue) {
        final float fontScale = context.getResources().getDisplayMetrics().scaledDensity;
        return (int) (pxValue / fontScale + 0.5f);
    }

    /**
     * 将sp值转换为px值，保证文字大小不变
     * @param context
     * @param spValue
     * @return 转换后的值
     */
    public static int spTopx(Context context, float spValue) {
        final float fontScale = context.getResources().getDisplayMetrics().scaledDensity;
        return (int) (spValue * fontScale + 0.5f);
    }
}
