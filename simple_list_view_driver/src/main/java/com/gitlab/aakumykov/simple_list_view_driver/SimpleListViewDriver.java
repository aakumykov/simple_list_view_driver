package com.gitlab.aakumykov.simple_list_view_driver;

import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import androidx.annotation.NonNull;
import androidx.arch.core.util.Function;
import androidx.core.util.Consumer;

import java.util.ArrayList;
import java.util.List;

public class SimpleListViewDriver {

    private final List<iTitleItem> mItemsList = new ArrayList<>();
    private final SimpleArrayAdapter mSimpleArrayAdapter;
    private final ListView mListView;
    private boolean mDefaultScrollToNewItem = true;


    public SimpleListViewDriver(@NonNull ListView listView) {

        mListView  = listView;

        mSimpleArrayAdapter = new SimpleArrayAdapter(
                listView.getContext(),
                R.layout.list_item,
                R.id.titleView,
                mItemsList
        );

        listView.setAdapter(mSimpleArrayAdapter);
    }


    public void addItem(iTitleItem item) {
        mItemsList.add(item);
        notifyForNewElements();
    }

    public void addItem(iTitleItem item, boolean shouldScrollToNewItem) {
        mItemsList.add(item);
        notifyForNewElements(shouldScrollToNewItem);
    }


    public void addList(List<iTitleItem> list) {
        mItemsList.addAll(list);
        notifyForNewElements();
    }

    public void addList(List<iTitleItem> list, boolean scrollToBottom) {
        mItemsList.addAll(list);
        notifyForNewElements(scrollToBottom);
    }


    public void setList(List<iTitleItem> list) {
        mItemsList.clear();
        addList(list);
    }

    public void setList(List<iTitleItem> list, boolean scrollToBottom) {
        mItemsList.clear();
        addList(list, scrollToBottom);
    }


    public void setItemClickListener(final Consumer<iTitleItem> consumer) {
        mListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                consumer.accept(mItemsList.get(position));
            }
        });
    }

    public void setItemLongClickListener(final Function<iTitleItem, Boolean> function) {
        mListView.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {
                iTitleItem titleItem = mItemsList.get(position);
                return function.apply(titleItem);
            }
        });
    }


    public void setDefaultScrollToNewItem(boolean value) {
        mDefaultScrollToNewItem = value;
    }


    public void scrollTo(int position) {
        mListView.smoothScrollToPosition(position);
    }

    public void scrollToTop() {
        mListView.smoothScrollToPosition(0);
    }

    public void scrollToBottom() {
        mListView.smoothScrollToPosition(mItemsList.size());
    }



    private void notifyForNewElements() {
        notifyForNewElements(mDefaultScrollToNewItem);
    }

    private void notifyForNewElements(boolean shouldScrollToNewItem) {
        mSimpleArrayAdapter.notifyDataSetChanged();

        if (shouldScrollToNewItem)
            mListView.smoothScrollToPosition(mItemsList.size());
    }

}
