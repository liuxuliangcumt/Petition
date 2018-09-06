package com.realpower.petition.util;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.ColorFilter;
import android.graphics.LinearGradient;
import android.graphics.Paint;
import android.graphics.PixelFormat;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffXfermode;
import android.graphics.Rect;
import android.graphics.RectF;
import android.graphics.Shader;
import android.graphics.drawable.Drawable;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.view.ViewCompat;
import android.util.Log;
import android.view.View;

public class ShadowDrawableTest extends Drawable {

    private Paint mPaint;
    private int mShadowRadius;
    private int mShape;
    private int mShapeRadius;
    private int mOffsetX;
    private int mOffsetY;
    private int mBgColor[];
    private RectF mRect;
    private int textColor[] = {Color.parseColor("#4d4d4d"),
            Color.parseColor("#2389d4")};


    public final static int SHAPE_ROUND = 1;
    public final static int SHAPE_CIRCLE = 2;

    private ShadowDrawableTest(int shape, int[] bgColor, int shapeRadius, int shadowColor, int shadowRadius, int offsetX, int offsetY) {
        this.mShape = shape;
        this.mBgColor = bgColor;
        this.mShapeRadius = shapeRadius;
        this.mShadowRadius = shadowRadius;
        this.mOffsetX = offsetX;
        this.mOffsetY = offsetY;
        mPaint = new Paint();
        mPaint.setColor(Color.TRANSPARENT);
        mPaint.setAntiAlias(false);
        mPaint.setShadowLayer(shadowRadius, offsetX, offsetY, shadowColor);
        mPaint.setXfermode(new PorterDuffXfermode(PorterDuff.Mode.DST_ATOP));
        mPaint.setDither(true);
    }

    @Override
    public void setBounds(int left, int top, int right, int bottom) {
        super.setBounds(left, top, right, bottom);
        mRect = new RectF(left + mShadowRadius - mOffsetX, top + mShadowRadius - mOffsetY, right - mShadowRadius - mOffsetX,
                bottom - mShadowRadius - mOffsetY);
    }

    @Override
    public void draw(@NonNull Canvas canvas) {
        Log.e("bbb", " mRect.bottom  " + mRect.bottom);

        Paint newPaint = new Paint();
        if (mBgColor != null) {
            if (mBgColor.length == 1) {
                newPaint.setColor(mBgColor[0]);
            } else {
                newPaint.setShader(new LinearGradient(mRect.left, mRect.height() / 2, mRect.right, mRect.height() / 2, mBgColor,
                        null, Shader.TileMode.CLAMP));
            }
        }
        newPaint.setAntiAlias(false);
        mPaint.setShader(new LinearGradient(mRect.width() / 2, mRect.top,
                mRect.width() / 2, mRect.bottom,
                textColor,
                null, Shader.TileMode.CLAMP));
        if (mShape == SHAPE_ROUND) {
            mRect.bottom += 3 * mOffsetY;

            canvas.drawRoundRect(mRect, mShapeRadius, mShapeRadius, mPaint);
            //  mRect.top -= mOffsetY;
            //   mRect.bottom -= 5 * mOffsetY;
            canvas.drawRoundRect(mRect, mShapeRadius, mShapeRadius, newPaint);

        } else {
            canvas.drawCircle(mRect.centerX(), mRect.centerY(), Math.min(mRect.width(), mRect.height()) / 2, mPaint);
            canvas.drawCircle(mRect.centerX(), mRect.centerY(), Math.min(mRect.width(), mRect.height()) / 2, newPaint);

        }
    }

    @Override
    public void setAlpha(int alpha) {
        mPaint.setAlpha(alpha);
    }

    @Override
    public void setColorFilter(@Nullable ColorFilter colorFilter) {
        mPaint.setColorFilter(colorFilter);
    }

    @Override
    public int getOpacity() {
        return PixelFormat.TRANSLUCENT;
    }

    public static void setShadowDrawable(View view, Drawable drawable) {


        view.setLayerType(View.LAYER_TYPE_SOFTWARE, null);
        ViewCompat.setBackground(view, drawable);
    }

    public static void setShadowDrawable(View view, int shapeRadius, int shadowColor,
                                         int shadowRadius, int offsetX, int offsetY) {
        ShadowDrawableTest drawable = new Builder()
                .setShapeRadius(shapeRadius)
                .setShadowColor(shadowColor)
                .setShadowRadius(shadowRadius)
                .setOffsetX(offsetX)
                .setOffsetY(offsetY)
                .builder();
        view.setLayerType(View.LAYER_TYPE_SOFTWARE, null);
        ViewCompat.setBackground(view, drawable);
    }

    public static void setShadowDrawable(View view, int bgColor, int shapeRadius,
                                         int shadowColor,
                                         int shadowRadius, int offsetX, int offsetY) {

        ShadowDrawable drawable = new ShadowDrawable.Builder()
                .setBgColor(bgColor)
                .setShapeRadius(shapeRadius)
                .setShadowColor(shadowColor)
                .setShadowRadius(shadowRadius)
                .setOffsetX(offsetX)
                .setOffsetY(offsetY)
                .builder();
        view.setLayerType(View.LAYER_TYPE_SOFTWARE, null);
        ViewCompat.setBackground(view, drawable);
    }

    public static void setShadowDrawable(View view, int shape, int bgColor, int shapeRadius,
                                         int shadowColor, int shadowRadius, int offsetX, int offsetY) {
        ShadowDrawableTest drawable = new Builder()
                .setShape(shape)
                .setBgColor(bgColor)
                .setShapeRadius(shapeRadius)
                .setShadowColor(shadowColor)
                .setShadowRadius(shadowRadius)
                .setOffsetX(offsetX)
                .setOffsetY(offsetY)
                .builder();
        view.setLayerType(View.LAYER_TYPE_SOFTWARE, null);
        ViewCompat.setBackground(view, drawable);
    }

    public Paint getmPaint() {
        return mPaint;
    }

    public void setmPaint(Paint mPaint) {
        this.mPaint = mPaint;
    }

    public int getmShadowRadius() {
        return mShadowRadius;
    }

    public void setmShadowRadius(int mShadowRadius) {
        this.mShadowRadius = mShadowRadius;
    }

    public int getmShape() {
        return mShape;
    }

    public void setmShape(int mShape) {
        this.mShape = mShape;
    }

    public int getmShapeRadius() {
        return mShapeRadius;
    }

    public void setmShapeRadius(int mShapeRadius) {
        this.mShapeRadius = mShapeRadius;
    }

    public int getmOffsetX() {
        return mOffsetX;
    }

    public void setmOffsetX(int mOffsetX) {
        this.mOffsetX = mOffsetX;
    }

    public int getmOffsetY() {
        return mOffsetY;
    }

    public void setmOffsetY(int mOffsetY) {
        this.mOffsetY = mOffsetY;
    }

    public int[] getmBgColor() {
        return mBgColor;
    }

    public void setmBgColor(int[] mBgColor) {
        this.mBgColor = mBgColor;
    }

    public RectF getmRect() {
        return mRect;
    }

    public void setmRect(RectF mRect) {
        this.mRect = mRect;
    }

    public int[] getTextColor() {
        return textColor;
    }

    public void setTextColor(int[] textColor) {
        this.textColor = textColor;
    }

    public static void setShadowDrawable(View view, int[] bgColor, int shapeRadius, int shadowColor,
                                         int shadowRadius, int offsetX, int offsetY) {
        ShadowDrawableTest drawable = new Builder()
                .setBgColor(bgColor)
                .setShapeRadius(shapeRadius)
                .setShadowColor(shadowColor)
                .setShadowRadius(shadowRadius)
                .setOffsetX(offsetX)
                .setOffsetY(offsetY)
                .builder();
        view.setLayerType(View.LAYER_TYPE_SOFTWARE, null);
        ViewCompat.setBackground(view, drawable);

    }

    public static class Builder {
        private int mShape;
        private int mShapeRadius;
        private int mShadowColor;
        private int mShadowRadius;
        private int mOffsetX = 0;
        private int mOffsetY = 0;
        private int[] mBgColor;

        public Builder() {
            mShape = ShadowDrawableTest.SHAPE_ROUND;
            mShapeRadius = 12;
            mShadowColor = Color.parseColor("#4d000000");
            mShadowRadius = 18;
            mOffsetX = 0;
            mOffsetY = 0;
            mBgColor = new int[1];
            mBgColor[0] = Color.TRANSPARENT;
        }

        public Builder setShape(int mShape) {
            this.mShape = mShape;
            return this;
        }

        public Builder setShapeRadius(int ShapeRadius) {
            this.mShapeRadius = ShapeRadius;
            return this;
        }

        public Builder setShadowColor(int shadowColor) {
            this.mShadowColor = shadowColor;
            return this;
        }

        public Builder setShadowRadius(int shadowRadius) {
            this.mShadowRadius = shadowRadius;
            return this;
        }

        public Builder setOffsetX(int OffsetX) {
            this.mOffsetX = OffsetX;
            return this;
        }

        public Builder setOffsetY(int OffsetY) {
            this.mOffsetY = OffsetY;
            return this;
        }

        public Builder setBgColor(int BgColor) {
            this.mBgColor[0] = BgColor;
            return this;
        }

        public Builder setBgColor(int[] BgColor) {
            this.mBgColor = BgColor;
            return this;
        }

        public ShadowDrawableTest builder() {
            return new ShadowDrawableTest(mShape, mBgColor, mShapeRadius, mShadowColor, mShadowRadius, mOffsetX, mOffsetY);
        }
    }
}