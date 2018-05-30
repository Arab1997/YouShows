package org.michaelbel.ui.view;

import android.content.Context;
import android.graphics.Typeface;
import android.support.v4.content.ContextCompat;
import android.util.AttributeSet;
import android.util.TypedValue;
import android.view.Gravity;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import org.michaelbel.app.Theme;
import org.michaelbel.app.LayoutHelper;
import org.michaelbel.shows.R;

/**
 * Date: 01 APR 2018
 * Time: 18:19 MSK
 *
 * @author Michael Bel
 */

public class EmptyView extends LinearLayout {

    private TextView emptyText;
    private ImageView emptyIcon;

    public EmptyView(Context context) {
        super(context);
        initialize(context);
    }

    public EmptyView(Context context, AttributeSet attrs) {
        super(context, attrs);
        initialize(context);
    }

    private void initialize(Context context) {
        setOrientation(VERTICAL);
        setGravity(Gravity.CENTER);

        emptyIcon = new ImageView(context);
        emptyIcon.setLayoutParams(LayoutHelper.makeLinear(52, 52));
        addView(emptyIcon);

        emptyText = new TextView(context);
        emptyText.setGravity(Gravity.CENTER);
        emptyText.setTextSize(TypedValue.COMPLEX_UNIT_SP, 17);
        emptyText.setTypeface(Typeface.create("sans-serif-medium", Typeface.NORMAL));
        emptyText.setTextColor(ContextCompat.getColor(getContext(), Theme.Color.iconActive()));
        emptyText.setLayoutParams(LayoutHelper.makeLinear(LayoutHelper.WRAP_CONTENT, LayoutHelper.WRAP_CONTENT, Gravity.CENTER, 24, 10, 24, 0));
        addView(emptyText);
    }

    public EmptyView setMode(@EmptyViewMode int mode) {
        if (mode == EmptyViewMode.MODE_NO_CONNECTION) {
            emptyIcon.setImageDrawable(Theme.getIcon(R.drawable.ic_offline, ContextCompat.getColor(getContext(), Theme.Color.iconActive())));
            emptyText.setText(R.string.NoConnection);
        } else if (mode == EmptyViewMode.MODE_NO_SHOWS) {
            emptyIcon.setImageDrawable(Theme.getIcon(R.drawable.ic_show, ContextCompat.getColor(getContext(), Theme.Color.iconActive())));
            emptyText.setText(R.string.NoShows);
        } else if (mode == EmptyViewMode.MODE_NO_RESULTS) {
            emptyIcon.setImageDrawable(Theme.getIcon(R.drawable.ic_search_results, ContextCompat.getColor(getContext(), Theme.Color.iconActive())));
            emptyText.setText(R.string.NoResults);
        } else if (mode == EmptyViewMode.MODE_NO_EPISODES) {
            emptyIcon.setImageDrawable(Theme.getIcon(R.drawable.ic_movie_roll, ContextCompat.getColor(getContext(), Theme.Color.iconActive())));
            emptyText.setText(R.string.NoEpisodes);
        } else if (mode == EmptyViewMode.MODE_SEARCH_HISTORY) {
            emptyIcon.setImageDrawable(Theme.getIcon(R.drawable.ic_search_results, ContextCompat.getColor(getContext(), Theme.Color.iconActive())));
            emptyText.setText(R.string.NoSearchHistory);
        }

        return this;
    }
}