package com.gitlab.aakumykov.simple_list_view_driver;

import android.os.Bundle;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

public class MainActivity extends AppCompatActivity {

    private int mCounter = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(com.gitlab.aakumykov.simple_list_view_driver.R.layout.activity_main);

        SimpleListViewDriver simpleListViewDriver = new SimpleListViewDriver(
                findViewById(com.gitlab.aakumykov.simple_list_view_driver.R.id.listView)
        );

        int b = mCounter++;
        simpleListViewDriver.addItem(() -> "Элемент "+b);

        simpleListViewDriver.setOnItemClickListener(stringSupplier -> {
            Toast.makeText(this, stringSupplier.get(), Toast.LENGTH_SHORT).show();
        });

        FloatingActionButton floatingActionButton =
                findViewById(R.id.floatingActionButton);

        floatingActionButton.setOnClickListener(v -> {
            int c = mCounter++;
            simpleListViewDriver.addItem(() -> "Элемент "+c);
        });
    }
}