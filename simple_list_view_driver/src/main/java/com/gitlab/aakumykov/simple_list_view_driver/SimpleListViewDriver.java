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

    private final List<iTitleItem> mItemList = new ArrayList<>();
    private final SimpleArrayAdapter mSimpleArrayAdapter;
    private final ListView mListView;
    private boolean mShouldScrollToNewItem = true;


    public SimpleListViewDriver(@NonNull ListView listView) {

        mListView  = listView;

        mSimpleArrayAdapter = new SimpleArrayAdapter(
                listView.getContext(),
                R.layout.list_item,
                R.id.titleView,
                mItemList
        );

        listView.setAdapter(mSimpleArrayAdapter);
    }


    public void addItem(iTitleItem item) {
        mItemList.add(item);
        notifyAndScroll();
    }

    public void addList(List<iTitleItem> list) {
        mItemList.addAll(list);
        notifyAndScroll();
    }

    public void setList(List<iTitleItem> list) {
        mItemList.clear();
        addList(list);
    }


    public void setItemClickListener(final Consumer<iTitleItem> consumer) {
        mListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                consumer.accept(mItemList.get(position));
            }
        });
    }

    public void setItemLongClickListener(final Function<iTitleItem, Boolean> function) {
        mListView.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {
                iTitleItem titleItem = mItemList.get(position);
                return function.apply(titleItem);
            }
        });
    }

    public void setScrollToNewItem(boolean b) {
        mShouldScrollToNewItem = b;
    }


    private void notifyAndScroll() {
        mSimpleArrayAdapter.notifyDataSetChanged();

        if (mShouldScrollToNewItem)
            mListView.smoothScrollToPosition(mItemList.size());
    }

}
