package com.gitlab.aakumykov.simple_list_view_driver;

import android.os.Bundle;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.UUID;

public class MainActivity extends AppCompatActivity {

    private int mCounter = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        SimpleListViewDriver simpleListViewDriver = new SimpleListViewDriver(findViewById(R.id.listView));

        int b = mCounter++;
        simpleListViewDriver.addItem(new ListItem(
                UUID.randomUUID().toString(),
                getString(R.string.element, b)
        ));

        simpleListViewDriver.setItemClickListener(titleItem -> {
            String message = getString(R.string.click_on, titleItem.getTitle());
            Toast.makeText(this, message, Toast.LENGTH_SHORT).show();
        });

        simpleListViewDriver.setItemLongClickListener(titleItem -> {
            String message = getString(R.string.long_click_on, titleItem.getTitle());
            Toast.makeText(MainActivity.this, message, Toast.LENGTH_SHORT).show();
            return true;
        });

        FloatingActionButton floatingActionButton = findViewById(R.id.floatingActionButton);

        floatingActionButton.setOnClickListener(v -> {
            int c = mCounter++;
            simpleListViewDriver.addItem(new ListItem(
                    UUID.randomUUID().toString(),
                    getString(R.string.element, c)
            ));
        });
    }
}