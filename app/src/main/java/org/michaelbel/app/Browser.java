package org.michaelbel.app;

import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.os.Parcelable;
import android.support.annotation.NonNull;

import org.michaelbel.material.util2.Utils;
import org.michaelbel.shows.R;

/**
 * Date: 02 APR 2018
 * Time: 21:52 MSK
 *
 * @author Michael Bel
 */

public class Browser {

    public static void openUrl(@NonNull Context context, @NonNull String url) {
        SharedPreferences prefs = context.getSharedPreferences("mainconfig", Context.MODE_PRIVATE);
        if (prefs.getBoolean("in_app_browser", true)) {
            openInAppUrl(context, url);
        } else {
            openBrowserUrl(context, url);
        }
    }

    private static void openInAppUrl(@NonNull Context context, @NonNull String url) {
        try {
            Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse(url));
            intent.putExtra("android.support.customtabs.extra.SESSION", (Parcelable) null);
            intent.putExtra("android.support.customtabs.extra.TOOLBAR_COLOR", Utils.getAttrColor(context, R.attr.colorPrimary));
            intent.putExtra("android.support.customtabs.extra.TITLE_VISIBILITY", 1);
            Intent actionIntent = new Intent(Intent.ACTION_SEND);
            actionIntent.setType("text/plain");
            actionIntent.putExtra(Intent.EXTRA_TEXT, Uri.parse(url).toString());
            actionIntent.putExtra(Intent.EXTRA_SUBJECT, "");
            PendingIntent pendingIntent = PendingIntent.getActivity(context, 0, actionIntent, PendingIntent.FLAG_ONE_SHOT);
            Bundle bundle = new Bundle();
            bundle.putInt("android.support.customtabs.customaction.ID", 0);
            bundle.putParcelable("android.support.customtabs.customaction.ICON", BitmapFactory.decodeResource(context.getResources(), org.michaelbel.material.R.drawable.abc_ic_menu_share_mtrl_alpha));
            bundle.putString("android.support.customtabs.customaction.DESCRIPTION", "Share link");
            bundle.putParcelable("android.support.customtabs.customaction.PENDING_INTENT", pendingIntent);
            intent.putExtra("android.support.customtabs.extra.ACTION_BUTTON_BUNDLE", bundle);
            intent.putExtra("android.support.customtabs.extra.TINT_ACTION_BUTTON", false);
            intent.putExtra(android.provider.Browser.EXTRA_APPLICATION_ID, context.getPackageName());
            context.startActivity(intent);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private static void openBrowserUrl(@NonNull Context context, @NonNull String url) {
        context.startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse(url)));
    }
}