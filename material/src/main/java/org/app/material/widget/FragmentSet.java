package org.app.material.widget;

import android.support.annotation.StringRes;
import android.support.v4.app.Fragment;

public class FragmentSet {

    private Fragment fragment;
    private CharSequence title;
    private @StringRes int resId;

    public FragmentSet(Fragment fragment, CharSequence title) {
        this.fragment = fragment;
        this.title = title;
    }

    public FragmentSet(Fragment fragment, @StringRes int resId) {
        this.fragment = fragment;
        this.resId = resId;
    }

    public Fragment getFragment() {
        return fragment;
    }

    public CharSequence getTitle() {
        return title;
    }

    public int getResId() {
        return resId;
    }
}