/*
 * Copyright (c) 2016 Zhang Hai <Dreaming.in.Code.ZH@Gmail.com>
 * All Rights Reserved.
 */

package me.zhanghai.android.materialratingbar;

import android.content.res.ColorStateList;
import android.graphics.PorterDuff;
import android.graphics.drawable.ClipDrawable;
import android.graphics.drawable.Drawable;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.util.Log;

class ClipDrawableCompat extends ClipDrawable implements TintableDrawable {

    private static final String TAG = ClipDrawableCompat.class.getSimpleName();

    private Drawable mDrawable;

    private DummyConstantState mConstantState = new DummyConstantState();

    public ClipDrawableCompat(Drawable drawable, int gravity, int orientation) {
        super(drawable, gravity, orientation);

        mDrawable = drawable;
    }

    @Nullable
    @Override
    public Drawable getDrawable() {
        return mDrawable;
    }

    @Override
    public void setTint(int tintColor) {
        if (mDrawable instanceof TintableDrawable) {
            //noinspection RedundantCast
            ((TintableDrawable) mDrawable).setTint(tintColor);
        } else {
            Log.w(TAG, "Drawable did not implement TintableDrawable, it won't be tinted below" +
                    " Lollipop");
            super.setTint(tintColor);
        }
    }

    @Override
    public void setTintList(ColorStateList tint) {
        if (mDrawable instanceof TintableDrawable) {
            //noinspection RedundantCast
            ((TintableDrawable) mDrawable).setTintList(tint);
        } else {
            Log.w(TAG, "Drawable did not implement TintableDrawable, it won't be tinted below" +
                    " Lollipop");
            super.setTintList(tint);
        }
    }

    @Override
    public void setTintMode(PorterDuff.Mode tintMode) {
        if (mDrawable instanceof TintableDrawable) {
            //noinspection RedundantCast
            ((TintableDrawable) mDrawable).setTintMode(tintMode);
        } else {
            Log.w(TAG, "Drawable did not implement TintableDrawable, it won't be tinted below" +
                    " Lollipop");
            super.setTintMode(tintMode);
        }
    }

    // Workaround LayerDrawable.ChildDrawable which calls getConstantState().newDrawable()
    // without checking for null.
    // We are never inflated from XML so the protocol of ConstantState does not apply to us. In
    // order to make LayerDrawable happy, we return ourselves from DummyConstantState.newDrawable().

    @Override
    public ConstantState getConstantState() {
        return mConstantState;
    }

    private class DummyConstantState extends ConstantState {

        @Override
        public int getChangingConfigurations() {
            return 0;
        }

        @NonNull
        @Override
        public Drawable newDrawable() {
            return ClipDrawableCompat.this;
        }
    }
}
