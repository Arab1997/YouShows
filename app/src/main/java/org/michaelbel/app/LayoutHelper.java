package org.michaelbel.app;

import android.support.v4.widget.SwipeRefreshLayout;
import android.widget.FrameLayout;
import android.widget.LinearLayout;

import org.michaelbel.old.ScreenUtils;

public class LayoutHelper {

    public static final int MATCH_PARENT = -1;
    public static final int WRAP_CONTENT = -2;

    private static int getSize(float size) {
        return (int) (size < 0 ? size : ScreenUtils.dp(size));
    }

    public static FrameLayout.LayoutParams makeFrame(int width, int height) {
        return new FrameLayout.LayoutParams(getSize(width), getSize(height));
    }

    public static FrameLayout.LayoutParams makeFrame(int width, int height, int gravity) {
        FrameLayout.LayoutParams params = new FrameLayout.LayoutParams(getSize(width), getSize(height));
        params.gravity = gravity;
        return params;
    }

    public static FrameLayout.LayoutParams makeFrame(int width, int height, float start, float top, float end, float bottom) {
        FrameLayout.LayoutParams params = new FrameLayout.LayoutParams(getSize(width), getSize(height));
        params.leftMargin = getSize(start);
        params.topMargin = getSize(top);
        params.rightMargin = getSize(end);
        params.bottomMargin = getSize(bottom);
        return params;
    }

    public static FrameLayout.LayoutParams makeFrame(int width, int height, int gravity, float start, float top, float end, float bottom) {
        FrameLayout.LayoutParams params = new FrameLayout.LayoutParams(getSize(width), getSize(height));
        params.gravity = gravity;
        params.leftMargin = getSize(start);
        params.topMargin = getSize(top);
        params.rightMargin = getSize(end);
        params.bottomMargin = getSize(bottom);
        return params;
    }

    public static LinearLayout.LayoutParams makeLinear(int width, int height) {
        return new LinearLayout.LayoutParams(getSize(width), getSize(height));
    }

    public static LinearLayout.LayoutParams makeLinear(int width, int height, int gravity) {
        LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(getSize(width), getSize(height));
        params.gravity = gravity;
        return params;
    }

    public static LinearLayout.LayoutParams makeLinear(int width, int height, float start, float top, float end, float bottom) {
        LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(getSize(width), getSize(height));
        params.leftMargin = getSize(start);
        params.topMargin = getSize(top);
        params.rightMargin = getSize(end);
        params.bottomMargin = getSize(bottom);
        return params;
    }

    public static LinearLayout.LayoutParams makeLinear(int width, int height, int gravity, float start, float top, float end, float bottom) {
        LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(getSize(width), getSize(height));
        params.gravity = gravity;
        params.leftMargin = getSize(start);
        params.topMargin = getSize(top);
        params.rightMargin = getSize(end);
        params.bottomMargin = getSize(bottom);
        return params;
    }

    public static LinearLayout.LayoutParams makeLinear(int width, int height, float weight) {
        LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(getSize(width), getSize(height));
        params.weight = weight;
        return params;
    }

    public static LinearLayout.LayoutParams makeLinear(int width, int height, int gravity, float weight) {
        LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(getSize(width), getSize(height));
        params.gravity = gravity;
        params.weight = weight;
        return params;
    }

    public static LinearLayout.LayoutParams makeLinear(int width, int height, float weight, float start, float top, float end, float bottom) {
        LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(getSize(width), getSize(height));
        params.weight = weight;
        params.leftMargin = getSize(start);
        params.topMargin = getSize(top);
        params.rightMargin = getSize(end);
        params.bottomMargin = getSize(bottom);
        return params;
    }

    public static LinearLayout.LayoutParams makeLinear(int width, int height, int gravity, float weight, float start, float top, float end, float bottom) {
        LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(getSize(width), getSize(height));
        params.gravity = gravity;
        params.weight = weight;
        params.leftMargin = getSize(start);
        params.topMargin = getSize(top);
        params.rightMargin = getSize(end);
        params.bottomMargin = getSize(bottom);
        return params;
    }

    public static SwipeRefreshLayout.LayoutParams makeSwipeRefresh(int width, int height) {
        return new SwipeRefreshLayout.LayoutParams(getSize(width), getSize(height));
    }
}