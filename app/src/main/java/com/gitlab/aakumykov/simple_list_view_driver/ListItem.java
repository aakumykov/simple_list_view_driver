package com.gitlab.aakumykov.simple_list_view_driver;

public class ListItem implements iTitleItem {

    private final String mTitle;

    public ListItem(String title) {
        mTitle = title;
    }

    @Override
    public String getTitle() {
        return mTitle;
    }
}
