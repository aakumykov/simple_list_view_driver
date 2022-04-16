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

    private SimpleListViewDriver<TextViewHolder> mSimpleListViewDriver;
    private final Random mRandom = new Random();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        ActivityMainBinding viewBinding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(viewBinding.getRoot());

        ViewHolderProcessor<TextViewHolder> viewHolderProcessor = new ViewHolderProcessor<TextViewHolder>() {
            @Override
            public TextViewHolder createViewHolder(View itemView) {
                return new TextViewHolder(itemView);
            }

            @Override
            public void fillViewHolder(TextViewHolder viewHolder, iTitleItem titleItem) {
                String title = titleItem.getTitle();
                viewHolder.titleView.setText(title);
                viewHolder.lengthView.setText(String.valueOf(title.length()));
            }
        };

        mSimpleListViewDriver = new SimpleListViewDriver<>(
                findViewById(R.id.listView),
                R.layout.list_item_main,
                viewHolderProcessor
        );

        mSimpleListViewDriver.setItemClickListener(titleItem -> {
            String message = getString(R.string.click_on, titleItem.getTitle());
            Toast.makeText(this, message, Toast.LENGTH_SHORT).show();
        });

        mSimpleListViewDriver.setItemLongClickListener(titleItem -> {
            String message = getString(R.string.long_click_on, titleItem.getTitle());
            Toast.makeText(MainActivity.this, message, Toast.LENGTH_SHORT).show();
            return true;
        });

        viewBinding.addButton.setOnClickListener(this::onAddButtonClicked);
        viewBinding.setButton.setOnClickListener(this::onSetButtonClicked);


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