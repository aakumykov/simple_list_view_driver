package com.gitlab.aakumykov.simple_list_view_driver;

import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import androidx.annotation.NonNull;
import androidx.core.util.Consumer;
import androidx.core.util.Supplier;

import java.util.ArrayList;
import java.util.List;

public class SimpleListViewDriver {

    private final List<iTitleItem> mItemsList = new ArrayList<>();
    private final SimpleArrayAdapter mSimpleArrayAdapter;
    private final ListView mListView;
    private boolean mShouldScrollToNewItem = true;


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
        notifyAndScroll();
    }

    public void addList(List<iTitleItem> list) {
        mItemsList.addAll(list);
        notifyAndScroll();
    }

    public void setList(List<iTitleItem> list) {
        mItemsList.clear();
        addList(list);
    }


    public void setOnItemClickListener(final Consumer<iTitleItem> consumer) {
        mListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                consumer.accept(mItemsList.get(position));
            }
        });
    }


    public void setScrollToNewItem(boolean b) {
        mShouldScrollToNewItem = b;
    }


    private void notifyAndScroll() {
        mSimpleArrayAdapter.notifyDataSetChanged();

        if (mShouldScrollToNewItem)
            mListView.smoothScrollToPosition(mItemsList.size());
    }

}
