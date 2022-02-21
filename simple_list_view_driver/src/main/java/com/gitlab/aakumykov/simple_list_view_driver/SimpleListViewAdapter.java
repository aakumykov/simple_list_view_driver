package com.gitlab.aakumykov.simple_list_view_driver;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.util.Supplier;

import java.util.List;

class SimpleArrayAdapter extends ArrayAdapter<String> {

    private final List<iTitleItem> mItemList;

    private final LayoutInflater mLayoutInflater;
    private final int mLayoutResource;
    private final int mTextViewResourceId;

    public SimpleArrayAdapter(@NonNull Context context,
                              int layoutResource,
                              int textViewResourceId,
                              @NonNull List<iTitleItem> itemList)
    {
        super(context, layoutResource, textViewResourceId);

        mLayoutInflater = LayoutInflater.from(context);
        mItemList = itemList;
        mLayoutResource = layoutResource;
        mTextViewResourceId = textViewResourceId;
    }

    @NonNull @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {

        ViewHolder viewHolder;

        if (null == convertView) {
            convertView = mLayoutInflater.inflate(mLayoutResource, parent, false);
            viewHolder = new ViewHolder(convertView, mTextViewResourceId);
            convertView.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) convertView.getTag();
        }

        viewHolder.titleView.setText(mItemList.get(position).getTitle());

        return convertView;
    }

    @Override
    public int getCount() {
        return mItemList.size();
    }

    private static class ViewHolder {
        TextView titleView;

        public ViewHolder(View view, int textViewResourceId) {
            this.titleView = view.findViewById(textViewResourceId);
        }
    }
}
