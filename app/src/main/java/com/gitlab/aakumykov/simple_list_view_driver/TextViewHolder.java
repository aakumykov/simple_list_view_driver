package com.gitlab.aakumykov.simple_list_view_driver;

import android.view.View;
import android.widget.TextView;

public class TextViewHolder {

    public TextView titleView;
    public TextView lengthView;

    public TextViewHolder(View itemView) {
        this.titleView = itemView.findViewById(R.id.titleView);
        this.lengthView = itemView.findViewById(R.id.lengthView);
    }
}
