package com.gitlab.aakumykov.simple_list_view_driver;

public class ListItem implements iTitleItem {

    private final String mId;
    private final String mTitle;

    public ListItem(String id, String title) {
        mId = id;
        mTitle = title;
    }

    @Override
    public String getTitle() {
        return mTitle;
    }

    public String getId() {
        return mId;
    }
}
