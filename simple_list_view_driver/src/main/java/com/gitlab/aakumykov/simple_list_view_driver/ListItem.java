package com.gitlab.aakumykov.simple_list_view_driver;

import androidx.annotation.Nullable;

public class ListItem implements iTitleItem {

    private final String mId;
    private final String mTitle;
    @Nullable final Object mPayload;

    public ListItem(String id, String title) {
        mId = id;
        mTitle = title;
        mPayload = null;
    }

    public ListItem(String id, String title, @Nullable Object payload) {
        mId = id;
        mTitle = title;
        mPayload = payload;
    }

    @Override
    public String getTitle() {
        return mTitle;
    }

    public String getId() {
        return mId;
    }

    public boolean hasPayload() {
        return null != mPayload;
    }

    @Nullable
    public Object getPayload() {
        return mPayload;
    }
}
