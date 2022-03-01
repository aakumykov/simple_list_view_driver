package com.gitlab.aakumykov.simple_list_view_driver;

import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.gitlab.aakumykov.simple_list_view_driver.databinding.ActivityMainBinding;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.UUID;

public class MainActivity extends AppCompatActivity {

    private ActivityMainBinding mViewBinding;
    private SimpleListViewDriver mSimpleListViewDriver;
    private final Random mRandom = new Random();

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

        mViewBinding.addButton.setOnClickListener(this::onAddButtonClicked);
        mViewBinding.setButton.setOnClickListener(this::onSetButtonClicked);


        // Добавляю начальный элемент
        onSetButtonClicked(null);
    }

    private void onAddButtonClicked(View view) {
        iTitleItem listItem = createListItem();
        mSimpleListViewDriver.addItem(listItem);
    }

    private void onSetButtonClicked(View view) {
        List<iTitleItem> list = new ArrayList<>();
        int count = new Random().nextInt(20)+10;
        for (int i=0; i<count; i++)
            list.add(createListItem());
        mSimpleListViewDriver.setList(list);
    }

    private iTitleItem createListItem() {
        return new ListItem(
                UUID.randomUUID().toString(),
                getString(R.string.element, mRandom.nextInt(100))
        );
    }
}