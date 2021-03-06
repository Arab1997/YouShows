package org.michaelbel.youshows.ui.view;

import android.content.Context;
import android.graphics.Typeface;
import android.support.annotation.DrawableRes;
import android.support.annotation.StringRes;
import android.support.v4.content.ContextCompat;
import android.util.TypedValue;
import android.view.Gravity;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import org.michaelbel.youshows.AndroidExtensions;
import org.michaelbel.material.widget.LayoutHelper;
import org.michaelbel.youshows.Theme;

/**
 * Date: 10 JUN 2018
 * Time: 20:45 MSK
 *
 * @author Michael Bel
 */

public class TabView extends FrameLayout {

    private int icon;

    private ImageView tabIcon;
    private TextView tabNameText;

    public TabView(Context context) {
        super(context);

        LinearLayout linearLayout = new LinearLayout(context);
        linearLayout.setOrientation(LinearLayout.VERTICAL);
        linearLayout.setLayoutParams(LayoutHelper.makeFrame(context, LayoutHelper.MATCH_PARENT, LayoutHelper.MATCH_PARENT));
        addView(linearLayout);

        tabIcon = new ImageView(context);
        tabIcon.setLayoutParams(LayoutHelper.makeLinear(context, 20, 20, Gravity.CENTER_HORIZONTAL));
        linearLayout.addView(tabIcon);

        tabNameText = new TextView(context);
        tabNameText.setLines(1);
        tabNameText.setMaxLines(1);
        tabNameText.setSingleLine();
        tabNameText.setTextSize(TypedValue.COMPLEX_UNIT_SP, 9.5F);
        tabNameText.setTypeface(Typeface.create("sans-serif-medium", Typeface.NORMAL));
        tabNameText.setLayoutParams(LayoutHelper.makeLinear(context, LayoutHelper.WRAP_CONTENT, LayoutHelper.WRAP_CONTENT, Gravity.CENTER_HORIZONTAL, 0, 2,0,0));
        linearLayout.addView(tabNameText);
    }

    public TabView setTab(@StringRes int nameResId, @DrawableRes int iconResId, boolean selected) {
        icon = iconResId;
        int color = ContextCompat.getColor(getContext(), selected ? Theme.tabSelectColor() : Theme.tabUnselectColor());

        tabNameText.setText(getResources().getString(nameResId).toUpperCase());
        tabNameText.setTextColor(color);
        tabIcon.setImageDrawable(AndroidExtensions.getIcon(getContext(), icon, color));
        return this;
    }

    public void setSelect(boolean selected) {
        int color = ContextCompat.getColor(getContext(), selected ? Theme.tabSelectColor() : Theme.tabUnselectColor());

        tabNameText.setTextColor(color);
        tabIcon.setImageDrawable(AndroidExtensions.getIcon(getContext(), icon, color));
    }
}