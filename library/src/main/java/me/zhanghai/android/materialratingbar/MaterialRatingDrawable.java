/*
 * Copyright (c) 2016 Zhang Hai <Dreaming.in.Code.ZH@Gmail.com>
 * All Rights Reserved.
 */

package me.zhanghai.android.materialratingbar;

import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.LayerDrawable;
import android.support.v7.content.res.AppCompatResources;
import android.view.Gravity;

import me.zhanghai.android.materialratingbar.internal.ThemeUtils;

public class MaterialRatingDrawable extends LayerDrawable {

    public MaterialRatingDrawable(Context context, boolean fillBackgroundStars) {
        super(new Drawable[] {
                createLayerDrawableWithTintAttrRes(fillBackgroundStars ?
                        R.drawable.mrb_star_icon_black_36dp
                        : R.drawable.mrb_star_border_icon_black_36dp, fillBackgroundStars ?
                        R.attr.colorControlHighlight : R.attr.colorControlNormal, context),
                fillBackgroundStars ? createClippedLayerDrawableWithTintColor(
                        R.drawable.mrb_star_icon_black_36dp, Color.TRANSPARENT, context)
                        : createClippedLayerDrawableWithTintAttrRes(
                        R.drawable.mrb_star_border_icon_black_36dp, R.attr.colorControlActivated,
                        context),
                createClippedLayerDrawableWithTintAttrRes(R.drawable.mrb_star_icon_black_36dp,
                        R.attr.colorControlActivated, context)
        });

        setId(0, android.R.id.background);
        setId(1, android.R.id.secondaryProgress);
        setId(2, android.R.id.progress);
    }

    private static Drawable createLayerDrawableWithTintColor(int tileRes, int tintColor,
                                                             Context context) {
        TileDrawable drawable = new TileDrawable(AppCompatResources.getDrawable(context,
                tileRes));
        drawable.mutate();
        //noinspection RedundantCast
        ((TintableDrawable) drawable).setTint(tintColor);
        return drawable;
    }

    private static Drawable createLayerDrawableWithTintAttrRes(int tileRes, int tintAttrRes,
                                                               Context context) {
        int tintColor = ThemeUtils.getColorFromAttrRes(tintAttrRes, context);
        return createLayerDrawableWithTintColor(tileRes, tintColor, context);
    }

    @SuppressLint("RtlHardcoded")
    private static Drawable createClippedLayerDrawableWithTintColor(int tileResId, int tintColor,
                                                                    Context context) {
        return new ClipDrawableCompat(createLayerDrawableWithTintColor(tileResId, tintColor,
                context), Gravity.LEFT, ClipDrawableCompat.HORIZONTAL);
    }

    @SuppressLint("RtlHardcoded")
    private static Drawable createClippedLayerDrawableWithTintAttrRes(int tileResId,
                                                                      int tintAttrRes,
                                                                      Context context) {
        return new ClipDrawableCompat(createLayerDrawableWithTintAttrRes(tileResId, tintAttrRes,
                context), Gravity.LEFT, ClipDrawableCompat.HORIZONTAL);
    }

    @SuppressLint("RtlHardcoded")
    private static Drawable createClippedTransparentLayerDrawable() {
        return new ClipDrawableCompat(new TileDrawable(new ColorDrawable(Color.TRANSPARENT)),
                Gravity.LEFT, ClipDrawableCompat.HORIZONTAL);
    }

    public float getTileRatio() {
        Drawable drawable = getTileDrawableByLayerId(android.R.id.progress).getDrawable();
        return (float) drawable.getIntrinsicWidth() / drawable.getIntrinsicHeight();
    }

    public void setStarCount(int count) {
        getTileDrawableByLayerId(android.R.id.background).setTileCount(count);
        getTileDrawableByLayerId(android.R.id.secondaryProgress).setTileCount(count);
        getTileDrawableByLayerId(android.R.id.progress).setTileCount(count);
    }

    @SuppressLint("NewApi")
    private TileDrawable getTileDrawableByLayerId(int id) {
        Drawable layerDrawable = findDrawableByLayerId(id);
        switch (id) {
            case android.R.id.background:
                return (TileDrawable) layerDrawable;
            case android.R.id.secondaryProgress:
            case android.R.id.progress: {
                ClipDrawableCompat clipDrawable = (ClipDrawableCompat) layerDrawable;
                return (TileDrawable) clipDrawable.getDrawable();
            }
            default:
                // Should never reach here.
                throw new RuntimeException();
        }
    }
}
