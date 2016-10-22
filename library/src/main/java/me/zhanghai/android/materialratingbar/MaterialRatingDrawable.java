/*
 * Copyright (c) 2016 Zhang Hai <Dreaming.in.Code.ZH@Gmail.com>
 * All Rights Reserved.
 */

package me.zhanghai.android.materialratingbar;

import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.drawable.ClipDrawable;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.LayerDrawable;
import android.support.v7.content.res.AppCompatResources;
import android.view.Gravity;

import me.zhanghai.android.materialratingbar.internal.ThemeUtils;

public class MaterialRatingDrawable extends LayerDrawable {

    public MaterialRatingDrawable(Context context) {
        super(new Drawable[] {
                createLayerDrawable(R.drawable.mrb_star_border_icon_black_36dp, false, context),
                createClippedLayerDrawable(R.drawable.mrb_star_border_icon_black_36dp, true,
                        context),
                createClippedLayerDrawable(R.drawable.mrb_star_icon_black_36dp, true, context)
        });

        setId(0, android.R.id.background);
        setId(1, android.R.id.secondaryProgress);
        setId(2, android.R.id.progress);
    }

    private static Drawable createLayerDrawable(int tileResId, boolean tintAsActivatedElseNormal,
                                                Context context) {
        int tintColor = ThemeUtils.getColorFromAttrRes(tintAsActivatedElseNormal ?
                R.attr.colorControlActivated : R.attr.colorControlNormal, context);
        TiledDrawable drawable = new TiledDrawable(AppCompatResources.getDrawable(context,
                tileResId));
        //noinspection RedundantCast
        ((TintableDrawable) drawable).setTint(tintColor);
        return drawable;
    }

    @SuppressLint("RtlHardcoded")
    private static Drawable createClippedLayerDrawable(int tileResId,
                                                       boolean tintAsActivatedElseNormal,
                                                       Context context) {
        return new ClipDrawable(createLayerDrawable(tileResId, tintAsActivatedElseNormal, context),
                Gravity.LEFT, ClipDrawable.HORIZONTAL);
    }

    public float getTileRatio() {
        // Cannot get wrapped drawable out of ClipDrawable until API 23, so we take the equivalent
        // from the android.R.id.background drawable.
        Drawable tileDrawable = ((TiledDrawable) findDrawableByLayerId(
                android.R.id.background)).getTileDrawable();
        return (float) tileDrawable.getIntrinsicWidth() / tileDrawable.getIntrinsicHeight();
    }
}
