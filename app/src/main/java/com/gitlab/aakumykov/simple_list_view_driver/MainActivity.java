package com.gitlab.aakumykov.simple_list_view_driver;

import android.os.Bundle;
import android.view.View;
import android.widget.CompoundButton;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.gitlab.aakumykov.simple_list_view_driver.databinding.ActivityMainBinding;

import java.util.UUID;

public class MainActivity extends AppCompatActivity {

    private int mCounter = 1;
    private ActivityMainBinding mViewBinding;
    private SimpleListViewDriver mSimpleListViewDriver;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        mViewBinding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(mViewBinding.getRoot());


        mSimpleListViewDriver = new SimpleListViewDriver(findViewById(R.id.listView));

        mSimpleListViewDriver.setItemClickListener(titleItem -> {
            String message = getString(R.string.click_on, titleItem.getTitle());
            Toast.makeText(this, message, Toast.LENGTH_SHORT).show();
        });

        mSimpleListViewDriver.setItemLongClickListener(titleItem -> {
            String message = getString(R.string.long_click_on, titleItem.getTitle());
            Toast.makeText(MainActivity.this, message, Toast.LENGTH_SHORT).show();
            return true;
        });

        mSimpleListViewDriver.setDefaultScrollToNewItem(mViewBinding.scrollToNewItemsSwitch.isChecked());


        mViewBinding.scrollToNewItemsSwitch.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                mSimpleListViewDriver.setDefaultScrollToNewItem(isChecked);
            }
        });

        mViewBinding.addButton.setOnClickListener(this::onAddClicked);
        mViewBinding.addWithScrollButton.setOnClickListener(this::onAddWithScrollClicked);


        // Добавляю начальный элемент
        /*int b = mCounter++;
        mSimpleListViewDriver.addItem(new ListItem(
                UUID.randomUUID().toString(),
                getString(R.string.element, b)
        ));*/
        onAddWithScrollClicked(null);
    }

    private void onAddClicked(View view) {
        ListItem listItem = createListItem();
        for (int i=0; i<3; i++)
            mSimpleListViewDriver.addItem(listItem);
    }

    private void onAddWithScrollClicked(View view) {
        ListItem listItem = createListItem();
        for (int i=0; i<3; i++)
            mSimpleListViewDriver.addItem(
                    listItem,
                    true
            );
    }

    private ListItem createListItem() {
        int c = mCounter++;
        return new ListItem(
                UUID.randomUUID().toString(),
                getString(R.string.element, c)
        );
    }
}