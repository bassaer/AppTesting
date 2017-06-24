package com.github.bassaer.apptesting;

/**
 * Created by nakayama on 2017/06/24.
 */

public class Item {
    private int mIcon;
    private String mText;

    public Item() {
    }

    public Item(int icon, String text) {
        mIcon = icon;
        mText = text;
    }

    public int getIcon() {
        return mIcon;
    }

    public void setIcon(int icon) {
        mIcon = icon;
    }

    public String getText() {
        return mText;
    }

    public void setText(String text) {
        mText = text;
    }
}
