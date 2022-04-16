package com.gitlab.aakumykov.simple_list_view_driver;

import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import androidx.annotation.LayoutRes;
import androidx.annotation.NonNull;
import androidx.arch.core.util.Function;
import androidx.core.util.Consumer;

import java.util.ArrayList;
import java.util.List;

public class SimpleListViewDriver<V,I> {

    private final List<I> mItemsList = new ArrayList<>();
    private final SimpleArrayAdapter<V,I> mSimpleArrayAdapter;
    private final ListView mListView;


    public SimpleListViewDriver(@NonNull ListView listView,
                                @LayoutRes int itemLayoutResource,
                                @NonNull ViewHolderProcessor<V, I> viewHolderProcessor
    ) {
        mListView  = listView;

        mSimpleArrayAdapter = new SimpleArrayAdapter<V,I>(
                listView.getContext(),
                itemLayoutResource,
                mItemsList,
                viewHolderProcessor
        );

        listView.setAdapter(mSimpleArrayAdapter);
    }


    public void addItem(I item) {
        mItemsList.add(item);
        notifyForNewElements(true);
    }

    public void addItem(I item, boolean shouldScrollToNewItem) {
        mItemsList.add(item);
        notifyForNewElements(shouldScrollToNewItem);
    }


    public void addList(List<I> list) {
        mItemsList.addAll(list);
        notifyForNewElements(true);
    }

    public void addList(List<I> list, boolean scrollToBottom) {
        mItemsList.addAll(list);
        notifyForNewElements(scrollToBottom);
    }


    public void setList(List<I> list) {
        mItemsList.clear();
        mItemsList.addAll(list);
        notifyForNewElements(false);
    }

    public void setList(List<I> list, boolean scrollToBottom) {
        mItemsList.clear();
        mItemsList.addAll(list);
        notifyForNewElements(scrollToBottom);
    }


    public void setItemClickListener(final Consumer<I> consumer) {
        mListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                consumer.accept(mItemsList.get(position));
            }
        });
    }

    public void setItemLongClickListener(final Function<I, Boolean> function) {
        mListView.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {
                I titleItem = mItemsList.get(position);
                return function.apply(titleItem);
            }
        });
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


    private void notifyForNewElements(boolean shouldScrollToNewItem) {
        mSimpleArrayAdapter.notifyDataSetChanged();

        if (shouldScrollToNewItem)
            mListView.smoothScrollToPosition(mItemsList.size());
    }

}
