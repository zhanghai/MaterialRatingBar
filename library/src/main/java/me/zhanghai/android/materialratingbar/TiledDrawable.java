/*
 * Copyright (c) 2016 Zhang Hai <Dreaming.in.Code.ZH@Gmail.com>
 * All Rights Reserved.
 */

package me.zhanghai.android.materialratingbar;

import android.graphics.Canvas;
import android.graphics.ColorFilter;
import android.graphics.drawable.Drawable;
import android.support.annotation.NonNull;

class TiledDrawable extends BaseDrawable {

    private Drawable mTileDrawable;

    public TiledDrawable(Drawable tileDrawable) {
        mTileDrawable = tileDrawable;
    }

    public Drawable getTileDrawable() {
        return mTileDrawable;
    }

    @NonNull
    @Override
    public Drawable mutate() {
        mTileDrawable = mTileDrawable.mutate();
        return this;
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
}
