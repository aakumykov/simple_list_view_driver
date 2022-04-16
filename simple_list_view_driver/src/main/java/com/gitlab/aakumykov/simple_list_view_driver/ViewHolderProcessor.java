package com.gitlab.aakumykov.simple_list_view_driver;

import android.view.View;

interface ViewHolderProcessor<V,I> {
    V createViewHolder(View itemView);
    void fillViewHolder(V viewHolder, I listItem);
}
