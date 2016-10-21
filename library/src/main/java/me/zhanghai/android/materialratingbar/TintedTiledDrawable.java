/*
 * Copyright (c) 2016 Zhang Hai <Dreaming.in.Code.ZH@Gmail.com>
 * All Rights Reserved.
 */

package me.zhanghai.android.materialratingbar;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.ColorFilter;
import android.graphics.drawable.Drawable;
import android.support.annotation.NonNull;

import me.zhanghai.android.materialratingbar.internal.ThemeUtils;

class TintedTiledDrawable extends BaseDrawable {

    private Drawable mTileDrawable;

    public TintedTiledDrawable(Drawable tileDrawable, boolean tintAsActivatedElseNormal,
                               Context context) {
        int tintColor = ThemeUtils.getColorFromAttrRes(tintAsActivatedElseNormal ?
                R.attr.colorControlActivated : R.attr.colorControlNormal, context);
        // setTint() has been overridden for compatibility; DrawableCompat won't work because
        // wrapped Drawable won't be Animatable.
        setTint(tintColor);
        mTileDrawable = tileDrawable;
    }

    public Drawable getTileDrawable() {
        return mTileDrawable;
    }

    @Override
    protected void onDraw(Canvas canvas, int width, int height) {

        mTileDrawable.setAlpha(mAlpha);
        ColorFilter colorFilter = getColorFilterForDrawing();
        if (colorFilter != null) {
            mTileDrawable.setColorFilter(colorFilter);
        }

        int tileHeight = mTileDrawable.getIntrinsicHeight();
        float scale = (float) height / tileHeight;
        canvas.scale(scale, scale);

        float scaledWidth = width / scale;
        int tileWidth = mTileDrawable.getIntrinsicWidth();
        for (int x = 0; x < scaledWidth; x += tileWidth) {
            mTileDrawable.setBounds(x, 0, x + tileWidth, tileHeight);
            mTileDrawable.draw(canvas);
        }
    }

    @NonNull
    @Override
    public Drawable mutate() {
        mTileDrawable = mTileDrawable.mutate();
        return this;
    }
}
