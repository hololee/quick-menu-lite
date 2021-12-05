package com.hanstudio.quick.lite;

import android.graphics.Bitmap;
import android.graphics.BitmapShader;
import android.graphics.Canvas;
import android.graphics.ColorFilter;
import android.graphics.Paint;
import android.graphics.PixelFormat;
import android.graphics.Rect;
import android.graphics.RectF;
import android.graphics.Shader;
import android.graphics.drawable.Drawable;

/**
 * Created by L on 2015-10-23.
 */
public class RoundedDrawable extends Drawable {
    private final Bitmap mBitmap;
    private final Paint mPaint;
    private final RectF mRectF;
    private final int mBitmapWidth;
    private final int mBitmapHeight;

    public RoundedDrawable(Bitmap bitmap) {
        mBitmap = bitmap;
        mRectF = new RectF();
        mPaint = new Paint();
        mPaint.setAntiAlias(true);
        mPaint.setDither(true);
        final BitmapShader shader = new BitmapShader(bitmap, Shader.TileMode.CLAMP, Shader.TileMode.CLAMP);
        //비트맵 이미지를 타일(무늬)로 적용후 패인트 객체에 씌움
        mPaint.setShader(shader);


        if(mBitmap.getWidth() > mBitmap.getHeight()){
            mBitmapHeight =  mBitmap.getHeight();
            mBitmapWidth = mBitmap.getHeight();
        }else if(mBitmap.getWidth() < mBitmap.getHeight()){
            mBitmapHeight =  mBitmap.getWidth();
            mBitmapWidth = mBitmap.getWidth();
        }else{
            mBitmapWidth = mBitmap.getWidth();
            mBitmapHeight = mBitmap.getHeight();
        }
    }
    @Override
    public void draw(Canvas canvas) {
        canvas.drawOval(mRectF, mPaint);
    }
    @Override
    protected void onBoundsChange(Rect bounds) {
        super.onBoundsChange(bounds);
        mRectF.set(bounds);
    }
    @Override
    public void setAlpha(int alpha) {
        if (mPaint.getAlpha() != alpha) {
            mPaint.setAlpha(alpha);
            invalidateSelf();
        }
    }
    @Override
    public void setColorFilter(ColorFilter cf) {
        mPaint.setColorFilter(cf);
    }
    @Override
    public int getOpacity() {
        return PixelFormat.TRANSLUCENT;
    }
    @Override
    public int getIntrinsicWidth() {
        return mBitmapWidth;
    }
    @Override
    public int getIntrinsicHeight() {
        return mBitmapHeight;
    }

    @Override
    public void setFilterBitmap(boolean filter) {
        mPaint.setFilterBitmap(filter);
        invalidateSelf();
    }


}
