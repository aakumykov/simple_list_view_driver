package com.gitlab.aakumykov.simple_list_view_driver;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import java.util.List;

class SimpleArrayAdapter<V, I> extends ArrayAdapter<String> {

    private final List<I> mItemList;

    private final LayoutInflater mLayoutInflater;
    private final int mLayoutResource;
    private final ViewHolderProcessor<V,I> mViewHolderProcessor;
    private V mViewHolder;

    public SimpleArrayAdapter(@NonNull Context context,
                              int layoutResource,
                              @NonNull List<I> itemList,
                              @NonNull ViewHolderProcessor<V,I> viewHolderProcessor)
    {
        super(context, layoutResource);

        mLayoutInflater = LayoutInflater.from(context);
        mItemList = itemList;
        mLayoutResource = layoutResource;
        mViewHolderProcessor = viewHolderProcessor;
    }

    @NonNull @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {

        if (null == convertView) {
            convertView = mLayoutInflater.inflate(mLayoutResource, parent, false);
            mViewHolder = mViewHolderProcessor.createViewHolder(convertView);
            convertView.setTag(mViewHolder);
        }
        else {
            mViewHolder = (V) convertView.getTag();
        }

        mViewHolderProcessor.fillViewHolder(mViewHolder, mItemList.get(position));

        return convertView;
    }

    @Override
    public int getCount() {
        return mItemList.size();
    }

    private static abstract class AbstractViewHolder {

    }

    private static class ViewHolder extends AbstractViewHolder {
        TextView titleView;

        public ViewHolder(View view, int textViewResourceId) {
            this.titleView = view.findViewById(textViewResourceId);
        }
    }
}
