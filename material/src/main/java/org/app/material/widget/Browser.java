/*
 * Copyright 2016 Michael Bel
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *    http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package org.app.material.widget;

import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.os.Parcelable;
import android.support.annotation.DrawableRes;
import android.support.annotation.NonNull;

public class Browser {

    private int mToolbarColor;
    private int mShareIcon;
    private String mShareIconText;
    private String mUrl;

    public Browser setUrl(String address) {
        this.mUrl = address;
        return this;
    }

    public String getUrl() {
        return mUrl;
    }

    public Browser setToolbarColor(int color) {
        this.mToolbarColor = color;
        return this;
    }

    public int getToolbarColor() {
        return mToolbarColor;
    }

    public Browser setShareIcon(@DrawableRes int icon) {
        this.mShareIcon = icon;
        return this;
    }

    public int getShareIcon() {
        return mShareIcon;
    }

    public Browser setShareIconHiddenText(@NonNull String text) {
        this.mShareIconText = text;
        return this;
    }

    public String getShareIconText() {
        return mShareIconText;
    }

    public Browser show(Context context) {
        if (context == null || mUrl == null) {
            return this;
        }

        try {
            Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse(mUrl));
            intent.putExtra("android.support.customtabs.extra.SESSION", (Parcelable) null);
            if (mToolbarColor != 0) {
                intent.putExtra("android.support.customtabs.extra.TOOLBAR_COLOR", getToolbarColor());
            }
            intent.putExtra("android.support.customtabs.extra.TITLE_VISIBILITY", 1);
            Intent actionIntent = new Intent(Intent.ACTION_SEND);
            actionIntent.setType("text/plain");
            actionIntent.putExtra(Intent.EXTRA_TEXT, Uri.parse(mUrl).toString());
            actionIntent.putExtra(Intent.EXTRA_SUBJECT, "");
            PendingIntent pendingIntent = PendingIntent.getActivity(context, 0, actionIntent, PendingIntent.FLAG_ONE_SHOT);
            Bundle bundle = new Bundle();
            bundle.putInt("android.support.customtabs.customaction.ID", 0);
            if (mShareIcon != 0) {
                bundle.putParcelable("android.support.customtabs.customaction.ICON", BitmapFactory.decodeResource(context.getResources(), getShareIcon()));
            }
            if (mShareIconText != null) {
                bundle.putString("android.support.customtabs.customaction.DESCRIPTION", getShareIconText());
            }
            bundle.putParcelable("android.support.customtabs.customaction.PENDING_INTENT", pendingIntent);
            intent.putExtra("android.support.customtabs.extra.ACTION_BUTTON_BUNDLE", bundle);
            intent.putExtra("android.support.customtabs.extra.TINT_ACTION_BUTTON", false);
            intent.putExtra(android.provider.Browser.EXTRA_APPLICATION_ID, context.getPackageName());
            context.startActivity(intent);
        } catch (Exception ignored) {}

        return this;
    }
}