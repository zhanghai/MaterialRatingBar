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

    public MaterialRatingBar(Context context) {
        super(context);

        init(null, R.attr.ratingBarStyle);
    }

    public MaterialRatingBar(Context context, AttributeSet attrs) {
        super(context, attrs);

        init(attrs, R.attr.ratingBarStyle);
    }

    public MaterialRatingBar(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);

        init(attrs, defStyleAttr);
    }

    private void init(AttributeSet attrs, int defStyleAttr) {
        //setProgressDrawable();
    }

    @Override
    protected synchronized void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);

        int height = getMeasuredHeight();
        // TODO: Gap.
        int width = height * getNumStars();
        setMeasuredDimension(ViewCompat.resolveSizeAndState(width, widthMeasureSpec, 0), height);
    }
}
