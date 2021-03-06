package org.michaelbel.youshows.ui.view;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.ObjectAnimator;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffColorFilter;
import android.graphics.PorterDuffXfermode;
import android.graphics.drawable.Drawable;
import android.text.TextPaint;
import android.view.View;

import org.michaelbel.material.extensions.Extensions;

/**
 * Date: 18 MAY 2018
 * Time: 19:59 MSK
 *
 * @author Michael Bel
 */

public class CheckBox extends View {

    private Drawable checkDrawable;
    private static Paint paint;
    private static Paint eraser;
    private static Paint eraser2;
    private static Paint checkPaint;
    private static Paint backgroundPaint;
    private static TextPaint textPaint;

    private Bitmap drawBitmap;
    private Bitmap checkBitmap;
    private Canvas bitmapCanvas;
    private Canvas checkCanvas;

    private boolean drawBackground;
    private boolean hasBorder;

    private float progress;
    private ObjectAnimator checkAnimator;
    private boolean isCheckAnimation = true;

    private boolean attachedToWindow;
    private boolean isChecked;

    private int size = 24; // 22
    private int checkOffset;
    private int color;
    private String checkedText;

    private final static float progressBounceDiff = 0.2f;

    public CheckBox(Context context, int resId) {
        super(context);
        if (paint == null) {
            paint = new Paint(Paint.ANTI_ALIAS_FLAG);
            eraser = new Paint(Paint.ANTI_ALIAS_FLAG);
            eraser.setColor(0);
            eraser.setXfermode(new PorterDuffXfermode(PorterDuff.Mode.CLEAR));
            eraser2 = new Paint(Paint.ANTI_ALIAS_FLAG);
            eraser2.setColor(0);
            eraser2.setStyle(Paint.Style.STROKE);
            eraser2.setStrokeWidth(Extensions.dp(context,28));
            eraser2.setXfermode(new PorterDuffXfermode(PorterDuff.Mode.CLEAR));
            backgroundPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
            backgroundPaint.setColor(0xFFFFFFFF);
            backgroundPaint.setStyle(Paint.Style.STROKE);
            backgroundPaint.setStrokeWidth(Extensions.dp(context,2));
            textPaint = new TextPaint(Paint.ANTI_ALIAS_FLAG);
            textPaint.setTextSize(Extensions.dp(context,18));
            //textPaint.setTypeface(ScreenUtils.getTypeface("fonts/rmedium.ttf"));
        }

        checkDrawable = context.getResources().getDrawable(resId).mutate();
    }

    @Override
    public void setVisibility(int visibility) {
        super.setVisibility(visibility);
        if (visibility == VISIBLE && drawBitmap == null) {
            drawBitmap = Bitmap.createBitmap(Extensions.dp(getContext(), size), Extensions.dp(getContext(),size), Bitmap.Config.ARGB_4444);
            bitmapCanvas = new Canvas(drawBitmap);
            checkBitmap = Bitmap.createBitmap(Extensions.dp(getContext(),size), Extensions.dp(getContext(),size), Bitmap.Config.ARGB_4444);
            checkCanvas = new Canvas(checkBitmap);
        }
    }

    public void setProgress(float value) {
        if (progress == value) {
            return;
        }
        progress = value;
        invalidate();
    }

    /*public void setDrawBackground(boolean value) {
        drawBackground = value;
    }

    public void setHasBorder(boolean value) {
        hasBorder = value;
    }

    public void setCheckOffset(int value) {
        checkOffset = value;
    }*/

    public void setSize(int size) {
        this.size = size;
    }

    public float getProgress() {
        return progress;
    }

    public void setColor(int backgroundColor, int checkColor) {
        color = backgroundColor;
        checkDrawable.setColorFilter(new PorterDuffColorFilter(checkColor, PorterDuff.Mode.MULTIPLY));
        textPaint.setColor(checkColor);
        invalidate();
    }

    public void setBackgroundColor(int backgroundColor) {
        color = backgroundColor;
        invalidate();
    }

    public void setCheckColor(int checkColor) {
        checkDrawable.setColorFilter(new PorterDuffColorFilter(checkColor, PorterDuff.Mode.MULTIPLY));
        textPaint.setColor(checkColor);
        invalidate();
    }

    private void cancelCheckAnimator() {
        if (checkAnimator != null) {
            checkAnimator.cancel();
            checkAnimator = null;
        }
    }

    private void animateToCheckedState(boolean newCheckedState) {
        isCheckAnimation = newCheckedState;
        checkAnimator = ObjectAnimator.ofFloat(this, "progress", newCheckedState ? 1 : 0);
        checkAnimator.addListener(new AnimatorListenerAdapter() {
            @Override
            public void onAnimationEnd(Animator animation) {
                if (animation.equals(checkAnimator)) {
                    checkAnimator = null;
                }
                if (!isChecked) {
                    checkedText = null;
                }
            }
        });
        checkAnimator.setDuration(300);
        checkAnimator.start();
    }

    @Override
    protected void onAttachedToWindow() {
        super.onAttachedToWindow();
        attachedToWindow = true;
    }

    @Override
    protected void onDetachedFromWindow() {
        super.onDetachedFromWindow();
        attachedToWindow = false;
    }

    @Override
    protected void onLayout(boolean changed, int left, int top, int right, int bottom) {
        super.onLayout(changed, left, top, right, bottom);
    }

    public void setChecked(boolean checked, boolean animated) {
        setChecked(-1, checked, animated);
    }

    /*public void setNum(int num) {
        if (num >= 0) {
            checkedText = "" + (num + 1);
        } else if (checkAnimator == null) {
            checkedText = null;
        }
        invalidate();
    }*/

    public void setChecked(int num, boolean checked, boolean animated) {
        if (num >= 0) {
            checkedText = "" + (num + 1);
        }
        if (checked == isChecked) {
            return;
        }
        isChecked = checked;

        if (attachedToWindow && animated) {
            animateToCheckedState(checked);
        } else {
            cancelCheckAnimator();
            setProgress(checked ? 1.0f : 0.0f);
        }
    }

    public boolean isChecked() {
        return isChecked;
    }

    @Override
    protected void onDraw(Canvas canvas) {
        if (getVisibility() != VISIBLE) {
            return;
        }
        if (drawBackground || progress != 0) {
            eraser2.setStrokeWidth(Extensions.dp(getContext(),size + 6));

            drawBitmap.eraseColor(0);
            float rad = getMeasuredWidth() / 2;

            float roundProgress = progress >= 0.5f ? 1.0f : progress / 0.5f;
            float checkProgress = progress < 0.5f ? 0.0f : (progress - 0.5f) / 0.5f;

            float roundProgressCheckState = isCheckAnimation ? progress : (1.0f - progress);
            if (roundProgressCheckState < progressBounceDiff) {
                rad -= Extensions.dp(getContext(),2) * roundProgressCheckState / progressBounceDiff;
            } else if (roundProgressCheckState < progressBounceDiff * 2) {
                rad -= Extensions.dp(getContext(),2) - Extensions.dp(getContext(),2) * (roundProgressCheckState - progressBounceDiff) / progressBounceDiff;
            }
            if (drawBackground) {
                paint.setColor(0x44000000);
                canvas.drawCircle(getMeasuredWidth() / 2, getMeasuredHeight() / 2, rad - Extensions.dp(getContext(),1), paint);
                canvas.drawCircle(getMeasuredWidth() / 2, getMeasuredHeight() / 2, rad - Extensions.dp(getContext(),1), backgroundPaint);
            }

            paint.setColor(color);

            if (hasBorder) {
                rad -= Extensions.dp(getContext(),2);
            }
            bitmapCanvas.drawCircle(getMeasuredWidth() / 2, getMeasuredHeight() / 2, rad, paint);
            bitmapCanvas.drawCircle(getMeasuredWidth() / 2, getMeasuredHeight() / 2, rad * (1 - roundProgress), eraser);
            canvas.drawBitmap(drawBitmap, 0, 0, null);

            checkBitmap.eraseColor(0);
            if (checkedText != null) {
                int w = (int) Math.ceil(textPaint.measureText(checkedText));
                checkCanvas.drawText(checkedText, (getMeasuredWidth() - w) / 2, Extensions.dp(getContext(),21), textPaint);
            } else {
                int w = checkDrawable.getIntrinsicWidth();
                int h = checkDrawable.getIntrinsicHeight();
                int x = (getMeasuredWidth() - w) / 2;
                int y = (getMeasuredHeight() - h) / 2;

                checkDrawable.setBounds(x, y + checkOffset, x + w, y + h + checkOffset);
                checkDrawable.draw(checkCanvas);
            }
            checkCanvas.drawCircle(getMeasuredWidth() / 2 - Extensions.dp(getContext(),2.5f), getMeasuredHeight() / 2 + Extensions.dp(getContext(),4), ((getMeasuredWidth() + Extensions.dp(getContext(),6)) / 2) * (1 - checkProgress), eraser2);

            canvas.drawBitmap(checkBitmap, 0, 0, null);
        }
    }
}