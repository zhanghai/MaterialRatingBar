/*
 * Copyright (c) 2016 Zhang Hai <Dreaming.in.Code.ZH@Gmail.com>
 * All Rights Reserved.
 */

package me.zhanghai.android.materialratingbar;

import android.content.Context;
import android.support.v4.view.ViewCompat;
import android.util.AttributeSet;
import android.widget.RatingBar;

public class MaterialRatingBar extends RatingBar {

    private MaterialRatingDrawable mDrawable;

    public MaterialRatingBar(Context context) {
        super(context);

        init();
    }

    public MaterialRatingBar(Context context, AttributeSet attrs) {
        super(context, attrs);

        init();
    }

    public MaterialRatingBar(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);

        init();
    }

    private void init() {
        mDrawable = new MaterialRatingDrawable(getContext());
        setProgressDrawable(mDrawable);
    }

    @Override
    protected synchronized void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);

        int height = getMeasuredHeight();
        int width = Math.round(height * mDrawable.getTileRatio() * getNumStars());
        setMeasuredDimension(ViewCompat.resolveSizeAndState(width, widthMeasureSpec, 0), height);
    }
}
