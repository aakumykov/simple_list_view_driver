package com.gitlab.aakumykov.simple_list_view_driver;

import android.view.View;

interface ViewHolderProcessor<T> {
    T createViewHolder(View itemView);

    void fillViewHolder(T viewHolder, iTitleItem titleItem);
}
