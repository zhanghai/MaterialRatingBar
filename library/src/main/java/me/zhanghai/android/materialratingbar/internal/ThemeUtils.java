/*
 * Copyright (c) 2016 Zhang Hai <Dreaming.in.Code.ZH@Gmail.com>
 * All Rights Reserved.
 */

package me.zhanghai.android.materialratingbar.internal;

import android.content.Context;
import android.content.res.TypedArray;

public class ThemeUtils {

    private ThemeUtils() {}

    public static int getColorFromAttrRes(int attrRes, Context context) {
        TypedArray a = context.obtainStyledAttributes(new int[] { attrRes });
        try {
            return a.getColor(0, 0);
        } finally {
            a.recycle();
        }
    }

    public static float getFloatFromAttrRes(int attrRes, Context context) {
        TypedArray a = context.obtainStyledAttributes(new int[] { attrRes });
        try {
            return a.getFloat(0, 0);
        } finally {
            a.recycle();
        }
    }
}
